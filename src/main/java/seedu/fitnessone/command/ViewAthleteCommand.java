package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;

public class ViewAthleteCommand implements Command {
    private final String athleteName;

    public ViewAthleteCommand(String athleteName) {
        this.athleteName = athleteName;
    }

    @Override
    public void execute(Coach coachController, Ui view) {
        try {
            Athlete athlete = coachController.accessAthlete(athleteName);
            ArrayList<Session> sessions = athlete.getSessions();

            view.divider();
            view.println("Athlete: " + athlete.getAthleteName());
            view.println("Sessions:");
            for (int i = 0; i < sessions.size(); i++) {
                Session session = sessions.get(i);
                view.println("-   " + (i + 1) + " " + session.getTrainingNotes());
            }
            view.divider();
        } catch (InvalidAthleteException e) {
            view.printWithDivider("Athlete not found: " + athleteName);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
