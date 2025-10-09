package seedu.fitnessone.controller;

import seedu.fitnessone.exception.InvalidIDException;
import seedu.fitnessone.model.Athlete;
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
}
