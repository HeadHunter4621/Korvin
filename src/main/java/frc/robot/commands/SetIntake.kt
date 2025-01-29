package frc.robot.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.Intake

class SetIntake : Command() {
    
    private var speed:Double = 0.0
    
    init { addRequirements(Intake) }

    override fun execute() { Intake.intakeControl(speed) }
    
    override fun isFinished(): Boolean { return false }
    
    override fun end(interrupted: Boolean) { Intake.stop() }
}
