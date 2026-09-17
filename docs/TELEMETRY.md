# Telemetry

Telemetry exposes internal robot state for observation, debugging, visualization, and future log analysis.

## API

The `TelemetrySink` interface supports numeric, text, and boolean values. Simulation code depends on the interface rather than a particular output system.

## Current implementations

### InMemoryTelemetry

Stores the latest value for each key. It is useful for tests, examples, future dashboards, and deterministic simulation.

### ConsoleTelemetry

Writes values to a `PrintStream`. It is useful for simple experiments and command-line learning exercises.

## Naming

Telemetry keys should use stable hierarchical names:

```text
drive/xMeters
drive/yMeters
drive/headingRadians
drive/leftDistanceMeters
drive/rightDistanceMeters
```

Stable names will make it easier to add log files, charts, cloud dashboards, and AI-assisted diagnostics later.
