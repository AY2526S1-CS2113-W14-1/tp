package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Ui;

public interface Command {
    void execute(Coach coachController, Ui view) throws InvalidCommandException,
            InvalidAthleteException, InvalidSessionException, InvalidExerciseException;
    boolean isExit();
    void help(Ui view);
}
