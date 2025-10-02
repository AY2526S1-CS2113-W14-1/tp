
/**
 * Represents a command to exit the application.
 * When executed, displays a farewell message and signals the app to terminate.
 */
package seedu.Bart.Commands;

import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

public class ExitCommand implements Command {


    /**
     * Executes the command to display a farewell message.
     * @param tasks The task list (unused).
     * @param ui The UI for displaying output.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidCommandException {
        ui.printWithDivider("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether this command should exit the application.
     * @return true, as this command signals the app to terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
