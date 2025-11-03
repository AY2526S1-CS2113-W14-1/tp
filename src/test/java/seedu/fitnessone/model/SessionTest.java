package seedu.fitnessone.model;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.ExerciseLimitReachedException;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SessionTest {

    private static final Pattern DATE_RE =
            Pattern.compile("^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}$");

    @Test
    void constructor_setsFields_andFormatsIdsAndDate() {
        Session session = new Session(1, "Legs day");
        assertEquals("001", session.getSessionIdString(), "Session ID must be zero-padded to 3 digits");
        assertEquals("Legs day", session.getTrainingNotes());
        assertFalse(session.isCompleted(), "Default completion should be false");
        assertNotNull(session.getSessionDateString());
        assertTrue(DATE_RE.matcher(session.getSessionDateString()).matches(),
                "Date should match dd-MM-yyyy HH:mm");
        assertTrue(session.getExercises().isEmpty(), "Exercises should start empty");
    }

    @Test
    void constructor_blankNotes_getsDefaultMessage() {
        Session session = new Session(5, "   \t  ");
        assertEquals("005", session.getSessionIdString());
        assertEquals(
                "EMPTY. Add sessions notes with: /UpdateSessionNote <athlete> <session> <notes>",
                session.getTrainingNotes(),
                "Blank notes should be replaced with default helper message"
        );
    }

    @Test
    void setTrainingNotes_updatesValue() {
        Session session = new Session(7, "old");
        session.setTrainingNotes("new notes");
        assertEquals("new notes", session.getTrainingNotes());
    }

    @Test
    void completionFlags_toggleCorrectly() {
        Session session = new Session(2, "notes");
        assertFalse(session.isCompleted());
        session.setCompleted();
        assertTrue(session.isCompleted());
        session.setNotCompleted();
        assertFalse(session.isCompleted());
    }

    @Test
    void addExercise_addsAndReturnsSameInstance() throws ExerciseLimitReachedException {
        Session session = new Session(3, "notes");
        Exercise exercise1 = session.addExercise("Pushups", 3, 10);
        Exercise exercise2  = session.addExercise("Squats", 4, 8);

        assertNotNull(exercise1);
        assertNotNull(exercise2);
        assertEquals(2, session.getExercises().size());
        assertSame(exercise1, session.getExercises().get(0));
        assertSame(exercise2, session.getExercises().get(1));
    }

    @Test
    void addExercise_whenAtCapacity_throws() {
        Session session = new Session(4, "notes");
        session.setNextExerciseIndex(99);
        assertThrows(ExerciseLimitReachedException.class,
                () -> session.addExercise("Overflow", 1, 1));
    }

    /*@Test
    void removeExercise_validIndex_removesCorrectItem() throws InvalidExerciseException {
        Session session = new Session(6, "notes");
        Exercise exercise1 = session.addExercise("A", 1, 1);
        Exercise exercise2  = session.addExercise("B", 1, 1);
        Exercise exercise3 = session.addExercise("C", 1, 1);
        assertEquals(3, session.getExercises().size());

        session.removeExercise(String.valueOf(1));
        assertEquals(2, session.getExercises().size());
        assertSame(exercise1, session.getExercises().get(0));
        assertSame(exercise3, session.getExercises().get(1));
    }*/

    @Test
    void removeExercise_negativeIndex_throws() throws ExerciseLimitReachedException {
        Session session = new Session(8, "notes");
        session.addExercise("Only", 1, 1);

        InvalidExerciseException ex = assertThrows(InvalidExerciseException.class,
                () -> session.removeExercise(String.valueOf(-1)));
        assertEquals("Exercise with ID 'Exercise not found: -1' is not available" +
                " in the system.", ex.getMessage());
    }

    @Test
    void removeExercise_indexTooLarge_throws() throws ExerciseLimitReachedException {
        Session session = new Session(9, "notes");
        session.addExercise("Only", 1, 1);

        InvalidExerciseException ex = assertThrows(InvalidExerciseException.class,
                () -> session.removeExercise(String.valueOf(5)));
        assertEquals("Exercise with ID 'Exercise not found: 5' is not " +
                "available in the system.", ex.getMessage());
    }
}
