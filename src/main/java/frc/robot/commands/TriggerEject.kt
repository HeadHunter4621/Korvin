package frc.robot.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.Intake
import frc.robot.subsystems.Kicker

class TriggerEject : Command() {

    var first: Boolean = true
    
    init { addRequirements(Intake, Kicker) }

    override fun execute() {

        if (first) {

            if (Intake.isUp) {
                Intake.lowerArm()
            }

            first = false
        }
        
        if (! Kicker.isUp) {
            Kicker.raiseKicker()
        }
        
        Intake.intakeControl(.75)
    }
    
    override fun isFinished(): Boolean { return false }
    
    // Called once after isFinished returns true
    override fun end(interrupted: Boolean) {
        first = true
        Kicker.lowerKicker()
        Intake.stop()
    }
    
    fun interrupted() { end(true) }
}