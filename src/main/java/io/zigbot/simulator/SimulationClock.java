package io.zigbot.simulator;

public final class SimulationClock {

    private final double timestepSeconds;
    private long stepCount;

    public SimulationClock(double timestepSeconds) {
        if (!(timestepSeconds > 0.0) || !Double.isFinite(timestepSeconds)) {
            throw new IllegalArgumentException("Timestep must be a finite positive value.");
        }
        this.timestepSeconds = timestepSeconds;
    }

    public double timestepSeconds() {
        return timestepSeconds;
    }

    public long stepCount() {
        return stepCount;
    }

    public double timeSeconds() {
        return stepCount * timestepSeconds;
    }

    public double tick() {
        stepCount++;
        return timeSeconds();
    }

    public void reset() {
        stepCount = 0;
    }
}
