package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class CompleteSession implements Command {
    private final String athleteName;
    private final int sessionId;

    public CompleteSession(String trimmedInput){
        try {

            String[] parts = trimmedInput.trim().split(" ");

            if (parts[0].isEmpty()) {
                throw new InvalidCommandException("No name provided.");
            }

            String inputAthleteName = parts[0] + " " + parts[1];

            this.athleteName = inputAthleteName;
            this.sessionId = Integer.parseInt(parts[2]);

        } catch (InvalidCommandException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * * Mark a Session as Completed `/complete <Athlete Name> <Session ID>`
     */

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            coachController.getAthleteByName(athleteName).getSessions().get(sessionId).setCompleted();
        } catch (NullPointerException e) {
            throw new InvalidCommandException("Athlete with name " + athleteName + " does not exist.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
