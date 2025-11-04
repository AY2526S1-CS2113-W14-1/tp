package seedu.fitnessone;

import seedu.fitnessone.command.Command;
import seedu.fitnessone.controller.Coach;
import java.io.IOException;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidIDException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;
import seedu.fitnessone.storage.StorageManager;
import seedu.fitnessone.exception.AthleteLimitReachedException;
import seedu.fitnessone.exception.SessionLimitReachedException;
import seedu.fitnessone.exception.ExerciseLimitReachedException;

public class FitnessONE {
    private final Ui view;
    private Coach coachController;
    private final StorageManager storage;

    /**
     * Constructs a new FitnessONE application instance.
     * Initializes the UI, storage manager, and attempts to load saved athlete data
     * from the data file. If loading fails (file missing or corrupted), starts with
     * an empty coach controller and displays an error message to the user.
     */
    public FitnessONE() {
        view = new Ui();
        storage = new StorageManager("data/athletes_export.txt");
        // try loading saved data; fall back to empty coach if load fails
        try {
            coachController = storage.load();
            view.showWelcome();
            view.printWithDivider("Loaded saved athletes data.");
        } catch (IOException e) {
            coachController = new Coach();
            view.showWelcome();
            // If loading failed, it may be because the file is missing or the file
            // contents are corrupted/unreadable. Give the user clearer feedback.
            String msg = e.getMessage() == null ? "" : (": " + e.getMessage());
            String errorMsg = String.format("Failed to load saved data (missing or corrupted)%s," 
                    + " starting with empty data.", msg);
            view.printWithDivider(errorMsg);
        }
    }

    /**
     * Runs the main command loop of the FitnessONE application.
     * Continuously reads user input, parses commands, executes them, and handles exceptions.
     * After each successful command execution, persists the current state to the data file.
     * Continues until the user executes an exit command. Catches and displays error messages
     * for all expected exception types (InvalidCommand, InvalidID, InvalidSession, 
     * InvalidExercise, InvalidAthlete).
     */
    private void run() {
        boolean isExit = false;
        while (!isExit) {
            Command c = null;
            try {
                String userInput = view.readCommand();
                c = Parser.parse(userInput);
                c.execute(coachController, view);
                isExit = c.isExit();
                // persist state after every successful command
                try {
                    storage.save(coachController);
                } catch (IOException ioEx) {
                    view.printWithDivider("Failed to save data: " + ioEx.getMessage());
                }
            } catch (AthleteLimitReachedException e) {
                view.printWithDivider("AthleteLimitReachedException " + e.getMessage());

            } catch (SessionLimitReachedException e) {
                view.printWithDivider("SessionLimitReachedException " + e.getMessage());

            } catch (ExerciseLimitReachedException e) {
                view.printWithDivider("ExerciseLimitReachedException " + e.getMessage());

            } catch (InvalidCommandException e) {
                view.printWithDivider("InvalidCommandException: " + e.getMessage());
                if (c != null) {
                    c.help(view);
                }
            } catch (InvalidIDException e) {
                view.printWithDivider("InvalidIDException: " + e.getMessage());
                if (c != null) {
                    c.help(view);
                }
            } catch (InvalidSessionException e) {
                view.printWithDivider("InvalidSessionException " + e.getMessage());

            } catch (InvalidExerciseException e) {
                view.printWithDivider("InvalidExerciseException " + e.getMessage());

            } catch (InvalidAthleteException e) {
                view.printWithDivider("InvalidAthleteException " + e.getMessage());

            }
        }
    }

    /**
     * Main entry point for the FitnessONE fitness tracking application.
     * Creates a new FitnessONE instance and starts the command loop.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new FitnessONE().run();
    }

}
