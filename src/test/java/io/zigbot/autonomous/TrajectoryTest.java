package io.zigbot.autonomous;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class TrajectoryTest {

    @Test
    void calculatesPolylineLength() {
        var trajectory = new Trajectory(List.of(
                new Waypoint(0.0, 0.0, 0.0),
                new Waypoint(3.0, 4.0, 0.0),
                new Waypoint(6.0, 8.0, Math.PI)
        ));

        assertEquals(10.0, trajectory.approximateLengthMeters(), 1e-12);
        assertEquals(0.0, trajectory.start().xMeters(), 1e-12);
        assertEquals(6.0, trajectory.end().xMeters(), 1e-12);
        assertEquals(-Math.PI, trajectory.end().headingRadians(), 1e-12);
    }
}
