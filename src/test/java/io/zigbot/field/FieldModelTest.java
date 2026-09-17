package io.zigbot.field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FieldModelTest {

    @Test
    void checksBoundsAndObstacles() {
        var field = new FieldModel(10.0, 5.0);
        field.addObstacle(new AxisAlignedObstacle(2.0, 1.0, 3.0, 2.0));

        assertTrue(field.isInsideField(5.0, 2.5));
        assertFalse(field.isInsideField(11.0, 2.5));

        assertTrue(field.isObstacleAt(2.5, 1.5));
        assertFalse(field.isObstacleAt(5.0, 4.0));
    }
}
