package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Ui;

import java.util.Arrays;
import java.util.List;

public class CompleteSession implements Command {
    private final String athleteName;
    private final int sessionId;

    public CompleteSession(String rawInput) throws InvalidCommandException {
        if (rawInput == null) {
            throw new InvalidCommandException("Usage: /complete <Athlete Name> <Session ID>");
        }

        String[] parts = rawInput.trim().split("\\s+");
        // Expect at least: [/complete, <name>, <id>]
        if (parts.length < 3) {
            throw new InvalidCommandException("Usage: /complete <Athlete Name> <Session ID>");
        }

        String last = parts[parts.length - 1];
        if (!isInteger(last)) {
            throw new InvalidCommandException("Session ID must be a positive number.");
        }
        int id = Integer.parseInt(last);
        if (id <= 0) {
            throw new InvalidCommandException("Session ID must be a positive number.");
        }
        this.sessionId = id;

        this.athleteName = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1)).trim();
        if (athleteName.isEmpty()) {
            throw new InvalidCommandException("No athlete name provided.");
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
     * Mark a Session as Completed `/complete <Athlete Name> <Session ID>`
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


        target.setCompleted();
        view.print("Athlete " + athleteName + " session " + sessionId + " has been marked as completed.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
