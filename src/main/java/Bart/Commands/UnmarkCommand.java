
/**
 * Represents a command to unmark a task as done in the task list.
 * Parses user input for the index and unmarks the task when executed.
 */
package Bart.Commands;

import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

public class UnmarkCommand implements Command {
    private final int index;


    /**
     * Constructs an UnmarkCommand by parsing the input for the task index.
     * @param trimmedInput The user input string after the command keyword.
     * @throws InvalidCommandException if the index is not a valid number.
     */
    public UnmarkCommand(String trimmedInput) throws InvalidCommandException {
        try {
            String numberStr = trimmedInput.substring(7).trim();
            index = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid number format after 'mark'. Please enter a valid index.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException("No index provided after 'mark'.");
        }
    }


    /**
     * Executes the command to unmark a task as done in the task list and display confirmation.
     * @param tasks The task list to update.
     * @param ui The UI for displaying output.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.unmarkItem(index - 1);
        ui.printWithDivider("Unmarked item " + index);
    }

    /**
     * Indicates whether this command should exit the application.
     * @return false, as unmarking does not exit the app.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
