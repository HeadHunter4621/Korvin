package frc.robot.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.ConfigConstants
import frc.robot.Input
import frc.robot.subsystems.Drivetrain

class Drive: Command() {

    init { addRequirements(Drivetrain) }

    override fun initialize() {}

    override fun execute() { Drivetrain.setSpeeds((Input.getLeftJoystickY() * ConfigConstants.DRIVE_SPEED_MULTIPLY), (Input.getRightJoystickY() * ConfigConstants.DRIVE_SPEED_MULTIPLY)) }
    
    override fun isFinished(): Boolean { return false }

    override fun end(interrupted: Boolean) {Drivetrain.stop()}
}
