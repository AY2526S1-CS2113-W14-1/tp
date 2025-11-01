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
    public void constructor_validInput_createsNewSession() {
        String input = "NewSession 0001 Legs";
        NewSessionCommand command = new NewSessionCommand(input);
        assertNotNull(command);
    }

    @Test
    public void constructor_validInputWithoutTrainingNotes_createsNewSession()  {
        String input = "NewSession 0001 0001";
        NewSessionCommand command = new NewSessionCommand(input);
        assertNotNull(command);
    }

    @Test
    public void constructor_withoutTrainingNote_createsNewSession()  {
        Coach coach = new Coach();
        UiStub ui = new UiStub();
        NewSessionCommand cmd = new NewSessionCommand("NewSession 0001");

        InvalidCommandException ex = assertThrows(InvalidCommandException.class,
                () -> cmd.execute(coach, ui));
        assertEquals("Unknown command: 'Invalid input, try /NewSession <Athlete ID>" +
                " <Session Notes>'. Type /help to see available commands.", ex.getMessage());
    }

    @Test
    public void constructor_missingAthleteId_throwsInvalidAthleteException() {
        Coach coach = new Coach();
        UiStub ui = new UiStub();
        NewSessionCommand cmd = new NewSessionCommand("NewSession Legs ");

        InvalidCommandException ex = assertThrows(InvalidCommandException.class,
                () -> cmd.execute(coach, ui));
        assertEquals("Unknown command: 'Invalid format: Athlete ID must contain only digits." +
                "'. Type /help to see available commands.", ex.getMessage());
    }

    @Test
    public void execute_validSession_addsSessionAndPrints()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();
        String athleteName = "jonas hardwell";
        String input = "newAthlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachTest, uiStub);
        String athleteId = coachTest.accessAthlete(athleteName).getAthleteID();
        String inputNewSession = "NewSession "+ athleteId +" Legs";
        NewSessionCommand sessionCommand = new NewSessionCommand(inputNewSession);
        sessionCommand.execute(coachTest, uiStub);
        assertEquals(2, coachTest.accessAthlete("jonas hardwell").getSessionID());
        assertEquals("New session created:\n" +
                "\tAthlete Name: jonas hardwell | ID: "+athleteId+"\n" +
                "\n" +
                "\tSession ID: 001\n" +
                "\tSession Description: Legs\n", uiStub.lastPrinted);
    }
    @Test
    public void execute_validSessionWithoutTrainNote_addsSessionAndPrints()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coach = new Coach();
        UiStub ui = new UiStub();

        new NewAthleteCommand("newAthlete jonas hardwell").execute(coach, ui);
        String athleteId = coach.accessAthlete("jonas hardwell").getAthleteID();

        NewSessionCommand cmd = new NewSessionCommand("NewSession " + athleteId + " " + athleteId);
        cmd.execute(coach, ui);

        assertEquals(2, coach.accessAthlete("jonas hardwell").getSessionID());
        assertEquals("New session created:\n" +
                        "\tAthlete Name: jonas hardwell | ID: " + athleteId + "\n\n" +
                        "\tSession ID: 001\n" +
                        "\tSession Description: " + athleteId + "\n", ui.lastPrinted);
    }

    @Test
    public void execute_invalidSession1_addsSessionAndPrints()
            throws InvalidCommandException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();
        String athleteName = "jonas hardwell";
        String input = "newAthlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachTest, uiStub);

        String inputNewSession = "NewSession 0002 blah";
        NewSessionCommand sessionCommand = new NewSessionCommand(inputNewSession);

        InvalidAthleteException exception = assertThrows(InvalidAthleteException.class, () -> {
            sessionCommand.execute(coachTest, uiStub);
        });
        assertEquals("Athlete with ID '0002' does not exist.\nTip:" +
                " Use /listAthletes to see all available athletes.", exception.getMessage());
    }
}
