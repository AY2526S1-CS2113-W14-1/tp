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
    public void execute(Coach coachController, Ui view) throws InvalidCommandException,
            InvalidSessionException, InvalidAthleteException {
        if (inputString.split(" ").length > 3) {
            throw new InvalidCommandException(USAGE);
        }
        String athleteID = Parser.checkAthleteIDValidity(inputString);
        String sessionID = Parser.checkSessionIDValidity(inputString);

        Athlete athlete = coachController.accessAthleteID(athleteID);
        Session session = coachController.accessSessionID(athlete, sessionID);

        if(session.isCompleted()) {
            session.setNotCompleted();
            view.printWithDivider("Session (ID: " + sessionID + ") has been marked as not completed for "
                    + athlete.getAthleteName() + " (ID: " + athleteID + ").");
        }
        else{
            view.printWithDivider("Session (ID: " + sessionID + ") has already been marked as not completed for "
                    + athlete.getAthleteName() + " (ID: " + athleteID + ").");
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
