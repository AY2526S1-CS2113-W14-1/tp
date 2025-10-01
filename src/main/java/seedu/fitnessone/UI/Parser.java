package seedu.fitnessone.UI;

import seedu.fitnessone.Command.Command;
import seedu.fitnessone.Command.ExitCommand;
import seedu.fitnessone.Exception.InvalidCommandException;

public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object.
     * @param input The raw user input string.
     * @return The corresponding Command object for the input.
     * @throws InvalidCommandException if the input is empty or the command is not recognized.
     */
    public static Command parse(String input) throws InvalidCommandException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException("Input cannot be empty");
        }
        String trimmedInput = input.trim();
        String commandWord = trimmedInput.split("\\s+", 2)[0];
        switch (commandWord) {
            case "bye":
                return new ExitCommand();

            default:
                throw new InvalidCommandException("input keyword not found");
        }
    }
}
