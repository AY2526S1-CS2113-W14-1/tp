package seedu.fitnessone.exception;

public class InvalidAthleteException extends Throwable {

    public InvalidAthleteException(String message, boolean custom) {
        super(message);
    }

    public InvalidAthleteException(String athleteID) {
        super("Athlete with ID '" + athleteID + "' does not exist.\n" +
                "Tip: Use /listAthletes to see all available athletes.");
    }

    public InvalidAthleteException() {
        super("The specified athlete does not exist in the system.");
    }
}
