package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class NewSessionCommand implements Command {
    private final String athleteID;
    private String trainingNotes;

    /**
     * command constructor.
     *
     * @param trimmedInput String.
     */
    public NewSessionCommand(String trimmedInput) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(trimmedInput);
        this.trainingNotes = findTrainingNotes(trimmedInput);
        if(this.trainingNotes.equals(athleteID)) {
            trainingNotes ="";
        }
    }

    private String findTrainingNotes (String inputString) throws InvalidCommandException {
        inputString = inputString.trim();

        String[] inputParts = inputString.split("\\s+", 3);

        if (inputParts.length != 3) {
            throw new InvalidCommandException("Invalid input, try /NewSession <Athlete ID> " +
                    "<Session Notes>");
        }

        return inputParts[2];
    }

    /**
     * Executes the command to add session to a certain athlete and display the message.
     *
     * @param coachController The task list.
     * @param view            The UI for displaying output.
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidAthleteException {
        Athlete athlete = coachController.accessAthleteID(this.athleteID);
        Session newSession = coachController.addSessionToAthlete(athleteID, trainingNotes);

        view.divider();
        view.println("New session created:");
        view.println(" Athlete Name: " + athlete.getAthleteName() + " | ID: " + athleteID );
        view.println("");
        view.println("Session ID: " + newSession.getSessionIdString());
        view.println("Session Description: " +newSession.getTrainingNotes());
        view.divider();
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
}
