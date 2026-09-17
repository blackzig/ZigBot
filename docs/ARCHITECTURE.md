# ZigBot Architecture

## Principles

ZigBot follows these principles:

1. **Simulation first** — core concepts must work without physical hardware.
2. **Deterministic behavior** — the same inputs and timestep should produce the same result.
3. **Small domain objects** — simulation concepts should remain easy to understand and test.
4. **Framework independence** — integrations with external robotics frameworks belong at the edges.
5. **Testable physics** — motion and sensor behavior should be deterministic Java code.
6. **Composable behavior** — commands describe actions while subsystems describe robot capabilities.

## Core

`io.zigbot.core` defines framework-independent robotics concepts.

### Subsystem

A robot capability with an optional periodic update.

### Commands

`io.zigbot.core.command` currently contains:

- `Command`
- `CommandScheduler`
- `InstantCommand`
- `RunCommand`

Commands declare subsystem requirements. Scheduling a conflicting command interrupts the command that currently owns the same subsystem.

### Telemetry

`io.zigbot.core.telemetry` abstracts robot observations through `TelemetrySink`.

Current sinks:

- `InMemoryTelemetry`
- `ConsoleTelemetry`

## Math

`io.zigbot.math` contains framework-independent utilities such as angle normalization to the interval `[-π, π)`.

## Simulator

`io.zigbot.simulator` provides:

- immutable robot pose;
- fixed-step simulation clock;
- differential-drive kinematics;
- wheel-distance tracking.

The differential-drive model uses exact constant-velocity arc integration for each simulation step.

### Actuators

`io.zigbot.simulator.actuator` contains the simulated motor abstraction with normalized output in `[-1, 1]`.

### Sensors

`io.zigbot.simulator.sensor` currently contains:

- encoder;
- gyroscope;
- range sensor.

## Robot compositions

`io.zigbot.robot` combines core abstractions and simulation components into understandable robot mechanisms.

The first composition is `DifferentialDriveSubsystem`, which owns motors, encoders, gyroscope, drivetrain physics, and telemetry publication.

## Execution model

A simulation loop currently follows this order:

1. run scheduled commands;
2. update subsystem physics with the fixed timestep;
3. update virtual sensors;
4. publish telemetry;
5. advance the simulation clock.

## Direction

Future layers will introduce:

- time-bounded and sequential commands;
- autonomous trajectories;
- PID control;
- field and obstacle models;
- visualization;
- log persistence and analysis;
- optional WPILib adapters.

External integrations must not become dependencies of the core simulation model.
