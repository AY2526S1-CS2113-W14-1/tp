package seedu.fitnessone.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class ViewAthleteCommandTest {

    private static class CoachStub extends Coach {
        private final Athlete athlete;

        public CoachStub() {
            athlete = new Athlete("jonas hardwell");

            Session session1 = new Session(1, "chest");
            session1.addExercise("bench press", 5, 3);
            session1.addExercise("cable-press", 5, 15);
            session1.addExercise("leg-press", 5, 15);

            Session session2 = new Session(2, "legs");
            session2.addExercise("squat", 5, 5);

            athlete.addSession(session1);
            athlete.addSession(session2);
        }

        @Override
        public Athlete accessAthleteID(String athleteID) throws InvalidAthleteException {
            if (!athlete.getAthleteID().equals(athleteID)) {
                throw new InvalidAthleteException("Athlete not found");
            }
            return athlete;
        }
    }

    private static class UiStub extends Ui {
        List<String> printedLines = new ArrayList<>();

        @Override
        public void println(String message) {
            printedLines.add(message);
        }

        @Override
        public void divider() {
            printedLines.add("=========================================================");
        }
    }

    private CoachStub coach;
    private UiStub ui;

    @BeforeEach
    public void setUp() {
        coach = new CoachStub();
        ui = new UiStub();
    }

    @Test
    public void viewAthlete_validID_printsCorrectly() throws InvalidCommandException {
        ViewAthleteCommand cmd = new ViewAthleteCommand("0001", true);
        cmd.execute(coach, ui);

        assertEquals("Athlete ID: 0001", ui.printedLines.get(1));
        assertEquals("Athlete Name: jonas hardwell", ui.printedLines.get(2));
        assertEquals("Sessions: 2", ui.printedLines.get(3));
        assertEquals("List:", ui.printedLines.get(4));

        assertEquals("   Session 1. | Notes: chest", ui.printedLines.get(5));
        assertEquals("   Exercises:", ui.printedLines.get(6));
        assertEquals("      1. | Notes: bench press | sets x reps: 5 x 3", ui.printedLines.get(7));
        assertEquals("      2. | Notes: cable-press | sets x reps: 5 x 15", ui.printedLines.get(8));
        assertEquals("      3. | Notes: leg-press | sets x reps: 5 x 15", ui.printedLines.get(9));

        assertEquals("   Session 2. | Notes: legs", ui.printedLines.get(10));
        assertEquals("   Exercises:", ui.printedLines.get(11));
        assertEquals("      1. | Notes: squat | sets x reps: 5 x 5", ui.printedLines.get(12));
    }

    @Test
    public void viewAthlete_invalidID_throwsException() {
        assertThrows(InvalidCommandException.class, () -> new ViewAthleteCommand("9999").execute(coach, ui));
    }
}
