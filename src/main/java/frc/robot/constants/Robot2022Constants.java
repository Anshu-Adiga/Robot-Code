package frc.robot.constants;

import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;
import frc.robot.motors.FeedbackConstant;
import frc.robot.motors.GearRatio;
import frc.robot.motors.MotorType;
import frc.robot.motors.RamseteConstant;
import frc.robot.motors.SimpleFeedforwardConstant;
import frc.robot.utilities.IMUAxis;
import frc.robot.utilities.IMUType;

public class Robot2022Constants implements Constants {

    @Override
    public String name() {
        return "Robot 2022";
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
  public IdleMode driveIdleMode() {
    return IdleMode.kCoast;
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
    public RamseteConstant driveRamsete() {
        return new RamseteConstant();
    }

    @Override
    public SimpleFeedforwardConstant driveDriveFF() {
        return new SimpleFeedforwardConstant(0.16745, 2.8243, 0.49144);
    }

    @Override
    public FeedbackConstant driveDriveVelocityPID() {
        return new FeedbackConstant(3.1939/2, 0.0);
    }

    @Override
    public FeedbackConstant driveDrivePositionPID() {
        return new FeedbackConstant(96.073, 8.4843);
    }

    @Override
    public SimpleFeedforwardConstant driveTurnFF() {
        return new SimpleFeedforwardConstant(2.9078, 2.9078, 0.59513);
    }

    @Override
    public FeedbackConstant driveTurnVelocityPID() {
        return new FeedbackConstant(3.2276, 0);
    }

    @Override
    public FeedbackConstant driveTurnPositionPID() {
        return new FeedbackConstant(0.5, 0.01);
    }

    @Override
    public double driveWheelDiameter() {
        return Units.inchesToMeters(6);
    }

  @Override
  public GearRatio driveGearRatio() {
    return new GearRatio(10.71, 1);
  }

  @Override
    public DifferentialDriveKinematics driveKinematics() {
        return new DifferentialDriveKinematics(0.656);
    }

    @Override
    public double driveMaxVelocity() {
        return 3.0;
    }

    @Override
    public double driveMaxAcceleration() {
        return 3;
    }

    @Override
    public double driveAutoMaxVelocity() {
        return 2;
    }

    @Override
    public double driveAutoMaxAcceleration() {
        return 1.0;
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
        return true;
    }

    @Override
    public IMUType gyroIMUType() {
        return IMUType.ADIS16470;
    }

    @Override
    public IMUAxis gyroYawAxis() {
        return IMUAxis.kY;
    }

    @Override
    public boolean hasClimber2020() {
        return false;
    }

    @Override
    public int climber2020MotorId() {
        return 11;
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

    @Override
    public boolean hasIndexer2022() {
        return true;
    }

    @Override
    public int indexer2022MotorID() {
        return 9;
    }

    @Override
    public double indexer2022IdleSpeed() {
        return 0.2;
    }

    @Override
    public double indexer2022InSpeed() {
        return 0.8;
    }

    @Override
    public double indexer2022OutSpeed() {
        return 0.3;
    }

    @Override
    public boolean indexer2022MotorInverted() {
        return true;
    }

    @Override
    public boolean hasLlamaNeck2022() {
        return true;
    }

    @Override
    public int llamaNeck2022MotorID() {
        return 8;
    }

    @Override
    public boolean llamaNeck2022MotorInverted() {
        return true;
    }

    @Override
    public double llamaNeck2022IdleSpeed() {
        return 0.5;
    }

    @Override
    public double llamaNeck2022InSpeed() {
        return 0.5;
    }
    
    @Override
    public double llamaNeck2022OutSpeed() {
     return 0.5;   
    }

    @Override
    public int llamaNeck2022UpperLimitSwitchChannel() {
        return 0;
    }

    @Override
    public int llamaNeck2022LowerLimitSwitchChannel() {
        return 1;
    }
    
    @Override
    public boolean hasSpitter2022() {
        return true;
    }

    @Override 
    public int spitter2022MotorId() {
        return 5;
    }

    @Override
    public boolean spitter2022MotorInverted() {
        return true;
    }

    @Override
    public boolean spitter2022UseVelocity() {
        return true;
    }

    @Override
    public boolean spitter2022UsePID() {
        return false;
    }

    @Override
    public SimpleFeedforwardConstant spitter2022FF() {
        return new SimpleFeedforwardConstant(0.23723, 0.02025, 0.00063608);
    }

  @Override
  public double spitter2022MomentOfInertia() {
    return 0;
  }

  @Override
  public GearRatio spitter2022GearRatio() {
    return new GearRatio();
  }

  @Override
    public FeedbackConstant spitter2022FB() {
        return new FeedbackConstant(0.14261, 0);
    }

    @Override
    public double spitter2022MaxVelocity() {
        return 90 * 2 * Math.PI;
    }

    @Override
    public double spitter2022ForwardSpeed() {
        return 0.46;
    }

    @Override
    public double spitter2022BackwardSpeed() {
        return 0.1;
    }

  @Override
  public double spitter2022DistanceLinearM() {
    return 141.8090384;
  }

    @Override
    public double spitter2022DistanceLinearB() {
        return 108.5792974;
    }

    @Override
    public boolean hasHubCameraLED() {
        return true;
    }

    @Override
    public int hubCameraLEDChannel() {
        return 0;
    }

    // TODO: Pester cad into giving me the offset for the hub camera
  @Override
  public Translation2d hubCameraOffset() {
    return new Translation2d(1, 0);
  }
}
