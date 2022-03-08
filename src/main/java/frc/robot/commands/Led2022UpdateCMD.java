package frc.robot.commands;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotConstants;
import frc.robot.subsystems.Climber2022;
import frc.robot.subsystems.Led2022;
import frc.robot.subsystems.LlamaNeck2022;
import frc.robot.subsystems.Spitter2022;

import frc.robot.vision.BallTracking;
import frc.robot.vision.HubTarget;

public class Led2022UpdateCMD extends CommandBase {
    private final double TIMER_SPEED = 0.35;

    private Led2022 ledStrip;
    private LlamaNeck2022 llamaNeck = null;
    private Spitter2022 spitter = null;
    private Climber2022 climber = null;
    
    int color = 0;
    private Timer timer = new Timer();

    public static final double TARGET_MAX_RANGE = 3.0;
    public static final double TARGET_MAX_ANGLE = 4.0;
    public static final double BALL_MAX_RANGE = 3.0;
    public static final double BALL_MAX_ANGLE = 4.0;

    private COLORS_467 idleColorTop = COLORS_467.Blue;
    private COLORS_467 idleColorBottom = COLORS_467.Gold;
    private COLORS_467 hasBallColor = COLORS_467.White;
    private COLORS_467 seeTargetColor = COLORS_467.Gold;
    private COLORS_467 seeBallColor = COLORS_467.Blue;

    private NetworkTableEntry[] targetIndicators;
    private NetworkTableEntry[] seeBallIndicators;
    private NetworkTableEntry[] hasBallIndicators;

    /*
     * Color blind preferred pallet includes White, Black, Red, Blue, Gold
     */
    public enum COLORS_467 {
        White(0xFF, 0xFF, 0xFF, 0xFFFFFF00),
        Red(0xFF, 0x00, 0x00, 0xFF000000),
        Green(0x00, 0x80, 0x00, 0x00800000),
        Blue(0x00, 0x00, 0xCC, 0x0000CC00),
        Gold(0xFF, 0xC2, 0x0A, 0xFFC20A00),
        Pink(0xDC, 0x26, 0x7F, 0xDC267f00),
        Black(0x00, 0x00, 0x00, 0x00000000);

        public final int red;
        public final int green;
        public final int blue;
        public final int shuffleboard;

