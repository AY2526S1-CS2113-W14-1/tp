package seedu.fitnessone.exception;

public class ExerciseLimitReachedException extends InvalidExerciseException {

    public ExerciseLimitReachedException() {
        super("Cannot create more exercises in this session. Maximum of 99 exercises reached.", true);
    }

    public ExerciseLimitReachedException(String message, boolean custom) {
        super(message, custom);
    }
}
