package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.model.Exercise;

import java.util.ArrayList;

public class ViewExerciseCommand implements Command {
    private final String athleteID;
    private final String sessionID;
    private final String exerciseID;

    public ViewExerciseCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.sessionID = Parser.checkSessionIDValidity(inputString);
        this.exerciseID = Parser.checkExerciseIDValidity(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) {
        try {
            Athlete athlete = coachController.accessAthleteID(athleteID);
            Session session = coachController.accessSessionID(athlete, sessionID);
            ArrayList<Exercise> exercises = session.getExercises();

            view.divider();
            view.println("Athlete ID: " + athleteID);
            view.println("Athlete Name: " + athlete.getAthleteID());
            view.println("Session ID: " + sessionID + "\n");
            view.println("Session Note: " + session.getTrainingNotes());

            for (int i = 0; i < exercises.size(); i++) {
                Exercise exercise = exercises.get(i);
                String status = exercise.isCompleted() ? "[X]" : "[ ]";
                int displayIndex = i + 1;
                view.println(displayIndex + ".   " + status + " " + exercise.getExerciseDescription()
                        + " (" + exercise.getSets() + "x" + exercise.getReps() + ")");
            }

            view.divider();

        } catch (InvalidAthleteException e) {
            view.printWithDivider("Error: Athlete not found - " + athleteID);
        } catch (InvalidSessionException e) {
            view.printWithDivider("Error: Session not found - " + sessionID);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
