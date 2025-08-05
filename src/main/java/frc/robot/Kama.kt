package frc.robot

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj.XboxController

object Kama {
    private val iloTawaLuka = XboxController(0)

    fun sonaPiLeftJoystickY(): Double{ return iloTawaLuka.leftY }

    fun sonaPiLeftJoystickX(): Double{ return iloTawaLuka.leftX }

    fun sonaPiRightJoystickX(): Double { return iloTawaLuka.rightX }
    
    fun sonaPiRightJoystickY(): Double { return iloTawaLuka.rightY }

    fun aButton(): Boolean { return iloTawaLuka.aButton }

    fun sonaAnteSike(): Rotation2d { return Rotation2d()
    }
}