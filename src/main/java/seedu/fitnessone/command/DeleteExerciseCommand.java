package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class DeleteExerciseCommand implements Command {
    private static final String COMMAND_WORD = "/deleteexercise";
    private static final String USAGE = "/deleteexercise <Athlete ID> <Session ID> <Exercise ID>";
    private static final String DESCRIPTION = "Deletes a specific exercise from a specific session";
    private static final String EXAMPLE = "/deleteexercise 0001 003 05";
    private static final String NOTE = "Warning: This action cannot be undone!" +
            " Athlete ID = 4 digits, Session ID = 3 digits, Exercise ID = 2 digits";


    private final String inputString;


    public DeleteExerciseCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            String athleteID = Parser.checkAthleteIDValidity(inputString);
            String sessionID = Parser.checkSessionIDValidity(inputString);
            String exerciseID = Parser.checkExerciseIDValidity(inputString);

            Athlete athlete = coachController.accessAthleteID(athleteID);
            Session session = coachController.accessSessionID(athlete, sessionID);
            Exercise exercise = coachController.accessExerciseID(session, exerciseID);
            coachController.deleteExerciseFromSession(session, exercise);

        } catch (InvalidAthleteException | InvalidSessionException | InvalidExerciseException e) {
            throw new InvalidCommandException(e.getMessage());
        }
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
        view.println("Note: " + NOTE);
        view.divider();
    }
}
