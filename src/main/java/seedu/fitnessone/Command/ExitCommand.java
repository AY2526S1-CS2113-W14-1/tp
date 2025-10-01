
/**
 * Represents a command to exit the application.
 * When executed, displays a farewell message and signals the app to terminate.
 */
package seedu.fitnessone.Command;


import seedu.fitnessone.Controller.Coach;
import seedu.fitnessone.Exception.InvalidCommandException;
import seedu.fitnessone.UI.Ui;

public class ExitCommand implements Command {


    /**
     * Executes the command to display a farewell message.
     * @param _coach The task list (unused).
     * @param _ui The UI for displaying output.
     */
    @Override
    public void execute(Coach _coach, Ui _ui) throws InvalidCommandException {
        _ui.printWithDivider("Bye. Hope to see you again soon!");
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
