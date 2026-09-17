package io.zigbot.core.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.zigbot.core.Subsystem;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CommandSchedulerTest {

    @Test
    void runsAndFinishesACommand() {
        var scheduler = new CommandScheduler();
        var command = new TrackingCommand(Set.of(), true);

        assertTrue(scheduler.schedule(command));
        assertEquals(1, command.initializeCount);

        scheduler.run();

        assertEquals(1, command.executeCount);
        assertEquals(1, command.normalEndCount);
        assertFalse(scheduler.isScheduled(command));
    }

    @Test
    void interruptsCommandWhenRequirementsConflict() {
        var scheduler = new CommandScheduler();
        var drive = new TestSubsystem();
        var first = new TrackingCommand(Set.of(drive), false);
        var second = new TrackingCommand(Set.of(drive), false);

        scheduler.schedule(first);
        scheduler.schedule(second);

        assertEquals(1, first.interruptedEndCount);
        assertFalse(scheduler.isScheduled(first));
        assertTrue(scheduler.isScheduled(second));
    }

    @Test
    void rejectsDuplicateScheduling() {
        var scheduler = new CommandScheduler();
        var command = new TrackingCommand(Set.of(), false);

        assertTrue(scheduler.schedule(command));
        assertFalse(scheduler.schedule(command));
        assertEquals(1, scheduler.size());
    }

    private static final class TestSubsystem implements Subsystem {
    }

    private static final class TrackingCommand implements Command {
        private final Set<Subsystem> requirements;
        private final boolean finishAfterExecute;
        private int initializeCount;
        private int executeCount;
        private int normalEndCount;
        private int interruptedEndCount;

        private TrackingCommand(Set<Subsystem> requirements, boolean finishAfterExecute) {
            this.requirements = requirements;
            this.finishAfterExecute = finishAfterExecute;
        }

        @Override
        public void initialize() {
            initializeCount++;
        }

        @Override
        public void execute() {
            executeCount++;
        }

        @Override
        public boolean isFinished() {
            return finishAfterExecute && executeCount > 0;
        }

        @Override
        public void end(boolean interrupted) {
            if (interrupted) {
                interruptedEndCount++;
            } else {
                normalEndCount++;
            }
        }

        @Override
        public Set<Subsystem> requirements() {
            return requirements;
        }
    }
}
