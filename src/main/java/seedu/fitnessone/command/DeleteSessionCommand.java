package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Ui;

public class DeleteSessionCommand implements Command {
    private final String athleteName;
    private final int sessionID;

    /**
     * command constructor.
     * @param athleteName String.
     * @param sessionID String.
     */
    public DeleteSessionCommand(String athleteName, int sessionID) {
        this.athleteName = athleteName;
        this.sessionID = sessionID;
    }

    /**
     * Executes the command to delete session for a certain athlete and display the message.
     * @param coachController The task list.
     * @param view The UI for displaying output.
     */
    @Override
    public void execute(Coach coachController, Ui view){
        try {
            coachController.deleteSessionFromAthlete(athleteName, sessionID);
        } catch (InvalidSessionException | InvalidAthleteException e) {
            throw new RuntimeException(e);
        }
        view.printWithDivider("Session " + sessionID + " deleted for " + athleteName);
    }
    /**
     * Indicates whether this command should exit the application.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
