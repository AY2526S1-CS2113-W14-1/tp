package seedu.fitnessone.exception;

public class InvalidIDException extends RuntimeException {

    public InvalidIDException(String message) {
        super(message);
    }

    public InvalidIDException() {
        super("The provided ID is invalid or out of range.");
    }

    public InvalidIDException(int id, String entityType) {
        super("Invalid " + entityType + " ID: " + id + ". Please provide a valid positive integer.");
    }
}
