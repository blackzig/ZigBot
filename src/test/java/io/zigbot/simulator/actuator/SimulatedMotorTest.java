package io.zigbot.simulator.actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SimulatedMotorTest {

    @Test
    void convertsOutputToVelocity() {
        var motor = new SimulatedMotor(4.0);

        motor.setOutput(0.5);

        assertEquals(0.5, motor.output(), 1e-12);
        assertEquals(2.0, motor.velocity(), 1e-12);
    }

    @Test
    void clampsOutputToValidRange() {
        var motor = new SimulatedMotor(4.0);

        motor.setOutput(2.0);
        assertEquals(1.0, motor.output(), 1e-12);

        motor.setOutput(-2.0);
        assertEquals(-1.0, motor.output(), 1e-12);
    }
}
