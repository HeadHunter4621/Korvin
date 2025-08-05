package frc.robot.drivetrain

import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.system.plant.DCMotor
import edu.wpi.first.units.Units
import edu.wpi.first.units.Units.Pounds
import kotlin.math.PI

object DrivetrainConstants {

    val robotMass = Pounds.of(115.0)
    val momentOfInertia = Units.KilogramSquareMeters.of(0.0)

    const val HALF_SIDE_LENGTH = 0.5773 / 2.0
    const val DRIVE_BASE_RADIUS = 0.4082

    val swerveModuleData = listOf(
        SwerveModuleData(Translation2d(HALF_SIDE_LENGTH, -HALF_SIDE_LENGTH), 7, 11, 4, -0.355-1.61-1.56+ PI /2, false), //Back Left
        SwerveModuleData(Translation2d(-HALF_SIDE_LENGTH, -HALF_SIDE_LENGTH), 8, 12, 3, -0.138-1.57-1.54+ PI /2, false), //Back Right
        SwerveModuleData(Translation2d(HALF_SIDE_LENGTH, HALF_SIDE_LENGTH), 5, 9, 1, 2.41-1.612-1.58+ PI /2, false), //Front Left
        SwerveModuleData(Translation2d(-HALF_SIDE_LENGTH, HALF_SIDE_LENGTH), 6, 10, 2, 0.059-1.568-1.575+ PI /2, false) //Front Right
    )

    const val DRIVE_MOTOR_INVERTED = false
    const val ANGLE_MOTOR_INVERTED = false

    const val DRIVE_CURRENT_LIMIT = 0
    const val ANGLE_CURRENT_LIMIT = 0

    const val ANGLE_ENCODER_MULTIPLY = 1.0
    const val DRIVE_ENCODER_MULTIPLY_POSITION = 1.0
    const val DRIVE_ENCODER_MULTIPLY_VELOCITY = DRIVE_ENCODER_MULTIPLY_POSITION / 60

    const val DRIVE_P = 0.0
    const val DRIVE_I = 0.0
    const val DRIVE_D = 0.0

    const val ANGLE_P = 0.0
    const val ANGLE_I = 0.0
    const val ANGLE_D = 0.0

    val driveMotorGearbox = DCMotor.getNEO(1).withReduction(6.75)

    val moduleTranslations = arrayOf(
        Translation2d(11.35, 11.35),
        Translation2d(11.35, -11.35),
        Translation2d(-11.35, 11.35),
        Translation2d(-11.35, -11.35)
    )

}

class SwerveModuleData(val position: Translation2d, val driveMotorID: Int, val angleMotorID: Int, val angleEncoderID: Int, val angleOffset: Double, val inverted: Boolean){}