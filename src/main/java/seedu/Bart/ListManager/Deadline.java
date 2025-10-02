package seedu.Bart.ListManager;

import Bart.Exceptions.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends ListItem {
    private LocalDate by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);

    }

    @Override
    public String toString() {
        String isDoneText = this.getIsMarked() ? "[X]" : "[ ]";

        return "[D]" + isDoneText + " " + this.getDescription() + " (by: " + this.by + ")";
    }
}
