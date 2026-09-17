package io.zigbot.simulator.sensor;

public final class SimulatedEncoder {

    private double positionMeters;
    private double velocityMetersPerSecond;

    public double positionMeters() {
        return positionMeters;
    }

    public double velocityMetersPerSecond() {
        return velocityMetersPerSecond;
    }

    public void update(double velocityMetersPerSecond, double dtSeconds) {
        if (!Double.isFinite(velocityMetersPerSecond)) {
            throw new IllegalArgumentException("Encoder velocity must be finite.");
        }
        if (!(dtSeconds > 0.0) || !Double.isFinite(dtSeconds)) {
            throw new IllegalArgumentException("Timestep must be a finite positive value.");
        }

        this.velocityMetersPerSecond = velocityMetersPerSecond;
        positionMeters += velocityMetersPerSecond * dtSeconds;
    }

    public void reset() {
        positionMeters = 0.0;
        velocityMetersPerSecond = 0.0;
    }
}
