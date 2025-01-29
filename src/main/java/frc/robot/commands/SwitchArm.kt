package frc.robot.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.Intake

class SwitchArm: Command() {
    init { addRequirements(Intake) }
    
    override fun initialize() {}
    
    override fun execute() {}
    
    override fun isFinished(): Boolean { return false }
    
    override fun end(isFinished: Boolean) {
        if (Intake.isUp) {
            Intake.lowerArm()
        } else {
            Intake.raiseArm()
        }
    }
}