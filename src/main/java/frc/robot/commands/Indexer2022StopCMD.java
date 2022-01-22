package frc.robot.commands;

import frc.robot.logging.RobotLogManager;
import frc.robot.subsystems.Indexer2022;

import org.apache.logging.log4j.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Indexer2022StopCMD extends CommandBase {
  private final Indexer2022 indexer;

  private static final Logger LOGGER = RobotLogManager.getMainLogger(Intake2022OutCMD.class.getName());


  /**
   * Creates a new Indexer2022StopCMD.
   *
   * @param indexer The subsystem used by this command.
   */
  public Indexer2022StopCMD(Indexer2022 indexer) {
    this.indexer = indexer;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    LOGGER.info("Setting Indexer stop");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    indexer.indexerStop();
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