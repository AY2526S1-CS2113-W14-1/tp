package seedu.fitnessone.exception;

public class InvalidCommandException extends Throwable {

    public InvalidCommandException(String message, boolean custom) {
        super(message);
    }

    public InvalidCommandException(String command) {
        super("Unknown command: '" + command + "'. Type /help to see available commands.");
    }

}
