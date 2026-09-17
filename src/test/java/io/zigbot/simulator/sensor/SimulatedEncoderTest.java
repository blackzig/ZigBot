package io.zigbot.simulator.sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SimulatedEncoderTest {

    @Test
    void integratesVelocityIntoDistance() {
        var encoder = new SimulatedEncoder();

        encoder.update(2.0, 0.5);
        encoder.update(1.0, 1.0);

        assertEquals(2.0, encoder.positionMeters(), 1e-12);
        assertEquals(1.0, encoder.velocityMetersPerSecond(), 1e-12);
    }
}
