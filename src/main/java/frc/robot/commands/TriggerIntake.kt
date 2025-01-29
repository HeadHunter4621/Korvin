package frc.robot.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.Intake
import frc.robot.subsystems.Kicker

class TriggerIntake : Command() {

    var first: Boolean = true
    
    init { addRequirements(Intake, Kicker) }

    override fun execute() {

        if (first) {
            Intake.down()
            Kicker.down()
            first = false
        }
        Intake.intake()
    }
    
    override fun isFinished(): Boolean { return false }
    
    override fun end(interrupted: Boolean) {
        Intake.stop()
        first = true
    }

    fun interrupted() { end(true) }
}