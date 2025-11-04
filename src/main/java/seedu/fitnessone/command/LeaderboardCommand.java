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

    /**
     * Displays the leaderboard ranking all athletes by their achievement scores.
     * Athletes are sorted in descending order by their achievement score (calculated from
     * completed exercises). Shows each athlete's rank, ID, name, and score in a formatted table.
     * Validates that no extra arguments are provided with the command.
     *
     * @param coachController The coach controller managing all athletes and their scores
     * @param view The UI component for displaying the leaderboard
     * @throws InvalidCommandException If any arguments are provided with the command
     */
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
