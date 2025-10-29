package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class CompleteSessionCommand implements Command {
    private static final String COMMAND_WORD = "/completesession";
    private static final String USAGE = "/completesession <Athlete ID> <Session ID>";
    private static final String DESCRIPTION = "Marks a session as completed";
    private static final String EXAMPLE = "/completesession 1234 001";
    private static final String NOTE = "Athlete ID = 4 digits, Session ID = 3 digits";

    private final String inputString;



    public CompleteSessionCommand(String inputString) throws InvalidCommandException {
        this.inputString = inputString;
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            String athleteID = Parser.checkAthleteIDValidity(inputString);
            String sessionID = Parser.checkSessionIDValidity(inputString);

            Athlete athlete = coachController.accessAthleteID(athleteID);
            Session session = coachController.accessSessionID(athlete, sessionID);
            session.setCompleted();

            view.printWithDivider("Session (ID: " + sessionID + ") completed by "
                    + athlete.getAthleteName() + " (ID: " + athleteID + ").");

        } catch (InvalidAthleteException | InvalidSessionException e) {
            throw new InvalidCommandException(e.getMessage());
        } catch (RuntimeException e) {
            throw new InvalidCommandException("There was an error while trying to complete " +
                    "the session." +
                    "\nTry: /completeSession <Athlete ID> <Session ID>");
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
