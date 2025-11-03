package seedu.fitnessone.exception;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.model.Session;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExerciseLimitReachedExceptionTest {

    @Test
    void addExercise_whenAtCapacity_throwsExerciseLimitReachedException() {
        Session session = new Session(1, "notes");
        // Set index to capacity boundary
        session.setNextExerciseIndex(99);
        // Next addition should throw
        assertThrows(ExerciseLimitReachedException.class,
                () -> session.addExercise("Overflow exercise", 1, 1));
    }
}
