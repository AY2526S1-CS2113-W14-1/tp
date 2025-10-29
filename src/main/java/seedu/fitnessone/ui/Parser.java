package seedu.fitnessone.ui;


import seedu.fitnessone.command.Command;
import seedu.fitnessone.command.CompleteExerciseCommand;
import seedu.fitnessone.command.CompleteSessionCommand;
import seedu.fitnessone.command.DeleteExerciseCommand;
import seedu.fitnessone.command.DeleteSessionCommand;
import seedu.fitnessone.command.ExitCommand;
import seedu.fitnessone.command.LeaderboardCommand;
import seedu.fitnessone.command.NewExerciseCommand;
import seedu.fitnessone.command.NewSessionCommand;
import seedu.fitnessone.command.NewAthleteCommand;
import seedu.fitnessone.command.DeleteAthleteCommand;
import seedu.fitnessone.command.ListAthleteCommand;
import seedu.fitnessone.command.ViewAthleteCommand;
import seedu.fitnessone.command.ViewSessionsCommand;
import seedu.fitnessone.command.ViewExerciseCommand;
import seedu.fitnessone.command.UndoExerciseCommand;
import seedu.fitnessone.command.UndoSessionCommand;
import seedu.fitnessone.command.UpdateSessionNotesCommand;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.command.FlagAthleteCommand;

public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * @param input The raw user input string.
     * @return The corresponding Command object for the input.
     * @throws InvalidCommandException if the input is empty or the command is not recognized.
     */
    public static Command parse(String input) throws InvalidAthleteException, InvalidExerciseException,
            InvalidSessionException, InvalidCommandException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException("Input cannot be empty");
        }
        String trimmedInput = input.trim().toLowerCase();
        String commandWord = trimmedInput.split("\\s+", 2)[0];

        return switch (commandWord) {
        case "bye" -> new ExitCommand();
        /*------------------ATHLETE COMMANDS ------------------ */
        case "/newathlete" -> new NewAthleteCommand(trimmedInput);
        case "/viewathlete" -> new ViewAthleteCommand(trimmedInput);
        case "/listathletes" -> new ListAthleteCommand();
        case "/deleteathlete" -> new DeleteAthleteCommand(trimmedInput);
        case "/flagathlete" -> new FlagAthleteCommand(trimmedInput);
        case "/leaderboard" -> new LeaderboardCommand();
        /*------------------SESSION COMMANDS ------------------ */
        case "/newsession" -> new NewSessionCommand(trimmedInput);
        case "/deletesession" -> new DeleteSessionCommand(trimmedInput);
        case "/completesession" -> new CompleteSessionCommand(trimmedInput);
        case "/viewsessions" -> new ViewSessionsCommand(trimmedInput);
        case "/updatesessionnote" -> new UpdateSessionNotesCommand(trimmedInput);
        case "/undosession" -> new UndoSessionCommand(trimmedInput);
        /*------------------EXERCISE COMMANDS ------------------ */
        case "/newexercise" -> new NewExerciseCommand(trimmedInput);
        case "/deleteexercise" -> new DeleteExerciseCommand(trimmedInput);
        case "/viewexercises" -> new ViewExerciseCommand(trimmedInput);
        case "/completeexercise" -> new CompleteExerciseCommand(trimmedInput);
        case "/undoexercise" -> new UndoExerciseCommand(trimmedInput);
        default -> throw new InvalidCommandException("Input keyword not found.");
        };
    }

    /*HELPER FUNCTIONS
     * -----------------------------------
     * USE THESE
     */
    public static String checkAthleteIDValidity(String inputString) throws InvalidCommandException {
        inputString = inputString.trim();

        if (inputString.split(" ").length < 2) {
            throw new InvalidCommandException("Input is empty. Check if there's athletes or sessions created.");
        }

        String athleteID = inputString.split(" ")[1];

        athleteID = athleteID.trim();
        if (athleteID.isBlank()) {
            throw new InvalidCommandException("Athlete ID cannot be blank.");
        }
        if (athleteID.length() != 4) {
            throw new InvalidCommandException("Athlete ID must be 4 characters long.");
        }

        char[] athleteIDSplit = athleteID.toCharArray();

        for (char c : athleteIDSplit) {
            if (!Character.isDigit(c)) {
                throw new InvalidCommandException("Invalid format: Athlete ID must contain only digits.");
            }
        }
        return athleteID;
    }


    public static String checkSessionIDValidity(String inputString) throws InvalidCommandException {
        inputString = inputString.trim();

        if (inputString.split(" ").length < 3) {
            throw new InvalidCommandException("Invalid Command. Check if there's athletes or sessions created.");
        }

        String sessionID = inputString.split(" ")[2];

        // /<command> <athleteID> <Session>....

        sessionID = sessionID.trim();

        if (sessionID.isBlank()) {
            throw new InvalidCommandException("Session ID cannot be blank.");
        }
        if (sessionID.length() != 3) {
            throw new InvalidCommandException("Session ID must be 3 characters long.");
        }

        char[] sessionIDSplit = sessionID.toCharArray();

        for (char c : sessionIDSplit) {
            if (!Character.isDigit(c)) {
                throw new InvalidCommandException("Invalid format: Session ID must contain only digits.");
            }
        }
        return sessionID;
    }

    public static String checkExerciseIDValidity(String inputString) throws InvalidCommandException {
        inputString = inputString.trim();


        if (inputString.split(" ").length < 4) {
            throw new InvalidCommandException("Input is empty. Check if there's athletes or sessions created.");
        }

        String exerciseID = inputString.split(" ")[3];

        // /<command> <athleteID> <Session> <Exercise>...

        exerciseID = exerciseID.trim();

        if (exerciseID.isBlank()) {
            throw new InvalidCommandException("Exercise ID cannot be blank.");
        }
        if (exerciseID.length() != 2) {
            throw new InvalidCommandException("Exercise ID must be 3 characters long.");
        }

        char[] exerciseIDSplit = exerciseID.toCharArray();

        for (char c : exerciseIDSplit) {
            if (!Character.isDigit(c)) {
                throw new InvalidCommandException("Invalid format: Exercise ID must contain only digits.");
            }
        }
        return exerciseID;
    }

    public static String onlyDescription(String inputString) throws InvalidCommandException {
        inputString = inputString.trim();
        String[] parts = inputString.split("\\s+", 4); // parts[3] is the remainder (notes)

        if (parts.length < 4 || parts[3].trim().isEmpty()) {
            throw new InvalidCommandException("The correct format is:\n/trainingNotes <Athlete ID> <Session ID> ...");
        }

        return parts[3].trim();
    }


    public static String isTrainingNotes(String inputString) throws InvalidCommandException {
        inputString = inputString.trim();
        String[] parts = inputString.split("\\s+", 4); // parts[3] is the remainder (notes)

        if (parts.length < 4 || parts[3].trim().isEmpty()) {
            throw new InvalidCommandException("Training notes cannot be empty.\n" +
                    "The correct format is:\n" +
                    "/trainingNotes <Athlete ID> <Session ID> <Notes>");
        }

        return parts[3].trim();
    }


}

