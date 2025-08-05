package frc.robot.drivetrain

import com.ctre.phoenix6.hardware.CANcoder
import com.revrobotics.spark.SparkBase
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import com.studica.frc.AHRS
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.*
import edu.wpi.first.math.util.Units
import edu.wpi.first.wpilibj.MotorSafety
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.SubsystemBase
import kotlin.math.*


class NokaWan(
    private val soweliTawa: SparkMax,
    private val soweliSike: SparkMax,
    private val okoPiSoweliSike: CANcoder,
    private val antePiSoweliSike: Double,
    var nasin: SwerveModuleState,
) : MotorSafety() {

    var ma: SwerveModulePosition
    private var maWile = nasin
    private var reference = 0.0
    val ala = false

    init{

        val nasinNanpaPiSoweliTawa = SparkMaxConfig()
        val nasinNanpaPiSoweliSike = SparkMaxConfig()

        // Drive Motors
        nasinNanpaPiSoweliTawa.inverted(NanpaPiIloTawa.soweliTawaLiAla)
        nasinNanpaPiSoweliTawa.idleMode(SparkBaseConfig.IdleMode.kBrake)
        nasinNanpaPiSoweliTawa.smartCurrentLimit(NanpaPiIloTawa.nanpaWawaSuliPiSoweliTawa)

        nasinNanpaPiSoweliTawa.closedLoop.p(NanpaPiIloTawa.DRIVE_P)
        nasinNanpaPiSoweliTawa.closedLoop.i(NanpaPiIloTawa.DRIVE_I)
        nasinNanpaPiSoweliTawa.closedLoop.d(NanpaPiIloTawa.DRIVE_D)

        nasinNanpaPiSoweliTawa.encoder.positionConversionFactor(NanpaPiIloTawa.DRIVE_ENCODER_MULTIPLY_POSITION)
        nasinNanpaPiSoweliTawa.encoder.velocityConversionFactor(NanpaPiIloTawa.DRIVE_ENCODER_MULTIPLY_VELOCITY)

        // Angle Motors
        nasinNanpaPiSoweliSike.inverted(NanpaPiIloTawa.soweliSikeLiAla)
        nasinNanpaPiSoweliSike.idleMode(SparkBaseConfig.IdleMode.kBrake)
        nasinNanpaPiSoweliSike.smartCurrentLimit(NanpaPiIloTawa.nanpaWawaSuliPiSoweliSike)

        nasinNanpaPiSoweliSike.closedLoop.p(NanpaPiIloTawa.ANGLE_P)
        nasinNanpaPiSoweliSike.closedLoop.i(NanpaPiIloTawa.ANGLE_I)
        nasinNanpaPiSoweliSike.closedLoop.d(NanpaPiIloTawa.ANGLE_D)

        nasinNanpaPiSoweliSike.closedLoop.positionWrappingEnabled(true)
        nasinNanpaPiSoweliSike.closedLoop.positionWrappingMinInput(-PI)
        nasinNanpaPiSoweliSike.closedLoop.positionWrappingMaxInput(PI)

        // Angle Encoders
        nasinNanpaPiSoweliSike.encoder.positionConversionFactor(NanpaPiIloTawa.ANGLE_ENCODER_MULTIPLY)
        nasinNanpaPiSoweliSike.encoder.velocityConversionFactor(NanpaPiIloTawa.ANGLE_ENCODER_MULTIPLY / 60)

        ma = SwerveModulePosition(soweliTawa.encoder.position, lukinNanpaSike())

        soweliSike.configure(nasinNanpaPiSoweliSike, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters)
        soweliTawa.configure(nasinNanpaPiSoweliTawa, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters)

    }

    fun lukinNanpaSike(): Rotation2d {
        if(ala) {
            soweliSike.encoder.setPosition(okoPiSoweliSike.absolutePosition.valueAsDouble * NanpaPiIloTawa.ANGLE_ENCODER_MULTIPLY - antePiSoweliSike)
        }else{
            soweliSike.encoder.setPosition((-(okoPiSoweliSike.absolutePosition.valueAsDouble * NanpaPiIloTawa.ANGLE_ENCODER_MULTIPLY - antePiSoweliSike)))
        }

        return Rotation2d((soweliSike.encoder.position+ PI).mod(2*PI)-PI)
    }

    // Run periodically
    fun anteNasin() {
        val angle = lukinNanpaSike()
        nasin = SwerveModuleState(soweliTawa.encoder.velocity, angle)
        ma = SwerveModulePosition(soweliTawa.encoder.position, angle)
    }

    fun lon(wanted: SwerveModuleState) {
        wanted.optimize(lukinNanpaSike())
        val driveError = wanted.speedMetersPerSecond - soweliTawa.encoder.velocity

        maWile = SwerveModuleState(wanted.speedMetersPerSecond, Rotation2d(wanted.angle.radians))
        reference = driveError.pow(2) * sign(driveError) + soweliTawa.encoder.velocity
        soweliSike.closedLoopController.setReference(wanted.angle.radians, SparkBase.ControlType.kPosition)
    }

    fun lukinWileLuka():SwerveModuleState { return SwerveModuleState(maWile.speedMetersPerSecond, Rotation2d(maWile.angle.radians)) }

    fun setMotorMode(coast: Boolean, driveConfig:SparkMaxConfig, angleConfig:SparkMaxConfig) {

        if(coast) {
            driveConfig.idleMode(SparkBaseConfig.IdleMode.kCoast)
            angleConfig.idleMode(SparkBaseConfig.IdleMode.kCoast)
        } else {
            driveConfig.idleMode(SparkBaseConfig.IdleMode.kBrake)
            angleConfig.idleMode(SparkBaseConfig.IdleMode.kBrake)
        }


    }

    fun getModuleReference(): Double { return reference }

    fun getAmps() :Pair<Double, Double> { return Pair(soweliTawa.outputCurrent, soweliSike.outputCurrent) }

    fun setCurrentLimit(amps:Int, driveConfig: SparkMaxConfig) { driveConfig.smartCurrentLimit(amps) }

    override fun stopMotor() {
        soweliTawa.stopMotor()
        soweliSike.stopMotor()
    }

    fun getEncoderHealth(): Double { return okoPiSoweliSike.magnetHealth.valueAsDouble }

    override fun getDescription(): String { return "Swerve"
    }
}

