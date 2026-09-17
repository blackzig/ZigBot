package io.zigbot.core.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class SequentialCommandGroupTest {

    @Test
    void initializesCommandsInSequence() {
        var events = new ArrayList<String>();
        var group = new SequentialCommandGroup(
                new InstantCommand(() -> events.add("first")),
                new InstantCommand(() -> events.add("second"))
        );

        group.initialize();
        group.execute();
        group.execute();

        assertEquals(List.of("first", "second"), events);
        assertTrue(group.isFinished());
    }
}
