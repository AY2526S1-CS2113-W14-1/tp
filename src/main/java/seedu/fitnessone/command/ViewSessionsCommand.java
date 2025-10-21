package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;


public class ViewSessionsCommand implements Command {

    private final String athleteID;

    public ViewSessionsCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidAthleteException, InvalidCommandException {
        try {
            Athlete athlete = coachController.accessAthleteID(athleteID);
            ArrayList<Session> sessions = athlete.getSessions();

            view.divider();
            view.println("Athlete ID | Name: " + athlete.getAthleteID() + " - " + athlete.getAthleteName() + "\n");
            view.println("Status | Session ID | Notes: ");
            for (int i = 0; i < sessions.size(); i++) {
                Session session = sessions.get(i);
                String status = session.isCompleted() ? "[X]" : "[ ]";

                view.println((i + 1) + ". " + status + ".   Session: " +
                        session.getSessionIdString() + " | " + session.getTrainingNotes());
            }
            view.divider();

        } catch (InvalidAthleteException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public ViewSessionsCommand(String athleteID, boolean skipParser) {
        this.athleteID = athleteID;
    }
}
