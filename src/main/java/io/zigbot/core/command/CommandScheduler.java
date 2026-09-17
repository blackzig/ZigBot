package io.zigbot.core.command;

import io.zigbot.core.Subsystem;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public final class CommandScheduler {

    private final Set<Command> scheduledCommands = new LinkedHashSet<>();

    public boolean schedule(Command command) {
        Objects.requireNonNull(command, "Command is required.");

        if (scheduledCommands.contains(command)) {
            return false;
        }

        Set<Subsystem> requirements = Set.copyOf(command.requirements());

        for (Command scheduled : new ArrayList<>(scheduledCommands)) {
            if (hasRequirementConflict(requirements, scheduled.requirements())) {
                cancel(scheduled);
            }
        }

        scheduledCommands.add(command);
        command.initialize();
        return true;
    }

    public void run() {
        for (Command command : new ArrayList<>(scheduledCommands)) {
            command.execute();

            if (command.isFinished()) {
                scheduledCommands.remove(command);
                command.end(false);
            }
        }
    }

    public boolean cancel(Command command) {
        Objects.requireNonNull(command, "Command is required.");

        if (!scheduledCommands.remove(command)) {
            return false;
        }

        command.end(true);
        return true;
    }

    public void cancelAll() {
        for (Command command : new ArrayList<>(scheduledCommands)) {
            cancel(command);
        }
    }

    public boolean isScheduled(Command command) {
        return scheduledCommands.contains(command);
    }

    public int size() {
        return scheduledCommands.size();
    }

    private static boolean hasRequirementConflict(Set<Subsystem> left, Set<Subsystem> right) {
        for (Subsystem subsystem : left) {
            if (right.contains(subsystem)) {
                return true;
            }
        }
        return false;
    }
}
