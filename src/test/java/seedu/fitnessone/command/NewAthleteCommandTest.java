package seedu.fitnessone.command;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewAthleteCommandTest {

    private static class CoachStub extends Coach {
        String lastAddedAthlete = null;

        @Override
        public String newAthlete(String athleteName) {
            lastAddedAthlete = athleteName;
            return athleteName;
        }

        // Implement other methods in Coach as no-op or throw UnsupportedOperationException
    }

    private static class UiStub extends Ui {
        String lastPrinted = null;

        @Override
        public void printWithDivider(String message) {
            lastPrinted = message;
        }

        // Implement other Ui methods as needed
    }

    @Test
    public void constructor_validInput_storesAthleteName() throws InvalidCommandException {
        String input = "new athlete John Doe";
        NewAthleteCommand command = new NewAthleteCommand(input);
        assertNotNull(command);
    }

    @Test
    public void constructor_missingName_throwsInvalidCommandException() throws InvalidCommandException {
        NewAthleteCommand cmd = new NewAthleteCommand("new athlete ");
        assertNotNull(cmd);

        NewAthleteCommandTest.CoachStub coach = new NewAthleteCommandTest.CoachStub();
        NewAthleteCommandTest.UiStub ui = new NewAthleteCommandTest.UiStub();
        InvalidCommandException ex = assertThrows(InvalidCommandException.class, () -> cmd.execute(coach, ui));
    assertEquals("Unknown command: 'Athlete name was not specified. Usage: /newathlete <Athlete Name>'." +
        " Type /help to see available commands.", ex.getMessage());
    }

    @Test
    public void execute_blankName_throwsInvalidCommandException() throws InvalidCommandException {
        NewAthleteCommand cmd = new NewAthleteCommand("new athlete      ");

        NewAthleteCommandTest.CoachStub coach = new NewAthleteCommandTest.CoachStub();
        NewAthleteCommandTest.UiStub ui = new NewAthleteCommandTest.UiStub();
        InvalidCommandException ex = assertThrows(InvalidCommandException.class, () -> cmd.execute(coach, ui));
        assertEquals("Unknown command: 'Athlete name was not specified. Usage: /newathlete <Athlete Name>'." +
                " Type /help to see available commands.", ex.getMessage());

    }

    @Test
    public void execute_validName_addsAthleteAndPrints() throws InvalidCommandException {
        CoachStub coachStub = new CoachStub();
        UiStub uiStub = new UiStub();
        String athleteName = "John Doe";
        String input = "new athlete " + athleteName;
        NewAthleteCommand command = new NewAthleteCommand(input);

        command.execute(coachStub, uiStub);

        assertEquals(athleteName, coachStub.lastAddedAthlete);
        assertEquals("Athlete added:" + System.lineSeparator() + "     " + athleteName, uiStub.lastPrinted);
    }
}
