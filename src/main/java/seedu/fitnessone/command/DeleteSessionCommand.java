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
    private static final String COMMAND_WORD = "/deletesession";
    private static final String USAGE = "/deletesession <Athlete ID> <Session ID>";
    private static final String DESCRIPTION = "Deletes a specific session that" +
            " is assigned to a specific athlete";
    private static final String EXAMPLE = "/deletesession 0001 003";
    private static final String NOTE = "Warning: This action cannot be undone!" +
            " Athlete ID = 4 digits, Session ID = 3 digits";

    private final String inputString;

    /**
     * command constructor.
     *
     * @param inputString String.
     */
    public DeleteSessionCommand(String inputString) throws InvalidCommandException {
        if (inputString == null || inputString.trim().isEmpty()) {
            throw new InvalidCommandException("Athlete name cannot be empty.");
        }
        this.inputString = inputString;
    }

    /**
     * Executes the command to delete session for a certain athlete and display the message.
     *
     * @param coachController The task list.
     * @param view            The UI for displaying output.
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException,
            InvalidSessionException, InvalidAthleteException {

        String athleteID = Parser.checkAthleteIDValidity(inputString);
        String sessionID = Parser.checkSessionIDValidity(inputString);

        Athlete athlete = coachController.accessAthleteID(athleteID);
        Session session = coachController.accessSessionID(athlete, sessionID);
        coachController.deleteSessionFromAthlete(athleteID, sessionID);
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
