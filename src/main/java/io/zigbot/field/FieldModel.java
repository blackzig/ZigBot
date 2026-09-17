package io.zigbot.field;

import io.zigbot.simulator.RobotPose;
import java.util.ArrayList;
import java.util.List;

public final class FieldModel {

    private final double widthMeters;
    private final double heightMeters;
    private final List<AxisAlignedObstacle> obstacles = new ArrayList<>();

    public FieldModel(double widthMeters, double heightMeters) {
        if (!(widthMeters > 0.0) || !Double.isFinite(widthMeters)
                || !(heightMeters > 0.0) || !Double.isFinite(heightMeters)) {
            throw new IllegalArgumentException("Field dimensions must be finite positive values.");
        }
        this.widthMeters = widthMeters;
        this.heightMeters = heightMeters;
    }

    public double widthMeters() {
        return widthMeters;
    }

    public double heightMeters() {
        return heightMeters;
    }

    public void addObstacle(AxisAlignedObstacle obstacle) {
        if (obstacle == null) {
            throw new IllegalArgumentException("Obstacle is required.");
        }
        obstacles.add(obstacle);
    }

    public List<AxisAlignedObstacle> obstacles() {
        return List.copyOf(obstacles);
    }

    public boolean isInsideField(double xMeters, double yMeters) {
        return xMeters >= 0.0 && xMeters <= widthMeters
                && yMeters >= 0.0 && yMeters <= heightMeters;
    }

    public boolean isInsideField(RobotPose pose) {
        if (pose == null) {
            throw new IllegalArgumentException("Pose is required.");
        }
        return isInsideField(pose.xMeters(), pose.yMeters());
    }

    public boolean isObstacleAt(double xMeters, double yMeters) {
        for (AxisAlignedObstacle obstacle : obstacles) {
            if (obstacle.contains(xMeters, yMeters)) {
                return true;
            }
        }
        return false;
    }
}
