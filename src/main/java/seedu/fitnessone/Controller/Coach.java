package seedu.fitnessone.Controller;

import seedu.fitnessone.Model.Athlete;

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
}
