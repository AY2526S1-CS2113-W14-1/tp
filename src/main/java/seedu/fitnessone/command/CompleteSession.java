package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;

public class CompleteSession implements Command {
    private final ArrayList<String> athleteNames = new ArrayList<>();
    private int sessionId;

    public CompleteSession(String trimmedInput){
        try {

            String[] parts = trimmedInput.trim().split("\\s+");

            if (parts[1].isEmpty()) {
                throw new InvalidCommandException("No name provided.");
            }
            String candidateName;

            for (int i = 1; i < parts.length; i++) {
                candidateName = parts[i] + " " + parts[i + 1];
                athleteNames.add(candidateName);
            }

            for (int i = parts.length-1; i > 0; i--) {
                if(isInteger(parts[i])){
                    this.sessionId = Integer.parseInt(parts[i]);
                }
            }

        } catch (InvalidCommandException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean isInteger(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * * Mark a Session as Completed `/complete <Athlete Name> <Session ID>`
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        for (String candidate : athleteNames) {
            try {
                var athlete = coachController.accessAthlete(candidate);
                try {
                    athlete.getSessions().get(sessionId).setCompleted();
                    view.print("Athlete " + candidate + " session " +
                            sessionId + " has been marked as completed.");
                } catch (IndexOutOfBoundsException | NullPointerException ignored) {
                    throw new InvalidCommandException("Session ID " +
                            sessionId + " is invalid for athlete " +
                            candidate + ".");
                }
                return;
            } catch (InvalidAthleteException ignored) {
                throw new InvalidCommandException("Invalid: Session ID " + sessionId);
            }
        }
        throw new InvalidCommandException("Athlete not found.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}