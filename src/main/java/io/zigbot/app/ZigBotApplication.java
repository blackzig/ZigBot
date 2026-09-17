package io.zigbot.app;

import io.zigbot.simulator.DifferentialDriveSimulator;
import io.zigbot.simulator.RobotPose;

public final class ZigBotApplication {

    private ZigBotApplication() {
    }

    public static void main(String[] args) {
        var simulator = new DifferentialDriveSimulator(
                0.60,
                new RobotPose(0.0, 0.0, 0.0)
        );

        double dtSeconds = 0.02;

        for (int step = 0; step < 100; step++) {
            simulator.step(1.0, 1.0, dtSeconds);
        }

        var pose = simulator.pose();
        System.out.printf(
                "ZigBot pose -> x=%.2f m, y=%.2f m, heading=%.2f rad%n",
                pose.xMeters(),
                pose.yMeters(),
                pose.headingRadians()
        );
    }
}
