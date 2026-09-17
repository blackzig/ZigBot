package io.zigbot.core.command;

import io.zigbot.core.Subsystem;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class SequentialCommandGroup implements Command {

    private final List<Command> commands;
    private final Set<Subsystem> requirements;
    private int currentIndex;

    public SequentialCommandGroup(Command... commands) {
        this.commands = List.copyOf(Arrays.asList(commands));

        var combinedRequirements = new LinkedHashSet<Subsystem>();
        for (Command command : this.commands) {
            combinedRequirements.addAll(command.requirements());
        }
        this.requirements = Set.copyOf(combinedRequirements);
    }

    @Override
    public void initialize() {
        currentIndex = 0;
        if (!commands.isEmpty()) {
            commands.getFirst().initialize();
        }
    }

    @Override
    public void execute() {
        if (isFinished()) {
            return;
        }

        Command current = commands.get(currentIndex);
        current.execute();

        if (current.isFinished()) {
            current.end(false);
            currentIndex++;

            if (!isFinished()) {
                commands.get(currentIndex).initialize();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return currentIndex >= commands.size();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted && !isFinished()) {
            commands.get(currentIndex).end(true);
        }
    }

    @Override
    public Set<Subsystem> requirements() {
        return requirements;
    }
}
