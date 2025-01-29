package frc.robot.subsystems

import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.PneumaticsModuleType
import edu.wpi.first.wpilibj.motorcontrol.Jaguar
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.ElectronicIDs

object Intake : SubsystemBase() {
    
    val arm = DoubleSolenoid(PneumaticsModuleType.REVPH, ElectronicIDs.INTAKE_RAISE_ID, ElectronicIDs.INTAKE_LOWER_ID) // I'm not sure if this is the right type
    val intake = Jaguar(ElectronicIDs.INTAKE_ID)
    var isUp = false
    
    init {}
    
    fun intake() { intake.set(- .9) }
    
    fun eject() { intake.set(.7) }
    
    fun stop() { intake.set(0.0) }
    
    fun intakeControl(speed: Double) { intake.set(speed) }
    
    fun up() {
        
        isUp = true
        arm.set(DoubleSolenoid.Value.kForward)
    }
    
    fun down() {
        isUp = false
        arm.set(DoubleSolenoid.Value.kReverse)
    }
    
    fun resetArmSolenoid() { arm.set(DoubleSolenoid.Value.kOff) }
}