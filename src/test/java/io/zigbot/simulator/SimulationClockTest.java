package io.zigbot.simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SimulationClockTest {

    @Test
    void advancesUsingAFixedTimestep() {
        var clock = new SimulationClock(0.02);

        clock.tick();
        clock.tick();
        clock.tick();

        assertEquals(3, clock.stepCount());
        assertEquals(0.06, clock.timeSeconds(), 1e-12);
    }

    @Test
    void resetsToZero() {
        var clock = new SimulationClock(0.02);
        clock.tick();

        clock.reset();

        assertEquals(0, clock.stepCount());
        assertEquals(0.0, clock.timeSeconds(), 1e-12);
    }
}
