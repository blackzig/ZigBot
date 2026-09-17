package io.zigbot.simulator;

import io.zigbot.math.AngleUtil;

public final class DifferentialDriveSimulator {

    private static final double STRAIGHT_LINE_EPSILON = 1e-12;

    private final double trackWidthMeters;
    private RobotPose pose;
    private double leftDistanceMeters;
    private double rightDistanceMeters;

    public DifferentialDriveSimulator(double trackWidthMeters, RobotPose initialPose) {
        if (!(trackWidthMeters > 0.0) || !Double.isFinite(trackWidthMeters)) {
            throw new IllegalArgumentException("Track width must be a finite positive value.");
        }
        if (initialPose == null) {
            throw new IllegalArgumentException("Initial pose is required.");
        }

        this.trackWidthMeters = trackWidthMeters;
        this.pose = initialPose;
    }

    public RobotPose pose() {
        return pose;
    }

    public double leftDistanceMeters() {
        return leftDistanceMeters;
    }

    public double rightDistanceMeters() {
        return rightDistanceMeters;
    }

    public RobotPose step(double leftVelocityMetersPerSecond,
                          double rightVelocityMetersPerSecond,
                          double dtSeconds) {
        validateVelocity(leftVelocityMetersPerSecond);
        validateVelocity(rightVelocityMetersPerSecond);

        if (!(dtSeconds > 0.0) || !Double.isFinite(dtSeconds)) {
            throw new IllegalArgumentException("Simulation timestep must be a finite positive value.");
        }

        leftDistanceMeters += leftVelocityMetersPerSecond * dtSeconds;
        rightDistanceMeters += rightVelocityMetersPerSecond * dtSeconds;

        double linearVelocity = (leftVelocityMetersPerSecond + rightVelocityMetersPerSecond) / 2.0;
        double angularVelocity = (rightVelocityMetersPerSecond - leftVelocityMetersPerSecond) / trackWidthMeters;

        double heading = pose.headingRadians();
        double deltaHeading = angularVelocity * dtSeconds;
        double nextX;
        double nextY;

        if (Math.abs(angularVelocity) < STRAIGHT_LINE_EPSILON) {
            double distance = linearVelocity * dtSeconds;
            nextX = pose.xMeters() + distance * Math.cos(heading);
            nextY = pose.yMeters() + distance * Math.sin(heading);
        } else {
            double radius = linearVelocity / angularVelocity;
            double finalHeading = heading + deltaHeading;

            nextX = pose.xMeters()
                    + radius * (Math.sin(finalHeading) - Math.sin(heading));
            nextY = pose.yMeters()
                    - radius * (Math.cos(finalHeading) - Math.cos(heading));
        }

        double nextHeading = AngleUtil.normalizeRadians(heading + deltaHeading);
        pose = new RobotPose(nextX, nextY, nextHeading);
        return pose;
    }

    public void reset(RobotPose newPose) {
        if (newPose == null) {
            throw new IllegalArgumentException("Pose is required.");
        }

        pose = newPose;
        leftDistanceMeters = 0.0;
        rightDistanceMeters = 0.0;
    }

    private static void validateVelocity(double velocityMetersPerSecond) {
        if (!Double.isFinite(velocityMetersPerSecond)) {
            throw new IllegalArgumentException("Wheel velocity must be finite.");
        }
    }
}
