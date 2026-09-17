package io.zigbot.control;

public final class PidController {

    private final double kP;
    private final double kI;
    private final double kD;

    private double setpoint;
    private double integral;
    private double previousError;
    private boolean hasPreviousError;

    public PidController(double kP, double kI, double kD) {
        requireFinite(kP, "kP");
        requireFinite(kI, "kI");
        requireFinite(kD, "kD");
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public void setSetpoint(double setpoint) {
        requireFinite(setpoint, "Setpoint");
        this.setpoint = setpoint;
    }

    public double setpoint() {
        return setpoint;
    }

    public double calculate(double measurement, double dtSeconds) {
        requireFinite(measurement, "Measurement");
        if (!(dtSeconds > 0.0) || !Double.isFinite(dtSeconds)) {
            throw new IllegalArgumentException("Timestep must be a finite positive value.");
        }

        double error = setpoint - measurement;
        integral += error * dtSeconds;

        double derivative = 0.0;
        if (hasPreviousError) {
            derivative = (error - previousError) / dtSeconds;
        }

        previousError = error;
        hasPreviousError = true;

        return kP * error + kI * integral + kD * derivative;
    }

    public boolean atSetpoint(double measurement, double tolerance) {
        requireFinite(measurement, "Measurement");
        if (tolerance < 0.0 || !Double.isFinite(tolerance)) {
            throw new IllegalArgumentException("Tolerance must be finite and non-negative.");
        }
        return Math.abs(setpoint - measurement) <= tolerance;
    }

    public void reset() {
        integral = 0.0;
        previousError = 0.0;
        hasPreviousError = false;
    }

    private static void requireFinite(double value, String name) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(name + " must be finite.");
        }
    }
}
