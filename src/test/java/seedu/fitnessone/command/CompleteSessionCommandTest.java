package seedu.fitnessone.command;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompleteSessionCommandTest {
    private static class UiStub extends Ui {
        String lastPrinted = null;

        @Override
        public void printWithDivider(String message) {
            lastPrinted = message;
        }

    }

    @Test
    public void execute_validCompletion_completesSessionAndPrints()
            throws InvalidCommandException, InvalidAthleteException, InvalidSessionException {
        Coach coachTest = new Coach();
        CompleteSessionCommandTest.UiStub uiStub = new CompleteSessionCommandTest.UiStub();
        String athleteName = "Jonas Hardwell";

        NewAthleteCommand newAthlete = new NewAthleteCommand("newathlete " + athleteName);
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        NewSessionCommand newSession = new NewSessionCommand("NewSession " + athleteId + " Legs");
        newSession.execute(coachTest, uiStub);

        String sessionId = "001";

        CompleteSessionCommand completeCommand =
                new CompleteSessionCommand("completeSession " + athleteId + " " + sessionId);
        completeCommand.execute(coachTest, uiStub);

        assertEquals("Session (ID: " + sessionId + ") completed by " + athleteName
                + " (ID: " + athleteId + ").", uiStub.lastPrinted);
    }

    @Test
    public void execute_invalidCompletionWithIncorrectSessionId_throwsInvalidSessionException()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        CompleteSessionCommandTest.UiStub uiStub = new CompleteSessionCommandTest.UiStub();
        String athleteName = "Jonas Hardwell";

        NewAthleteCommand newAthlete = new NewAthleteCommand("newAthlete " + athleteName);
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        NewSessionCommand newSession = new NewSessionCommand("NewSession " + athleteId + " Legs");
        newSession.execute(coachTest, uiStub);

        CompleteSessionCommand completeCommand =
                new CompleteSessionCommand("completeSession " + athleteId + " 999");
        InvalidSessionException exception = assertThrows(InvalidSessionException.class, () -> {
            completeCommand.execute(coachTest, uiStub);
        });
        assertEquals("Session with ID '999' could not be found.", exception.getMessage());
    }

    @Test
    public void execute_invalidCompletionWithIncorrectAthleteId_throwsInvalidAthleteException()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        CompleteSessionCommandTest.UiStub uiStub = new CompleteSessionCommandTest.UiStub();
        String athleteName = "Jonas Hardwell";

        NewAthleteCommand newAthlete = new NewAthleteCommand("newAthlete " + athleteName);
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        NewSessionCommand newSession = new NewSessionCommand("NewSession " + athleteId + " Legs");
        newSession.execute(coachTest, uiStub);

        CompleteSessionCommand completeCommand =
                new CompleteSessionCommand("completeSession 0100 001");
        InvalidAthleteException exception = assertThrows(InvalidAthleteException.class, () -> {
            completeCommand.execute(coachTest, uiStub);
        });
        assertEquals("Athlete with ID '0100' does not exist.\nTip: Use /listAthletes " +
                "to see all available athletes.", exception.getMessage());
    }
}
