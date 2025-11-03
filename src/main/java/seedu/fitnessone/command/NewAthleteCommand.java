package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class NewAthleteCommand implements Command {
    private static final String COMMAND_WORD = "/newathlete";
    private static final String USAGE = "/newathlete <Athlete Name>";
    private static final String DESCRIPTION = "Creates a new log for a new athlete with a specified name";
    private static final String EXAMPLE = "/newathlete Jonas Hardwell";

    private final String inputString;


    public NewAthleteCommand(String inputString) throws InvalidCommandException {
        if (inputString == null || inputString.trim().isEmpty()) {
            throw new InvalidCommandException("Athlete name cannot be empty.");
        }
        this.inputString = inputString;

    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        String athleteName;
        athleteName = inputString.substring(11).trim();
        if (athleteName.isBlank()) {
            throw new InvalidCommandException("Athlete name was not specified");
        }
        if (!athleteName.matches("[A-Za-z ]+")) {
            throw new InvalidCommandException("Athlete name must contain only letters and spaces.");
        }

        String itemToString = coachController.newAthlete(athleteName);
        view.printWithDivider("Athlete added:" + System.lineSeparator() + "     " + itemToString);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void help(Ui view) {
        view.divider();
        view.println("Command: " + COMMAND_WORD);
        view.println("Usage: " + USAGE);
        view.println("Description: " + DESCRIPTION);
        view.println("Example: " + EXAMPLE);
        view.divider();
    }
}
