package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotConstants;
import frc.robot.subsystems.Climber2020;
import frc.robot.subsystems.Led2022;
import frc.robot.subsystems.LlamaNeck2022;
import frc.robot.subsystems.Spitter2022;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Led2022UpdateCMD extends CommandBase {
    private final double TIMER_SPEED = 0.35;

    private NetworkTableEntry hasBallEntry;
    private NetworkTableEntry ballAngleEntry;
    private NetworkTableEntry ballDistanceEntry;

    private NetworkTableEntry seesTargetEntry;
    private NetworkTableEntry targetAngleEntry;
    private NetworkTableEntry targetDistanceEntry;

    private Led2022 ledClimber;
    private LlamaNeck2022 llamaNeck = null;
    private Spitter2022 spitter = null;
    private Climber2020 climber = null;
    
    private Color teamColor = Color.kBlue;
    private int color = 0;
    private Timer timer = new Timer();


    public Led2022UpdateCMD(
        Led2022 ledClimber,
        Spitter2022 spitter, 
        LlamaNeck2022 llamaNeck, 
        Climber2020 climber) {
        this.ledClimber = ledClimber;
        this.spitter = spitter;
        this.llamaNeck = llamaNeck;

        timer.start();

        addRequirements(ledClimber);
    }

    /**
     * For Testing, don't want to depend on other game pieces.
     * 
     * @param ledClimber the led strips
     */
    public Led2022UpdateCMD(Led2022 ledClimber) {
        this.ledClimber = ledClimber;
    }

    @Override
    public void initialize() {

        NetworkTable visionTable = NetworkTableInstance.getDefault().getTable("Vision");
        seesTargetEntry = visionTable.getSubTable("HubTarget").getEntry("isValid");
        targetAngleEntry = visionTable.getSubTable("HubTarget").getEntry("angle");
        targetDistanceEntry = visionTable.getSubTable("HubTarget").getEntry("distance");

        ballAngleEntry = visionTable.getSubTable("BallTracking").getEntry("Angle");
        ballDistanceEntry = visionTable.getSubTable("BallTracking").getEntry("Distance");

        if (DriverStation.getAlliance() == Alliance.Red) {
            teamColor = Color.kRed;
            hasBallEntry = visionTable.getSubTable("BallTracking").getSubTable("Red").getEntry("hasBall");
        } else {
            teamColor = Color.kBlue;
            hasBallEntry = visionTable.getSubTable("BallTracking").getSubTable("Blue").getEntry("hasBall");
        }

        timer.reset();

    }

    @Override
    public void execute() { 
       
        boolean seesBall = hasBallEntry.getBoolean(false);
        double ballDistance = ballDistanceEntry.getDouble(0.0);
        double ballAngle = ballAngleEntry.getDouble(0.0);

        boolean seesTarget = seesTargetEntry.getBoolean(false);
        double targetDistance = targetDistanceEntry.getDouble(0.0);
        double targetAngle = targetAngleEntry.getDouble(0.0);

        if (climber != null && climber.isEnabled()) {
            setRainbowMovingUp();
        } else if (spitter != null && spitter.isAtShootingSpeed()) {
            //spitter.getCurrentCommand() instanceof Spitter2022ForwardCMD
            setPurpleMovingUp();
        } else if (llamaNeck != null && llamaNeck.hasLowerBall()) {
            if (seesTarget && targetDistance < 3.0 &&  Math.abs(targetAngle) < 4.0) {
                setTopGreen();
                setBottomGreen();
            } else {
                setTopPink();
                setBottomPink();
            }
        } else if (llamaNeck != null && llamaNeck.hasUpperBall()) {
            setBottomPink();
            if (seesBall && ballDistance < 3.0 && Math.abs(ballAngle) < 10.0) {
                if (teamColor == Color.kBlue) {
                    setTopBlue();
                } else {
                    setTopRed();
                }
            } else if (seesTarget && targetDistance < 3.0 &&  Math.abs(targetAngle) < 4.0) {
                setTopGreen();
            } else {
                setTopOff();
            }
        } else {
            if (seesBall && ballDistance < 3.0 && Math.abs(ballAngle) < 10.0) {
                if (teamColor == Color.kBlue) {
                    setTopBlue();
                    setBottomBlue();
                } else {
                    setTopRed();
                    setBottomRed();
                }
            } else {
                setBottomGold();
                setTopGold();
            }
        }

        ledClimber.sendData();
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public void setTopPink() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledClimber.setRGB(i,255,192,203);
        }
    }

    public void setBottomPink() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledClimber.setRGB(i,255,192,203);
        }
    }

    public void setTopRed() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledClimber.setRGB(i,255,0,0);
        }
    }

    public void setBottomRed() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledClimber.setRGB(i,255,0,0);
        }
    }

    public void setTopBlue() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledClimber.setRGB(i,0,0,204);
        }
    }

    public void setBottomBlue() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledClimber.setRGB(i,0,0,204);
        }
    }

    public void setTopGreen() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledClimber.setRGB(i,0,128,0);
        }
    }

    public void setBottomGreen() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledClimber.setRGB(i,0,128,0);
        }
    }

    public void setTopGold() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledClimber.setRGB(i,255,215,0);
        }
    }

    public void setBottomGold() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledClimber.setRGB(i,255,215,0);
        }
    }

    public void setTopOff() {
        for (int i = RobotConstants.get().led2022LedCount()/2 +1; i < RobotConstants.get().led2022LedCount(); i++) {
            ledClimber.setRGB(i,0,0,0);
        }
    }

    public void setBottomOff() {
        for (int i = 0; i < RobotConstants.get().led2022LedCount()/2; i++) {
            ledClimber.setRGB(i,0,0,0);
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

                ledClimber.setRGB(i, 1 * brightness,0 * brightness, 1 * brightness);
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
            ledClimber.setHSB(i, (color + (i * 360/RobotConstants.get().led2022LedCount())) % 360, 255, 127);
        }

        ledClimber.sendData();
    }
}
