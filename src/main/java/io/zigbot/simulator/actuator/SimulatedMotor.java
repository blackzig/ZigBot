package io.zigbot.simulator.actuator;

public final class SimulatedMotor implements Motor {

    private final double maxVelocity;
    private double output;

    public SimulatedMotor(double maxVelocity) {
        if (!(maxVelocity > 0.0) || !Double.isFinite(maxVelocity)) {
            throw new IllegalArgumentException("Maximum velocity must be a finite positive value.");
        }
        this.maxVelocity = maxVelocity;
    }

    @Override
    public void setOutput(double output) {
        if (!Double.isFinite(output)) {
            throw new IllegalArgumentException("Motor output must be finite.");
        }
        this.output = Math.max(-1.0, Math.min(1.0, output));
    }

    @Override
    public double output() {
        return output;
    }

    @Override
    public double velocity() {
        return output * maxVelocity;
    }
}
