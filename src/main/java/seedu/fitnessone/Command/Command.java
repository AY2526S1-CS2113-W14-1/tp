package seedu.fitnessone.Command;

import seedu.fitnessone.Controller.Coach;
import seedu.fitnessone.Exception.InvalidCommandException;
import seedu.fitnessone.UI.Ui;

public interface Command {
    void execute(Coach _coach, Ui _ui) throws InvalidCommandException;
    boolean isExit();
}
