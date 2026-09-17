package io.zigbot.field;

public record AxisAlignedObstacle(double minX, double minY, double maxX, double maxY) {

    public AxisAlignedObstacle {
        if (!Double.isFinite(minX) || !Double.isFinite(minY)
                || !Double.isFinite(maxX) || !Double.isFinite(maxY)) {
            throw new IllegalArgumentException("Obstacle bounds must be finite.");
        }
        if (maxX <= minX || maxY <= minY) {
            throw new IllegalArgumentException("Obstacle maximum bounds must exceed minimum bounds.");
        }
    }

    public boolean contains(double xMeters, double yMeters) {
        return xMeters >= minX && xMeters <= maxX
                && yMeters >= minY && yMeters <= maxY;
    }
}
