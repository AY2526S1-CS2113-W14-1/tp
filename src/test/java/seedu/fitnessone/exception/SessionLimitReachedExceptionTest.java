package seedu.fitnessone.exception;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.model.Athlete;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SessionLimitReachedExceptionTest {

    @Test
    void addSession_whenAtCapacity_throwsSessionLimitReachedException() throws Exception {
    Coach coach = new Coach();
    // Create one athlete without invoking newAthlete to avoid checked exceptions
    coach.addAthlete(new Athlete("0001", "Tester"));
    Athlete athlete = coach.getAthletes().get(0);
        String athleteId = athlete.getAthleteID();
        // Set counter to capacity
        athlete.setSessionIdCounter(999);
        // Next addition should throw
        assertThrows(SessionLimitReachedException.class,
                () -> coach.addSessionToAthlete(athleteId, "Overflow session"));
    }
}
