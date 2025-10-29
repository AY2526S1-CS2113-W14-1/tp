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
    private static final String COMMAND_WORD = "/undosession";
    private static final String USAGE = "/undosession <Athlete ID> <Session ID>";
    private static final String DESCRIPTION = "Marks the specified session as not completed";
    private static final String EXAMPLE = "/undosession 0001 003";
    private static final String NOTE = "Athlete ID = 4 digits, Session ID = 3 digits";

    private final String inputString;


    public UndoSessionCommand(String inputString) {
        this.inputString = inputString;
    }


    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            String athleteID = athleteID = Parser.checkAthleteIDValidity(inputString);
            String sessionID = sessionID = Parser.checkSessionIDValidity(inputString);

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
