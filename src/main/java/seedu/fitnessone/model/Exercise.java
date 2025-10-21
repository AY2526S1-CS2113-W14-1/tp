package seedu.fitnessone.model;

public class Exercise {
    private final String exerciseDescription;
    private final int reps;
    private final int sets;
    private boolean isCompleted;
    private final String exerciseIDString;

    public Exercise(int exerciseIDString, String exerciseDescription, int sets, int reps) {
        this.exerciseIDString = String.format("%02d", exerciseIDString);
        this.exerciseDescription = exerciseDescription;
        this.sets = sets;
        this.reps = reps;
        this.isCompleted = false;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public String getExerciseIDString() {
        return exerciseIDString;
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
