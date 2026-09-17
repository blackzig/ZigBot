package io.zigbot.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AngleUtilTest {

    @Test
    void keepsAnglesInsideMinusPiInclusiveToPiExclusive() {
        assertEquals(0.0, AngleUtil.normalizeRadians(0.0), 1e-12);
        assertEquals(-Math.PI, AngleUtil.normalizeRadians(Math.PI), 1e-12);
        assertEquals(-Math.PI, AngleUtil.normalizeRadians(3.0 * Math.PI), 1e-12);
        assertEquals(Math.PI / 2.0, AngleUtil.normalizeRadians(5.0 * Math.PI / 2.0), 1e-12);
    }

    @Test
    void rejectsNonFiniteAngles() {
        assertThrows(IllegalArgumentException.class,
                () -> AngleUtil.normalizeRadians(Double.NaN));
    }
}
