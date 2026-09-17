package io.zigbot.simulator;

public final class DifferentialDriveSimulator {

    private final double trackWidthMeters;
    private RobotPose pose;

    public DifferentialDriveSimulator(double trackWidthMeters, RobotPose initialPose) {
        if (!(trackWidthMeters > 0.0) || !Double.isFinite(trackWidthMeters)) {
            throw new IllegalArgumentException("Track width must be a finite positive value.");
        }
        this.trackWidthMeters = trackWidthMeters;
        this.pose = initialPose;
    }

    public RobotPose pose() {
        return pose;
    }

    public RobotPose step(double leftVelocityMetersPerSecond,
                          double rightVelocityMetersPerSecond,
                          double dtSeconds) {
        if (!(dtSeconds > 0.0) || !Double.isFinite(dtSeconds)) {
            throw new IllegalArgumentException("Simulation timestep must be a finite positive value.");
        }

        double linearVelocity = (leftVelocityMetersPerSecond + rightVelocityMetersPerSecond) / 2.0;
        double angularVelocity = (rightVelocityMetersPerSecond - leftVelocityMetersPerSecond) / trackWidthMeters;

        double heading = pose.headingRadians();
        double nextX = pose.xMeters() + linearVelocity * Math.cos(heading) * dtSeconds;
        double nextY = pose.yMeters() + linearVelocity * Math.sin(heading) * dtSeconds;
        double nextHeading = heading + angularVelocity * dtSeconds;

        pose = new RobotPose(nextX, nextY, nextHeading);
        return pose;
    }
}
