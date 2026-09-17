# ZigBot Architecture

## Principles

ZigBot follows four initial principles:

1. **Simulation first** — core concepts must work without physical hardware.
2. **Deterministic behavior** — the same inputs and timestep should produce the same result.
3. **Small domain objects** — simulation concepts should remain easy to understand and test.
4. **Framework independence** — integrations with external robotics frameworks belong at the edges.

## Initial layers

### Application

Entry points and runnable examples.

`io.zigbot.app`

### Simulator

Pure Java simulation models and physics.

`io.zigbot.simulator`

The first implementation models a differential-drive robot in a 2D plane.

## Direction

Future modules will introduce:

- commands and scheduler;
- subsystem abstractions;
- virtual sensors;
- telemetry;
- diagnostics;
- autonomous routines;
- visualization;
- optional WPILib adapters.

External integrations must not become dependencies of the core simulation model.
