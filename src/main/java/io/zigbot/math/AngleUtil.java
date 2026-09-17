package io.zigbot.math;

public final class AngleUtil {

    private static final double TWO_PI = Math.PI * 2.0;

    private AngleUtil() {
    }

    public static double normalizeRadians(double angleRadians) {
        if (!Double.isFinite(angleRadians)) {
            throw new IllegalArgumentException("Angle must be finite.");
        }

        double normalized = angleRadians % TWO_PI;

        if (normalized >= Math.PI) {
            normalized -= TWO_PI;
        } else if (normalized < -Math.PI) {
            normalized += TWO_PI;
        }

        return normalized;
    }
}
