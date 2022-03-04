package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotConstants;
import frc.robot.subsystems.Climber2022;
import frc.robot.subsystems.Led2022;
import frc.robot.subsystems.LlamaNeck2022;
import frc.robot.subsystems.Spitter2022;

import frc.robot.vision.BallTracking;
import frc.robot.vision.HubTarget;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Led2022UpdateCMD extends CommandBase {
    private final double TIMER_SPEED = 0.35;

    private Led2022 ledStrip;
    private LlamaNeck2022 llamaNeck = null;
    private Spitter2022 spitter = null;
    private Climber2022 climber = null;
    
    private Color teamColor = Color.kBlue;
    private int color = 0;
    private Timer timer = new Timer();


    public Led2022UpdateCMD(
        Led2022 ledStrip,
        Spitter2022 spitter, 
        LlamaNeck2022 llamaNeck, 
        Climber2022 climber) {
        this.ledStrip = ledStrip;
        this.spitter = spitter;
        this.llamaNeck = llamaNeck;

        timer.start();

        addRequirements(ledStrip);
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

    }

    @Override
    public void initialize() {

        // ShuffleboardTab tab = Shuffleboard.getTab("Operator");
        // SuppliedValueWidget<Boolean> rightUpper = tab.addBoolean("Right Status",() -> { return true; })
        //     .withWidget(BuiltInWidgets.kBooleanBox);
        // rightUpper.withProperties(Map.of("colorWhenTrue", Color.kGold));


        timer.reset();

    }

    @Override
    public void execute() { 
       
        boolean seesBall = BallTracking.hasBall();
        double ballDistance = BallTracking.getDistance();
        double ballAngle = BallTracking.getAngle();

        boolean seesTarget = HubTarget.hasTarget();
        double targetDistance = HubTarget.getDistance();
        double targetAngle = HubTarget.getAngle();

        if (climber != null && climber.isEnabled()) {
            setRainbowMovingUp();
//        } else if (spitter != null && spitter.isAtShootingSpeed()) {
//            //spitter.getCurrentCommand() instanceof Spitter2022ForwardCMD
//            setPurpleMovingUp();
        } else if (llamaNeck != null && llamaNeck.hasLowerBall()) {
            if (seesTarget && targetDistance < 3.0 &&  Math.abs(targetAngle) < 4.0) {
                setTopGold();
                setBottomGold();
            } else {
                set(Color.kWhite);
            }
        } else if (llamaNeck != null && llamaNeck.hasUpperBall()) {
            setBottom(Color.kPink);
            if (seesBall && ballDistance < 3.0 && Math.abs(ballAngle) < 10.0) {
                setTop(teamColor);
            } else if (seesTarget && targetDistance < 3.0 &&  Math.abs(targetAngle) < 4.0) {
                setTopGold();
            } else {
                setTop(Color.kBlack);
            }
        } else {
            if (seesBall && ballDistance < 3.0 && Math.abs(ballAngle) < 10.0) {
                set(teamColor);
            } else {
                setTop(Color.kWhite);
                setBottom(Color.kBlue);
            }
        }

        ledStrip.sendData();
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

    public void setTopWhite() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setRGB(i,255,255,255);
        }
    }

    public void setBottomWhite() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledStrip.setRGB(i,255,255,255);
        }
    }

    // public void setTopPink() {
    //     for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
    //         ledStrip.setRGB(i,255,192,203);
    //     }
    // }

    // public void setBottomPink() {
    //     for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
    //         ledStrip.setRGB(i,255,192,203);
    //     }
    // }

    public void setTopRed() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setRGB(i,255,0,0);
        }
    }

    public void setBottomRed() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledStrip.setRGB(i,255,0,0);
        }
    }

    public void setTopBlue() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setRGB(i,0,0,204);
        }
    }

    public void setBottomBlue() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledStrip.setRGB(i,0,0,204);
        }
    }

    public void setTopGreen() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setRGB(i,0,128,0);
        }
    }

    public void setBottomGreen() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledStrip.setRGB(i,0,128,0);
        }
    }

    public void setTopGold() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setRGB(i,255,194,10);
        }
    }

    public void setBottomGold() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledStrip.setRGB(i,255,194,10);
        }
    }

    public void setTopOff() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledStrip.setRGB(i,0,0,0);
        }
    }

    public void setBottomOff() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledStrip.setRGB(i,0,0,0);
        }
    }

}