object NokaAle: SubsystemBase() {

    private val imu = AHRS(AHRS.NavXComType.kMXP_SPI)

    private val kinematics: SwerveDriveKinematics
    private var modules: Array<NokaWan>
    private var odometry: SwerveDriveOdometry
    private val poseEstimator: SwerveDrivePoseEstimator

    private var pose = Pose2d()
    private var visionPose = Pose2d()

    private var prevPose = Pose2d()
    private var prevTime = Timer.getFPGATimestamp()

    var deltaPose = Pose2d()
        private set

    init {

        val modulePositions = mutableListOf<Translation2d>()
        val moduleList = mutableListOf<NokaWan>()

        for (moduleData in NanpaPiIloTawa.nanpaPiLukaWanAle) {
            val driveMotor = SparkMax(moduleData.driveMotorID, SparkLowLevel.MotorType.kBrushless)
            val angleMotor = SparkMax(moduleData.angleMotorID, SparkLowLevel.MotorType.kBrushless)

            modulePositions.add(moduleData.position)
            val module = createModule(driveMotor, angleMotor, moduleData)
            module.isSafetyEnabled = true
            moduleList.add(module)
        }

        modules = moduleList.toTypedArray()

        val positions = mutableListOf<SwerveModulePosition>()

        for (module in modules) {
            module.anteNasin()
            positions.add(module.ma)
        }

        val positionsArray = positions.toTypedArray()

        kinematics = SwerveDriveKinematics(*modulePositions.toTypedArray())
        odometry = SwerveDriveOdometry(kinematics, getYawAsRotation2d(), positionsArray, Pose2d())
        poseEstimator = SwerveDrivePoseEstimator(kinematics, getYawAsRotation2d(), positionsArray, Pose2d())

        NokaAle.defaultCommand = DrivetrainCommand(true)

    }


    // "Fix this nonsense" -Whoever made the original code
    private fun createModule(driveMotor: SparkMax, angleMotor:SparkMax, moduleData: SwerveModuleData): NokaWan {

        return NokaWan(
            driveMotor,
            angleMotor,
            CANcoder(moduleData.angleEncoderID),
            moduleData.angleOffset,
            SwerveModuleState()
        )

    }

    override fun periodic() {

        val positions = mutableListOf<SwerveModulePosition>()

        for (module in modules) {
            module.anteNasin()
            positions.add(module.ma)
        }

        val positionsArray = positions.toTypedArray()

        pose = odometry.update(getYawAsRotation2d(), positionsArray)

        val currTime = Timer.getFPGATimestamp()
        val deltaTime = currTime - prevTime

        poseEstimator.update(getYawAsRotation2d(), positionsArray)

        deltaPose = Pose2d((pose.y - prevPose.y) / deltaTime, (pose.x - prevPose.x) / deltaTime, -(pose.rotation - prevPose.rotation) / deltaTime)

        prevPose = pose
        prevTime = currTime

    }

