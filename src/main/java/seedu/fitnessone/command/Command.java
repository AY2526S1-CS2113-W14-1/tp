package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Ui;

/**
 * Represents a command issued by the user to the training planner application.
 * Implementations encapsulate an action that can be executed against the
 * application model via a {@link Coach} controller and that parses the interaction with
 * the user via the commands and the {@link Ui} view.
 */

public interface Command {

    /**
     * Executes the command using the provided controller and view.
     * Implementations should perform all necessary validation and update the
     * application state via {@code coachController}. Any user-facing messages
     * should be delivered via {@code view}.
     *
     * @param coachController Controller used to manipulate application state.
     * @param view View used for assisting with user interaction.
     */
    void execute(Coach coachController, Ui view) throws InvalidCommandException,
            InvalidAthleteException, InvalidSessionException, InvalidExerciseException;
    boolean isExit();
    void help(Ui view);
}
