package seedu.Bart.ListManager;

// needs to be public to be accessible by Bart.IO
public class ListItem {
    private boolean isMarked = false;
    private String description;

    public ListItem(String description) {
        this.isMarked = false;
        this.description = description;
    }

    public boolean getIsMarked() {
        return this.isMarked;
    }

    public void markThis() {
        this.isMarked = true;
    }

    public void unmarkThis() {
        this.isMarked = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "";
    }
}