
/**
 * Handles loading and saving of task data to and from a file.
 * Responsible for persisting the user's task list between sessions.
 */
package seedu.Bart.IO;

import Bart.Exceptions.*;
import Bart.ListManager.*;
import Bart.Ui.Ui;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final String fileName;


    /**
     * Constructs a Storage object for the specified file.
     * @param fileName The path to the file for storing task data.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Saves the given list of tasks to the file.
     * @param items The list of tasks to save.
     * @throws StorageException if an I/O error occurs during saving.
     */
    public void saveToFile(List<ListItem> items) throws StorageException{
        File file = new File(fileName);

        // create parent directories if needed
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (ListItem item : items) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new StorageException("StorageException: " + e.getMessage());

        }
    }


    /**
     * Loads tasks from the file and updates the given task list.
     * @param _taskList The task list to update with loaded tasks.
     * @throws FileMissingException if the file is missing or cannot be read.
     */
    public void saveFromFile(TaskList _taskList) throws FileMissingException {
        List<ListItem> items = this.parseFromFile();
        _taskList.updateItems(items);
    }

    /**
     * Parses the file and reconstructs the list of tasks from its contents.
     * @return The list of tasks loaded from the file.
     * @throws FileMissingException if the file is missing or cannot be read.
     */
    private List<ListItem> parseFromFile() throws FileMissingException {
        List<ListItem> items = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() < 7) {
                    throw new CorruptStorageException("Invalid line format: " + line);
                }
                char type = line.charAt(1); // T, E, or D
                boolean isMarked = line.charAt(4) == 'X';

                switch (type) {
                    case 'T': {
                        // Format: [T][ ] description
                        String description = line.substring(7);
                        Todo todo = new Todo(description);
                        if (isMarked) todo.markThis();
                        items.add(todo);
                        break;
                    }
                    case 'E': {
                        // Format: [E][ ] name (from: start to: end)
                        int startIndex = line.indexOf("] ") + 2;
                        int fromIndex = line.indexOf("(from: ");
                        if (fromIndex == -1) {
                            throw new StorageException("Missing (from:) in event line: " + line);
                        }
                        String name = line.substring(startIndex, fromIndex - 1);

                        int toIndex = line.indexOf(" to: ");
                        int endIndex = line.indexOf(")", toIndex);
                        if (toIndex == -1 || endIndex == -1) {
                            throw new StorageException("Invalid event time format in line: " + line);
                        }

                        String start = line.substring(fromIndex + 7, toIndex);
                        String end = line.substring(toIndex + 5, endIndex);

                        Event event = new Event(name, start, end);
                        if (isMarked) event.markThis();
                        items.add(event);
                        break;
                    }
                    case 'D': {
                        // Format: [D][ ] name (by: due)
                        int startIndex = line.indexOf("] ") + 2;
                        int byIndex = line.indexOf("(by: ");
                        int endIndex = line.indexOf(")", byIndex);
                        if (byIndex == -1 || endIndex == -1) {
                            throw new StorageException("Invalid deadline format in line: " + line);
                        }
                        String name = line.substring(startIndex, byIndex - 1);
                        String by = line.substring(byIndex + 5, endIndex);

                        Deadline deadline = new Deadline(name, by);
                        if (isMarked) deadline.markThis();
                        items.add(deadline);
                        break;
                    }
                    default:
                        throw new StorageException("Unknown task type in line: " + line);
                }
            }
        } catch (IOException e) {
            throw new FileMissingException("No save data found: " + e.getMessage());

        } catch (DateTimeParseException e) {
            throw new CorruptStorageException("Save data corrupted: " + e.getMessage());

        }

        return items;
    }

}
