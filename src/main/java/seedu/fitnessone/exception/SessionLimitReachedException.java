package seedu.fitnessone.exception;

public class SessionLimitReachedException extends InvalidCommandException {

    public SessionLimitReachedException() {
        super("Cannot create more sessions for this athlete. Maximum of 999 sessions reached.", true);
    }

    public SessionLimitReachedException(String message, boolean custom) {
        super(message, custom);
    }
}
