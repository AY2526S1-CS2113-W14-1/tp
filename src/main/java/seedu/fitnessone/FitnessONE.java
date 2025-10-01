package seedu.fitnessone;

import seedu.fitnessone.Command.Command;
import seedu.fitnessone.Controller.Coach;
import seedu.fitnessone.Exception.InvalidCommandException;
import seedu.fitnessone.UI.Parser;
import seedu.fitnessone.UI.Ui;

public class FitnessONE {
    private Ui _ui;
    private Coach _coach;


    public FitnessONE() {
        _ui = new Ui();
        _coach = new Coach();

        _ui.showWelcome();
    }

    private void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = _ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(_coach, _ui);
                isExit = c.isExit();
            }
            catch (InvalidCommandException e) {
                _ui.printWithDivider(e.getMessage());
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
