package frc.robot.subsystems

import edu.wpi.first.wpilibj.motorcontrol.Jaguar
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ElectronicIDs

object Drivetrain : SubsystemBase() {
    
    private val motorLeftFront = Jaguar(ElectronicIDs.DRIVE_LEFT_1_ID)
    private val motorLeftBack = Jaguar(ElectronicIDs.DRIVE_LEFT_2_ID)
    
    private val motorRightFront = Jaguar(ElectronicIDs.DRIVE_RIGHT_1_ID)
    private val motorRightBack = Jaguar(ElectronicIDs.DRIVE_RIGHT_2_ID)
    
    fun setSpeeds(leftSpeed:Double, rightSpeed: Double) {
        
        motorLeftFront.set(leftSpeed)
        motorLeftBack.set(leftSpeed)
        
        motorRightFront.set(rightSpeed)
        motorRightBack.set(rightSpeed)
        
    }
    
    fun stop() {
        
        motorLeftFront.stopMotor()
        motorLeftBack.stopMotor()
        
        motorRightFront.stopMotor()
        motorRightBack.stopMotor()
        
    }
}