package Bart.ListManager;

public class Event extends ListItem {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String isDoneText = this.getIsMarked() ? "[X]" : "[ ]";

        return "[E]" + isDoneText + " " + this.getDescription() + " (from: " + this.start + " to: " + this.end + ")";
    }

}
