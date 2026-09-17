package io.zigbot.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.zigbot.core.telemetry.InMemoryTelemetry;
import io.zigbot.simulator.RobotPose;
import org.junit.jupiter.api.Test;

class DifferentialDriveSubsystemTest {

    @Test
    void updatesPoseSensorsAndTelemetryTogether() {
        var telemetry = new InMemoryTelemetry();
        var drive = new DifferentialDriveSubsystem(
                0.60,
                2.0,
                new RobotPose(0.0, 0.0, 0.0),
                telemetry
        );

        drive.setTankOutput(0.5, 0.5);

        for (int i = 0; i < 50; i++) {
            drive.periodic(0.02);
        }

        assertEquals(1.0, drive.pose().xMeters(), 1e-9);
        assertEquals(1.0, drive.leftDistanceMeters(), 1e-9);
        assertEquals(1.0, drive.rightDistanceMeters(), 1e-9);
        assertEquals(0.0, drive.headingRadians(), 1e-9);
        assertEquals(1.0, (Double) telemetry.get("drive/xMeters"), 1e-9);
    }
}
