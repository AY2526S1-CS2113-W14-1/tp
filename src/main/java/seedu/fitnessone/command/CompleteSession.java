package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.ui.Ui;

public class CompleteSession implements Command {
    private final String athleteName;
    private final int sessionId;
    private final String trainingNotes;

    public CompleteSession(String trimmedInput) throws InvalidCommandException {
        try {
            trimmedInput.substring(9).trim();

            String[] parts = trimmedInput.trim().split(" ");

            if (parts[0].isEmpty()) {
                throw new InvalidCommandException("No name provided.");
            }

            String inputAthleteName = parts[0] + " " + parts[1];
            int partsLength = parts.length;

            StringBuilder builder = new StringBuilder();

            for (int i = 2; i < partsLength; i++) {
                builder.append(parts[i] + " ");
            }

            this.athleteName = inputAthleteName;
            this.sessionId = Integer.parseInt(parts[2]);
            this.trainingNotes = builder.toString();

        } catch (InvalidCommandException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * * Mark a Session as Completed `/complete <Athlete Name> <Session ID>`
     */

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        coachController.getAthleteByName(athleteName).getSessions().get(sessionId).setCompleted();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
