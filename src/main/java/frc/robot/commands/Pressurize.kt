/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands

import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj2.command.Command

/**
 *
 * @author SERT
 */
class Pressurize(compressorChannel: Int): Command() {
    val compressorChannel: Int
    
    init {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addRequirements(Compressor)
        this.compressorChannel = compressorChannel
    }
    
    // Called just before this Command runs the first time
    override fun initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    override fun execute() {
        when (compressorChannel) {
            1 -> Compressor.startOnboardCompressor()
            2 -> Compressor.startOffboardCompressor()
        }
    }
    
    protected val isFinished: Boolean
        // Make this return true when this Command no longer needs to run execute()
        get() = false
    
    // Called once after isFinished returns true
    override fun end() {
        Compressor.stopOffboardCompressor()
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected fun interrupted() {
    }
}