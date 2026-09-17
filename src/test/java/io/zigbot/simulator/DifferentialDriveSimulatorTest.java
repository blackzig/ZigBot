package io.zigbot.simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DifferentialDriveSimulatorTest {

    @Test
    void movesForwardWhenWheelVelocitiesAreEqual() {
        var simulator = new DifferentialDriveSimulator(
                0.60,
                new RobotPose(0.0, 0.0, 0.0)
        );

        simulator.step(1.0, 1.0, 1.0);

        assertEquals(1.0, simulator.pose().xMeters(), 1e-9);
        assertEquals(0.0, simulator.pose().yMeters(), 1e-9);
        assertEquals(0.0, simulator.pose().headingRadians(), 1e-9);
    }

    @Test
    void rotatesWhenWheelVelocitiesDiffer() {
        var simulator = new DifferentialDriveSimulator(
                0.50,
                new RobotPose(0.0, 0.0, 0.0)
        );

        simulator.step(-0.5, 0.5, 1.0);

        assertEquals(2.0, simulator.pose().headingRadians(), 1e-9);
    }
}
