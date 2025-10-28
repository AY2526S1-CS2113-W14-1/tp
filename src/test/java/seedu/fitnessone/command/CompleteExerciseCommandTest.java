// java
package seedu.fitnessone.command;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompleteExerciseCommandTest {
    private static class UiStub extends Ui {
        String lastPrinted = null;

        @Override
        public void printWithDivider(String message) {
            lastPrinted = message;
        }

        // Implement other Ui methods as needed
    }

    @Test
    public void constructor_validInput_createsCommand() throws InvalidCommandException {
        String input = "completeExercise 0001 001 01";
        CompleteExerciseCommand command = new CompleteExerciseCommand(input);
        assertNotNull(command);
    }

    @Test
    public void execute_validCompletion_completesExerciseAndPrints()
            throws InvalidCommandException, InvalidAthleteException, InvalidSessionException, InvalidExerciseException {
        Coach coachTest = new Coach();
        CompleteExerciseCommandTest.UiStub uiStub = new CompleteExerciseCommandTest.UiStub();
        String athleteName = "Jonas Hardwell";

        // create athlete
        NewAthleteCommand newAthlete = new NewAthleteCommand("newAthlete " + athleteName);
        newAthlete.execute(coachTest, uiStub);

        // get athlete id
        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();

        // create session
        NewSessionCommand newSession = new NewSessionCommand("NewSession " + athleteId + " Chest");
        newSession.execute(coachTest, uiStub);

        // compute session id (matches pattern used elsewhere in tests)
        String sessionId = "001";

        // add an exercise to the session
        NewExerciseCommand newExercise = new NewExerciseCommand("NewExercise " + athleteId + " " + sessionId + " Pushups 5 5");
        newExercise.execute(coachTest, uiStub);

        // retrieve the exercise id from the session (assumes first exercise is the one added)
        String exerciseId = coachTest.accessSessionID(coachTest.accessAthlete(athleteName), sessionId)
                .getExercises().get(0).getExerciseIDString();

        // complete the exercise
        CompleteExerciseCommand completeCommand =
                new CompleteExerciseCommand("completeExercise " + athleteId + " " + sessionId + " " + exerciseId);
        completeCommand.execute(coachTest, uiStub);

        assertEquals("Exercise (ID: " + exerciseId + ") completed by " + athleteName
                + " (ID: " + athleteId + ").", uiStub.lastPrinted);
    }

    @Test
    public void execute_invalidCompletionWithIncorrectSessionId_throwsInvalidCommandException()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        CompleteExerciseCommandTest.UiStub uiStub = new CompleteExerciseCommandTest.UiStub();
        String athleteName = "Jonas Hardwell";

        NewAthleteCommand newAthlete = new NewAthleteCommand("newAthlete " + athleteName);
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        NewSessionCommand newSession = new NewSessionCommand("NewSession " + athleteId + " Legs");
        newSession.execute(coachTest, uiStub);

        CompleteExerciseCommand completeCommand =
                new CompleteExerciseCommand("completeExercise " + athleteId + " 999 01");
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> {
            completeCommand.execute(coachTest, uiStub);
        });
        assertEquals("Session not found: 999", exception.getMessage());
    }

    @Test
    public void execute_invalidCompletionWithIncorrectAthleteId_throwsInvalidCommandException()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        CompleteExerciseCommandTest.UiStub uiStub = new CompleteExerciseCommandTest.UiStub();
        String athleteName = "Jonas Hardwell";

        NewAthleteCommand newAthlete = new NewAthleteCommand("newAthlete " + athleteName);
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        NewSessionCommand newSession = new NewSessionCommand("NewSession " + athleteId + " Legs");
        newSession.execute(coachTest, uiStub);

        CompleteExerciseCommand completeCommand =
                new CompleteExerciseCommand("completeExercise 0100 001 01");
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> {
            completeCommand.execute(coachTest, uiStub);
        });
        assertEquals("Athlete not found: 0100", exception.getMessage());
    }
}
