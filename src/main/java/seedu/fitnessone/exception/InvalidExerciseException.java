package seedu.fitnessone.exception;

public class InvalidExerciseException extends Throwable {

    public InvalidExerciseException(String message, boolean custom) {
        super(message);
    }

    public InvalidExerciseException() {
        super("The specified exercise does not exist or is invalid.");
    }

    public InvalidExerciseException(String exerciseID) {
        super("Exercise with ID '" + exerciseID + "' is not available in the system.");
    }
}
