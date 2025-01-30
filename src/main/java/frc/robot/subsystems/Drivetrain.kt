package frc.robot.subsystems

import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.motorcontrol.Jaguar
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ElectronicIDs

object  Drivetrain : SubsystemBase() {

    private val motorLF = Jaguar(ElectronicIDs.DRIVE_LF_ID)
    private val motorRF = Jaguar(ElectronicIDs.DRIVE_RF_ID)

    private val motorLB = Jaguar(ElectronicIDs.DRIVE_LB_ID)
    private val motorRB = Jaguar(ElectronicIDs.DRIVE_RB_ID)
    val differentialDrive: DifferentialDrive

    init{

        motorLF.addFollower(motorLB)
        motorRF.addFollower(motorRB)

        differentialDrive = DifferentialDrive(motorLF, motorRF)

    }

}