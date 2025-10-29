package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

import java.util.ArrayList;

public class ViewAthleteCommand implements Command {
    private static final String COMMAND_WORD = "/viewathlete";
    private static final String USAGE = "/viewathlete <Athlete ID>";
    private static final String DESCRIPTION = "Views the sessions that are assigned to the specified athlete";
    private static final String EXAMPLE = "/viewathlete 0001";
    private static final String NOTE = "Athlete ID = 4 digits";
    private final String inputString;

    public ViewAthleteCommand(String inputString) {
        this.inputString = inputString;
    }



    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            String athleteID = Parser.checkAthleteIDValidity(inputString);
            Athlete athlete = coachController.accessAthleteID(athleteID);
            ArrayList<Session> sessions = athlete.getSessions();

            view.divider();
            view.println("Athlete ID: " + athlete.getAthleteID());
            view.println("Athlete Name: " + athlete.getAthleteName());

            view.println("Sessions: " + sessions.size());
            view.println("List:");
            for (int i = 0; i < sessions.size(); i++) {
                Session session = sessions.get(i);
                view.println("   Session " + (i + 1) + ". | Notes: " + session.getTrainingNotes());
                view.println("   " + "Exercises:");
                for (int j = 0; j < session.getExercises().size(); j++) {
                    Exercise exercise = session.getExercises().get(j);
                    view.println("      " + (j + 1) + ". | Notes: " + exercise.getExerciseDescription()
                            + " | sets x reps: " + exercise.getSets() + " x " + exercise.getReps());
                }
            }
            view.divider();
        } catch (InvalidAthleteException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public void help(Ui view) {
        view.divider();
        view.println("Command: " + COMMAND_WORD);
        view.println("Usage: " + USAGE);
        view.println("Description: " + DESCRIPTION);
        view.println("Example: " + EXAMPLE);
        view.println("Note: " + NOTE);
        view.divider();
    }
}
