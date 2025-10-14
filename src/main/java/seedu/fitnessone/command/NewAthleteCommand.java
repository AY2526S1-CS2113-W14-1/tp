package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class NewAthleteCommand implements Command{
    private String athleteName;

    public NewAthleteCommand(String trimmedInput) throws InvalidCommandException {
        try {
            this.athleteName = trimmedInput.substring(11).trim();

        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException("athlete name was not specified");

        }
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        if (athleteName.isBlank()) {
            throw new InvalidCommandException("no todo item was specified.");
        }
        String itemToString = coachController.newAthlete(athleteName);
        view.printWithDivider("athlete added." + System.lineSeparator() + "     " + itemToString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}