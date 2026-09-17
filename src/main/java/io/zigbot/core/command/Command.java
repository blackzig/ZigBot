package io.zigbot.core.command;

import io.zigbot.core.Subsystem;
import java.util.Set;

public interface Command {

    default void initialize() {
    }

    void execute();

    default boolean isFinished() {
        return false;
    }

    default void end(boolean interrupted) {
    }

    default Set<Subsystem> requirements() {
        return Set.of();
    }
}
