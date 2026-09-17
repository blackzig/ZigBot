package io.zigbot.simulator.sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SimulatedGyroscopeTest {

    @Test
    void normalizesAccumulatedRotation() {
        var gyroscope = new SimulatedGyroscope();

        gyroscope.setAngleRadians(Math.PI);
        assertEquals(-Math.PI, gyroscope.angleRadians(), 1e-12);

        gyroscope.addRotation(Math.PI / 2.0);
        assertEquals(-Math.PI / 2.0, gyroscope.angleRadians(), 1e-12);
    }
}
