package seedu.fitnessone.controller;


import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;

import seedu.fitnessone.exception.InvalidIDException;
import seedu.fitnessone.ui.Ui;


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
            throws InvalidAthleteException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                Session newSession = new Session(sessionID, sessionTrainingNotes);
                athlete.addSession(newSession);
                return;
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteName);
    }

    public void deleteSessionFromAthlete(String athleteName, int sessionID)
            throws InvalidAthleteException, InvalidSessionException {

        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                athlete.removeSession(sessionID);
                return;
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteName);
            }

    public String newAthlete (String athleteName) {
        Athlete athlete = new Athlete(athleteName);
        athletes.add(athlete);

        return athlete.toString();
    }

    public String deleteAthlete(String athleteID) throws InvalidIDException {
        for (int i = 0; i < athletes.size(); i++) {
            Athlete athlete = athletes.get(i);
            if (athlete.getAthleteID().equals(athleteID)) {
                athletes.remove(i);
                return athlete.toString();
            }
        }
        throw new InvalidIDException("athleteID " + athleteID + " not found.");
    }

    public void printAthletes(Ui view) {
        view.divider();
        for (int i = 0; i < athletes.toArray().length; i++) {
            int index = i + 1;
            view.print(index + ". ");
            view.println(athletes.get(i).toString());
        }
        view.println("");
        view.divider();
    }
}
