package frc.robot.constants;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import frc.robot.motors.FeedbackConstant;
import frc.robot.motors.SimpleFeedforwardConstant;
import frc.robot.motors.MotorType;
import frc.robot.utilities.IMUAxis;
import frc.robot.utilities.IMUType;

public class Robot2019Constants implements Constants {

    @Override
    public String name() {
        return "Robot 2019";
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
        return MotorType.TALON_SRX;
    }

    @Override
    public boolean driveUseVelocity() {
        return false;
    }

    @Override
    public boolean driveUsePID() {
        return false;
    }

    @Override
    public SimpleFeedforwardConstant driveDriveFF() {
        return new SimpleFeedforwardConstant(0.0, 0.0, 0.0);
    }

    @Override
    public FeedbackConstant driveDriveVelocityPID() {
        return new FeedbackConstant(0.0, 0.0);
    }

    @Override
    public FeedbackConstant driveDrivePositionPID() {
        return new FeedbackConstant(0.0, 0.0);
    }

    @Override
    public SimpleFeedforwardConstant driveTurnFF() {
        return null;
    }

    @Override
    public FeedbackConstant driveTurnVelocityPID() {
        return null;
    }

    @Override
    public FeedbackConstant driveTurnPositionPID() {
        return null;
    }

    @Override
    public double driveUnitsPerRotation() {
        return 1.0;
    }

    @Override
    public DifferentialDriveKinematics driveKinematics() {
        return new DifferentialDriveKinematics(0);
    }

    @Override
    public double driveMaxVelocity() {
        return 0;
    }

    @Override
    public double driveMaxAcceleration() {
        return 100000;
    }

    @Override
    public double driveAutoMaxVelocity() {
        return 0;
    }

    @Override
    public double driveAutoMaxAcceleration() {
        return 0;
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
    public boolean hasGyro() {
        return false;
    }

    @Override
    public IMUType gyroIMUType() {
        return IMUType.NONE;
    }

    @Override
    public IMUAxis gyroYawAxis() {
        return IMUAxis.NA;
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
        return true;
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

    @Override
    public boolean hasIndexer2022() {
        return false;
    }

    @Override
    public int indexer2022MotorID() {
        return 0;
    }

    @Override
    public double indexer2022IdleSpeed() {
        return 0;
    }

    @Override
    public double indexer2022InSpeed() {
        return 0;
    }

    @Override
    public double indexer2022OutSpeed() {
        return 0;
    }

    @Override
    public boolean indexer2022MotorInverted() {
        return false;
    }

    @Override
    public boolean hasLlamaNeck2022() {
        return false;
    }

    @Override
    public int llamaNeck2022MotorID() {
        return 0;
    }

    @Override
    public boolean llamaNeck2022MotorInverted() {
        return false;
    }

    @Override
    public double llamaNeck2022IdleSpeed() {
        return 0;
    }

    @Override
    public double llamaNeck2022InSpeed() {
        return 0;
    }
    
    @Override
    public double llamaNeck2022OutSpeed() {
     return 0;   
    }

    @Override
    public int llamaNeck2022UpperLimitSwitchChannel() {
        return 0;
    }

    @Override
    public int llamaNeck2022LowerLimitSwitchChannel() {
        return 0;
    }

    @Override
    public boolean hasSpitter2022() {
        return false;
    }

    @Override 
    public int spitter2022MotorId() {
        return 0;
    }

    @Override
    public boolean spitter2022MotorInverted() {
        return false;
    }

  @Override
  public boolean spitter2022UseVelocity() {
    return false;
  }

    @Override
    public boolean spitter2022UsePID() {
        return false;
    }

    @Override
    public SimpleFeedforwardConstant spitter2022FF() {
        return new SimpleFeedforwardConstant(0, 0, 0);
    }

    @Override
    public FeedbackConstant spitter2022FB() {
        return new FeedbackConstant(0, 0);
    }

    @Override
    public double spitter2022MaxVelocity() {
        return 0;
    }

    @Override
    public double spitter2022ForwardSpeed() {
        return 0;
    }

    @Override
    public double spitter2022BackwardSpeed() {
        return 0;
    }

    @Override
    public boolean hasHubCameraLED() {
        return false;
    }

    @Override
    public int hubCameraLEDChannel() {
        return 0;
    }

}
