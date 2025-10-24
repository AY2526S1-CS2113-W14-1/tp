package seedu.fitnessone.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Ui;

class ViewExerciseCommandTest {

    private static class CoachStub extends Coach {
        Athlete testAthlete;
        Session testSession;

        public CoachStub() {
            testAthlete = new Athlete("Jonas Hardwell");
            testSession = new Session(1, "chest");
            testSession.addExercise("bench press", 5, 3);
            testSession.addExercise("cable-press", 5, 15);
            testSession.addExercise("leg-press", 5, 15);
            testAthlete.addSession(testSession);
        }

        @Override
        public Athlete accessAthleteID(String athleteID) throws InvalidAthleteException {
            if (athleteID.equals(testAthlete.getAthleteID())) {
                return testAthlete;
            }
            throw new InvalidAthleteException("Athlete not found");
        }

        @Override
        public Session accessSessionID(Athlete athlete, String sessionID) throws InvalidSessionException {
            if (sessionID.equals(testSession.getSessionIdString())) {
                return testSession;
            }
            throw new InvalidSessionException("Session not found");
        }
    }

    private static class UiStub extends Ui {
        StringBuilder output = new StringBuilder();

        @Override
        public void println(String message) {
            output.append(message).append("\n");
        }

        @Override
        public void divider() {
            output.append("=========================================================\n");
        }
    }

    @Test
    void viewExercise_validIDs_printsCorrectly() throws InvalidCommandException {
        CoachStub coach = new CoachStub();
        UiStub ui = new UiStub();

        ViewExerciseCommand command = new ViewExerciseCommand(
                coach.testAthlete.getAthleteID(),
                coach.testSession.getSessionIdString(),
                true
        );

        command.execute(coach, ui);

        String out = ui.output.toString();
        assertTrue(out.contains("Athlete ID: " + coach.testAthlete.getAthleteID()));
        assertTrue(out.contains("Athlete Name: " + coach.testAthlete.getAthleteID()));
        assertTrue(out.contains("Session ID: " + coach.testSession.getSessionIdString()));
        assertTrue(out.contains("Session Note: " + coach.testSession.getTrainingNotes()));
        assertTrue(out.contains("bench press") && out.contains("cable-press") && out.contains("leg-press"));
    }

    @Test
    void viewExercise_invalidAthleteID_throwsException() {
        CoachStub coach = new CoachStub();
        UiStub ui = new UiStub();

        assertThrows(InvalidCommandException.class, () -> {
            ViewExerciseCommand command = new ViewExerciseCommand("9999", "001", true);
            command.execute(coach, ui);
        });
    }

    @Test
    void viewExercise_invalidSessionID_throwsException() {
        CoachStub coach = new CoachStub();
        UiStub ui = new UiStub();

        assertThrows(InvalidCommandException.class, () -> {
            ViewExerciseCommand command = new ViewExerciseCommand(coach.testAthlete.getAthleteID(), " 999", true);
            command.execute(coach, ui);
        });
    }
}
