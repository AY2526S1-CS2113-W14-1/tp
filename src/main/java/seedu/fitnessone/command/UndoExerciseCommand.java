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
    private final String athleteID;
    private final String sessionID;
    private final String exerciseID;

    public UndoExerciseCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.sessionID = Parser.checkSessionIDValidity(inputString);
        this.exerciseID = Parser.checkExerciseIDValidity(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            Athlete athlete = coachController.accessAthlete(athleteID);
            Session session = coachController.accessSessionID(athlete, sessionID);
            ArrayList<Exercise> exercises = session.getExercises();

            for (Exercise exercise : exercises) {
                if (exercise.getExerciseIDString().equals(exerciseID)) {
                    exercise.setCompleted();
                    view.printWithDivider("Exercise (ID: " + exerciseID + "), " +
                            "Session (ID: " + sessionID + ") has been marked as not completed for "
                            + athlete.getAthleteName() + " (ID: " + athleteID + ").");
                    break;
                }
            }
        } catch (InvalidAthleteException | InvalidSessionException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