        COLORS_467(int red, int green, int blue, int shuffleboard) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.shuffleboard = shuffleboard;
        }

    }

    public Led2022UpdateCMD(
        Led2022 ledStrip,
        Spitter2022 spitter, 
        LlamaNeck2022 llamaNeck, 
        Climber2022 climber) {

        this(ledStrip);
        
        this.spitter = spitter;
        this.llamaNeck = llamaNeck;
        this.climber = climber;

        addRequirements(spitter);
        addRequirements(llamaNeck);
        addRequirements(climber);
    }

    /**
     * For Testing, don't want to depend on other game pieces.
     * 
     * @param ledStrip the led strips
     */
    public Led2022UpdateCMD(Led2022 ledStrip) {
        this.ledStrip = ledStrip;
        addRequirements(ledStrip);

        if (DriverStation.getAlliance() == Alliance.Red) {
            seeBallColor = COLORS_467.Red;
        } else {
            seeBallColor = COLORS_467.Blue;
        }

        ShuffleboardTab tab = Shuffleboard.getTab("Operator");
        Shuffleboard.selectTab("Operator");
        ShuffleboardLayout layout = tab.getLayout("Indicators", BuiltInLayouts.kGrid)
            .withPosition(0, 0) 
            .withSize(5, 2)
            .withProperties(Map.of(
                "Number of columns", 5, 
                "Number of rows", 2, 
                "Label position", "HIDDEN"));

        targetIndicators = new NetworkTableEntry[4];
        seeBallIndicators = new NetworkTableEntry[4];
        hasBallIndicators = new NetworkTableEntry[2];

        for (int i = 0; i < 4; i++) {
            targetIndicators[i] = layout.add("Target Indicator " + i, false)
                .withWidget(BuiltInWidgets.kBooleanBox)
                .withPosition(i, 0)
                .withSize(1,1)
                .withProperties(Map.of(
                    "Color when false", COLORS_467.Black.shuffleboard,
                    "Color when true", seeTargetColor.shuffleboard))
                .getEntry();
            targetIndicators[i].setBoolean(false);
        }
        
        for (int i = 0; i < 4; i++) {
            seeBallIndicators[i] = layout.add("See Ball Indicator " + i, false)
            .withWidget(BuiltInWidgets.kBooleanBox)
            .withPosition(i, 1)
            .withSize(1,1)
            .withProperties(Map.of(
                "Color when false", COLORS_467.Black.shuffleboard,
                "Color when true", seeBallColor.shuffleboard))
            .getEntry();
            seeBallIndicators[i].setBoolean(false);
        }

        for (int i = 0; i < 2; i++) {
            hasBallIndicators[i] = layout.add("Has Ball Indicator " + i, false)
            .withWidget(BuiltInWidgets.kBooleanBox)
            .withPosition(4, i)
            .withSize(1,1)
            .withProperties(Map.of(
                "Color when false", COLORS_467.Black.shuffleboard,
                "Color when true", hasBallColor.shuffleboard))
            .getEntry();
            seeBallIndicators[i].setBoolean(false);
        }

        timer.start();
    }

    private void indicators(
        NetworkTableEntry[] indicators, boolean isValid, 
        double distance, double range,
        double angle, double maxAngle) {
        if (isValid && distance < range) {
            if  (Math.abs(angle) < maxAngle) {
                indicators[0].setBoolean(false);
                indicators[1].setBoolean(true);
                indicators[2].setBoolean(true);
                indicators[3].setBoolean(false);
            } else if (angle < 0) {
                indicators[0].setBoolean(true);
                indicators[1].setBoolean(true);
                indicators[2].setBoolean(false);
                indicators[3].setBoolean(false);    
            } else {
                indicators[0].setBoolean(false);
                indicators[1].setBoolean(false);
                indicators[2].setBoolean(true);
                indicators[3].setBoolean(true);
            }
        } else {
            indicators[0].setBoolean(false);
            indicators[1].setBoolean(false);
            indicators[2].setBoolean(false);
            indicators[3].setBoolean(false);
        }
    }

    private void cargoIndicator(
        NetworkTableEntry[] indicators, 
        int capacity, int amount) {
        for (int i = 0; i < amount; i++) {
            indicators[capacity-1-i].setBoolean(true);
        }
        for (int i = amount; i < capacity; i++) {
            indicators[capacity-1-i].setBoolean(true);
        }
    }

    @Override
    public void initialize() {
        timer.reset();
    }

    @Override
    public void execute() { 

        if (DriverStation.isAutonomous() || DriverStation.isTeleop()) {
            idleColorTop = COLORS_467.Black;
            idleColorBottom = COLORS_467.Black;
        } else {
            idleColorTop = COLORS_467.Blue;
            idleColorBottom = COLORS_467.Gold;
        }

        boolean seesTarget = HubTarget.hasTarget();
        double targetDistance = HubTarget.getDistance();
        double targetAngle = HubTarget.getAngle();
        indicators(targetIndicators, seesTarget, 
            targetDistance, TARGET_MAX_RANGE,
            targetAngle, TARGET_MAX_ANGLE);

        boolean seesBall = BallTracking.hasBall();
        double ballDistance = BallTracking.getDistance();
        double ballAngle = BallTracking.getAngle();
        indicators(seeBallIndicators, seesBall, 
            ballDistance, BALL_MAX_RANGE,
            ballAngle, BALL_MAX_ANGLE);
        
        if (climber != null && climber.isEnabled()) {
            setRainbowMovingUp();
//        } else if (spitter != null && spitter.isAtShootingSpeed()) {
//            //spitter.getCurrentCommand() instanceof Spitter2022ForwardCMD
//            setPurpleMovingUp();
        } else if (llamaNeck != null && llamaNeck.hasLowerBall()) {
            cargoIndicator(hasBallIndicators, 2, 2);
            if (seesTarget && targetDistance < TARGET_MAX_RANGE &&  Math.abs(targetAngle) < TARGET_MAX_ANGLE) {
                setTop(seeTargetColor);
                setBottom(seeTargetColor);
            } else {
                set(hasBallColor);
            }
        } else if (llamaNeck != null && llamaNeck.hasUpperBall()) {
            cargoIndicator(hasBallIndicators, 2, 1);
            setBottom(hasBallColor);
            if (seesBall && ballDistance < BALL_MAX_RANGE && Math.abs(ballAngle) < BALL_MAX_ANGLE) {
                set(seeBallColor);
            } else if (seesTarget && targetDistance < TARGET_MAX_RANGE &&  Math.abs(targetAngle) < TARGET_MAX_ANGLE) {
                setTop(seeTargetColor);
            } else {
                setTop(COLORS_467.Black); // Off
            }
        } else {
            cargoIndicator(hasBallIndicators, 2, 0);
            if (seesBall && ballDistance < BALL_MAX_RANGE && Math.abs(ballAngle) < BALL_MAX_ANGLE) {
                set(seeBallColor);
            } else {
                setTop(idleColorTop);
                setBottom(idleColorBottom);
            }
        }

    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public void set(Color color) {
        setTop(color);
        setBottom(color);
    }

    public void setTop(Color color) {
        for (int i = RobotConstants.get().led2022LedCount()/2; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setLED(i, color);
        }
    }

    public void setBottom(Color color) {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledStrip.setLED(i, color);
        }
    }

    public void set(COLORS_467 color) {
        setTop(color);
        setBottom(color);
    }

    public void setTop(COLORS_467 color) {
        for (int i = RobotConstants.get().led2022LedCount()/2; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setRGB(i, color.red, color.green, color.blue);
        }
    }

    public void setBottom(COLORS_467 color) {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledStrip.setRGB(i, color.red, color.green, color.blue);
        }
    }

    public void setPurpleMovingUp() {
        if (timer.hasElapsed(TIMER_SPEED * (RobotConstants.get().led2022LedCount() + 1))) {
            timer.reset();
        }

        for (int i = 0; i < RobotConstants.get().led2022LedCount(); i++) {
            if (timer.hasElapsed(TIMER_SPEED * i)) {
                double timeUntilOff = Math.max(0, (TIMER_SPEED * (i + 1)) - timer.get());
                int brightness = (int) (255 * timeUntilOff);

                ledStrip.setRGB(i, 1 * brightness,0 * brightness, 1 * brightness);
             }
        }
    }

    public void setRainbowMovingUp() {
        if (timer.hasElapsed(TIMER_SPEED)) {
            color += 1;

            if (color > 360) color = 0;
            timer.reset();
        }
        
        for (int i = 0; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setHSB(i, (color + (i * 360/RobotConstants.get().led2022LedCount())) % 360, 255, 127);
        }
    }

}
