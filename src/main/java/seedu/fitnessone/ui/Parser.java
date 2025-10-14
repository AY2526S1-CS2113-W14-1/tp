package seedu.fitnessone.ui;

import seedu.fitnessone.command.AddTrainingNotes;
import seedu.fitnessone.command.Command;
import seedu.fitnessone.command.DeleteSessionCommand;
import seedu.fitnessone.command.ExitCommand;
import seedu.fitnessone.command.NewSessionCommand;
import seedu.fitnessone.command.NewAthleteCommand;
import seedu.fitnessone.command.DeleteAthleteCommand;
import seedu.fitnessone.command.ListAthleteCommand;
import seedu.fitnessone.command.ViewAthleteCommand;
import seedu.fitnessone.command.ViewSessionCommand;
import seedu.fitnessone.command.ViewExerciseCommand;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.command.CompleteSession;

public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object.
     *
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
        String info;
        String[] parts;
        String athleteName;
        String[] sessionInfo;
        int sessionId;
        String trainingNotes;

        switch (commandWord) {
        case "bye":
            return new ExitCommand();

        case "/newSession":
            try {
                info = trimmedInput.split("\\s+", 2)[1];
                parts = info.split(" (?=\\d)", 2);
                athleteName = parts[0];
                sessionInfo = parts[1].split("\\s+", 2);
                sessionId = Integer.parseInt(sessionInfo[0]);
                trainingNotes = sessionInfo[1];
                return new NewSessionCommand(athleteName, sessionId, trainingNotes);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                throw new InvalidCommandException("Oops, seems like you forgot something?\n" +
                            "The correct format is\n" +
                            "/newSession <Athlete Name> <Session ID> <Description>");
            }

        case "/deleteSession":
            try {
                info = trimmedInput.split("\\s+", 2)[1];
                parts = info.split(" (?=\\d)", 2);
                athleteName = parts[0];
                sessionId = Integer.parseInt(parts[1]);
                return new DeleteSessionCommand(athleteName, sessionId);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                throw new InvalidCommandException("Oops, seems like you forgot something?\n" +
                            "The correct format is\n" +
                            "/deleteSession <Athlete Name> <Session ID>");
            }

        case "/newAthlete":
            return new NewAthleteCommand(trimmedInput);

        case "/listAthlete":
            return new ListAthleteCommand();

        case "/delAthlete":
            return new DeleteAthleteCommand(trimmedInput);

        /*
         * Add a Training Note to a Session `/trainingNotes <Athlete Name> <Session ID> <Notes>`
         */
        case "/trainingNotes":
            return new AddTrainingNotes(trimmedInput);

        /*
         * * Mark a Session as Completed `/complete <Athlete Name> <Session ID>`
         */
        case "/trainingNotes":
            return new AddTrainingNotes(trimmedInput);

        case "/complete":
            return new CompleteSession(trimmedInput);

        case "/viewAthlete":
            try {
                info = trimmedInput.split("\\s+", 2)[1];
                athleteName = info.trim();
                return new ViewAthleteCommand(athleteName);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new InvalidCommandException("Oops, seems like you forgot something?\n" +
                            "The correct format is\n" +
                            "/viewAthlete <Athlete Name>");
            }

        case "/viewSession":
            try {
                info = trimmedInput.split("\\s+", 2)[1];
                athleteName = info.trim();
                return new ViewSessionCommand(athleteName);
            } catch (Exception e) {
                throw new InvalidCommandException("Oops, seems like you forgot something?\n" +
                            "The correct format is\n" +
                            "/viewSession <Athlete Name>");
            }

        case "/viewExercise":
            try {
                info = trimmedInput.split("\\s+", 2)[1]; // 获取剩余参数
                parts = info.split("\\s+", 2);           // 按空格分成两部分：athleteName 和 sessionID
                athleteName = parts[0].trim();
                sessionId = Integer.parseInt(parts[1].trim());
                return new ViewExerciseCommand(athleteName, sessionId);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException e) {
                throw new InvalidCommandException("Oops, seems like you forgot something?\n" +
                            "The correct format is\n" +
                            "/viewExercise <Athlete Name> <Session ID>");
            }

        default:
            throw new InvalidCommandException("Input keyword not found");
        }
    }
}
