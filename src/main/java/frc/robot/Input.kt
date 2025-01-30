package frc.robot

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj.XboxController

object Input {
    private val driverController = XboxController(0)
    var rotationOffset = Rotation2d(0.0)

    // All getJoystick functions were initially negative, not sure if that's necessary

    fun getLeftJoystickY(): Double{ return driverController.leftY }

    fun getLeftJoystickX(): Double{ return driverController.leftX }

    fun getRightJoystickX(): Double { return driverController.rightX }
    
    fun getRightJoystickY(): Double { return driverController.rightY }

    fun aButton(): Boolean { return driverController.aButton }
}