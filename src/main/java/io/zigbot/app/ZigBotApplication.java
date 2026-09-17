package io.zigbot.app;

import io.zigbot.core.command.CommandScheduler;
import io.zigbot.core.command.RunCommand;
import io.zigbot.core.telemetry.InMemoryTelemetry;
import io.zigbot.robot.DifferentialDriveSubsystem;
import io.zigbot.simulator.RobotPose;
import io.zigbot.simulator.SimulationClock;

public final class ZigBotApplication {

    private ZigBotApplication() {
    }

    public static void main(String[] args) {
        var clock = new SimulationClock(0.02);
        var telemetry = new InMemoryTelemetry();
        var scheduler = new CommandScheduler();

        var drive = new DifferentialDriveSubsystem(
                0.60,
                2.0,
                new RobotPose(0.0, 0.0, 0.0),
                telemetry
        );

        var driveCommand = new RunCommand(
                () -> drive.setTankOutput(0.50, 0.65),
                drive::stop,
                drive
        );

        scheduler.schedule(driveCommand);

        while (clock.timeSeconds() < 2.0) {
            scheduler.run();
            drive.periodic(clock.timestepSeconds());
            clock.tick();
        }

        scheduler.cancel(driveCommand);

        var pose = drive.pose();
        System.out.printf(
                "ZigBot t=%.2f s -> x=%.2f m, y=%.2f m, heading=%.2f rad%n",
                clock.timeSeconds(),
                pose.xMeters(),
                pose.yMeters(),
                drive.headingRadians()
        );
        System.out.printf(
                "Encoders -> left=%.2f m, right=%.2f m%n",
                drive.leftDistanceMeters(),
                drive.rightDistanceMeters()
        );
        System.out.printf("Telemetry keys -> %d%n", telemetry.snapshot().size());
    }
}
