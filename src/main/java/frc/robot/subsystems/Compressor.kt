package frc.robot.subsystems

import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.PneumaticsModuleType
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.ElectronicIDs

object Compressor : SubsystemBase() {

    var onboardCompressor: Compressor = Compressor(ElectronicIDs.ONBOARD_COMPRESSOR_ID, PneumaticsModuleType.REVPH)
    var offboardCompressor: Compressor = Compressor(ElectronicIDs.OFFBOARD_COMPRESSOR_ID, PneumaticsModuleType.REVPH)
    
    fun startOnboardCompressor() { onboardCompressor.enableDigital() }
    
    fun stopOnboardCompressor() { onboardCompressor.disable() }
    
    fun startOffboardCompressor() { offboardCompressor.enableDigital() }
    
    fun stopOffboardCompressor() { offboardCompressor.disable() }
}