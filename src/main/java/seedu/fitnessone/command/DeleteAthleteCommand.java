package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class DeleteAthleteCommand implements Command {
    private static final String COMMAND_WORD = "/deleteathlete";
    private static final String USAGE = "/deleteathlete <Athlete ID>";
    private static final String DESCRIPTION = "Deletes an athlete and all associated data";
    private static final String EXAMPLE = "/deleteathlete 0001";
    private static final String NOTE = "Warning: This action cannot be undone! Athlete ID = 4 digits";

    private final String inputString;

    public DeleteAthleteCommand(String inputString) throws InvalidCommandException {
        this.inputString = inputString;
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException, InvalidAthleteException {
        if (inputString.split(" ").length > 2) {
            throw new InvalidCommandException(USAGE);
        }
        String athleteID = Parser.checkAthleteIDValidity(inputString);

        coachController.deleteAthlete(athleteID);
        view.printWithDivider("Deleted athlete with ID " + athleteID);
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
