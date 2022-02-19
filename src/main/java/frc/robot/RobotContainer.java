// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDriveCMD;
import frc.robot.commands.Indexer2022BackwardCMD;
import frc.robot.commands.Indexer2022ForwardCMD;
import frc.robot.commands.Indexer2022IdleCMD;
import frc.robot.commands.Indexer2022StopCMD;
import frc.robot.commands.ClimberDisable2022CMD;
import frc.robot.commands.ClimberEnable2022CMD;
import frc.robot.commands.ClimberStop2022CMD;
import frc.robot.commands.ClimberUp2022CMD;
import frc.robot.commands.ClimberDown2022CMD;
import frc.robot.commands.LlamaNeck2022BackwardCMD;
import frc.robot.commands.LlamaNeck2022ForwardCMD;
import frc.robot.commands.ClimberDownCMD;
import frc.robot.commands.ClimberEnableCMD;
import frc.robot.commands.ClimberStopCMD;
import frc.robot.commands.ClimberUpCMD;
import frc.robot.commands.Indexer2022StopCMD;
import frc.robot.commands.LlamaNeck2022StopCMD;
import frc.robot.commands.Shooter2022FlushBallCMD;
import frc.robot.commands.Shooter2022IdleCMD;
import frc.robot.commands.Shooter2022SetDefaultCMD;
import frc.robot.commands.Shooter2022ShootCMD;
import frc.robot.commands.Shooter2022StopCMD;
import frc.robot.commands.ShooterRunFlywheelCMD;
import frc.robot.commands.ShooterStopFlywheelCMD;
import frc.robot.commands.ShooterTriggerForwardCMD;
import frc.robot.commands.ShooterTriggerStopCMD;
import frc.robot.commands.Spitter2022StopCMD;
import frc.robot.controllers.CustomController2020;
import frc.robot.controllers.XboxController467;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer2022;
import frc.robot.subsystems.LlamaNeck2022;
import frc.robot.subsystems.Shooter2020;
import frc.robot.subsystems.Shooter2022;
import frc.robot.subsystems.Climber2022;
import frc.robot.subsystems.Spitter2022;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private Drivetrain drivetrain = null;
  private Shooter2020 shooter = null;
  private LlamaNeck2022 llamaNeck = null;
  private Indexer2022 indexer = null;
  private Spitter2022 spitter = null;
  private Shooter2022 shooter2022 = null;
  private Climber2022 climber = null;

  // User interface objects
  // Xbox controller for driver
  private final XboxController467 driverJoystick = new XboxController467(0);
  private final JoystickButton driverButtonA = new JoystickButton(driverJoystick, XboxController467.Buttons.A.value);
  private final JoystickButton driverButtonB = new JoystickButton(driverJoystick, XboxController467.Buttons.B.value);
  private final JoystickButton driverButtonX = new JoystickButton(driverJoystick, XboxController467.Buttons.X.value);
  private final JoystickButton driverButtonY = new JoystickButton(driverJoystick, XboxController467.Buttons.Y.value);
  private final JoystickButton driverButtonBack = new JoystickButton(driverJoystick, XboxController467.Buttons.Back.value);
  private final JoystickButton driverButtonStart = new JoystickButton(driverJoystick, XboxController467.Buttons.Start.value);
  private final JoystickButton driverPovUp = new JoystickButton(driverJoystick, XboxController467.Buttons.POVup.value);
  private final JoystickButton driverPovDown = new JoystickButton(driverJoystick, XboxController467.Buttons.POVdown.value);
  private final JoystickButton driverPovLeft = new JoystickButton(driverJoystick, XboxController467.Buttons.POVleft.value);
  private final JoystickButton driverPovRight = new JoystickButton(driverJoystick, XboxController467.Buttons.POVright.value);
  private final JoystickButton driverLeftBumper = new JoystickButton(driverJoystick, XboxController467.Buttons.BumperLeft.value);
  private final JoystickButton driverRightBumper = new JoystickButton(driverJoystick, XboxController467.Buttons.BumperRight.value);

  // Custom controller for operator
  private final GenericHID operatorJoystick = new Joystick(1);
  private final JoystickButton operatorInakeArm = new JoystickButton(operatorJoystick, CustomController2020.Buttons.INTAKE_ARM.value);
  private final JoystickButton operatorIntakeRollerForward = new JoystickButton(operatorJoystick, CustomController2020.Buttons.INTAKE_ROLLER_FORWARD.value);
  private final JoystickButton operatorIntakeRollerBackward = new JoystickButton(operatorJoystick, CustomController2020.Buttons.INTAKE_ROLLER_BACKWARD.value);
  private final JoystickButton operatorIndexAuto = new JoystickButton(operatorJoystick, CustomController2020.Buttons.INDEX_AUTO.value);
  private final JoystickButton operatorIndexRollerForward = new JoystickButton(operatorJoystick, CustomController2020.Buttons.INDEX_ROLLER_FORWARD.value);
  private final JoystickButton operatorIndexRollerBackward = new JoystickButton(operatorJoystick, CustomController2020.Buttons.INDEX_ROLLER_BACKWARD.value);
  private final JoystickButton operatorShooterAuto = new JoystickButton(operatorJoystick, CustomController2020.Buttons.SHOOTER_AUTO.value);
  private final JoystickButton operatorShooterFlywheel = new JoystickButton(operatorJoystick, CustomController2020.Buttons.SHOOTER_FLYWHEEL.value);
  private final JoystickButton operatorShooterShoot = new JoystickButton(operatorJoystick, CustomController2020.Buttons.SHOOTER_SHOOT.value);
  private final JoystickButton operatorClimberLock = new JoystickButton(operatorJoystick, CustomController2020.Buttons.CLIMBER_LOCK_SWITCH.value);
  private final JoystickButton operatorClimberUp = new JoystickButton(operatorJoystick, CustomController2020.Buttons.CLIMBER_UP_BUTTON.value);
  private final JoystickButton operatorClimberDown = new JoystickButton(operatorJoystick, CustomController2020.Buttons.CLIMBER_DOWN_BUTTON.value);

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button to command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void configureButtonBindings() {
    initDrivetrain();
    initShooter2020();
    initIndexer2022();
    initClimber2022();
    initSpitter2022();
    initLlamaNeck2022();
    initShooter2022();
  }

  private void initDrivetrain() {
    if (RobotConstants.get().hasDrivetrain()) {
      drivetrain = new Drivetrain();
      drivetrain.setDefaultCommand(new ArcadeDriveCMD(drivetrain,
        () -> driverJoystick.getAdjustedDriveSpeed(),
        () -> driverJoystick.getAdjustedTurnSpeed()
      ));

    }
  }

  private void initShooter2020() {
    if (RobotConstants.get().hasShooter2020()) {
      shooter = new Shooter2020();
      operatorShooterFlywheel.whenPressed(new ShooterRunFlywheelCMD(shooter));
      operatorShooterFlywheel.whenReleased(new ShooterStopFlywheelCMD(shooter));
      operatorShooterShoot.whenPressed(new ShooterTriggerForwardCMD(shooter));
      operatorShooterShoot.whenReleased(new ShooterTriggerStopCMD(shooter));

      // This is test code used on the robot to spin the flywheel to a certian speed depedning on the joystick
      // shooter.setDefaultCommand(new ShooterSetCMD(shooter,
      //   () -> -driverJoystick.getRawAxis(XboxController467.Axes.LeftY.value)
      // ));
    }
  }

  private void initLlamaNeck2022() {
    if (RobotConstants.get().hasLlamaNeck2022()) {
      llamaNeck = new LlamaNeck2022();
      llamaNeck.setDefaultCommand(new LlamaNeck2022StopCMD(llamaNeck));
    }
  }

  private void initIndexer2022() {
    if (RobotConstants.get().hasIndexer2022()) {
      indexer = new Indexer2022();
      indexer.setDefaultCommand(new Indexer2022StopCMD(indexer));
    }
  }

  private void initClimber2022() {
    if (RobotConstants.get().hasClimber2022()) {
      climber = new Climber2022();
      climber.setDefaultCommand(new ClimberStop2022CMD(climber));
      operatorClimberLock.whenPressed(new ClimberEnable2022CMD(climber));
      operatorClimberLock.whenReleased(new ClimberDisable2022CMD(climber));
      operatorClimberUp.whileHeld(new ClimberUp2022CMD(climber));
      operatorClimberDown.whileHeld(new ClimberDown2022CMD(climber));
    }
  }

  private void initSpitter2022() {
    if (RobotConstants.get().hasSpitter2022()) {
      spitter = new Spitter2022();
      spitter.setDefaultCommand(new Spitter2022StopCMD(spitter));
    }
  }

  private void initShooter2022() {
    if (RobotConstants.get().hasLlamaNeck2022()
        && RobotConstants.get().hasIndexer2022()
        && RobotConstants.get().hasSpitter2022()) {
      shooter2022 = new Shooter2022(indexer, llamaNeck, spitter);
      if (operatorShooterFlywheel.get()) {
        shooter2022.setDefaultCommand(
            new Shooter2022IdleCMD(shooter2022));
      } else {
        shooter2022.setDefaultCommand(
            new Shooter2022StopCMD(shooter2022));
      }

      operatorShooterFlywheel
          .whenPressed(
              new Shooter2022SetDefaultCMD(
                  shooter2022, new Shooter2022IdleCMD(shooter2022)))
          .whenReleased(
              new Shooter2022SetDefaultCMD(
                  shooter2022, new Shooter2022StopCMD(shooter2022)));
      operatorShooterShoot.whenPressed(
          new Shooter2022ShootCMD(shooter2022));
      operatorIntakeRollerBackward.whenHeld(
          new Shooter2022FlushBallCMD(shooter2022));
    }
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
