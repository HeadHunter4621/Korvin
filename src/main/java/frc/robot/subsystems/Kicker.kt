package frc.robot.subsystems

import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.PneumaticsModuleType
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.ElectronicIDs

object Kicker : SubsystemBase() {
    
    val kicker = DoubleSolenoid(PneumaticsModuleType.REVPH, ElectronicIDs.KICKER_RAISE_ID, ElectronicIDs.KICKER_LOWER_ID)
    var isUp = false
    
    init { lowerKicker() }
    
    fun raiseKicker() {

        isUp = true
        kicker.set(DoubleSolenoid.Value.kForward)
    }
    
    fun lowerKicker() {

        isUp = false
        kicker.set(DoubleSolenoid.Value.kReverse)
    }
    
    fun resetKickerSolenoid() { kicker.set(DoubleSolenoid.Value.kOff) }
}