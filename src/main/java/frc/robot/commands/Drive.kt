package frc.robot.commands

import frc.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj2.command.Command

class Drive : Command() {

    init { addRequirements(Drivetrain) }

    override fun execute() {
        Drivetrain.tank()
    }
    
    override fun isFinished(): Boolean {
        return false
    }
}