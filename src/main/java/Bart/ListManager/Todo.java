package Bart.ListManager;

public class Todo extends ListItem {
    // [T][ ] <description>

    public Todo(String name) {
        super(name);

    }

    @Override
    public String toString() {
        String isDoneText = this.getIsMarked() ? "[X]" : "[ ]";

        return "[T]" + isDoneText + " " + this.getDescription();
    }
}
