package seedu.fitnessone.Model;

public class Exercise {
    String exerciseDescription;
    int reps;
    int sets;
    boolean isCompleted=false;

    public Exercise(String exerciseDescription, int sets, int reps) {
        this.exerciseDescription = exerciseDescription;
        this.sets = sets;
        this.reps = reps;
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
