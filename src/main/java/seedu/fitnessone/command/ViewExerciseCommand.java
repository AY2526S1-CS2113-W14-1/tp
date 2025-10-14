package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Ui;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.model.Exercise;

import java.util.ArrayList;

public class ViewExerciseCommand implements Command {
    private final String athleteName;
    private final int sessionId;

    public ViewExerciseCommand(String athleteName, int sessionId) {
        this.athleteName = athleteName;
        this.sessionId = sessionId;
    }

    @Override
    public void execute(Coach coachController, Ui view) {
        try {
            Athlete athlete = coachController.accessAthlete(athleteName);
            Session session = coachController.accessSession(athlete, sessionId);
            ArrayList<Exercise> exercises = session.getExercises();

            view.divider();
            view.println("Athlete Name: " + athleteName);
            view.println("Session Note: " + session.getTrainingNotes());
            view.println("Session ID: " + sessionId + "\n");

            for (int i = 0; i < exercises.size(); i++) {
                Exercise exercise = exercises.get(i);
                String status = exercise.isCompleted() ? "[X]" : "[ ]";
                int displayIndex = i + 1;
                view.println(displayIndex + ".   " + status + " " + exercise.getExerciseDescription()
                        + " (" + exercise.getSets() + "x" + exercise.getReps() + ")");
            }

            view.divider();

        } catch (InvalidAthleteException e) {
            view.printWithDivider("Error: Athlete not found - " + athleteName);
        } catch (InvalidSessionException e) {
            view.printWithDivider("Error: Session not found - " + sessionId);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
