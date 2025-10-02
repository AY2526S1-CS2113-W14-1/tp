
/**
 * Represents a command to find tasks containing a keyword in the task list.
 * Parses user input for the keyword and displays matching tasks when executed.
 */
package seedu.Bart.Commands;

import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.ListItem;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

import java.util.List;

public class FindCommand implements Command {
    private final String keyword;


    /**
     * Constructs a FindCommand by parsing the input for the search keyword.
     * @param trimmedInput The user input string after the command keyword.
     * @throws InvalidCommandException if the keyword is missing or empty.
     */
    public FindCommand(String trimmedInput) throws InvalidCommandException {
        if (trimmedInput.length() <= 5) {
            throw new InvalidCommandException("The find command requires a keyword.");
        }
        this.keyword = trimmedInput.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new InvalidCommandException("The find keyword cannot be empty.");
        }
    }


    /**
     * Executes the command to find and display tasks containing the keyword.
     * @param tasks The task list to search.
     * @param ui The UI for displaying output.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        List<ListItem> results = tasks.find(keyword);
        ui.divider();
        ui.print("Here are the matching tasks in your list:");
        int i = 1;
        for (ListItem item : results) {
            ui.print(i + "." + item.toString());
            i++;
        }
        if (results.isEmpty()) {
            ui.print("No matching tasks found.");
        }
        ui.divider();
    }

    /**
     * Indicates whether this command should exit the application.
     * @return false, as finding does not exit the app.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
