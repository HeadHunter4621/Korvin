package frc.robot.subsystems

import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.motorcontrol.Jaguar
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.ConfigConstants
import frc.robot.ElectronicIDs
import frc.robot.Input

object Drivetrain : SubsystemBase() {

    private var slowMode = false

    val rightLeader = Jaguar(ElectronicIDs.DRIVE_RIGHT_LEADER_ID)
    val leftLeader = Jaguar(ElectronicIDs.DRIVE_RIGHT_LEADER_ID)

    val rightFollower = Jaguar(ElectronicIDs.DRIVE_RIGHT_FOLLOWER_ID).addFollower(rightLeader)
    val leftFollower = Jaguar(ElectronicIDs.DRIVE_LEFT_FOLLOWER_ID).addFollower(leftLeader)

    val drivetrain = DifferentialDrive(rightLeader, leftLeader)
    
    init {}
    
    fun tank() {

        if (slowMode) {
            drivetrain.tankDrive(
                Input.getLeftJoystickY() * ConfigConstants.DRIVE_SPEED_SLOW_MULTIPLY,
                Input.getRightJoystickY() * ConfigConstants.DRIVE_SPEED_SLOW_MULTIPLY,
                true
            )
        } else {
            drivetrain.tankDrive(
                Input.getLeftJoystickX() * ConfigConstants.DRIVE_SPEED_NORMAL_MULTIPLY,
                Input.getRightJoystickY() * ConfigConstants.DRIVE_SPEED_NORMAL_MULTIPLY,
                true
            )
        }
    }
    
    fun slowMode() { slowMode = ! slowMode }
}