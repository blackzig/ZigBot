package io.zigbot.robot;

import io.zigbot.core.Subsystem;
import io.zigbot.core.telemetry.TelemetrySink;
import io.zigbot.simulator.DifferentialDriveSimulator;
import io.zigbot.simulator.RobotPose;
import io.zigbot.simulator.actuator.SimulatedMotor;
import io.zigbot.simulator.sensor.SimulatedEncoder;
import io.zigbot.simulator.sensor.SimulatedGyroscope;
import java.util.Objects;

public final class DifferentialDriveSubsystem implements Subsystem {

    private final DifferentialDriveSimulator simulator;
    private final SimulatedMotor leftMotor;
    private final SimulatedMotor rightMotor;
    private final SimulatedEncoder leftEncoder = new SimulatedEncoder();
    private final SimulatedEncoder rightEncoder = new SimulatedEncoder();
    private final SimulatedGyroscope gyroscope = new SimulatedGyroscope();
    private final TelemetrySink telemetry;

    public DifferentialDriveSubsystem(double trackWidthMeters,
                                      double maxWheelVelocityMetersPerSecond,
                                      RobotPose initialPose,
                                      TelemetrySink telemetry) {
        this.simulator = new DifferentialDriveSimulator(trackWidthMeters, initialPose);
        this.leftMotor = new SimulatedMotor(maxWheelVelocityMetersPerSecond);
        this.rightMotor = new SimulatedMotor(maxWheelVelocityMetersPerSecond);
        this.telemetry = Objects.requireNonNull(telemetry, "Telemetry sink is required.");
        this.gyroscope.setAngleRadians(initialPose.headingRadians());
    }

    public void setTankOutput(double leftOutput, double rightOutput) {
        leftMotor.setOutput(leftOutput);
        rightMotor.setOutput(rightOutput);
    }

    public void stop() {
        setTankOutput(0.0, 0.0);
    }

    public RobotPose pose() {
        return simulator.pose();
    }

    public double leftDistanceMeters() {
        return leftEncoder.positionMeters();
    }

    public double rightDistanceMeters() {
        return rightEncoder.positionMeters();
    }

    public double headingRadians() {
        return gyroscope.angleRadians();
    }

    @Override
    public void periodic(double dtSeconds) {
        double leftVelocity = leftMotor.velocity();
        double rightVelocity = rightMotor.velocity();

        RobotPose pose = simulator.step(leftVelocity, rightVelocity, dtSeconds);
        leftEncoder.update(leftVelocity, dtSeconds);
        rightEncoder.update(rightVelocity, dtSeconds);
        gyroscope.setAngleRadians(pose.headingRadians());

        telemetry.putNumber("drive/xMeters", pose.xMeters());
        telemetry.putNumber("drive/yMeters", pose.yMeters());
        telemetry.putNumber("drive/headingRadians", pose.headingRadians());
        telemetry.putNumber("drive/leftDistanceMeters", leftEncoder.positionMeters());
        telemetry.putNumber("drive/rightDistanceMeters", rightEncoder.positionMeters());
    }
}
