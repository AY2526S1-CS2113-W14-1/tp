package seedu.fitnessone.controller;

import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private List<Athlete> athletes;

    /**
     * Constructs an empty TaskList.
     */
    public Coach() {
        this.athletes = new ArrayList<>();
    }

    public void addSessionToAthlete(String athleteName, int sessionID, String sessionTrainingNotes)
            throws InvalidCommandException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                Session newSession = new Session(sessionID, sessionTrainingNotes);
                athlete.addSession(newSession);
                return;
            }
        }
        throw new InvalidCommandException("Athlete not found: " + athleteName);
    }

    public void deleteSessionFromAthlete(String athleteName, int sessionID) throws InvalidCommandException {

        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                athlete.removeSession(sessionID);
                return;
            }
        }
        throw new InvalidCommandException("Athlete not found: " + athleteName);
    }
}
