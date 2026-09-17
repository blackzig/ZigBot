package io.zigbot.core.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.zigbot.simulator.SimulationClock;
import org.junit.jupiter.api.Test;

class TimedCommandTest {

    @Test
    void finishesAfterConfiguredSimulationTime() {
        var clock = new SimulationClock(0.1);
        var command = new TimedCommand(
                new RunCommand(() -> { }, () -> { }),
                clock,
                0.3
        );

        command.initialize();

        assertFalse(command.isFinished());
        clock.tick();
        clock.tick();
        assertFalse(command.isFinished());

        clock.tick();
        assertTrue(command.isFinished());
    }
}
