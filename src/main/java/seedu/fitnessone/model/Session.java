package seedu.fitnessone.model;

import java.util.ArrayList;

import seedu.fitnessone.exception.InvalidExerciseException;

public class Session {
    private final ArrayList<Exercise> exercises;
    private int sessionId; //maybe can be LocalDate?
    private String trainingNotes;
    private boolean isCompleted;

    public Session(int sessionId, String trainingNotes) {
        this.sessionId = sessionId;
        this.trainingNotes = trainingNotes;
        this.exercises = new ArrayList<>();
        isCompleted = false;
    }

    public int getSessionId() {
        return sessionId;
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

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void removeExercise(int exerciseID) throws InvalidExerciseException {
        if (exerciseID < 0 || exerciseID >= exercises.size()) {
            throw new InvalidExerciseException("Exercise not found: " + exerciseID);
        }
        exercises.remove(exerciseID);
    }
}
