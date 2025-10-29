package seedu.fitnessone.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAthleteCommandTest {
    static class UiStub extends Ui {
        String previousPrint = null;
        final StringBuilder out = new StringBuilder();

        @Override
        public void printWithDivider(String message) {
            previousPrint = message;
            out.append(message).append("\n");
        }

        @Override
        public void divider() {
            out.append("---\n");
        }

        @Override
        public void println(String message) {
            out.append(message).append("\n");
        }

        String getOutput() {
            return out.toString();
        }
    }

    private Coach coach;
    private UiStub ui;

    @BeforeEach
    void setUp() {
        coach = new Coach();
        ui = new UiStub();
    }

    // constructor check
    @Test
    void constructor_validInput_createsCommand() throws InvalidCommandException {
        DeleteAthleteCommand cmd = new DeleteAthleteCommand("deleteAthlete 0001");
        assertNotNull(cmd);
    }

    // Successful deletion
    @Test
    void execute_validDeletion_deletesAthleteAndPrints() throws InvalidAthleteException, InvalidCommandException {
        // Arrange
        String name = " jonas hardwell";
        coach.newAthlete(name);
        String athleteId = coach.accessAthlete(name).getAthleteID();
        String input = "deleteAthlete " + athleteId;

        DeleteAthleteCommand cmd = new DeleteAthleteCommand(input);

        // Act
        cmd.execute(coach, ui);

        // Assert
        assertTrue(ui.previousPrint.contains("Deleted athlete with ID " + athleteId));
        assertThrows(InvalidAthleteException.class, () -> coach.accessAthleteID(athleteId),
                "Athlete should be deleted from coach");
    }

    // Delete nonexistent athlete
    @Test
    void execute_invalidAthleteId_throwsInvalidCommandException() throws InvalidCommandException {
        String input = "deleteAthlete 9999";
        DeleteAthleteCommand cmd = new DeleteAthleteCommand(input);

        InvalidCommandException error = assertThrows(InvalidCommandException.class, () ->
                cmd.execute(coach, ui));
        assertTrue(error.getMessage().contains("athleteID 9999 not found"));
    }

    // Invalid format (throw)
    @Test
    void execute_invalidFormat_throwsInvalidCommandException() throws InvalidCommandException {
        String input = "deleteAthlete 0a01";
        DeleteAthleteCommand cmd = new DeleteAthleteCommand(input);

        InvalidCommandException error = assertThrows(InvalidCommandException.class, () ->
                cmd.execute(coach, ui));
        assertEquals("Invalid format: Athlete ID must contain only digits.", error.getMessage());
    }

    @Test
    void execute_missingAthleteId_throwsInvalidCommandException() throws InvalidCommandException {
        DeleteAthleteCommand cmd = new DeleteAthleteCommand("deleteAthlete   ");
        InvalidCommandException error = assertThrows(InvalidCommandException.class, () ->
                cmd.execute(coach, ui));
        assertEquals("Input is empty. Check if there's athletes or sessions created.", error.getMessage());
    }

    @Test
    void help_printsFullUsageInfo() throws InvalidCommandException {
        DeleteAthleteCommand cmd = new DeleteAthleteCommand("deleteAthlete 0001");
        cmd.help(ui);
        String out = ui.getOutput();

        assertTrue(out.contains("Command: /deleteathlete"));
        assertTrue(out.contains("Usage: /deleteathlete <Athlete ID>"));
        assertTrue(out.contains("Description: Deletes an athlete and all associated data"));
        assertTrue(out.contains("Example: /deleteathlete 0001"));
        assertTrue(out.contains("Note: Warning: This action cannot be undone! Athlete ID = 4 digits"));
    }

    @Test
    void isExit_returnsFalse() throws InvalidCommandException {
        DeleteAthleteCommand cmd = new DeleteAthleteCommand("deleteAthlete 0001");
        assertFalse(cmd.isExit());
    }
}
