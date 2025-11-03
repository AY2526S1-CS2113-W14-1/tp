package seedu.fitnessone.exception;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.model.Athlete;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AthleteLimitReachedExceptionTest {

    @Test
    void newAthlete_whenAtCapacity_throwsAthleteLimitReachedException() throws Exception {
        Coach coach = new Coach();
        // Pre-fill the list to 9,999 athletes using storage constructor to avoid static ID side effects
        for (int i = 1; i <= 9999; i++) {
            coach.addAthlete(new Athlete("0001", "A"));
        }
        // Next creation should throw
        assertThrows(AthleteLimitReachedException.class, () -> coach.newAthlete("Overflow"));
    }
}
