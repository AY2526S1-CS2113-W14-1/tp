package seedu.Bart;

import java.time.format.DateTimeParseException;

import Bart.Commands.Command;
import Bart.Exceptions.CorruptStorageException;
import Bart.Exceptions.FileMissingException;
import Bart.Exceptions.InvalidCommandException;
import Bart.Exceptions.StorageException;
import Bart.IO.Storage;
import Bart.ListManager.ListItem;
import Bart.ListManager.TaskList;
import Bart.Ui.Parser;
import Bart.Ui.Ui;


/**
 * The main entry point for the Bart chatbot application.
 * Handles user interaction, command parsing, and manages the task list lifecycle.
 */
public class Bart {

    private final Ui _ui;
    private final TaskList _taskList;
    private final Storage _storage;



    /**
     * Starts the Bart chatbot application.
     * Initializes components and processes user commands until exit is requested.
     */

    public static void main(String[] args) {
        new Bart("./data/data.txt").Run();
    }


    /**
     * Constructs a Bart instance, initializing UI, task list, and storage.
     * Loads saved data if available, or creates new storage if not.
     * @param filePath The path to the file for storing task data.
     */
    public Bart(String filePath) {
        _ui = new Ui();
        _taskList = new TaskList();
        _storage = new Storage(filePath);
        _ui.showWelcome();
        try {
            _storage.saveFromFile(_taskList);
            _ui.printWithDivider("Save data retrieved.");
        }
        catch (StorageException e) {
            _ui.printWithDivider("StorageException: " + e.getMessage());
        }
        catch (FileMissingException e) {
            _ui.printWithDivider("No save data found, creating new..." + e.getMessage());
        }
        catch (CorruptStorageException e) {
            _ui.printWithDivider("Save data bad format?");
        }
    }


    /**
     * Runs the main command loop, processing user input until exit is requested.
     * Handles command execution and error reporting.
     */
    public void Run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = _ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(_taskList, _ui);
                isExit = c.isExit();
                _storage.saveToFile(_taskList.getItems());
            }
            catch (InvalidCommandException e) {
                _ui.printWithDivider("InvalidCommandException: " + e.getMessage());
            }
            catch (StorageException e) {
                _ui.printWithDivider("StorageException: " + e.getMessage());
            }
            catch (DateTimeParseException e) {
                _ui.printWithDivider("DateTimeParseException: " + e.getMessage());
            }
        }
    }


}


