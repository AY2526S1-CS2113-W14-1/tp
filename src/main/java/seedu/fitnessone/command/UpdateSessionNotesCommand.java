package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class UpdateSessionNotesCommand implements Command {
    private static final String COMMAND_WORD = "/updatesessionnote";
    private static final String USAGE = "/updatesessionnote <Athlete ID> <Session ID> <New Notes>";
    private static final String DESCRIPTION = "Updates training notes to specified session";
    private static final String EXAMPLE = "/updatesessionnote 0001 001 Chest-Day";
    private static final String NOTE = "Athlete ID = 4 digits, Session ID = 3 digits";

    private final String inputString;

    public UpdateSessionNotesCommand(String inputString) throws InvalidCommandException {
        this.inputString = inputString;
    }



    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            String athleteID = Parser.checkAthleteIDValidity(inputString);
            String sessionID = Parser.checkSessionIDValidity(inputString);
            String sessionNotes = Parser.isTrainingNotes(inputString);

            Athlete athlete = coachController.accessAthleteID(athleteID);
            Session session = coachController.accessSessionID(athlete, sessionID);

            session.setTrainingNotes(sessionNotes);

            view.printWithDivider("Successfully updated Athlete + (ID: " + athleteID + ")," +
                    " session: " + sessionID + " with notes: " + sessionNotes + ".");

        } catch (InvalidAthleteException | InvalidSessionException e) {
            throw new InvalidCommandException(e.getMessage());
        }
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

