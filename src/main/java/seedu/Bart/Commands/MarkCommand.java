
/**
 * Represents a command to mark a task as done in the task list.
 * Parses user input for the index and marks the task when executed.
 */
package seedu.Bart.Commands;

import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

public class MarkCommand implements Command {
    private final int index;


    /**
     * Constructs a MarkCommand by parsing the input for the task index.
     * @param trimmedInput The user input string after the command keyword.
     * @throws InvalidCommandException if the index is not a valid number or negative.
     */
    public MarkCommand(String trimmedInput) throws InvalidCommandException{
        try {
            String numberStr = trimmedInput.substring(5).trim();
            this.index = Integer.parseInt(numberStr) - 1;
            if (index < 0) {
                throw new InvalidCommandException("Index must be positive.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid number format after 'mark'. Please enter a valid index.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException("No index provided after 'mark'.");
        }
    }


    /**
     * Executes the command to mark a task as done in the task list and display confirmation.
     * @param tasks The task list to update.
     * @param ui The UI for displaying output.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.markItem(index - 1);
        ui.printWithDivider("Marked item " + index);
    }

    /**
     * Indicates whether this command should exit the application.
     * @return false, as marking does not exit the app.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
