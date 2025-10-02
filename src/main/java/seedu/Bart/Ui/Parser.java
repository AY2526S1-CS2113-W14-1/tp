
/**
 * Parses user input and returns the corresponding command object.
 * Responsible for interpreting user instructions for the chatbot.
 */
package seedu.Bart.Ui;

import Bart.Commands.*;
import Bart.Exceptions.InvalidCommandException;

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

            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(trimmedInput);

            case "unmark":
                return new UnmarkCommand(trimmedInput);

            case "todo":
                return new TodoCommand(trimmedInput);

            case "deadline":
                return new DeadlineCommand(trimmedInput);

            case "event":
                return new EventCommand(trimmedInput);

            case "delete":
                return new DeleteCommand(trimmedInput);

            case "find":
                return new FindCommand(trimmedInput);

            default:
                throw new InvalidCommandException("input keyword not found");
        }
    }
}
