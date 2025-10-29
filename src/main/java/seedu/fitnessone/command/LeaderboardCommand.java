package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class LeaderboardCommand implements Command {
    private static final String COMMAND_WORD = "/leaderboard";
    private static final String USAGE = "/leaderboard";
    private static final String DESCRIPTION = "Views the achievements of all athletes by ranking them";
    private static final String EXAMPLE = "/leaderboard";
    private static final String NOTE = null;

    private final String inputString;

    public LeaderboardCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        if (!inputString.trim().equals(COMMAND_WORD)) {
            throw new InvalidCommandException("Usage: " + USAGE + "\nThis command doesn't take any arguments.");
        }
        view.printWithDivider(coachController.leaderboardConstruct());
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
