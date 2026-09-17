package io.zigbot.app;

import io.zigbot.simulator.DifferentialDriveSimulator;
import io.zigbot.simulator.RobotPose;
import io.zigbot.simulator.SimulationClock;
import io.zigbot.simulator.actuator.SimulatedMotor;
import io.zigbot.simulator.sensor.SimulatedEncoder;
import io.zigbot.simulator.sensor.SimulatedGyroscope;

public final class ZigBotApplication {

    private ZigBotApplication() {
    }

    public static void main(String[] args) {
        var clock = new SimulationClock(0.02);
        var leftMotor = new SimulatedMotor(2.0);
        var rightMotor = new SimulatedMotor(2.0);
        var leftEncoder = new SimulatedEncoder();
        var rightEncoder = new SimulatedEncoder();
        var gyroscope = new SimulatedGyroscope();

        var simulator = new DifferentialDriveSimulator(
                0.60,
                new RobotPose(0.0, 0.0, 0.0)
        );

        leftMotor.setOutput(0.50);
        rightMotor.setOutput(0.65);

        while (clock.timeSeconds() < 2.0) {
            double dt = clock.timestepSeconds();
            double leftVelocity = leftMotor.velocity();
            double rightVelocity = rightMotor.velocity();

            RobotPose pose = simulator.step(leftVelocity, rightVelocity, dt);
            leftEncoder.update(leftVelocity, dt);
            rightEncoder.update(rightVelocity, dt);
            gyroscope.setAngleRadians(pose.headingRadians());

            clock.tick();
        }

        var pose = simulator.pose();
        System.out.printf(
                "ZigBot t=%.2f s -> x=%.2f m, y=%.2f m, heading=%.2f rad%n",
                clock.timeSeconds(),
                pose.xMeters(),
                pose.yMeters(),
                gyroscope.angleRadians()
        );
        System.out.printf(
                "Encoders -> left=%.2f m, right=%.2f m%n",
                leftEncoder.positionMeters(),
                rightEncoder.positionMeters()
        );
    }
}
