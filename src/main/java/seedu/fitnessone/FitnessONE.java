package seedu.fitnessone;

import seedu.fitnessone.command.Command;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidIDException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class FitnessONE {
    private final Ui view;
    private final Coach coachController;

    public FitnessONE() {
        view = new Ui();
        coachController = new Coach();

        view.showWelcome();
    }

    private void run() {
        boolean isExit = false;
        while (!isExit) {
            Command c = null;
            try {
                String userInput = view.readCommand();
                c = Parser.parse(userInput);
                c.execute(coachController, view);
                isExit = c.isExit();
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

            } catch (InvalidSessionException | InvalidExerciseException | InvalidAthleteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new FitnessONE().run();
    }

}
