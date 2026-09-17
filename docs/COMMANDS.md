# Commands and Subsystems

ZigBot uses a small command-based model to teach how robot behavior can be separated from robot hardware.

## Subsystem

A subsystem represents one robot capability, such as drivetrain, arm, intake, or shooter. Subsystems can update their simulated state through `periodic(dtSeconds)`.

## Command

A command represents behavior. It can initialize once, execute repeatedly, decide when it is finished, react to normal completion or interruption, and declare which subsystems it requires.

## Scheduler

`CommandScheduler` owns active commands. When a new command requires a subsystem already in use, the previously scheduled conflicting command is interrupted before the new one starts.

## Example

```java
var driveCommand = new RunCommand(
    () -> drive.setTankOutput(0.5, 0.5),
    drive::stop,
    drive
);

scheduler.schedule(driveCommand);

while (simulationIsRunning) {
    scheduler.run();
    drive.periodic(dt);
}
```

The command system is intentionally small so students can understand the mechanism before using larger robotics frameworks.