    fun setNewPose(newPose:Pose2d) {

        pose = Pose2d(newPose.y, newPose.x, newPose.rotation)

        val positions = mutableListOf<SwerveModulePosition>()

        for (module in modules) {
            module.anteNasin()
            positions.add(module.ma)
        }

        val positionsArray = positions.toTypedArray()

        odometry.resetPosition(getYawAsRotation2d(), positionsArray, pose)

    }

    private fun feed() {

        for (module in modules) {
            module.feed()
        }
    }

    fun drive(chassisSpeeds: ChassisSpeeds) {

        val wantedStates = kinematics.toSwerveModuleStates(chassisSpeeds)

        for (i in wantedStates.indices) {
            modules[i].lon(wantedStates[i])
        }

        feed()

    }

    fun getTiltDirection(): Translation2d {

        val unNormalized = Translation2d(atan(Units.degreesToRadians(imu.roll.toDouble())), atan(Units.degreesToRadians(imu.pitch.toDouble())))
        val norm = unNormalized.norm

        if (norm == 0.0) {
            return unNormalized
        }

        return unNormalized / norm

    }

    fun getModuleAngle(index: Int): Double { return modules[index].lukinNanpaSike().radians }

    fun getModuleVelocity(index: Int): Double { return modules[index].nasin.speedMetersPerSecond }

    fun getTilt(): Double { return atan(sqrt(tan(Units.degreesToRadians(imu.pitch.toDouble())).pow(2) + tan(Units.degreesToRadians(imu.roll.toDouble())).pow(2))) }

    fun getRoll(): Double { return Units.degreesToRadians(imu.roll.toDouble()) }

    fun getAccelerationSquared():Double { return (imu.worldLinearAccelY.pow(2) + imu.worldLinearAccelX.pow(2)).toDouble() }

    fun getGoals(): Array<SwerveModuleState> { return arrayOf(modules[0].lukinWileLuka(), modules[1].lukinWileLuka(), modules[2].lukinWileLuka(), modules[3].lukinWileLuka()) }

    fun getReferences(): Array<Double> { return arrayOf(modules[0].getModuleReference(), modules[1].getModuleReference(), modules[2].getModuleReference(), modules[3].getModuleReference()) }

    fun getAmps(): Array<Pair<Double, Double>>{ return arrayOf(modules[0].getAmps(), modules[1].getAmps(), modules[2].getAmps(), modules[3].getAmps()) }

    fun getDraw(): Double{ return modules[0].getAmps().first+modules[1].getAmps().first+modules[2].getAmps().first+modules[3].getAmps().first }

    fun getStates(): Array<SwerveModuleState>{ return arrayOf(modules[0].nasin, modules[1].nasin, modules[2].nasin, modules[3].nasin) }

    fun getHealth(module: Int): Double { return modules[module].getEncoderHealth() }

    fun getYaw(): Double { return -imu.yaw.toDouble() }

    fun getYawAsRotation2d(): Rotation2d { return Rotation2d.fromDegrees(-imu.angle) }

    fun getRelativeSpeeds(): ChassisSpeeds { return kinematics.toChassisSpeeds(*arrayOf(modules[0].nasin, modules[1].nasin, modules[2].nasin, modules[3].nasin)) } // Yes it says that the asterisk is wrong, but it is correct.

    fun getAbsoluteSpeeds(): ChassisSpeeds { return ChassisSpeeds.fromRobotRelativeSpeeds(getRelativeSpeeds(), getPose().rotation) }

    // "Fix this nonsense" -Whoever made the original code
    fun getPose(): Pose2d { return Pose2d(pose.y, pose.x, pose.rotation) }

    fun setMode(coast: Boolean, driveConfig: SparkMaxConfig, angleConfig: SparkMaxConfig) {

        for (module in modules) {
            module.setMotorMode(coast, driveConfig, angleConfig)
        }
    }

    fun setCurrentLimit(amps: Int, driveConfig: SparkMaxConfig) {

        for (module in modules) {
            module.setCurrentLimit(amps, driveConfig)
        }
    }

    fun stop() {

        for (module in modules) {
            module.stopMotor()
        }

        feed()

    }
}