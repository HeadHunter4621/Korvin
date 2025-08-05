package frc.robot.drivetrain

import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj2.command.Command
import frc.robot.EditingConstants
import frc.robot.Input
import kotlin.math.*


class JoystickDrive(private val fieldOriented: Boolean = true): Command() {

    val joystickX = Input::getLeftJoystickX
    val joystickY = Input::getLeftJoystickY
    val joystickZ = Input::getRightJoystickX
    val inputRotOffset = Input.rotOffset

    init {

        addRequirements(Drivetrain)

    }

    override fun initialize() {}

    override fun execute() {

        val x = joystickX()
        val y = joystickY()

        var magnitude = Translation2d(x, y).norm
        val angle = atan2(x, y)

        // This math (lines 38 to 51 was made by Benji
        // If it doesn't work blame them
        val mult: Double =
            if ((x == 0.0) || (y == 0.0)){
                1.0
            }else if (abs(x) >= abs(y)){
                abs(cos(angle))
            } else {
                abs(sin(angle))
            }

        magnitude *= mult
        magnitude = magnitude.pow(3)

        val newX = cos(angle) * magnitude
        val newY = sin(angle) * magnitude

        if ((joystickX() == 0.0) && (joystickY() == 0.0) && (joystickX() == 0.0)) {

            Drivetrain.stop()

        } else if (fieldOriented) {

            Drivetrain.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                    newX * EditingConstants.DRIVE_SPEED,
                    newY * EditingConstants.DRIVE_SPEED,
                    joystickZ().pow(3) * EditingConstants.TURN_SPEED,
                    Drivetrain.getPose().rotation.minus(inputRotOffset)
                )
            )

        } else {

            Drivetrain.drive(
                ChassisSpeeds(
                    newX * EditingConstants.DRIVE_SPEED,
                    newY * EditingConstants.DRIVE_SPEED,
                    joystickZ().pow(3) * EditingConstants.TURN_SPEED
                )
            )

        }

        fun isFinished(): Boolean { return false }

        fun end(interrupted: Boolean) { Drivetrain.stop() }

    }

}