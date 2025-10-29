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
