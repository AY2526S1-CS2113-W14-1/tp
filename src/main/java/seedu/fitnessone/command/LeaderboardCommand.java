package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class LeaderboardCommand implements Command {
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        String leaderboard = coachController.leaderboardConstruct();
        view.printWithDivider(leaderboard);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
