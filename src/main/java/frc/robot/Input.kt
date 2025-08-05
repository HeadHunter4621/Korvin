package frc.robot

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj.XboxController

object Input {
    private val controller = XboxController(0)

    // All getJoystick functions were initially negative, not sure if that's necessary

    fun getLeftJoystickY(): Double{ return controller.leftY }

    fun getLeftJoystickX(): Double{ return controller.leftX }

    fun getRightJoystickX(): Double { return controller.rightX }
    
    fun getRightJoystickY(): Double { return controller.rightY }

    val rotOffset = Rotation2d()
}