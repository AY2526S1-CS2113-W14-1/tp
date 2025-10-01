package seedu.fitnessone.Command;

import seedu.fitnessone.Controller.Coach;
import seedu.fitnessone.Exception.InvalidCommandException;
import seedu.fitnessone.UI.Ui;

public class AddAthleteCommand implements Command{
    private String name;

    public AddAthleteCommand(String trimmedInput) throws InvalidCommandException {
        try {
            name = trimmedInput.substring(11).trim();
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException("no athlete name was specified");
        }
    }

    @Override
    public void execute(Coach _coach, Ui _ui) throws InvalidCommandException {
        String athleteName = _coach.addAthlete(name);
        _ui.printWithDivider("athlete added." + System.lineSeparator() + "      " + athleteName);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
