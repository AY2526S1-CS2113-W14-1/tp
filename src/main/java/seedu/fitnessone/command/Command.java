package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public interface Command {
    void execute(Coach coachController, Ui view) throws InvalidCommandException;
    boolean isExit();
}