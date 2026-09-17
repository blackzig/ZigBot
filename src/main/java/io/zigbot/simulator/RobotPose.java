package io.zigbot.simulator;

public record RobotPose(double xMeters, double yMeters, double headingRadians) {

    public RobotPose {
        if (!Double.isFinite(xMeters) || !Double.isFinite(yMeters) || !Double.isFinite(headingRadians)) {
            throw new IllegalArgumentException("Pose values must be finite.");
        }
    }
}
