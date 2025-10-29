package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.model.Exercise;

import java.util.ArrayList;

public class ViewExerciseCommand implements Command {
    private static final String COMMAND_WORD = "/viewsessions";
    private static final String USAGE = "/viewsessions <Athlete ID>";
    private static final String DESCRIPTION = "Views the session IDs which are used in other commands";
    private static final String EXAMPLE = "/viewsessions 0001";
    private static final String NOTE = "Athlete ID = 4 digits";
    private final String inputString;


    public ViewExerciseCommand(String inputString) throws InvalidCommandException {
        this.inputString = inputString;
    }


    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            String athleteID = Parser.checkAthleteIDValidity(inputString);
            String sessionID = Parser.checkSessionIDValidity(inputString);

            Athlete athlete = coachController.accessAthleteID(athleteID);
            Session session = coachController.accessSessionID(athlete, sessionID);
            ArrayList<Exercise> exercises = session.getExercises();

            view.divider();
            view.println("Athlete ID: " + athleteID);
            view.println("Athlete Name: " + athlete.getAthleteID());
            view.println("Session ID: " + sessionID + "\n");
            view.println("Session Note: " + session.getTrainingNotes());

            view.println("Exercise List: " + exercises.size());
            for (int i = 0; i < exercises.size(); i++) {
                Exercise exercise = exercises.get(i);
                String status = exercise.isCompleted() ? "[X]" : "[ ]";
                int displayIndex = i + 1;
                view.println(displayIndex + ".   " + status + " " + exercise.getExerciseDescription()
                        + " (" + exercise.getSets() + "x" + exercise.getReps() + ")");
            }

            view.divider();

        } catch (InvalidAthleteException | InvalidSessionException e) {
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
