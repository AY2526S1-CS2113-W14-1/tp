package seedu.fitnessone.command;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Ui;
import seedu.fitnessone.exception.AthleteLimitReachedException;
import seedu.fitnessone.exception.SessionLimitReachedException;
import seedu.fitnessone.exception.ExerciseLimitReachedException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewExerciseCommandTest {

    private static class UiStub extends Ui {
        private final StringBuilder out = new StringBuilder();

        @Override
        public void divider() {
            out.append("DIVIDER\n");
        }

        @Override
        public void println(String message) {
            out.append(message).append("\n");
        }

        @Override
        public void printWithDivider(String message) {
            out.append(message).append("\n");
        }

        public String getOutput() {
            return out.toString();
        }
    }

    @Test
    public void constructor_validInput_createsCommand() throws InvalidCommandException {
        String input = "NewExercise 0001 001 Pushups 5 5";
        NewExerciseCommand command = new NewExerciseCommand(input);
        assertNotNull(command);
    }

    @Test
    public void execute_validCreation_createsExerciseAndPrints()
            throws InvalidCommandException, InvalidAthleteException, InvalidSessionException, InvalidExerciseException,
            AthleteLimitReachedException, SessionLimitReachedException, ExerciseLimitReachedException {
        Coach coach = new Coach();
        UiStub ui = new UiStub();
        String athleteName = "Jonas Hardwell";

        // create athlete
        NewAthleteCommand newAthlete = new NewAthleteCommand("newAthlete " + athleteName);
        newAthlete.execute(coach, ui);

        String athleteId = coach.accessAthlete(athleteName).getAthleteID();

        // create session
        NewSessionCommand newSession = new NewSessionCommand("NewSession " + athleteId + " Chest");
        newSession.execute(coach, ui);

        // compute session id used by tests (consistent with other tests)
        String sessionId = "001";

        // add exercise
        NewExerciseCommand newExercise = new NewExerciseCommand(
                "NewExercise " + athleteId + " " + sessionId + " Pushups 5 5");
        newExercise.execute(coach, ui);

        List<?> exercises = coach.accessSessionID(coach.accessAthlete(athleteName), sessionId).getExercises();
        assertFalse(exercises.isEmpty(), "Expected at least one exercise after creation.");

        String exerciseId = coach.accessSessionID(coach.accessAthlete(athleteName), sessionId)
                .getExercises().get(0).getExerciseIDString();

        String out = ui.getOutput();
        assertTrue(out.contains("New exercise created!"));
        assertTrue(out.contains("Exercise (ID): " + exerciseId));
        assertTrue(out.contains("sets x reps: 5 x 5"));
    }

    @Test
    public void execute_invalidSetsOrReps_printsWarning()
            throws InvalidCommandException, InvalidAthleteException,
            AthleteLimitReachedException, SessionLimitReachedException {
        Coach coach = new Coach();
        UiStub ui = new UiStub();
        String athleteName = "Jonas Hardwell";

        new NewAthleteCommand("newAthlete " + athleteName).execute(coach, ui);
        String athleteId = coach.accessAthlete(athleteName).getAthleteID();
        new NewSessionCommand("NewSession " + athleteId + " Back").execute(coach, ui);

        String sessionId = "001";
        NewExerciseCommand newExercise = new NewExerciseCommand(
                "NewExercise " + athleteId + " " + sessionId + " Pushups five 5");

        InvalidCommandException ex = assertThrows(
                InvalidCommandException.class,
                () -> newExercise.execute(coach, ui)
        );
        assertEquals("Unknown command: 'Sets and reps must be integers.'. " +
                "Type /help to see available commands.", ex.getMessage());
    }

    @Test
    public void execute_missingFields_throwsInvalidCommandException()
            throws InvalidCommandException, InvalidAthleteException,
            AthleteLimitReachedException, SessionLimitReachedException {
        Coach coach = new Coach();
        UiStub ui = new UiStub();
        String athleteName = "Jonas Hardwell";

        new NewAthleteCommand("newAthlete " + athleteName).execute(coach, ui);
        String athleteId = coach.accessAthlete(athleteName).getAthleteID();
        new NewSessionCommand("NewSession " + athleteId + " Legs").execute(coach, ui);

        String sessionId = "001";
        // Missing reps
        NewExerciseCommand newExercise = new NewExerciseCommand(
                "NewExercise " + athleteId + " " + sessionId + " Squats 5");

        InvalidCommandException ex = assertThrows(
                InvalidCommandException.class,
                () -> newExercise.execute(coach, ui)
        );
        // Match the command’s actual message:
        assertEquals(
                "Unknown command: 'Invalid input. Please follow: /newexercise <Athlete ID> " +
                        "<Session ID> <Exercise Description> <set> <rep>'. Type /help to see available commands.",
                ex.getMessage()
        );
    }
}
