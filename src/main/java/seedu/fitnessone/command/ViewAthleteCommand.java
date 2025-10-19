package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;

public class ViewAthleteCommand implements Command {
    private final String athleteID;

    public ViewAthleteCommand(String athleteID) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(athleteID);
    }

    @Override
    public void execute(Coach coachController, Ui view) {
        try {
            Athlete athlete = coachController.accessAthleteID(athleteID);
            ArrayList<Session> sessions = athlete.getSessions();



            view.divider();
            view.println("Athlete ID: " + athlete.getAthleteID());
            view.println("Athlete Name: " + athlete.getAthleteName());

            view.println("Sessions: " + sessions.size());
            view.println("List:");
            for (int i = 0; i < sessions.size(); i++) {
                Session session = sessions.get(i);
                view.println("   Session " + (i + 1) + ". | Notes: " + session.getTrainingNotes());
                view.println("   " + "Exercises:");
                for(int j = 0; j < session.getExercises().size(); j++) {
                    Exercise exercise = session.getExercises().get(j);
                    view.println("      " + (j + 1) + ". | Notes: " + exercise.getExerciseDescription()
                            + " | sets x reps: " + exercise.getSets() + " x " + exercise.getReps());
                }
            }
            view.divider();
        } catch (InvalidAthleteException e) {
            view.printWithDivider("Athlete ID not found: " + athleteID);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
