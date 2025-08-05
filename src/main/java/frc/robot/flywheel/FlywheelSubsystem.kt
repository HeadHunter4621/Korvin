package frc.robot.flywheel

import com.revrobotics.spark.config.SparkMaxConfig

object FlywheelSubsystem {

    private val topConfig = SparkMaxConfig()
    private val bottomConfig = SparkMaxConfig()
    private val invertAllMotors = false

    init {
        topConfig.smartCurrentLimit(20)
        topConfig.inverted(!invertAllMotors)
    }

    private val topMotor = 0

}