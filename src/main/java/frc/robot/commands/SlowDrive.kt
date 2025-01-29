package frc.robot.commands

import edu.wpi.first.wpilibj.templates.subsystems.Drivetrain
import edu.wpi.first.wpilibj2.command.Command

class SlowDrive : Command() {
    init { addRequirements(Drivetrain) }
    
    override fun initialize() {}
    
    override fun execute() {}
    
    override fun isFinished(): Boolean {return true}

    override fun end(interrupted: Boolean) { Drivetrain.slowMode() }
}