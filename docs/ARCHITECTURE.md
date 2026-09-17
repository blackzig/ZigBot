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

### Commands

`io.zigbot.core.command` contains the command model, scheduler, instant/continuous commands, timed commands, and sequential groups.

Commands declare subsystem requirements. Scheduling a conflicting command interrupts the command that currently owns the same subsystem.

### Telemetry

`io.zigbot.core.telemetry` abstracts robot observations through `TelemetrySink`.

Current sinks:

- `InMemoryTelemetry`
- `ConsoleTelemetry`

## Control

`io.zigbot.control` contains feedback-control algorithms.

Current controller:

- `PidController`

## Autonomous

`io.zigbot.autonomous` contains route representations:

- `Waypoint`
- `Trajectory`

The current trajectory model is intentionally data-oriented. Path following will be layered on top rather than coupled to the representation.

## Field

`io.zigbot.field` models the simulated environment.

Current concepts:

- rectangular field bounds;
- axis-aligned obstacles;
- point occupancy queries.

## Math

`io.zigbot.math` contains framework-independent utilities such as angle normalization to the interval `[-π, π)`.

## Simulator

`io.zigbot.simulator` provides immutable robot pose, fixed-step time, differential-drive kinematics, actuators, and virtual sensors.

The differential-drive model uses exact constant-velocity arc integration for each simulation step.

## Robot compositions

`io.zigbot.robot` combines core abstractions and simulation components into understandable robot mechanisms.

The first composition is `DifferentialDriveSubsystem`, which owns motors, encoders, gyroscope, drivetrain physics, and telemetry publication.

`io.zigbot.robot.autonomous` contains executable autonomous compositions such as `SimpleAutonomousRoutine`.

## Execution model

A deterministic simulation loop follows this order:

1. run scheduled commands;
2. update subsystem physics with the fixed timestep;
3. update virtual sensors;
4. publish telemetry;
5. advance the simulation clock.

## Next direction

The technical foundation now supports a learning layer. The next milestone will add:

- structured lessons;
- exercises;
- challenges;
- example solutions;
- richer examples and interactive documentation.

Later engineering milestones can add trajectory followers, visualization, persisted logs, cloud dashboards, and optional WPILib adapters.

External integrations must not become dependencies of the core simulation model.
