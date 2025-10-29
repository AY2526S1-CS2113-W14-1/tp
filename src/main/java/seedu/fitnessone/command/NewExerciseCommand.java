package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

import java.util.Arrays;

public class NewExerciseCommand implements Command {

    private static final String COMMAND_WORD = "/newexercise";
    private static final String USAGE = "/newexercise <Athlete ID> <Session ID> <Exercise Description>";
    private static final String DESCRIPTION = "Adds an exercise to the specified session";
    private static final String EXAMPLE = "/newexercise 0001 001 leg-press 5 15";
    private static final String NOTE = "Athlete ID = 4 digits, Session ID = 3 digits, Exercise ID = 2";


    private final String inputString;

    public NewExerciseCommand(String inputString) throws InvalidCommandException {
        this.inputString = inputString;
    }

    @Override
    public void execute(Coach coachController, Ui view)
            throws InvalidAthleteException, InvalidSessionException, InvalidCommandException, InvalidExerciseException {

        String athleteID = Parser.checkAthleteIDValidity(inputString);
        String sessionID = Parser.checkSessionIDValidity(inputString);
        String description = Parser.onlyDescription(inputString);

        String[] parts = description.trim().split("\\s+");
        if (parts.length < 3) {
            throw new InvalidCommandException("Invalid input. Please follow: " + USAGE);
        }

        int last = parts.length - 1;
        int secondLast = parts.length - 2;
        int reps;
        int sets;
        try {
            reps = Integer.parseInt(parts[last]);
            sets = Integer.parseInt(parts[secondLast]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Sets and reps must be integers.");
        }

        String descriptionOnly = String.join(" ",
                Arrays.copyOfRange(parts, 0, secondLast)).trim();

        Athlete athlete = coachController.accessAthleteID(athleteID);
        Session session = coachController.accessSessionID(athlete, sessionID);
        Exercise exercise = session.addExercise(descriptionOnly, sets, reps);

        view.divider();
        view.println("New exercise created!");
        view.println("");
        view.println("Athlete (ID) : " + athleteID);
        view.println("Athlete name: " + athlete.getAthleteName());
        view.println("");
        view.println("Session (ID): " + sessionID);
        view.println("Session Description: " + session.getTrainingNotes());
        view.println("");
        view.println("Exercise (ID): " + exercise.getExerciseIDString());
        view.println("Exercise Description: " + descriptionOnly);
        view.println("sets x reps: " + sets + " x " + reps);
        view.divider();

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
