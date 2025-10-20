package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class UndoSessionCommand implements Command {
    private final String athleteID;
    private final String sessionID;

    public UndoSessionCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.sessionID = Parser.checkSessionIDValidity(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            Athlete athlete = coachController.accessAthleteID(athleteID);
            Session session = coachController.accessSessionID(athlete, sessionID);
            session.setNotCompleted();

            view.printWithDivider("Session (ID: " + sessionID + ") has been marked as not completed for "
                    + athlete.getAthleteName() + " (ID: " + athleteID + ").");

        } catch (InvalidAthleteException | InvalidSessionException e) {
            throw new InvalidCommandException(e.getMessage());
        } catch (RuntimeException e) {
            throw new InvalidCommandException("There was an error while trying to mark the " +
                    "session not completed." +
                    "\nTry: /completeSession <Athlete ID> <Session ID>");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
