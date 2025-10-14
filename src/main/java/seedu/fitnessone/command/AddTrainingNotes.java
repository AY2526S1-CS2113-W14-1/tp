package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Ui;

import java.util.Arrays;
import java.util.List;

public class AddTrainingNotes implements Command {
    private final String athleteName;
    private final int sessionId;
    private final String trainingNotes;

    public AddTrainingNotes(String rawInput) throws InvalidCommandException {
        if (rawInput == null) {
            throw new InvalidCommandException("Usage: /addNotes <Athlete Name> <Session ID> <Training notes>");
        }
        String[] parts = rawInput.trim().split("\\s+");
        // Need at least: [/addNotes, <name>, <id>, <note>]
        if (parts.length < 4) {
            throw new InvalidCommandException("Usage: /addNotes <Athlete Name> <Session ID> <Training notes>");
        }

        int idIndex = -1;
        for (int i = 1; i < parts.length; i++) {
            if (isInteger(parts[i])) {
                idIndex = i;
                break;
            }
        }
        if (idIndex == -1) {
            throw new InvalidCommandException("Missing session ID. Usage: /addNotes <Athlete Name> <Session ID> <Training notes>");
        }
        if (idIndex == 1) {
            throw new InvalidCommandException("No athlete name provided.");
        }
        if (idIndex == parts.length - 1) {
            throw new InvalidCommandException("No training notes provided.");
        }

        String name = String.join(" ", Arrays.copyOfRange(parts, 1, idIndex)).trim();
        if (name.isEmpty()) {
            throw new InvalidCommandException("No athlete name provided.");
        }
        this.athleteName = name;

        int parsedId = Integer.parseInt(parts[idIndex]);
        if (parsedId <= 0) {
            throw new InvalidCommandException("Session ID must be a positive number.");
        }
        this.sessionId = parsedId;

        String notes = String.join(" ", Arrays.copyOfRange(parts, idIndex + 1, parts.length)).trim();
        if (notes.isEmpty()) {
            throw new InvalidCommandException("No training notes provided.");
        }
        this.trainingNotes = notes;
    }

    private static boolean isInteger(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * Add training notes: `/addNotes <Athlete Name> <Session ID> <Training notes...>`
     */
    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        final Athlete athlete;
        try {
            athlete = coachController.accessAthlete(athleteName);
        } catch (InvalidAthleteException e) {
            throw new InvalidCommandException("Athlete \"" + athleteName + "\" not found.");
        }

        List<Session> sessions = athlete.getSessions();
        Session target = null;
        for (Session s : sessions) {
            if (s.getSessionId() == sessionId) {
                target = s;
                break;
            }
        }
        if (target == null) {
            throw new InvalidCommandException(
                    "Session ID " + sessionId + " is invalid for athlete " + athleteName + "."
            );
        }

        target.setTrainingNotes(trainingNotes);
        view.print("Training notes added to " + athleteName + " (session " + sessionId + "): " + trainingNotes);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
