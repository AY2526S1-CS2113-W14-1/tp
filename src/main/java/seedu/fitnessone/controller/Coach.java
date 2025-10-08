package seedu.fitnessone.controller;

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
