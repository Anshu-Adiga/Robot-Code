package frc.robot.constants;

import frc.robot.motors.FeedbackConstant;
import frc.robot.motors.FeedforwardConstant;
import frc.robot.motors.MotorType;

public class KitBot2022Constants implements Constants {

    @Override
    public String name() {
        return "KitBot 2022";
    }

    @Override
    public boolean hasDrivetrain() {
        return true;
    }

    @Override
    public boolean driveDualMotors() {
        return true;
    }

    @Override
    public MotorType driveMotorType() {
        return MotorType.SPARK_MAX_BRUSHLESS;
    }

    @Override
    public boolean driveUseVelocity() {
        return true;
    }

    @Override
    public boolean driveUsePID() {
        return false;
    }

    @Override
    public FeedforwardConstant driveForwardRightFF() {
        return new FeedforwardConstant(0.15959, 0.26352, 0.044259);
    }

    @Override
    public FeedbackConstant driveForwardRightVelocityFB() {
        return new FeedbackConstant(0.12316, 0.0);
    }

    @Override
    public FeedbackConstant driveForwardRightPositionFB() {
        return new FeedbackConstant(35.842, 1.5815);
    }

    @Override
    public FeedforwardConstant driveForwardLeftFF() {
        return new FeedforwardConstant(0.12149, 0.1279, 0.01641);
    }

    @Override
    public FeedbackConstant driveForwardLeftVelocityFB() {
        return new FeedbackConstant(0.16326, 0.0);
    }

    @Override
    public FeedbackConstant driveForwardLeftPositionFB() {
        return new FeedbackConstant(26.488, 0.84657);
    }

    @Override
    public FeedforwardConstant driveBackwardRightFF() {
        return new FeedforwardConstant(0.14347, 0.13096, 0.010268);
    }

    @Override
    public FeedbackConstant driveBackwardRightVelocityFB() {
        return new FeedbackConstant(0.14205, 0.0);
    }

    @Override
    public FeedbackConstant driveBackwardRightPositionFB() {
        return new FeedbackConstant(22.371, 0.593);
    }

    @Override
    public FeedforwardConstant driveBackwardLeftFF() {
        return new FeedforwardConstant(0.15753, 0.12827, 0.0055311);
    }

    @Override
    public FeedbackConstant driveBackwardLeftVelocityFB() {
        return new FeedbackConstant(0.10653, 0.0);
    }

    @Override
    public FeedbackConstant driveBackwardLeftPositionFB() {
        return new FeedbackConstant(17.061, 0.35465);
    }

    @Override
    public double driveUnitsPerRotation() {
        // Already in inches
        return 1;
    }

    @Override
    public double driveMaxVelocity() {
        return 2.03;
    }

    @Override
    public double driveMaxAcceleration() {
        return 2.03;
    }

    @Override
    public double driveFastMaxSpeed() {
        return 1.0;
    }

    @Override
    public double driveNormalMaxSpeed() {
        return 0.8;
    }

    @Override
    public double driveSlowMaxSpeed() {
        return 0.5;
    }

    @Override
    public double driveNormalTurnMaxSpeed() {
        return 1.0;
    }

    @Override
    public double driveSlowTurnMaxSpeed() {
        return 0.8;
    }

    @Override
    public int driveMotorLeftLeaderId() {
        return 1;
    }

    @Override
    public boolean driveMotorLeftLeaderInverted() {
        return false;
    }

    @Override
    public int driveMotorLeftFollowerId() {
        return 2;
    }

    @Override
    public boolean driveMotorLeftFollowerInverted() {
        return false;
    }

    @Override
    public int driveMotorRightLeaderId() {
        return 3;
    }

    @Override
    public boolean driveMotorRightLeaderInverted() {
        return true;
    }

    @Override
    public int driveMotorRightFollowerId() {
        return 4;
    }

    @Override
    public boolean driveMotorRightFollowerInverted() {
        return true;
    }

    @Override
    public boolean hasClimber2020() {
        return false;
    }

    @Override
    public int climber2020MotorId() {
        return 0;
    }

    @Override
    public boolean climber2020MotorInverted() {
        return false;
    }

    @Override
    public double climber2020UpSpeed() {
        return 0.3;
    }

    @Override
    public double climber2020DownSpeed() {
        return 0.1;
    }

    @Override
    public boolean hasShooter2020() {
        return false;
    }

    @Override
    public MotorType shooter2020MotorType() {
        return MotorType.NONE;
    }

    @Override
    public boolean shooter2020FlywheelDualMotors() {
        return false;
    }

    @Override
    public int shooter2020FlywheelLeaderMotorId() {
        return 0;
    }

    @Override
    public boolean shooter202FlywheelLeaderInverted() {
        return false;
    }

    @Override
    public int shooter2020FlywheelFollowerMotorId() {
        return 0;
    }

    @Override
    public boolean shooter202FlywheelFollowerInverted() {
        return false;
    }

    @Override
    public int shooter2020TriggerMotorId() {
        return 0;
    }

    @Override
    public double shooter2020FlywheelDefaultSpeed() {
        return 0.0;
    }

    @Override
    public boolean shooter2020FlywheelUseVelocity() {
        return false;
    }

    @Override
    public double shooter2020FlywheelkP() {
        return 0.0;
    }

    @Override
    public double shooter2020FlywheelkI() {
        return 0.0;
    }

    @Override
    public double shooter2020FlywheelkD() {
        return 0.0;
    }

    @Override
    public double shooter2020FlywheelkS() {
        return 0.0;
    }

    @Override
    public double shooter2020FlywheelkV() {
        return 0.0;
    }

    @Override
    public double shooter2020FlywheelkA() {
        return 0.0;
    }

    @Override
    public double shooter2020FlywheelkMaxVelocity() {
        return 0;
    }

    @Override
    public boolean shooter2020TriggerInverted() {
        return false;
    }

    @Override
    public int shooter2020LeftServoId() {
        return 0;
    }

    @Override
    public double shooter2020LeftServoMax() {
        return 0;
    }

    @Override
    public double shooter2020LeftServoMin() {
        return 0;
    }

    @Override
    public int shooter2020RightServoId() {
        return 0;
    }

    @Override
    public double shooter2020RightServoMax() {
        return 0;
    }

    @Override
    public double shooter2020RightServoMin() {
        return 0;
    }
}
