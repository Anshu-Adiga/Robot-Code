package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.logging.RobotLogManager;
import frc.robot.subsystems.LlamaNeck2022;
import org.apache.logging.log4j.Logger;

public class LlamaNeck2022StopCMD extends CommandBase {
  private static final Logger LOGGER =
      RobotLogManager.getMainLogger(LlamaNeck2022StopCMD.class.getName());
  private final LlamaNeck2022 llamaNeck;

  /**
   * Creates a new LlamaNeck2022StopCMD.
   *
   * @param llamaNeck The subsystem used by this command.
   */
  public LlamaNeck2022StopCMD(LlamaNeck2022 llamaNeck) {
    this.llamaNeck = llamaNeck;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(llamaNeck);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    LOGGER.debug("Setting Llama Neck off");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    llamaNeck.stop();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
