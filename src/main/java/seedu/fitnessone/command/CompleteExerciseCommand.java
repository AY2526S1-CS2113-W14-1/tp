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

public class CompleteExerciseCommand implements Command {
    private final String athleteID;
    private final String sessionID;
    private final String exerciseID;

    public CompleteExerciseCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.sessionID = Parser.checkSessionIDValidity(inputString);
        this.exerciseID = Parser.checkExerciseIDValidity(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidAthleteException, InvalidSessionException {
        Athlete athlete = coachController.accessAthlete(athleteID);
        Session session = coachController.accessSessionID(athlete, sessionID);
        ArrayList<Exercise> exercises = session.getExercises();

        boolean isFound = false;

        for (Exercise exercise : exercises) {
            if (exercise.getExerciseIDString().equals(exerciseID)) {
                exercise.setCompleted();
                view.printWithDivider("Exercise (ID: "+ exerciseID +") completed by "
                        + athlete.getAthleteName() + " (ID: "+ athleteID+").");
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            view.divider();
            view.println("Could not find: " + exerciseID + ".");
            view.println("Try using: /completeExercise <athleteID> <sessionID> <exerciseID>");
            view.divider();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
