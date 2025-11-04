package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;
import seedu.fitnessone.exception.SessionLimitReachedException;

public class NewSessionCommand implements Command {
    private static final String COMMAND_WORD = "/newsession";
    private static final String USAGE = "/newsession <Athlete ID> <Description>";
    private static final String DESCRIPTION = "Adds a session to the specified athlete with a description";
    private static final String EXAMPLE = "/newsession 0001 Legs";
    private static final String NOTE = "Athlete ID = 4 digits";

    private final String inputString;

    public NewSessionCommand(String inputString) {
        this.inputString = inputString;
    }


    private String findTrainingNotes(String inputString) throws InvalidCommandException {
        inputString = inputString.trim();
        String[] inputParts = inputString.split("\\s+", 3);
        if (inputParts.length != 3) {
            throw new InvalidCommandException("Invalid input, try /NewSession <Athlete ID> " +
                    "<Session Notes>");
        }
        return inputParts[2];
    }

    /**
     * Creates a new training session for the specified athlete.
     * Validates the athlete ID, extracts the session description, and adds the session
     * to the athlete's record. Displays success message with session details including
     * the athlete name, athlete ID, new session ID, and description.
     *
     * @param coachController The coach controller managing all athletes
     * @param view The UI component for displaying results
     * @throws InvalidAthleteException If the athlete ID does not exist in the system
     * @throws InvalidCommandException If the athlete ID or session description is invalid
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidAthleteException, InvalidCommandException,
            SessionLimitReachedException {

        String athleteID = Parser.checkAthleteIDValidity(inputString);
        String trainingNotes = findTrainingNotes(inputString);

        Athlete athlete = coachController.accessAthleteID(athleteID);
        Session newSession = coachController.addSessionToAthlete(athleteID, trainingNotes);

        String output = "New session created:\n" + "\tAthlete Name: "
                + athlete.getAthleteName() + " | ID: " + athleteID + "\n\n"
                + "\tSession ID: " + newSession.getSessionIdString() + "\n"
                + "\tSession Description: " + newSession.getTrainingNotes() + "\n";
        view.printWithDivider(output);
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
