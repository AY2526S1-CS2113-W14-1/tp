package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.CaloriesIntake;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

import java.time.LocalDate;

public class ViewCaloriesIntakeCommand implements Command {
    private final String athleteID;
    private final LocalDate date;

    public ViewCaloriesIntakeCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.date = Parser.checkDateValidity(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            CaloriesIntake macros = coachController.getCaloriesIntake(athleteID, date);
            if (macros == null) {
                view.printWithDivider("No calories intake record found for Athlete " + athleteID + " on " + date);
            } else {
                view.printWithDivider("Calories intake for Athlete " + athleteID + " on " + date +
                        " (Carbs: " + macros.getCarbs() + ", Protein: " + macros.getProtein() + ", Fat: " +
                        macros.getFat() + ")");
            }
        } catch (InvalidAthleteException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
