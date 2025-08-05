package frc.robot.flywheel

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.math.controller.BangBangController

object FlywheelSubsystem {

    private val topConfig = SparkMaxConfig()
    private val bottomConfig = SparkMaxConfig()
    private val invertAllMotors = false
    private val bbController = BangBangController(0.5)

    init {
        topConfig.smartCurrentLimit(20)
        topConfig.inverted(!invertAllMotors)

        bottomConfig.smartCurrentLimit(20)
        bottomConfig.inverted(invertAllMotors)
    }

    private val topMotor = SparkMax(FlywheelConstants.TOP_ID , SparkLowLevel.MotorType.kBrushless)
    private val bottomMotor = SparkMax(FlywheelConstants.BOTTOM_ID , SparkLowLevel.MotorType.kBrushless)

    fun setSpeed(speed: Double) {

        topMotor.set(speed)
        bottomMotor.set(speed)

    }

}