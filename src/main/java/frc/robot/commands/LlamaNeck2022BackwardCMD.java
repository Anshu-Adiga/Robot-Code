package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.logging.RobotLogManager;
import frc.robot.subsystems.LlamaNeck2022;
import org.apache.logging.log4j.Logger;

public class LlamaNeck2022BackwardCMD extends CommandBase {
  private static final Logger LOGGER =
      RobotLogManager.getMainLogger(LlamaNeck2022BackwardCMD.class.getName());
  private final LlamaNeck2022 llamaNeck;

  public LlamaNeck2022BackwardCMD(LlamaNeck2022 llamaNeck) {
    this.llamaNeck = llamaNeck;
    addRequirements(llamaNeck);
  }

  @Override
  public void initialize() {
    LOGGER.debug("Setting Llama Neck backward");
  }

  @Override
  public void execute() {
    llamaNeck.backward();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
