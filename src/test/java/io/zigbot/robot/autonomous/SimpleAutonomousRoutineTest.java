package io.zigbot.robot.autonomous;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.zigbot.core.command.CommandScheduler;
import io.zigbot.core.telemetry.InMemoryTelemetry;
import io.zigbot.robot.DifferentialDriveSubsystem;
import io.zigbot.simulator.RobotPose;
import io.zigbot.simulator.SimulationClock;
import org.junit.jupiter.api.Test;

class SimpleAutonomousRoutineTest {

    @Test
    void completesAFullRoutineUsingSimulationTime() {
        var clock = new SimulationClock(0.02);
        var drive = new DifferentialDriveSubsystem(
                0.60,
                2.0,
                new RobotPose(0.0, 0.0, 0.0),
                new InMemoryTelemetry()
        );
        var scheduler = new CommandScheduler();
        var autonomous = SimpleAutonomousRoutine.create(drive, clock);

        scheduler.schedule(autonomous);

        int iterations = 0;
        while (scheduler.isScheduled(autonomous) && iterations < 250) {
            scheduler.run();
            drive.periodic(clock.timestepSeconds());
            clock.tick();
            iterations++;
        }

        assertFalse(scheduler.isScheduled(autonomous));
        assertTrue(iterations < 250);
        assertTrue(Math.hypot(drive.pose().xMeters(), drive.pose().yMeters()) > 1.0);
        assertTrue(Math.abs(drive.headingRadians()) > 0.5);
    }
}
