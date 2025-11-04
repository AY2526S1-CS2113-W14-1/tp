package seedu.fitnessone.command;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class FlagAthleteCommand implements Command {
    private static final String COMMAND_WORD = "/flagathlete";
    private static final String USAGE = "/flagathlete <Athlete ID> <Color>";
    private static final String DESCRIPTION = "Flags an athlete with a color for priority tracking";
    private static final String EXAMPLE = "/flagathlete 0001 red";
    private static final String NOTE = "Available colors: red, yellow, green. Athlete ID = 4 digits";

    private final String inputString;

    public FlagAthleteCommand(String inputString){
        this.inputString = inputString;
    }



    private boolean isValidColor(String color) {
        return switch (color) {
        case "red", "yellow", "green" -> true;
        default -> false;
        };
    }

    private String colorToEmoji(String color) {
        return switch (color.toLowerCase()) {
        case "red" -> "RED";
        case "yellow" -> "YELLOW";
        case "green" -> "GREEN";
        default -> color;
        };
    }


    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException, InvalidAthleteException {
        String athleteID = Parser.checkAthleteIDValidity(inputString);
        String[] parts = inputString.trim().split("\\s+");
        if (inputString.split(" ").length > 3) {
            throw new InvalidCommandException(USAGE);
        }
        if (parts.length < 3) {
            throw new InvalidCommandException("Flag color was not specified.\nUsage: " + USAGE);
        }
        String flagColor = parts[2].toLowerCase();
        if (!isValidColor(flagColor)) {
            throw new InvalidCommandException("Invalid color: " + flagColor + "\n" + NOTE);
        }
        String emojiFlag = colorToEmoji(flagColor);
        coachController.flagAthlete(athleteID, emojiFlag);
        view.printWithDivider("Athlete " + athleteID + " flagged as: " + emojiFlag);
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
