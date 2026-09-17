# ZigBot

**Learn → Simulate → Build → Analyze**

ZigBot is an open-source Java robotics learning platform designed to help developers and students learn robotics through simulation, practical exercises, telemetry, diagnostics, and modern software engineering practices.

## Vision

ZigBot starts as a lightweight Java robotics simulator and evolves toward a complete learning and experimentation platform for autonomous robots.

The project is intentionally independent. Public robotics projects such as WPILib-based repositories may be studied as technical references, but ZigBot code is written specifically for this project.

## What already works

- deterministic 2D simulation;
- differential-drive kinematics;
- simulated motors and sensors;
- command/subsystem programming model;
- command scheduler with requirement conflicts;
- in-memory and console telemetry;
- composed drivetrain subsystem;
- PID control;
- waypoints and trajectory representation;
- field and obstacle modeling;
- timed and sequential commands;
- executable autonomous routine;
- JUnit tests and GitHub Actions CI.

## Autonomous example

```java
var autonomous = SimpleAutonomousRoutine.create(drive, clock);
scheduler.schedule(autonomous);

while (scheduler.isScheduled(autonomous)) {
    scheduler.run();
    drive.periodic(clock.timestepSeconds());
    clock.tick();
}
```

## Requirements

- Java 21+
- Gradle 8+

## Run

```bash
gradle run
```

## Test

```bash
gradle test
```

## Documentation

- [Architecture](docs/ARCHITECTURE.md)
- [Commands and Subsystems](docs/COMMANDS.md)
- [Telemetry](docs/TELEMETRY.md)
- [Autonomous Robotics](docs/AUTONOMOUS.md)
- [Roadmap](ROADMAP.md)
- [Contributing](CONTRIBUTING.md)

## Roadmap

The next major milestone is **v0.4 — Learning Experience**, turning the technical foundation into structured lessons, exercises, challenges, and example solutions.

## License

Apache License 2.0. See [LICENSE](LICENSE).

ZigBot is not affiliated with or endorsed by FIRST or WPILib.
