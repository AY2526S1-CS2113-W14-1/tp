package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;


public class ViewSessionCommand implements Command {

    private final String athleteName;

    public ViewSessionCommand(String athleteName) {
        this.athleteName = athleteName;
    }

    @Override
    public void execute(Coach coachController, Ui view) {
        try {
            Athlete athlete = coachController.accessAthlete(athleteName);
            ArrayList<Session> sessions = athlete.getSessions();

            view.divider();
            view.println("Athlete Name: " + athlete.getAthleteName() + "\n");

            for (int i = 0; i < sessions.size(); i++) {
                Session session = sessions.get(i);
                String status = session.isCompleted() ? "[X]" : "[ ]";
                view.println((i + 1) + ".   " + status + " " + session.getTrainingNotes());
            }
            view.divider();

        } catch (InvalidAthleteException e) {
            view.divider();
            view.println("Athlete not found: " + athleteName);
            view.divider();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
