package seedu.fitnessone.command;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

import java.util.Arrays;

public class FlagAthleteCommand implements Command {
    private final String athleteName;
    private final String flagColor;

    public FlagAthleteCommand(String trimmedInput) throws InvalidCommandException{
        try {
            String info = trimmedInput.split("\\s+", 2)[1];
            String[] tokens = info.split("\\s+");
            if  (tokens.length < 2) {
                throw new InvalidCommandException("oops, seems like you forgot smth?\n" +
                        "the correct format is\n" +
                        "/flagAthlete <Athlete Name> <Flag Color>");
            }
            flagColor = tokens[tokens.length - 1];
            athleteName = String.join(" ", Arrays.copyOf(tokens, tokens.length - 1));
        }catch (Exception e) {
            throw new InvalidCommandException("oops, seems like you forgot smth?\n" +
                    "the correct format is\n" +
                    "/flagAthlete <Athlete Name> <Flag Color>");
        }
    }

    private String colorToEmoji(String color) {
        return switch (color.toLowerCase()) {
        case "red" -> "\uD83D\uDFE5";
        case "yellow" -> "\uD83D\uDFE8";
        case "green" -> "\uD83D\uDFE9";
        case "blue" -> "\uD83D\uDFE6";
        case "orange" -> "\uD83D\uDFE7";
        case "purple" -> "\uD83D\uDFEA";
        case "brown" -> "\uD83D\uDFEB";
        case "black" -> "⬛";
        case "white" -> "⬜";
        default -> color;
        };
    }


    @Override
    public void execute(Coach coachController, Ui view){
        try {
            String emojiFlag = colorToEmoji(flagColor);
            coachController.flagAthlete(athleteName, emojiFlag);
            view.printWithDivider("Athlete " + athleteName + " flagged as: " + emojiFlag);
        } catch (InvalidAthleteException e) {
            view.printWithDivider("Error: Athlete not found - " + athleteName);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
