package io.zigbot.autonomous;

import io.zigbot.math.AngleUtil;

public record Waypoint(double xMeters, double yMeters, double headingRadians) {

    public Waypoint {
        if (!Double.isFinite(xMeters) || !Double.isFinite(yMeters)) {
            throw new IllegalArgumentException("Waypoint coordinates must be finite.");
        }
        headingRadians = AngleUtil.normalizeRadians(headingRadians);
    }
}
