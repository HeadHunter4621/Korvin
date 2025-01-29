package frc.robot.commands

import frc.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj2.command.Command

class SlowDrive : Command() {

    init { addRequirements(Drivetrain) }

    override fun isFinished(): Boolean {return true}

    override fun end(interrupted: Boolean) { Drivetrain.slowMode() }
}