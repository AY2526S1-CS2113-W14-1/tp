package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class NewSessionCommand implements Command{
    private final String athleteName;
    private final int sessionID;
    private final String trainingNotes;

    /**
     * command constructor.
     * @param athleteName String.
     * @param sessionID String.
     * @param trainingNotes String.
     */
    public NewSessionCommand(String athleteName, int sessionID, String trainingNotes) {
        this.athleteName = athleteName;
        this.sessionID = sessionID;
        this.trainingNotes = trainingNotes;
    }
    /**
     * Executes the command to add session to a certain athlete and display the message.
     * @param coachController The task list.
     * @param view The UI for displaying output.
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        coachController.addSessionToAthlete(athleteName, sessionID, trainingNotes);
        view.printWithDivider("New session created: "+ athleteName +"\n" +
                "Session ID: " + sessionID);
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
