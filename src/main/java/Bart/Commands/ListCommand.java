
/**
 * Represents a command to list all tasks in the task list.
 * When executed, displays all tasks to the user.
 */
package Bart.Commands;

import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

public class ListCommand implements Command {

    /**
     * Executes the command to display all tasks in the task list.
     * @param tasks The task list to display.
     * @param ui The UI for displaying output.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.divider();
        tasks.printItems();
        ui.divider();
    }

    /**
     * Indicates whether this command should exit the application.
     * @return false, as listing does not exit the app.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
