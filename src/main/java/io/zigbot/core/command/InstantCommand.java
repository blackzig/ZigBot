package io.zigbot.core.command;

import io.zigbot.core.Subsystem;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public final class InstantCommand implements Command {

    private final Runnable action;
    private final Set<Subsystem> requirements;
    private boolean finished;

    public InstantCommand(Runnable action, Subsystem... requirements) {
        this.action = Objects.requireNonNull(action, "Action is required.");
        this.requirements = Set.copyOf(Arrays.asList(requirements));
    }

    @Override
    public void initialize() {
        action.run();
        finished = true;
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public Set<Subsystem> requirements() {
        return requirements;
    }
}
