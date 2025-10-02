
/**
 * Represents a command to add a deadline task to the task list.
 * Parses user input for deadline details and adds the deadline when executed.
 */
package seedu.Bart.Commands;

import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private final String description;
    private final String by;


    /**
     * Constructs a DeadlineCommand by parsing the input for description and due date.
     * @param trimmedInput The user input string after the command keyword.
     * @throws InvalidCommandException if the "/by" parameter is missing.
     */
    public DeadlineCommand(String trimmedInput) throws InvalidCommandException {
        String input = trimmedInput.substring(9).trim();
        int byIndex = input.indexOf(" /by ");
        if (byIndex == -1) {
            throw new InvalidCommandException("/by not specified!");
        }
        description = input.substring(0, byIndex);
        by = input.substring(byIndex + " /by ".length());
    }


    /**
     * Executes the command to add a deadline to the task list and display confirmation.
     * @param tasks The task list to add the deadline to.
     * @param ui The UI for displaying output.
     * @throws InvalidCommandException if the deadline cannot be added.
     * @throws DateTimeParseException if the date format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidCommandException, DateTimeParseException {
        String itemToString = tasks.addDeadline(description, by);
        ui.printWithDivider("deadline added." + System.lineSeparator() + "    " + itemToString);
    }

    /**
     * Indicates whether this command should exit the application.
     * @return false, as adding a deadline does not exit the app.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
