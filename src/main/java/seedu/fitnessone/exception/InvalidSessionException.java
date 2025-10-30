package seedu.fitnessone.exception;

public class InvalidSessionException extends Throwable {


    public InvalidSessionException() {
        super("The specified session is invalid or does not exist.");
    }

    public InvalidSessionException(String sessionID) {
        super("Session with ID '" + sessionID + "' could not be found.");
    }
}
