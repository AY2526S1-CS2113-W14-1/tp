package seedu.fitnessone.command;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteSessionCommandTest {
    private static class UiStub extends Ui {
        String lastPrinted = null;

        @Override
        public void printWithDivider(String message) {
            lastPrinted = message;
        }

        // Implement other Ui methods as needed
    }

    @Test
    public void deleter_validInput_deleteSession() throws InvalidCommandException {
        String input = "deleteSession 0001 002";
        DeleteSessionCommand command = new DeleteSessionCommand(input);
        assertNotNull(command);
    }

    @Test
    public void deleter_missingSessionId_deleteSession() {
        String input = "NewSession 0001";
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> {
            new DeleteSessionCommand(input);
        });
        assertEquals("Invalid Command. Check if there's athletes or sessions created.", exception.getMessage());
    }

    @Test
    public void deleter_missingAthleteId_throwsInvalidAthleteException() {
        String input = "NewSession 0001";
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> {
            new NewSessionCommand(input);
        });
        assertEquals("Invalid input, try /NewSession <Athlete ID> <Session Notes>", exception.getMessage());
    }

    @Test
    public void execute_validDeletion_deleteSessionAndPrints()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        DeleteSessionCommandTest.UiStub uiStub = new DeleteSessionCommandTest.UiStub();
        String athleteName = "John Doe";
        String input = "new athlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachTest, uiStub);
        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        String inputNewSession = "NewSession "+ athleteId+" Legs";
        NewSessionCommand sessionCommand = new NewSessionCommand(inputNewSession);
        sessionCommand.execute(coachTest, uiStub);
        String sessionId = String.valueOf(coachTest.accessAthlete(athleteName).getSessionID() - 1);
        String inputDeleteSession = "deleteSession "+athleteId+" 00"+sessionId;
        DeleteSessionCommand sessionDeleteCommand = new DeleteSessionCommand(inputDeleteSession);
        sessionDeleteCommand.execute(coachTest, uiStub);
        assertEquals("Session 00"+sessionId+" deleted for "+ athleteId, uiStub.lastPrinted);
    }
    @Test
    public void execute_invalidDeletionWithIncorrectSessionId_deleteSessionAndPrints()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        DeleteSessionCommandTest.UiStub uiStub = new DeleteSessionCommandTest.UiStub();
        String athleteName = "John Doe";
        String input = "new athlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        String inputNewSession = "NewSession "+ athleteId +" Legs";
        NewSessionCommand sessionCommand = new NewSessionCommand(inputNewSession);
        sessionCommand.execute(coachTest, uiStub);

        String inputDeleteSession = "deleteSession "+athleteId+" 999";
        DeleteSessionCommand sessionDeleteCommand = new DeleteSessionCommand(inputDeleteSession);
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> {
            sessionDeleteCommand.execute(coachTest, uiStub);
        });
        assertEquals("Session not found: 999", exception.getMessage());
    }
    @Test
    public void execute_invalidDeletionWithIncorrectAthleleId_deleteSessionAndPrints()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        DeleteSessionCommandTest.UiStub uiStub = new DeleteSessionCommandTest.UiStub();
        String athleteName = "John Doe";
        String input = "new athlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        String inputNewSession = "NewSession "+ athleteId +" Legs";
        NewSessionCommand sessionCommand = new NewSessionCommand(inputNewSession);
        sessionCommand.execute(coachTest, uiStub);

        String inputDeleteSession = "deleteSession 0100 001";
        DeleteSessionCommand sessionDeleteCommand = new DeleteSessionCommand(inputDeleteSession);
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> {
            sessionDeleteCommand.execute(coachTest, uiStub);
        });
        assertEquals("Athlete not found: 0100", exception.getMessage());
    }
}
