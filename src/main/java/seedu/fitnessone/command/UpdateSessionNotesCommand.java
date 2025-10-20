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
    private final String athleteID;
    private final String sessionID;
    private final String sessionNotes;

    public UpdateSessionNotesCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.sessionID = Parser.checkSessionIDValidity(inputString);
        this.sessionNotes = Parser.isTrainingNotes(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
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
}
