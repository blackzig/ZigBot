package io.zigbot.simulator.sensor;

import io.zigbot.math.AngleUtil;

public final class SimulatedGyroscope {

    private double angleRadians;

    public double angleRadians() {
        return angleRadians;
    }

    public void setAngleRadians(double angleRadians) {
        this.angleRadians = AngleUtil.normalizeRadians(angleRadians);
    }

    public void addRotation(double deltaRadians) {
        if (!Double.isFinite(deltaRadians)) {
            throw new IllegalArgumentException("Rotation delta must be finite.");
        }
        setAngleRadians(angleRadians + deltaRadians);
    }

    public void reset() {
        angleRadians = 0.0;
    }
}
