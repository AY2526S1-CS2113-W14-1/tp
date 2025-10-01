package seedu.fitnessone.Controller;

import seedu.fitnessone.Model.Athlete;
import seedu.fitnessone.Model.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Coach {
    private List<Athlete> athletes;

    /**
     * Constructs an empty TaskList.
     */
    public Coach() {
        this.athletes = new ArrayList<>();
    }

    /**
     * Prints all tasks in the list with their indices and details.
     * Used to display the current state of the task list to the user.
     */
    public void printAthletes() {
        for (int i = 0; i < athletes.toArray().length; i++) {
            int index = i + 1;
            System.out.print("       " + index + ". ");
            System.out.println(athletes.get(i).toString());
        }
    }

    /**
     * Returns the list of tasks in this TaskList.
     * @return The list of tasks.
     */
    public List<Athlete> getAthletes() {
        return athletes;
    }

    /**
     * Deletes the task at the specified index from the list.
     * @param index The index of the task to delete (0-based).
     * @return String representation of the deleted task.
     */
    public String deleteAthlete(int index) {
        Athlete athlete = athletes.get(index);
        athletes.remove(index);
        return athlete.toString();
    }

    public String addAthlete(String name) {
        Random rand = new Random();
        int athleteId = rand.nextInt(1000000); // Generates an integer between 0 and 999999
        Athlete athlete = new Athlete(athleteId, name, new ArrayList<Session>());
        athletes.add(athlete);
        return athlete.toString();
    }
}
