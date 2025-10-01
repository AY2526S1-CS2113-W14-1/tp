package seedu.fitnessone.Command;

import seedu.fitnessone.Controller.Coach;
import seedu.fitnessone.Exception.InvalidCommandException;
import seedu.fitnessone.UI.Ui;

public class DeleteCommand implements Command{
    @Override
    public void execute(Coach _coach, Ui _ui) throws InvalidCommandException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
