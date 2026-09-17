package io.zigbot.core.command;

import io.zigbot.core.Subsystem;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public final class RunCommand implements Command {

    private final Runnable action;
    private final Runnable onEnd;
    private final Set<Subsystem> requirements;

    public RunCommand(Runnable action, Runnable onEnd, Subsystem... requirements) {
        this.action = Objects.requireNonNull(action, "Action is required.");
        this.onEnd = Objects.requireNonNull(onEnd, "End action is required.");
        this.requirements = Set.copyOf(Arrays.asList(requirements));
    }

    @Override
    public void execute() {
        action.run();
    }

    @Override
    public void end(boolean interrupted) {
        onEnd.run();
    }

    @Override
    public Set<Subsystem> requirements() {
        return requirements;
    }
}
