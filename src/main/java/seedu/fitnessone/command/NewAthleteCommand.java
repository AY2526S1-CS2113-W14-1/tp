package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class NewAthleteCommand implements Command {
    private static final String COMMAND_WORD = "/newathlete";
    private static final String USAGE = "/newathlete <Athlete Name>";
    private static final String DESCRIPTION = "Creates a new log for a new athlete with a specified name";
    private static final String EXAMPLE = "/newathlete Jonas Hardwell";
    private static final String NOTE = null;

    private final String inputString;


    public NewAthleteCommand(String inputString) throws InvalidCommandException {
        if (inputString == null || inputString.trim().isEmpty()) {
            throw new InvalidCommandException("Athlete name cannot be empty.");
        }
        this.inputString = inputString;

    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {
        // Guard: ensure there is a name after the command word
        if (inputString.length() <= COMMAND_WORD.length()) {
            throw new InvalidCommandException("Athlete name was not specified. Usage: " + USAGE);
        }

        String rawName = inputString.substring(COMMAND_WORD.length()).trim();
        if (rawName.isBlank()) {
            throw new InvalidCommandException("Athlete name was not specified. Usage: " + USAGE);
        }

        String athleteName = normalizeName(rawName);

        String itemToString = coachController.newAthlete(athleteName);
        view.printWithDivider("Athlete added:" + System.lineSeparator() + "     " + itemToString);
    }

    /**
     * Normalizes an input name by capitalizing each word (title-case),
     * while preserving special tokens such as "s/o" exactly as "s/o".
     * Examples:
     *  - "adam" -> "Adam"
     *  - "john s/o doe" -> "John s/o Doe"
     */
    private static String normalizeName(String raw) {
        String[] parts = raw.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String token = parts[i];
            String normalized;
            if (token.equalsIgnoreCase("s/o")) {
                normalized = "s/o"; // keep canonical lowercase for this token
            } else if (token.isEmpty()) {
                normalized = token;
            } else {
                // Title-case: first char upper, rest lower
                char first = token.charAt(0);
                String rest = token.length() > 1 ? token.substring(1).toLowerCase() : "";
                normalized = Character.toUpperCase(first) + rest;
            }

            if (i > 0) {
                sb.append(' ');
            }
            sb.append(normalized);
        }
        return sb.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void help(Ui view) {
        view.divider();
        view.println("Command: " + COMMAND_WORD);
        view.println("Usage: " + USAGE);
        view.println("Description: " + DESCRIPTION);
        view.println("Example: " + EXAMPLE);
        view.divider();
    }
}
