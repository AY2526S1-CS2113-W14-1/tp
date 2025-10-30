package seedu.fitnessone.model;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.exception.InvalidSessionException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AthleteTest {

    @Test
    void constructor_autoIncrementsIds_andStoresName() {
        Athlete athlete1 = new Athlete("philip");
        Athlete athlete2 = new Athlete("halil");

        assertNotNull(athlete1.getAthleteID());
        assertNotNull(athlete2.getAthleteID());
        assertNotEquals(athlete1.getAthleteID(), athlete2.getAthleteID());
        assertEquals("philip", athlete1.getAthleteName());
        assertEquals("halil", athlete2.getAthleteName());
    }

    @Test
    void storageConstructor_preservesId_andNextAthleteID() {
        Athlete athlete = new Athlete("1111", "athlete");
        assertEquals("1111", athlete.getAthleteID());

        Athlete next = new Athlete("jonas");
        int athleteNum = Integer.parseInt(athlete.getAthleteID());
        int nextNum = Integer.parseInt(next.getAthleteID());
        assertTrue(nextNum >= athleteNum + 1);
    }

    @Test
    void storageConstructor_nonNumericId_doesNotCrash() {
        Athlete athlete = new Athlete("ABCD", "weird");
        assertEquals("ABCD", athlete.getAthleteID());

        Athlete next = new Athlete("Next");
        assertNotNull(next.getAthleteID());
    }

    @Test
    void toString_includesFlagColorWhenSet() {
        Athlete athlete = new Athlete("jonas");
        assertTrue(athlete.toString().contains("[ ]"));
        athlete.setFlagColor("red");
        assertTrue(athlete.toString().contains("[red]"));
    }

    @Test
    void flagColor_setAndGet() {
        Athlete athlete = new Athlete("jonas");
        assertEquals("", athlete.getFlagColor());
        athlete.setFlagColor("green");
        assertEquals("green", athlete.getFlagColor());
    }

    @Test
    void addSession_appendsToList() {
        Athlete athlete = new Athlete("jonas");
        Session session1 = new Session(0, "Leg day");
        Session session2 = new Session(1, "Push day");

        athlete.addSession(session1);
        athlete.addSession(session2);

        ArrayList<Session> sessions = athlete.getSessions();
        assertEquals(2, sessions.size());
        assertSame(session1, sessions.get(0));
        assertSame(session2, sessions.get(1));
    }

    @Test
    void getSessionID_incrementsEachCall_andSetSessionIdCounterWorks() {
        Athlete athlete = new Athlete("jonas");
        int id1 = athlete.getSessionID();
        int id2 = athlete.getSessionID();
        assertEquals(id1 + 1, id2);

        athlete.setSessionIdCounter(10);
        assertEquals(11, athlete.getSessionID());
    }

    @Test
    void getAchijonasmentScore_returnsZeroWhenNoCompletions() {
        Athlete athlete = new Athlete("jonas");
        Session session = new Session(0, "Core");
        session.getExercises().add(new Exercise(0, "Situps", 3, 15));
        session.getExercises().add(new Exercise(1, "Plank", 1, 60));
        athlete.addSession(session);

        assertEquals(0, athlete.getAchievementScore());
    }

    @Test
    void removeSession_existingId_removesCorrectly() throws InvalidSessionException {
        Athlete athlete = new Athlete("jonas");
        Session session1 = new Session(0, "Warmup");
        Session session2 = new Session(1, "Cooldown");
        athlete.addSession(session1);
        athlete.addSession(session2);

        athlete.removeSession(session1.getSessionIdString());
        assertEquals(1, athlete.getSessions().size());
        assertEquals(session2, athlete.getSessions().get(0));
    }

    @Test
    void removeSession_nonexistentId_throwsInvalidSessionException() {
        Athlete athlete = new Athlete("jonas");
        Session session = new Session(0, "Legs");
        athlete.addSession(session);

        InvalidSessionException ex = assertThrows(InvalidSessionException.class,
                () -> athlete.removeSession("999"));
        assertEquals("Session with ID 'Session not found: 999' could not be found.", ex.getMessage());
    }

    @Test
    void getAchievementScore_exerciseCompleted_onlyIncrements() {
        Athlete athlete = new Athlete("jonas");
        Session session = new Session(0, "Core");
        Exercise exercise1 = new Exercise(0, "Situps", 3, 15);
        Exercise exercise2 = new Exercise(1, "Plank", 1, 60);
        exercise1.setCompleted();
        session.getExercises().add(exercise1);
        session.getExercises().add(exercise2);
        athlete.addSession(session);

        assertEquals(1, athlete.getAchievementScore(),
                "One completed exercise should contribute +1");
    }

    @Test
    void getAchievementScore_sessionCompleted_incrementsForSession() {
        Athlete athlete = new Athlete("jonas");
        Session session = new Session(0, "Core");
        session.setCompleted();
        athlete.addSession(session);

        assertEquals(1, athlete.getAchievementScore(),
                "Completed session should contribute +1 even with no completed exercises");
    }

    @Test
    void getAchievementScore_bothCompleted_countingExercisesPlusSession() {
        Athlete athlete = new Athlete("jonas");
        Session session = new Session(0, "Core");

        Exercise exercise1 = new Exercise(0, "Pushups", 3, 10);
        exercise1.setCompleted();

        Exercise exercise2 = new Exercise(1, "Squats", 4, 8);
        exercise2.setCompleted();

        session.getExercises().add(exercise1);
        session.getExercises().add(exercise2);
        session.setCompleted();

        athlete.addSession(session);

        assertEquals(3, athlete.getAchievementScore(),
                "Two completed exercises (+2) + completed session (+1) = 3");
    }

    @Test
    void toString_handlesNullFlagColor() {
        Athlete athlete = new Athlete("jonas");
        athlete.setFlagColor(null);
        assertTrue(athlete.toString().contains("[ ]"),
                "Null flagColor should render as a single space flag");
    }
}
