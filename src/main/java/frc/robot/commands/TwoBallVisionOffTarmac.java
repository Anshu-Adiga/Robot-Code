package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** Autonomous mode:
 * 1. The robot shoots the pre-loaded ball.
 * 2. It drives to get the second ball.
 * 3. It then drives up and shoots it.
 */

public class TwoBallVisionOffTarmac extends SequentialCommandGroup{
    // Values will change due to vision.
    int GET_BALL_DISTANCE = 0;

    public OneBallAutoNoVisionOffTarmacCMD(
        Shooter2022 shooter, Drivetrain drive) {
      addCommands(
          // These will also change due to vision.
          new Shooter2022ShootCMD(shooter),
          new DriveDistanceCMD(drivetrain, gyro, GET_BALL_DISTANCE),
          new Indexer2022ForwardCMD(indexer),
          new DriveDistanceCMD(drivetrain, gyro, TARMAC_DISTANCE),
      )

    }
    
}
