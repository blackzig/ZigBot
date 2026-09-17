package io.zigbot.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PidControllerTest {

    @Test
    void proportionalGainRespondsToError() {
        var controller = new PidController(2.0, 0.0, 0.0);
        controller.setSetpoint(5.0);

        assertEquals(6.0, controller.calculate(2.0, 0.02), 1e-12);
    }

    @Test
    void integralAccumulatesOverTime() {
        var controller = new PidController(0.0, 1.0, 0.0);
        controller.setSetpoint(1.0);

        assertEquals(0.5, controller.calculate(0.0, 0.5), 1e-12);
        assertEquals(1.0, controller.calculate(0.0, 0.5), 1e-12);
    }

    @Test
    void reportsSetpointTolerance() {
        var controller = new PidController(1.0, 0.0, 0.0);
        controller.setSetpoint(10.0);

        assertTrue(controller.atSetpoint(10.05, 0.1));
        assertFalse(controller.atSetpoint(10.2, 0.1));
    }
}
