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
    private static final String COMMAND_WORD = "/viewsessions";
    private static final String USAGE = "/viewsessions <Athlete ID>";
    private static final String DESCRIPTION = "Views the session IDs which are used in other commands";
    private static final String EXAMPLE = "/viewsessions 0001";
    private static final String NOTE = "Athlete ID = 4 digits";
    private final String inputString;

    public ViewSessionsCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidAthleteException, InvalidCommandException {
        if (inputString.split(" ").length > 2) {
            throw new InvalidCommandException(USAGE);
        }

        String athleteID = Parser.checkAthleteIDValidity(inputString);
        Athlete athlete = coachController.accessAthleteID(athleteID);
        ArrayList<Session> sessions = athlete.getSessions();

        view.divider();
        view.println("Athlete ID: " + athlete.getAthleteID() + " | Name: " + athlete.getAthleteName() + "\n");
        view.println("Status | Session ID   | Date                   | Notes ");
        for (int i = 0; i < sessions.size(); i++) {
            Session session = sessions.get(i);
            String status = session.isCompleted() ? "[X]" : "[ ]";

            view.println((i + 1) + ". " + status + " | Session: " +
                    session.getSessionIdString() + " | Date: "
                    + session.getSessionDateString() + " | " + session.getTrainingNotes());
        }
        view.divider();
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
