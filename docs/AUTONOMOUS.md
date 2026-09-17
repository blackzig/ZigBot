# Autonomous Robotics

ZigBot v0.3 introduces the first building blocks for autonomous behavior.

## PID control

`PidController` implements proportional, integral, and derivative control using the simulation timestep.

Typical usage:

```java
var pid = new PidController(1.2, 0.0, 0.05);
pid.setSetpoint(target);

double output = pid.calculate(measurement, dtSeconds);
```

## Waypoints and trajectories

A `Waypoint` stores:

- x position;
- y position;
- desired heading.

A `Trajectory` is an immutable ordered list of at least two waypoints. At this stage it represents a route and can calculate its approximate polyline length.

Trajectory following will be layered on top of this representation later.

## Field model

`FieldModel` represents the rectangular simulation field and a collection of axis-aligned obstacles.

It can answer:

- whether a position is inside field bounds;
- whether a point overlaps an obstacle.

## Time-bounded commands

`TimedCommand` wraps another command and ends it after a configured amount of simulation time.

Because it uses `SimulationClock`, tests and autonomous routines remain deterministic.

## Sequential autonomous routines

`SequentialCommandGroup` executes commands one after another.

The first example, `SimpleAutonomousRoutine`, performs three steps:

1. drive forward;
2. turn left;
3. drive a second leg.

Each segment owns the drivetrain through the command requirement model.

## Execution

```java
var autonomous = SimpleAutonomousRoutine.create(drive, clock);
scheduler.schedule(autonomous);

while (scheduler.isScheduled(autonomous)) {
    scheduler.run();
    drive.periodic(clock.timestepSeconds());
    clock.tick();
}
```

This is intentionally small and transparent so the mechanics of autonomous robot software can be learned before more advanced trajectory followers and path planners are introduced.
