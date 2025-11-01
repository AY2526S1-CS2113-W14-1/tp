
/**
 * Represents a command to exit the application.
 * When executed, displays a farewell message and signals the app to terminate.
 */
package seedu.fitnessone.command;


import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class ExitCommand implements Command {
    private static final String COMMAND_WORD = "bye";
    private static final String DESCRIPTION = "Exit the application";
    private static final String USAGE = "bye";
    private final String inputString;
    public ExitCommand(String inputString) {
        this.inputString = inputString;
    }


    /**
     * Executes the command to display a farewell message.
     *
     * @param coachController The task list (unused).
     * @param view            The UI for displaying output.
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        if (inputString.split(" ").length > 1) {
            throw new InvalidCommandException(USAGE);
        }
        view.printWithDivider("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return true, as this command signals the app to terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void help(Ui view) {
    }
}
