package seedu.fitnessone;

import seedu.fitnessone.command.Command;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidIDException;
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
            try {
                String userInput = view.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(coachController, view);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                view.printWithDivider("InvalidCommandException: " + e.getMessage());

            } catch (InvalidIDException e) {
                view.printWithDivider("InvalidIDException: " + e.getMessage());

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