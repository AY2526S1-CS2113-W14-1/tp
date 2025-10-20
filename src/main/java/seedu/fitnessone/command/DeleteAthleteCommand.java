package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class DeleteAthleteCommand implements Command{
    private final String athleteID;

    public DeleteAthleteCommand(String inputString) throws InvalidCommandException {
        athleteID = Parser.checkAthleteIDValidity(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        try {
            coachController.deleteAthlete(athleteID);
            view.printWithDivider("Deleted athlete with ID " + athleteID);
        } catch (InvalidAthleteException | IndexOutOfBoundsException e) {
            throw new InvalidCommandException(e.getMessage());
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
