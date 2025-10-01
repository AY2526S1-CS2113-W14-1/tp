package seedu.fitnessone.Command;

import seedu.fitnessone.Controller.Coach;
import seedu.fitnessone.Exception.InvalidCommandException;
import seedu.fitnessone.UI.Ui;

public class ListAthleteCommand implements Command{

    @Override
    public void execute(Coach _coach, Ui _ui) throws InvalidCommandException {
        _ui.divider();
        _coach.printAthletes();
        _ui.divider();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
