package io.zigbot.autonomous;

import java.util.List;
import java.util.Objects;

public final class Trajectory {

    private final List<Waypoint> waypoints;

    public Trajectory(List<Waypoint> waypoints) {
        Objects.requireNonNull(waypoints, "Waypoints are required.");
        if (waypoints.size() < 2) {
            throw new IllegalArgumentException("A trajectory requires at least two waypoints.");
        }
        this.waypoints = List.copyOf(waypoints);
    }

    public List<Waypoint> waypoints() {
        return waypoints;
    }

    public Waypoint start() {
        return waypoints.getFirst();
    }

    public Waypoint end() {
        return waypoints.getLast();
    }

    public double approximateLengthMeters() {
        double length = 0.0;

        for (int i = 1; i < waypoints.size(); i++) {
            Waypoint previous = waypoints.get(i - 1);
            Waypoint current = waypoints.get(i);
            length += Math.hypot(
                    current.xMeters() - previous.xMeters(),
                    current.yMeters() - previous.yMeters()
            );
        }

        return length;
    }
}
