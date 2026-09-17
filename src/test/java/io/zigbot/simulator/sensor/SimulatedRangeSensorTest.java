package io.zigbot.simulator.sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SimulatedRangeSensorTest {

    @Test
    void startsAtMaximumRangeAndClampsLongDistances() {
        var sensor = new SimulatedRangeSensor(5.0);

        assertTrue(sensor.isAtMaxRange());

        sensor.setDistanceMeters(8.0);

        assertEquals(5.0, sensor.distanceMeters(), 1e-12);
        assertTrue(sensor.isAtMaxRange());
    }

    @Test
    void reportsDetectedDistance() {
        var sensor = new SimulatedRangeSensor(5.0);

        sensor.setDistanceMeters(1.5);

        assertEquals(1.5, sensor.distanceMeters(), 1e-12);
        assertFalse(sensor.isAtMaxRange());
    }
}
