
/**
 * Manages the user's list of tasks, including todos, deadlines, and events.
 * Provides methods to add, remove, mark, unmark, and search for tasks.
 */
package Bart.ListManager;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks, including todos, deadlines, and events.
 * Provides methods to add, mark, unmark, and print tasks.
 */
public class TaskList {
    private List<ListItem> items;


    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.items = new ArrayList<>();
    }


    /**
     * Returns the list of tasks in this TaskList.
     * @return The list of tasks.
     */
    public List<ListItem> getItems() {
        return items;
    }


    /**
     * Replaces the current list of tasks with the given list.
     * @param items The new list of tasks.
     */
    public void updateItems(List<ListItem> items) {
        this.items = items;
    }


    /**
     * Adds a new Todo task to the list.
     * @param name The description of the Todo.
     * @return String representation of the added Todo.
     */
    public String addTodo(String name) {
        ListItem todo = new Todo(name);
        items.add(todo);
        return todo.toString();
    }


    /**
     * Adds a new Deadline task to the list.
     * @param name The description of the Deadline.
     * @param by The due date of the Deadline.
     * @return String representation of the added Deadline.
     * @throws DateTimeParseException if the date format is invalid.
     */
    public String addDeadline(String name, String by) throws DateTimeParseException {
        ListItem deadline = new Deadline(name, by);
        items.add(deadline);
        return deadline.toString();
    }


    /**
     * Adds a new Event task to the list.
     * @param name The description of the Event.
     * @param start The start time of the Event.
     * @param end The end time of the Event.
     * @return String representation of the added Event.
     */
    public String addEvent(String name, String start, String end) {
        ListItem event = new Event(name, start, end);
        items.add(event);
        return event.toString();
    }


    /**
     * Deletes the task at the specified index from the list.
     * @param index The index of the task to delete (0-based).
     * @return String representation of the deleted task.
     */
    public String deleteItem(int index) {
        ListItem item = items.get(index);
        items.remove(index);
        return item.toString();
    }


    /**
     * Marks the task at the specified index as done.
     * @param index The index of the task to mark (0-based).
     */
    public void markItem(int index) {
        items.get(index).markThis();
    }


    /**
     * Marks the task at the specified index as not done.
     * @param index The index of the task to unmark (0-based).
     */
    public void unmarkItem(int index) {
        items.get(index).unmarkThis();
    }


    /**
     * Prints all tasks in the list with their indices and details.
     * Used to display the current state of the task list to the user.
     */
    public void printItems() {
        for (int i = 0; i < items.toArray().length; i++) {
            int index = i + 1;
            System.out.print("       " + index + ". ");
            System.out.println(items.get(i).toString());
        }
    }


    /**
     * Finds and returns all tasks containing the given keyword.
     * @param keyword The keyword to search for.
     * @return List of tasks whose string representation contains the keyword.
     */
    public List<ListItem> find(String keyword) {
        List<ListItem> matchingItems = new ArrayList<>();
        for (ListItem item : items) {
            if (item.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }
}
