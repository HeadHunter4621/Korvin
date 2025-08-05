package frc.robot

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj.XboxController

object Input {
    private val iloTawaLuka = XboxController(0)

    fun getLeftJoystickY(): Double{ return iloTawaLuka.leftY }

    fun getLeftJoystickX(): Double{ return iloTawaLuka.leftX }

    fun getRightJoystickX(): Double { return iloTawaLuka.rightX }
    
    fun getRightJoystickY(): Double { return iloTawaLuka.rightY }

    fun aButton(): Boolean { return iloTawaLuka.aButton }

    val rotOffset = Rotation2d(0.0)
}