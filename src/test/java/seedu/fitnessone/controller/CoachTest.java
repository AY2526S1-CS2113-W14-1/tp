package seedu.fitnessone.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.exception.AthleteLimitReachedException;
import seedu.fitnessone.exception.SessionLimitReachedException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoachTest {

    private Coach coach;

    // Ui stub
    /*
    UiStub is our homemade small test double (a stub) that extends the real Ui class
    and captures UI output into a StringBuilder instead of printing
    to the console. It overrides methods like divider(), print(),
    println() and printWithDivider() to append to the log, and
    exposes out() to read the captured output.
     */
    static class UiStub extends Ui {
        final StringBuilder log = new StringBuilder();

        @Override
        public void divider() {
            log.append("---\n");
        }

        @Override
        public void print(String input) {
            log.append(input);
        }

        @Override
        public void println(String input) {
            log.append(input).append("\n");
        }

        @Override
        public void printWithDivider(String input) {
            log.append(input).append("\n");
        }

        String out() {
            return log.toString();
        }
    }

    @BeforeEach
    void setUp() {
        coach = new Coach();
    }

    // addAthlete / getAthletes / newAthlete / deleteAthlete
    @Test
    void newAthlete_addsDeleteAthlete_removes() throws InvalidAthleteException, AthleteLimitReachedException {
        int before = coach.getAthletes().size();
        String output = coach.newAthlete("jonas hardwell");
        assertNotNull(output);
        assertEquals(before + 1, coach.getAthletes().size());

        Athlete athlete = coach.accessAthlete("jonas hardwell");
        String output2 = coach.deleteAthlete(athlete.getAthleteID());
        assertEquals(output, output2);
        assertEquals(before, coach.getAthletes().size());
    }

    @Test
    void deleteAthlete_notFound_throws() {
        assertThrows(InvalidAthleteException.class, () -> coach.deleteAthlete("9999"));
    }

    // addSessionToAthlete / deleteSessionFromAthlete
    @Test
    void addSessionToAthlete_deleteSessionFromAthlete() throws InvalidSessionException, InvalidAthleteException,
            AthleteLimitReachedException, SessionLimitReachedException {
        coach.newAthlete("jonas hardwell");
        Athlete athlete = coach.accessAthlete("jonas hardwell");
        String id = athlete.getAthleteID();

        Session session = coach.addSessionToAthlete(id, "Legs");
        assertNotNull(session);
        assertTrue(athlete.getSessions().contains(session));
        assertEquals("Legs", session.getTrainingNotes());

        // delete
        coach.deleteSessionFromAthlete(id, session.getSessionIdString());
        assertFalse(athlete.getSessions().contains(session));
    }

    @Test
    void addSessionToAthlete_athleteMissing_throws() {
        assertThrows(InvalidAthleteException.class, () -> coach.addSessionToAthlete("0001", "Notes"));
    }

    @Test
    void deleteSessionFromAthlete_athleteMissing_throws() {
        assertThrows(InvalidAthleteException.class, () -> coach.deleteSessionFromAthlete("0001", "001"));
    }

    // printAthletes
    @Test
    void printAthletes_emptyNonEmpty() throws AthleteLimitReachedException {
        UiStub ui = new UiStub();
        coach.printAthletes(ui);
        assertTrue(ui.out().startsWith("---"));
        assertTrue(ui.out().contains("\n\n"));

        coach.newAthlete("jonas");
        coach.newAthlete("philip");

        UiStub ui2 = new UiStub();
        coach.printAthletes(ui2);
        String out = ui2.out();
        assertTrue(out.contains("1. "));
        assertTrue(out.contains("2. "));
        assertTrue(out.endsWith("---\n"));
    }

    // accessAthlete / accessAthleteID
    @Test
    void accessAthleteID_foundMissing() throws InvalidAthleteException, AthleteLimitReachedException {
        coach.newAthlete("jonas hardwell");
        Athlete athlete = coach.accessAthlete("jonas hardwell");
        assertSame(athlete, coach.accessAthleteID(athlete.getAthleteID()));

        assertThrows(InvalidAthleteException.class, () -> coach.accessAthlete("Nobody"));
        assertThrows(InvalidAthleteException.class, () -> coach.accessAthleteID("9999"));
    }

    // accessSessionID / accessExerciseID
    @Test
    void accessSessionID_foundMissing() throws InvalidAthleteException, InvalidSessionException,
            AthleteLimitReachedException, SessionLimitReachedException {
        coach.newAthlete("jonas hardwell");
        Athlete athlete = coach.accessAthlete("jonas hardwell");
        String id = athlete.getAthleteID();

        Session session1 = coach.addSessionToAthlete(id, "Push");
        Session session2 = coach.addSessionToAthlete(id, "Pull");

        assertSame(session1, coach.accessSessionID(athlete, session1.getSessionIdString()));
        assertSame(session2, coach.accessSessionID(athlete, session2.getSessionIdString()));
        assertThrows(InvalidSessionException.class, () -> coach.accessSessionID(athlete, "999"));
    }

    @Test
    void accessExerciseID_foundMissing() throws InvalidAthleteException, InvalidExerciseException,
            AthleteLimitReachedException, SessionLimitReachedException {
        coach.newAthlete("jonas hardwell");
        Athlete athlete = coach.accessAthlete("jonas hardwell");
        String id = athlete.getAthleteID();

        Session session = coach.addSessionToAthlete(id, "Push");
        // add an exercise using the real model API
        ArrayList<Exercise> list = session.getExercises();
        // Assuming Exercise has (index, description, sets, reps)
        Exercise e1 = new Exercise(0, "Pushups", 3, 10);
        Exercise e2 = new Exercise(1, "Squats", 4, 8);
        list.add(e1);
        list.add(e2);

        assertSame(e1, coach.accessExerciseID(session, e1.getExerciseIDString()));
        assertSame(e2, coach.accessExerciseID(session, e2.getExerciseIDString()));
        assertThrows(InvalidExerciseException.class, () -> coach.accessExerciseID(session, "99"));
    }

    // deleteExerciseFromSession (normal + error path)
    @Test
    void deleteExerciseFromSession_removesNormally() throws
            InvalidAthleteException, InvalidSessionException, InvalidExerciseException,
            AthleteLimitReachedException, SessionLimitReachedException {
        coach.newAthlete("jonas hardwell");
        Athlete athlete = coach.accessAthlete("jonas hardwell");
        String id = athlete.getAthleteID();
        Session session = coach.addSessionToAthlete(id, "core");

        Exercise exercise = new Exercise(0, "twists", 1, 60);
        session.getExercises().add(exercise);

        coach.deleteExerciseFromSession(session, exercise);

        assertTrue(session.getExercises().isEmpty());
    }


    @Test
    void flagAthlete_success_errors() throws InvalidAthleteException, AthleteLimitReachedException {
        coach.newAthlete("jonas hardwell");
        Athlete athlete = coach.accessAthlete("jonas hardwell");
        String id = athlete.getAthleteID();

        // case-upperlower
        coach.flagAthlete(id.toLowerCase(), "red");

        // null / blank
        InvalidAthleteException ex1 = assertThrows(InvalidAthleteException.class,
                () -> coach.flagAthlete(null, "blue"));
        assertEquals("Athlete with ID 'Athlete ID cannot be empty.' does not exist.\nTip:" +
                " Use /listAthletes to see all available athletes.", ex1.getMessage());

        InvalidAthleteException ex2 = assertThrows(InvalidAthleteException.class,
                () -> coach.flagAthlete("   ", "blue"));
        assertEquals("Athlete with ID 'Athlete ID cannot be empty.' does not exist.\nTip:" +
                " Use /listAthletes to see all available athletes.", ex2.getMessage());

        // not found
        InvalidAthleteException ex3 = assertThrows(InvalidAthleteException.class,
                () -> coach.flagAthlete("9999", "blue"));
        assertEquals("Athlete with ID '9999' does not exist.\nTip:" +
                " Use /listAthletes to see all available athletes.", ex3.getMessage());
    }

    // leaderboardConstruct
    @Test
    void leaderboardConstruct_emptyNonEmpty() throws AthleteLimitReachedException {
        assertEquals("No athletes found, add some athletes and let them do workout!!",
                coach.leaderboardConstruct());

        coach.newAthlete("philip");
        coach.newAthlete("gordon");
        coach.newAthlete("halil");
        String out = coach.leaderboardConstruct();

        assertTrue(out.contains(String.format("%-15s %-7s %3s", "athleteName", "athleteId", "score")));
        assertTrue(out.contains("philip") || out.contains("gordon") || out.contains("halil"));
    }
}
