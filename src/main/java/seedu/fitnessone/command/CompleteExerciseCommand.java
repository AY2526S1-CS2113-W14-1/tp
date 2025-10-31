package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;

public class CompleteExerciseCommand implements Command {
    private static final String COMMAND_WORD = "/completeexercise";
    private static final String USAGE = "/completeexercise <Athlete ID> <Session ID> <Exercise ID>";
    private static final String DESCRIPTION = "Marks an exercise as completed";
    private static final String EXAMPLE = "/completeexercise 1234 001 01";
    private static final String NOTE = "Athlete ID = 4 digits, Session ID = 3 digits, Exercise ID = 2 digits";
    private final String inputString;

    public CompleteExerciseCommand(String inputString) throws InvalidCommandException {
        this.inputString = inputString;
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException,
            InvalidAthleteException, InvalidSessionException, InvalidExerciseException {
        String athleteID = Parser.checkAthleteIDValidity(inputString);
        String sessionID = Parser.checkSessionIDValidity(inputString);
        String exerciseID = Parser.checkExerciseIDValidity(inputString);

        Athlete athlete = coachController.accessAthleteID(athleteID);
        Session session = coachController.accessSessionID(athlete, sessionID);
        ArrayList<Exercise> exercises = session.getExercises();

        boolean found = false;
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseIDString().equals(exerciseID)) {
                exercise.setCompleted();
                view.printWithDivider("Exercise (ID: " + exerciseID + ") completed by "
                        + athlete.getAthleteName() + " (ID: " + athleteID + ").");
                found = true;
            }
        }
        if (!found) {
            throw new InvalidExerciseException("Exercise with ID '" + exerciseID
                    + "' not found for athlete '" + athlete.getAthleteName()
                    + "' (ID: " + athleteID + "), session ID: " + sessionID + ".");
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
