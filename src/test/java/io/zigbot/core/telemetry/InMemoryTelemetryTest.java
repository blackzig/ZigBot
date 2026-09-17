package io.zigbot.core.telemetry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class InMemoryTelemetryTest {

    @Test
    void storesTypedTelemetryValues() {
        var telemetry = new InMemoryTelemetry();

        telemetry.putNumber("robot/x", 1.25);
        telemetry.putText("robot/mode", "AUTO");
        telemetry.putBoolean("robot/enabled", true);

        assertEquals(1.25, (Double) telemetry.get("robot/x"), 1e-12);
        assertEquals("AUTO", telemetry.get("robot/mode"));
        assertEquals(true, telemetry.get("robot/enabled"));
    }

    @Test
    void snapshotCannotBeModified() {
        var telemetry = new InMemoryTelemetry();
        telemetry.putNumber("robot/x", 1.0);

        var snapshot = telemetry.snapshot();

        assertThrows(UnsupportedOperationException.class,
                () -> snapshot.put("robot/y", 2.0));
    }
}
