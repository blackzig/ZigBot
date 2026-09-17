# ZigBot

**Learn → Simulate → Build → Analyze**

ZigBot is an open-source Java robotics learning platform designed to help developers and students learn robotics through simulation, practical exercises, telemetry, diagnostics, and modern software engineering practices.

## Vision

ZigBot starts as a lightweight Java robotics simulator and evolves toward a complete learning and experimentation platform for autonomous robots.

The project is intentionally independent. Public robotics projects such as WPILib-based repositories may be studied as technical references, but ZigBot code is written specifically for this project.

## Goals

- Teach robotics concepts using Java.
- Allow experiments without requiring physical robot hardware.
- Keep the core small, understandable, and testable.
- Introduce production-grade practices: architecture, automated tests, telemetry, diagnostics, and CI.
- Provide a foundation for future integrations with robotics ecosystems.

## Current milestone — v0.1

The current foundation includes:

- robot pose (x, y, heading);
- deterministic fixed-step simulation clock;
- differential-drive motion with arc integration;
- simulated motors;
- virtual encoders and gyroscope;
- angle normalization;
- executable simulation example;
- JUnit tests;
- GitHub Actions CI;
- documented architecture.

## Planned modules

```text
ZigBot
├── zigbot-core
│   ├── commands
│   ├── subsystems
│   ├── telemetry
│   └── diagnostics
├── zigbot-simulator
│   ├── drivetrain
│   ├── sensors
│   ├── field
│   └── physics
├── zigbot-examples
│   ├── differential-drive
│   ├── swerve-drive
│   ├── shooter
│   └── autonomous
├── zigbot-learning
│   ├── lessons
│   ├── exercises
│   └── challenges
└── docs
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
- [Roadmap](ROADMAP.md)
- [Contributing](CONTRIBUTING.md)

## License

Apache License 2.0. See [LICENSE](LICENSE).

ZigBot is not affiliated with or endorsed by FIRST or WPILib.
