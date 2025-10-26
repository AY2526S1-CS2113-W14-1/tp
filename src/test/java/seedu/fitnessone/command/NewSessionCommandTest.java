package seedu.fitnessone.command;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewSessionCommandTest {

    private static class UiStub extends Ui {
        String lastPrinted = null;

        @Override
        public void printWithDivider(String message) {
            lastPrinted = message;
        }

        // Implement other Ui methods as needed
    }

    @Test
    public void constructor_validInput_createsNewSession() throws InvalidCommandException {
        String input = "NewSession 0001 Legs";
        NewSessionCommand command = new NewSessionCommand(input);
        assertNotNull(command);
    }

    @Test
    public void constructor_validInputWithoutTrainingNotes_createsNewSession() throws InvalidCommandException {
        String input = "NewSession 0001 0001";
        NewSessionCommand command = new NewSessionCommand(input);
        assertNotNull(command);
    }

    @Test
    public void constructor_withoutTrainingNote_createsNewSession() {
        String input = "NewSession 0001";
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> {
            new NewSessionCommand(input);
        });
        assertEquals("Invalid input, try /NewSession <Athlete ID> <Session Notes>", exception.getMessage());
    }

    @Test
    public void constructor_missingAthleteId_throwsInvalidAthleteException() {
        String input = "NewSession Legs ";
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, () -> {
            new NewSessionCommand(input);
        });
        assertEquals("Invalid format: Athlete ID must contain only digits.", exception.getMessage());
    }

    @Test
    public void execute_validSession_addsSessionAndPrints()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();
        String athleteName = "John Doe";
        String input = "new athlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachTest, uiStub);
        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        String inputNewSession = "NewSession "+ athleteId +" Legs";
        NewSessionCommand sessionCommand = new NewSessionCommand(inputNewSession);
        sessionCommand.execute(coachTest, uiStub);
        assertEquals(2, coachTest.accessAthlete("John Doe").getSessionID());
        assertEquals("New session created:\n" +
                " Athlete Name: John Doe | ID: "+athleteId+"\n" +
                "\n" +
                "Session ID: 001\n" +
                "Session Description: Legs\n", uiStub.lastPrinted);
    }
    @Test
    public void execute_validSessionWithoutTrainNote_addsSessionAndPrints()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();
        String athleteName = "John Doe";
        String input = "new athlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        String inputNewSession = "NewSession "+ athleteId + " "+ athleteId;
        NewSessionCommand sessionCommand = new NewSessionCommand(inputNewSession);
        sessionCommand.execute(coachTest, uiStub);
        assertEquals(2, coachTest.accessAthlete("John Doe").getSessionID());
        assertEquals("New session created:\n" +
                " Athlete Name: John Doe | ID: "+athleteId+"\n" +
                "\n" +
                "Session ID: 001\n" +
                "Session Description: EMPTY. Add sessions notes with: /UpdateSessionNote <athlete> <session> <notes>\n",
                uiStub.lastPrinted);
    }

    @Test
    public void execute_invalidSession1_addsSessionAndPrints()
            throws InvalidCommandException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();
        String athleteName = "John Doe";
        String input = "new athlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachTest, uiStub);

        String inputNewSession = "NewSession 0002 blah";
        NewSessionCommand sessionCommand = new NewSessionCommand(inputNewSession);

        InvalidAthleteException exception = assertThrows(InvalidAthleteException.class, () -> {
            sessionCommand.execute(coachTest, uiStub);
        });
        assertEquals("Athlete not found: 0002",  exception.getMessage());
    }
}
