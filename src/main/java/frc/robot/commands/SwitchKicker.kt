package frc.robot.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.Kicker

class SwitchKicker : Command() {
    
    init { addRequirements(Kicker) }

    override fun isFinished(): Boolean { return true }
    
    override fun end(isFinished: Boolean) {

        if (Kicker.isUp) {
            Kicker.lowerKicker()
        } else {
            Kicker.raiseKicker()
        }
    }
}