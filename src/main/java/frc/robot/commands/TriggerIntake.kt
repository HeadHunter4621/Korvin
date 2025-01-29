package edu.wpi.first.wpilibj.templates.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.Intake
import frc.robot.subsystems.Kicker

class TriggerIntake : Command() {
    var first: Boolean = true
    
    init { addRequirements(Intake, Kicker) }
    
    override fun initialize() {}
    
    override fun execute() {
        if (first) {
            if (Intake.isUp) {
                Intake.lowerArm()
            }
            if (Kicker.isUp) {
                Kicker.lowerKicker()
            }
            first = false
        }
        Intake.intake()
    }
    
    override fun isFinished(): Boolean {
        return false
    }
    
    override fun end(interrupted: Boolean) {
        Intake.stop()
        first = true
    }
    
    fun interrupted() {
        end(true)
    }
}