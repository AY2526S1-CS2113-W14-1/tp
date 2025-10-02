
/**
 * Represents a command to add an event task to the task list.
 * Parses user input for event details and adds the event when executed.
 */
package Bart.Commands;

import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

public class EventCommand implements Command {


    private final String description;
    private final String start;
    private final String end;


    /**
     * Constructs an EventCommand by parsing the input for description, start, and end times.
     * @param trimmedInput The user input string after the command keyword.
     * @throws InvalidCommandException if required parameters are missing.
     */
    public EventCommand(String trimmedInput) throws InvalidCommandException {
        String input = trimmedInput.substring(6).trim();
        int fromIndex = input.indexOf(" /from ");
        int toIndex = input.indexOf(" /to ");
        if (fromIndex == -1) {
            throw new InvalidCommandException("/from parameter missing!");
        }
        if (toIndex == -1) {
            throw new InvalidCommandException("/to parameter missing!");
        }
        description = input.substring(0, fromIndex);
        start = input.substring(fromIndex + " /from ".length(), toIndex);
        end = input.substring(toIndex + " /to ".length());
    }


    /**
     * Executes the command to add an event to the task list and display confirmation.
     * @param tasks The task list to add the event to.
     * @param ui The UI for displaying output.
     * @throws InvalidCommandException if the event cannot be added.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidCommandException {
        String itemToString = tasks.addEvent(description, start, end);
        ui.printWithDivider("event added." + System.lineSeparator() + "  " + itemToString);
    }

    /**
     * Indicates whether this command should exit the application.
     * @return false, as adding an event does not exit the app.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
