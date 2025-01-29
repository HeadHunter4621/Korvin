/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.Intake
import frc.robot.subsystems.Kicker

class TriggerEject : Command() {
    var first: Boolean = true
    
    init { addRequirements(Intake, Kicker) }
    
    // Called just before this Command runs the first time
    override fun initialize() {}
    
    // Called repeatedly when this Command is scheduled to run
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
    
    fun interrupted() {
        end(true)
    }
}