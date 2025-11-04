package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class ListAthleteCommand implements Command {
    private static final String COMMAND_WORD = "/listathletes";
    private static final String USAGE = "/listathletes";
    private static final String DESCRIPTION = "Lists all athletes in the system";
    private static final String EXAMPLE = "/listathletes";
    private static final String NOTE = null;

    private final String inputString;

    public ListAthleteCommand(String inputString) {
        this.inputString = inputString;
    }

    /**
     * Lists all athletes currently in the system.
     * Displays each athlete's ID, name, and achievement score in a formatted table.
     * Validates that no extra arguments are provided with the command.
     *
     * @param coachController The coach controller managing all athletes
     * @param view The UI component for displaying results
     * @throws InvalidCommandException If any arguments are provided with the command
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        if (!inputString.trim().equals(COMMAND_WORD)) {
            throw new InvalidCommandException("Usage: " + USAGE + ". This command does not take any arguments.");
        }
        coachController.printAthletes(view);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void help(Ui view) {
        view.divider();
        view.println("Command: " + COMMAND_WORD);
        view.println("Usage: " + USAGE);
        view.println("Description: " + DESCRIPTION);
        view.println("Example: " + EXAMPLE);
        view.divider();
    }
}
