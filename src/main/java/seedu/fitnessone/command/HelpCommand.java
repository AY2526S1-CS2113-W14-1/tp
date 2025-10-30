package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.ui.Ui;

public class HelpCommand implements Command {
    private static final String COMMAND_WORD = "/help";

    public HelpCommand(String inputString) {
    }

    @Override
    public void execute(Coach coachController, Ui view) {
        view.divider();
        view.println("FitnessONE - Available Commands");
        view.println("");

        // ATHLETE COMMANDS
        view.println("=== ATHLETE COMMANDS ===");
        view.println("/newathlete <Name> - Create a new athlete");
        view.println("/viewathlete <Athlete ID> - View athlete details");
        view.println("/listathletes - List all athletes");
        view.println("/deleteathlete <Athlete ID> - Delete an athlete");
        view.println("/flagathlete <Athlete ID> <Color> - Flag an athlete");
        view.println("");

        // SESSION COMMANDS
        view.println("=== SESSION COMMANDS ===");
        view.println("/newsession <Athlete ID> <Notes> - Create a new session");
        view.println("/deletesession <Athlete ID> <Session ID> - Delete a session");
        view.println("/completesession <Athlete ID> <Session ID> - Complete a session");
        view.println("/viewsessions <Athlete ID> - View all sessions");
        view.println("/updatesessionnote <Athlete ID> <Session ID> <Notes> - Update notes");
        view.println("/undosession <Athlete ID> <Session ID> - Undo completion");
        view.println("");

        // EXERCISE COMMANDS
        view.println("=== EXERCISE COMMANDS ===");
        view.println("/newexercise <Athlete ID> <Session ID> <Name> - Create exercise");
        view.println("/deleteexercise <Athlete ID> <Session ID> <Exercise ID> - Delete exercise");
        view.println("/viewexercises <Athlete ID> <Session ID> - View all exercises");
        view.println("/completeexercise <Athlete ID> <Session ID> <Exercise ID> - Complete exercise");
        view.println("/undoexercise <Athlete ID> <Session ID> <Exercise ID> - Undo completion");
        view.println("");

        // OTHER
        view.println("=== OTHER COMMANDS ===");
        view.println("/help - Show this help message");
        view.println("bye - Exit the application");
        view.println("/leaderboard - Show the leaderboard");
        view.println("");
        view.println("Tip: Each command has its own help. Try using an incorrect format to see details!");
        view.divider();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void help(Ui view) {
        view.divider();
        view.println("Command: " + COMMAND_WORD);
        view.println("Usage: /help");
        view.println("Description: Displays all available commands");
        view.println("Example: /help");
        view.divider();
    }
}
