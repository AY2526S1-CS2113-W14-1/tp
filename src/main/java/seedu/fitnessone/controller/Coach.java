package seedu.fitnessone.controller;

import seedu.fitnessone.exception.InvalidIDException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
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

    public Athlete getAthleteByName(String name) {
        Athlete athlete;
        int athleteIndex = -1;
        for (int i = 0; i < athletes.size(); i++) {
            if (athletes.get(i).getAthleteName().equals(name)) {
                athleteIndex = i;
            }
        }
        if (athleteIndex == -1) {
            throw new InvalidIDException("There is no such athlete with the name " + name);
        }
        return athletes.get(athleteIndex);
    }

    public Session getSession(Athlete athlete, int sessionID){
        return athlete.getSessions().get(sessionID);
    }

    public void addTrainingSession(Session session, String trainingNotes) {
        session.setTrainingNotes(trainingNotes);
    }

    public void completeSesssion(Session session) {
        session.setCompleted();
    }
}
