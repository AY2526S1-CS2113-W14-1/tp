package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class DeleteSessionCommand implements Command {
    private final String athleteID;
    private final String sessionID;

    /**
     * command constructor.
     *
     * @param inputString String.
     */
    public DeleteSessionCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.sessionID = Parser.checkSessionIDValidity(inputString);
    }

    /**
     * Executes the command to delete session for a certain athlete and display the message.
     *
     * @param coachController The task list.
     * @param view            The UI for displaying output.
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            Athlete athlete = coachController.accessAthleteID(athleteID);
            Session session = coachController.accessSessionID(athlete, sessionID);
            coachController.deleteSessionFromAthlete(athleteID, sessionID);
        } catch (InvalidAthleteException | InvalidSessionException e) {
            throw new InvalidCommandException(e.getMessage());
        }
        view.printWithDivider("Session " + sessionID + " deleted for " + athleteID);
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
