package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants;
import frc.robot.logging.RobotLogManager;
import frc.robot.motors.MotorControllerEncoder;
import frc.robot.motors.MotorControllerFactory;
import frc.robot.motors.MotorType;

import org.apache.logging.log4j.Logger;

public class LlamaNeck2022 extends SubsystemBase {
    private static final Logger LOGGER = RobotLogManager.getMainLogger(LlamaNeck2022.class.getName());

    private MotorControllerEncoder llamaNeck;

    private boolean isReversed = false;

    public LlamaNeck2022() {
        super();
        llamaNeck = MotorControllerFactory.create(RobotConstants.get().llamaNeck2022MotorID(), MotorType.TALON_SRX);
    }

    public void setIsReversed(boolean isReversed) {
        this.isReversed = isReversed;
    }

    public void llamaNeckForward() {
        LOGGER.info("Starting llamaNeck");
        llamaNeck.set(RobotConstants.get().llamaNeck2022InSpeed());
    }

    public void llamaNeckBackward() {
        LOGGER.info("Reversing index");
        llamaNeck.set(-RobotConstants.get().llamaNeck2022OutSpeed());
    }

    public void llamaNeckStop() {
        LOGGER.info("Stopping llamaNeck");
        llamaNeck.set(0);
    }

}
