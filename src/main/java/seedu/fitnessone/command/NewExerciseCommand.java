package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.ui.Ui;

public class NewExerciseCommand implements Command {
    private final String athleteName;
    private final int sessionID;
    private final String exerciseDescription;
    private final int sets;
    private final int reps;

    public NewExerciseCommand(String athleteName, int sessionID, String exerciseDescription, int sets, int reps) {
        this.athleteName = athleteName;
        this.sessionID = sessionID;
        this.exerciseDescription = exerciseDescription;
        this.sets = sets;
        this.reps = reps;
    }

    @Override
    public void execute(Coach coachController, Ui view){
        try {
            coachController.addExerciseToSession(athleteName, sessionID, exerciseDescription, sets, reps);
        } catch (InvalidAthleteException | InvalidSessionException | InvalidExerciseException e){
            throw new RuntimeException(e);
        }
        view.printWithDivider("New exercise created: " + athleteName + "\n" +
                "Session ID: " + sessionID + "\n" +
                "Exercise: " + exerciseDescription);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}