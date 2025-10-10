package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.ui.Ui;

public class AddTrainingNotes implements Command {

    private final String athleteName;
    private final int sessionId;
    private final String trainingNotes;

    // Add a Training Note to a Session `/trainingNotes <Athlete Name> <Session ID> <Notes>`
    public AddTrainingNotes(String trimmedInput) throws InvalidCommandException  {
        try {
            trimmedInput.substring(14).trim();

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

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            Athlete athlete = coachController.getAthleteByName(athleteName);
            athlete.getSessions().get(sessionId).setTrainingNotes(trainingNotes);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("invalid athleteID.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }



}
