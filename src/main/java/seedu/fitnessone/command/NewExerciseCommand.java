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
    private final String athleteID;
    private final String sessionID;
    private final String description;

    public NewExerciseCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.sessionID = Parser.checkSessionIDValidity(inputString);
        this.description = Parser.onlyDescription(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws
            InvalidAthleteException, InvalidSessionException, InvalidExerciseException {

        Athlete athlete = coachController.accessAthleteID(athleteID);
        Session session = coachController.accessSessionID(athlete, sessionID);

        String[] parts = description.trim().split("\\s+");

        if (parts.length < 3) {
            view.printWithDivider("Warning: Invalid input, please follow:" +
                    " /newexercise <athlete> <session> <description> <sets> <reps>");
            return;
        }

        int last = parts.length - 1;
        int secondLast = parts.length - 2;

        int reps;
        int sets;

        try {
            reps = Integer.parseInt(parts[last]);
            sets = Integer.parseInt(parts[secondLast]);
        } catch (NumberFormatException e) {
            view.printWithDivider("Warning: Sets and reps must be integers.");
            return;
        }

        String descriptionOnly = String.join(" ",
                Arrays.copyOfRange(parts, 0, secondLast)).trim();

        Exercise exercise = session.addExercise(descriptionOnly, sets, reps);

        view.divider();
        view.println("New exercise created!");
        view.println("");
        view.println("Athlete (ID) : " + athleteID);
        view.println("Athlete name: " + athlete.getAthleteName());
        view.println("Session (ID): " + sessionID + " | Date: " + session.getSessionDateString());
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
}
