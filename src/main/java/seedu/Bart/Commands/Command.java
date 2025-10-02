package seedu.Bart.Commands;

import Bart.Exceptions.InvalidCommandException;
import Bart.ListManager.TaskList;
import Bart.Ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui) throws InvalidCommandException;
    boolean isExit();
}
