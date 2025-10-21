package seedu.fitnessone.model;

import java.util.ArrayList;

import seedu.fitnessone.exception.InvalidExerciseException;

public class Session {
    private final ArrayList<Exercise> exercises;
    private int sessionIndex;
    private String trainingNotes;
    private boolean isCompleted;
    private final String sessionIdString;
    private int nextExerciseIndex = 0;

    public Session(int sessionIndex, String trainingNotes) {
        this.sessionIndex = sessionIndex;
        this.trainingNotes = trainingNotes;
        this.exercises = new ArrayList<>();
        this.isCompleted = false;
        this.sessionIdString = String.format("%03d", sessionIndex);

        if(trainingNotes.trim().isEmpty()) {
            this.trainingNotes = "EMPTY. Add sessions notes with: /UpdateSessionNote <athlete> <session> <notes>";
        }
    }

    public String getSessionIdString() {
        return sessionIdString;
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
        this.exercises.add(newExercise);
        return newExercise;
    }

    public void removeExercise(int exerciseID) throws InvalidExerciseException {
        if (exerciseID < 0 || exerciseID >= exercises.size()) {
            throw new InvalidExerciseException("Exercise not found: " + exerciseID);
        }
        exercises.remove(exerciseID);
    }
}
