package seedu.fitnessone.Model;

import java.util.ArrayList;

public class Session {
    int sessionId;
    String trainingNotes;
    private final ArrayList<Exercise> exercises;
    boolean isCompleted=false;

    public Session(int sessionId, ArrayList<Session> sessions) {
        this.sessionId = sessionId;
        this.exercises = new ArrayList<>();
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


}
