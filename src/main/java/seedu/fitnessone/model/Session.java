package seedu.fitnessone.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import seedu.fitnessone.exception.InvalidExerciseException;

public class Session {
    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final ArrayList<Exercise> exercises;
    private int sessionIndex;
    private String trainingNotes;
    private boolean isCompleted;
    private final String sessionIdString;
    private int nextExerciseIndex = 0;
    private LocalDateTime sessionDate;
    private String sessionDateString;



    public Session(int sessionIndex, String trainingNotes) {
        this.sessionIndex = sessionIndex;
        this.trainingNotes = trainingNotes;
        this.exercises = new ArrayList<>();
        this.isCompleted = false;
        this.sessionIdString = String.format("%03d", sessionIndex);

        if(trainingNotes.trim().isEmpty()) {
            this.trainingNotes = "EMPTY. Add sessions notes with: /UpdateSessionNote <athlete> <session> <notes>";
        }

        this.sessionDate = LocalDateTime.now();
        this.sessionDateString = this.sessionDate.format(DATE_TIME_FORMAT);

    }

    public String getSessionIdString() {
        return sessionIdString;
    }

    public String getSessionDateString() {
        return sessionDateString;
    }


    public String getTrainingNotes() {
        return trainingNotes;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setTrainingNotes(String trainingNotes) {
        this.trainingNotes = trainingNotes;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setNotCompleted() {
        this.isCompleted = false;
    }

    public Exercise addExercise(String description, int sets, int reps) {
        nextExerciseIndex = (nextExerciseIndex +1) % 100;
        Exercise newExercise = new Exercise(nextExerciseIndex, description, sets, reps);
        //assert newExercise != null : "ERROR: Session can not be null.";
        int before = this.exercises.size();
        this.exercises.add(newExercise);
        assert exercises.size() == before + 1 : "ERROR: Exercise not added correctly. Verify.";
        return newExercise;
    }

    /**
     * When restoring from storage, set the internal counter used to assign
     * new exercise IDs so future additions won't clash with restored ones.
     */
    public void setNextExerciseIndex(int value) {
        this.nextExerciseIndex = value;
    }

    public void removeExercise(String exerciseID) throws InvalidExerciseException {
        // Find and remove the exercise with the given ID
        Iterator<Exercise> iterator = exercises.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Exercise exercise = iterator.next();
            if (exercise.getExerciseIDString().equals(exerciseID)) {
                iterator.remove();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidExerciseException("Exercise not found: " + exerciseID);
        }
    }
}
