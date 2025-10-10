package seedu.fitnessone.ui;


import seedu.fitnessone.command.AddTrainingNotes;
import seedu.fitnessone.command.Command;
import seedu.fitnessone.command.ExitCommand;
import seedu.fitnessone.command.NewAthleteCommand;
import seedu.fitnessone.command.DeleteAthleteCommand;
import seedu.fitnessone.command.ListAthleteCommand;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.command.CompleteSession;
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

        case "newAthlete":
            return new NewAthleteCommand(trimmedInput);


        case "listAthlete":
            return new ListAthleteCommand();


        case "delAthlete":
            return new DeleteAthleteCommand(trimmedInput);

            /*
            * Add a Training Note to a Session `/trainingNotes <Athlete Name> <Session ID> <Notes>`
            */
        case "trainingNotes":
            return new AddTrainingNotes(trimmedInput);

            /*
            * * Mark a Session as Completed `/complete <Athlete Name> <Session ID>`
            */
        case "complete":
            return new CompleteSession(trimmedInput);

        default:
            throw new InvalidCommandException("input keyword not found");
        }
    }
}
