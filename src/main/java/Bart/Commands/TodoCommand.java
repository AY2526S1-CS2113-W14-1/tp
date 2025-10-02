
/**
 * Represents a command to add a todo task to the task list.
 * Parses user input for the todo description and adds the todo when executed.
 */
package Bart.Commands;

import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

public class TodoCommand implements Command {
    private final String name;


    /**
     * Constructs a TodoCommand by parsing the input for the todo description.
     * @param trimmedInput The user input string after the command keyword.
     * @throws InvalidCommandException if no todo item is specified.
     */
    public TodoCommand(String trimmedInput) throws InvalidCommandException {
        try {
            name = trimmedInput.substring(5).trim();
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException("no todo item was specified");
        }
    }


    /**
     * Executes the command to add a todo to the task list and display confirmation.
     * @param tasks The task list to add the todo to.
     * @param ui The UI for displaying output.
     * @throws InvalidCommandException if the todo description is blank.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidCommandException {
        if (name.isBlank()) {
            throw new InvalidCommandException("no todo item was specified.");
        }
        String itemToString = tasks.addTodo(name);
        ui.printWithDivider("todo added." + System.lineSeparator() + "  " + itemToString);
    }

    /**
     * Indicates whether this command should exit the application.
     * @return false, as adding a todo does not exit the app.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
