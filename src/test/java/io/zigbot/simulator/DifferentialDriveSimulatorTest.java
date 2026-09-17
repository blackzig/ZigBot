package io.zigbot.simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.zigbot.math.AngleUtil;
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
        assertEquals(1.0, simulator.leftDistanceMeters(), 1e-9);
        assertEquals(1.0, simulator.rightDistanceMeters(), 1e-9);
    }

    @Test
    void rotatesInPlaceWhenWheelVelocitiesAreOpposite() {
        var simulator = new DifferentialDriveSimulator(
                0.50,
                new RobotPose(0.0, 0.0, 0.0)
        );

        simulator.step(-0.5, 0.5, 1.0);

        assertEquals(0.0, simulator.pose().xMeters(), 1e-9);
        assertEquals(0.0, simulator.pose().yMeters(), 1e-9);
        assertEquals(2.0, simulator.pose().headingRadians(), 1e-9);
    }

    @Test
    void followsAnArcWhenWheelVelocitiesDiffer() {
        var simulator = new DifferentialDriveSimulator(
                0.50,
                new RobotPose(0.0, 0.0, 0.0)
        );

        simulator.step(0.0, 1.0, 1.0);

        assertEquals(0.25 * Math.sin(2.0), simulator.pose().xMeters(), 1e-9);
        assertEquals(0.25 * (1.0 - Math.cos(2.0)), simulator.pose().yMeters(), 1e-9);
        assertEquals(2.0, simulator.pose().headingRadians(), 1e-9);
    }

    @Test
    void normalizesHeadingAfterRotation() {
        var simulator = new DifferentialDriveSimulator(
                0.50,
                new RobotPose(0.0, 0.0, 0.0)
        );

        simulator.step(-1.0, 1.0, 2.0);

        assertEquals(AngleUtil.normalizeRadians(8.0),
                simulator.pose().headingRadians(), 1e-9);
    }

    @Test
    void resetClearsWheelDistances() {
        var simulator = new DifferentialDriveSimulator(
                0.50,
                new RobotPose(0.0, 0.0, 0.0)
        );
        simulator.step(1.0, 1.0, 1.0);

        simulator.reset(new RobotPose(2.0, 3.0, 0.5));

        assertEquals(2.0, simulator.pose().xMeters(), 1e-9);
        assertEquals(3.0, simulator.pose().yMeters(), 1e-9);
        assertEquals(0.0, simulator.leftDistanceMeters(), 1e-9);
        assertEquals(0.0, simulator.rightDistanceMeters(), 1e-9);
    }
}
