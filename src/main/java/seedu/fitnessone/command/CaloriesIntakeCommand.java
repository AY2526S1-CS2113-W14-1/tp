package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

import java.time.LocalDate;

public class CaloriesIntakeCommand implements Command {
    private final String athleteID;
    private final LocalDate date;
    private final int carbs;
    private final int protein;
    private final int fat;

    public CaloriesIntakeCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.date = Parser.checkDateValidity(inputString);
        int[] macros = Parser.parseIntakeValues(inputString);
        this.carbs = macros[0];
        this.protein = macros[1];
        this.fat = macros[2];
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            coachController.setCaloriesIntake(athleteID, date, carbs, protein, fat);
            view.printWithDivider("Calories intake recorded for Athlete " + athleteID + " on " + date +
                    " (Carbs: " + carbs + ", Protein: " + protein + ", Fat: " + fat + ")");
        } catch (InvalidAthleteException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
