
/**
 * Represents a command to delete a task from the task list.
 * Parses user input for the index and removes the task when executed.
 */
package seedu.Bart.Commands;

import Bart.Exceptions.BartException;
import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

public class DeleteCommand implements Command {
    private final int index;


    /**
     * Constructs a DeleteCommand by parsing the input for the task index.
     * @param trimmedInput The user input string after the command keyword.
     * @throws InvalidCommandException if the index is not a valid number.
     */
    public DeleteCommand(String trimmedInput) throws InvalidCommandException {
        try {
            String numberStr = trimmedInput.substring(7).trim();
            index = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid number format after 'mark'. Please enter a valid index.");
        }
    }


    /**
     * Executes the command to delete a task from the task list and display confirmation.
     * @param tasks The task list to delete from.
     * @param ui The UI for displaying output.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        String itemText = tasks.deleteItem(index - 1);
        ui.printWithDivider("Task Removed: " + itemText);
    }

    /**
     * Indicates whether this command should exit the application.
     * @return false, as deleting does not exit the app.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
