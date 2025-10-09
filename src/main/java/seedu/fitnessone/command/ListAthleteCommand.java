package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class ListAthleteCommand implements Command {
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        coachController.printAthletes(view);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
