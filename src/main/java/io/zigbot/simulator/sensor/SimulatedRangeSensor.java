package io.zigbot.simulator.sensor;

public final class SimulatedRangeSensor {

    private final double maxRangeMeters;
    private double distanceMeters;

    public SimulatedRangeSensor(double maxRangeMeters) {
        if (!(maxRangeMeters > 0.0) || !Double.isFinite(maxRangeMeters)) {
            throw new IllegalArgumentException("Maximum range must be a finite positive value.");
        }
        this.maxRangeMeters = maxRangeMeters;
        this.distanceMeters = maxRangeMeters;
    }

    public double maxRangeMeters() {
        return maxRangeMeters;
    }

    public double distanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(double distanceMeters) {
        if (!Double.isFinite(distanceMeters) || distanceMeters < 0.0) {
            throw new IllegalArgumentException("Distance must be finite and non-negative.");
        }
        this.distanceMeters = Math.min(distanceMeters, maxRangeMeters);
    }

    public boolean isAtMaxRange() {
        return distanceMeters >= maxRangeMeters;
    }
}
