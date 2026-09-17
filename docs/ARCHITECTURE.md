# ZigBot Architecture

## Principles

ZigBot follows these initial principles:

1. **Simulation first** — core concepts must work without physical hardware.
2. **Deterministic behavior** — the same inputs and timestep should produce the same result.
3. **Small domain objects** — simulation concepts should remain easy to understand and test.
4. **Framework independence** — integrations with external robotics frameworks belong at the edges.
5. **Testable physics** — motion and sensor behavior should be expressed as deterministic Java code.

## Initial packages

### Application

Runnable demonstrations and future CLI entry points.

`io.zigbot.app`

### Math

Framework-independent utilities used by simulation models.

`io.zigbot.math`

Current responsibility:

- angle normalization to the interval `[-π, π)`.

### Simulator

Robot state and deterministic time.

`io.zigbot.simulator`

Current responsibilities:

- immutable robot pose;
- fixed-step simulation clock;
- differential-drive kinematics;
- wheel-distance tracking.

The differential-drive model uses exact constant-velocity arc integration for each simulation step instead of a simple forward-Euler position update.

### Actuators

Hardware-independent simulated actuators.

`io.zigbot.simulator.actuator`

The first actuator model is a motor with normalized output in the range `[-1, 1]` and configurable maximum velocity.

### Sensors

Virtual sensors that can be driven by simulation state.

`io.zigbot.simulator.sensor`

Current sensors:

- encoder;
- gyroscope.

## Execution model

A simulation loop should:

1. read actuator outputs;
2. convert outputs into simulated velocities;
3. advance physics using a fixed timestep;
4. update virtual sensors from the new state;
5. advance the simulation clock;
6. publish telemetry.

Keeping the timestep fixed makes examples, tests, and future autonomous behavior reproducible.

## Direction

Future layers will introduce:

- command abstraction and scheduler;
- subsystem boundaries;
- range and field sensors;
- telemetry;
- diagnostics;
- autonomous routines;
- PID control;
- visualization;
- optional WPILib adapters.

External integrations must not become dependencies of the core simulation model.
