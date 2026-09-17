package io.zigbot.robot.autonomous;

import io.zigbot.core.command.Command;
import io.zigbot.core.command.RunCommand;
import io.zigbot.core.command.SequentialCommandGroup;
import io.zigbot.core.command.TimedCommand;
import io.zigbot.robot.DifferentialDriveSubsystem;
import io.zigbot.simulator.SimulationClock;
import java.util.Objects;

public final class SimpleAutonomousRoutine {

    private SimpleAutonomousRoutine() {
    }

    public static Command create(DifferentialDriveSubsystem drive, SimulationClock clock) {
        Objects.requireNonNull(drive, "Drive subsystem is required.");
        Objects.requireNonNull(clock, "Simulation clock is required.");

        Command driveForward = new TimedCommand(
                new RunCommand(
                        () -> drive.setTankOutput(0.55, 0.55),
                        drive::stop,
                        drive
                ),
                clock,
                1.0
        );

        Command turnLeft = new TimedCommand(
                new RunCommand(
                        () -> drive.setTankOutput(-0.40, 0.40),
                        drive::stop,
                        drive
                ),
                clock,
                0.75
        );

        Command driveSecondLeg = new TimedCommand(
                new RunCommand(
                        () -> drive.setTankOutput(0.45, 0.45),
                        drive::stop,
                        drive
                ),
                clock,
                1.0
        );

        return new SequentialCommandGroup(
                driveForward,
                turnLeft,
                driveSecondLeg
        );
    }
}
