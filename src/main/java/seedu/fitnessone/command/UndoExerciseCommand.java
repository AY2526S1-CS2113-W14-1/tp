package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;

public class UndoExerciseCommand implements Command {
    private static final String COMMAND_WORD = "/undoexercise";
    private static final String USAGE = "/undoexercise <Athlete ID> <Session ID> <Exercise ID>";
    private static final String DESCRIPTION = "Marks the specified exercise as not completed";
    private static final String EXAMPLE = "/undoexercise 0001 003 02";
    private static final String NOTE = "Athlete ID = 4 digits, Session ID = 3 digits, Exercise ID = 2 digits";

    private final String inputString;

    public UndoExerciseCommand(String inputString){
        this.inputString = inputString;
    }


    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException,
            InvalidAthleteException, InvalidSessionException {
        String athleteID = Parser.checkAthleteIDValidity(inputString);
        String sessionID = Parser.checkSessionIDValidity(inputString);
        String exerciseID = Parser.checkExerciseIDValidity(inputString);

        Athlete athlete = coachController.accessAthlete(athleteID);
        Session session = coachController.accessSessionID(athlete, sessionID);
        ArrayList<Exercise> exercises = session.getExercises();

        for (Exercise exercise : exercises) {
            if (exercise.getExerciseIDString().equals(exerciseID)) {
                exercise.setNotCompleted();
                view.printWithDivider("Exercise (ID: " + exerciseID + "), " +
                        "Session (ID: " + sessionID + ") has been marked as not completed for "
                        + athlete.getAthleteName() + " (ID: " + athleteID + ").");
                break;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

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
