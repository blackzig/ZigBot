package io.zigbot.core.command;

import io.zigbot.core.Subsystem;
import io.zigbot.simulator.SimulationClock;
import java.util.Objects;
import java.util.Set;

public final class TimedCommand implements Command {

    private final Command command;
    private final SimulationClock clock;
    private final double durationSeconds;
    private double startTimeSeconds;

    public TimedCommand(Command command, SimulationClock clock, double durationSeconds) {
        this.command = Objects.requireNonNull(command, "Command is required.");
        this.clock = Objects.requireNonNull(clock, "Simulation clock is required.");
        if (!(durationSeconds > 0.0) || !Double.isFinite(durationSeconds)) {
            throw new IllegalArgumentException("Duration must be a finite positive value.");
        }
        this.durationSeconds = durationSeconds;
    }

    @Override
    public void initialize() {
        startTimeSeconds = clock.timeSeconds();
        command.initialize();
    }

    @Override
    public void execute() {
        command.execute();
    }

    @Override
    public boolean isFinished() {
        return command.isFinished()
                || clock.timeSeconds() - startTimeSeconds >= durationSeconds;
    }

    @Override
    public void end(boolean interrupted) {
        command.end(interrupted);
    }

    @Override
    public Set<Subsystem> requirements() {
        return command.requirements();
    }
}
