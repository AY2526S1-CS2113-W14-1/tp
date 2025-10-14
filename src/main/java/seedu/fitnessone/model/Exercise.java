package seedu.fitnessone.model;

public class Exercise {
    private final String exerciseDescription;
    private final int reps;
    private final int sets;
    private boolean isCompleted;

    public Exercise(String exerciseDescription, int sets, int reps) {
        this.exerciseDescription = exerciseDescription;
        this.sets = sets;
        this.reps = reps;
        this.isCompleted = false;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public int getReps() {
        return reps;
    }
    public int getSets() {
        return sets;
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