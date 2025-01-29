package frc.robot.commands

import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.Compressor

class Pressurize(compressorChannel: Int): Command() {
    
    val compressorChannel: Int
    
    init {
        addRequirements(Compressor)
        this.compressorChannel = compressorChannel
    }
    
    override fun initialize() {}
    
    override fun execute() {

        when (compressorChannel) {
            1 -> Compressor.startOnboardCompressor()
            2 -> Compressor.startOffboardCompressor()
        }
    }
    
    override fun isFinished (): Boolean { return false }
    
    override fun end(interrupted: Boolean) { Compressor.stopOffboardCompressor() }
}