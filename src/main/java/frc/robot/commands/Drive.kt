package frc.robot.commands

import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain
import edu.wpi.first.wpilibj2.command.Command

class Drive : Command() {
    
    
    init { addRequirements(Drivetrain) }
    
    override fun initialize() {}
    
    override fun execute() {
        Drivetrain.tank()
    }
    
    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }
    
    override fun end(interrupted: Boolean) {}
}
