package seedu.fitnessone.exception;

public class AthleteLimitReachedException extends InvalidCommandException {

    public AthleteLimitReachedException() {
        super("Cannot create more athletes. Maximum of 9999 athletes has been reached.", true);
    }

    public AthleteLimitReachedException(String message, boolean custom) {
        super(message, custom);
    }
}
