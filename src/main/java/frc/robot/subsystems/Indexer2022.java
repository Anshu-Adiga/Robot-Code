package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants;
import frc.robot.logging.RobotLogManager;
import frc.robot.motors.MotorControllerEncoder;
import frc.robot.motors.MotorControllerFactory;
import frc.robot.motors.MotorType;
import org.apache.logging.log4j.Logger;

/** The indexer subsystem, contains the indexer wheel and its motor only. */
public class Indexer2022 extends SubsystemBase {
  private static final Logger LOGGER = RobotLogManager.getMainLogger(Indexer2022.class.getName());

  private final MotorControllerEncoder indexerMotor;

  private boolean isShooting;

  /** The indexer subsystem, contains the indexer wheel and its motor only. */
  public Indexer2022() {
    super();
    indexerMotor =
        MotorControllerFactory.create(
            RobotConstants.get().indexer2022MotorID(), MotorType.TALON_SRX);

    indexerMotor.setInverted(RobotConstants.get().indexer2022MotorInverted());

    isShooting = false;
  }

  /** Idles the indexer wheel. */
  public void idle() {
    LOGGER.debug(
        "Starting indexer slowly, setting speed to " + RobotConstants.get().indexer2022IdleSpeed());
    indexerMotor.set(RobotConstants.get().indexer2022IdleSpeed());
    isShooting = false;
  }

  /** Moves the indexer wheel forwards at a higher speed. */
  public void forward() {
    LOGGER.debug(
        "Starting indexer quickly, setting speed to " + RobotConstants.get().indexer2022InSpeed());
    indexerMotor.set(RobotConstants.get().indexer2022InSpeed());
    isShooting = true;
  }

  /** Moves the indexer wheel backwards. */
  public void backward() {
    LOGGER.debug(
        "Reversing indexer, setting speed to " + RobotConstants.get().indexer2022OutSpeed());
    indexerMotor.set(-RobotConstants.get().indexer2022OutSpeed());
    isShooting = false;
  }

  /** Stops the indexer wheel. */
  public void stop() {
    LOGGER.debug("Stopping indexer, setting speed to 0");
    indexerMotor.set(0);
    isShooting = false;
  }

  public boolean isShooting() {
    return isShooting;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);

    builder.addDoubleProperty("Indexer Position", indexerMotor::getPosition, null);
    builder.addDoubleProperty("Indexer Velocity", indexerMotor::getVelocity, null);
  }
}
