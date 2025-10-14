package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.ui.Ui;

public class DeleteExerciseCommand implements Command {
    private final String athleteName;
    private final int sessionID;
    private final int exerciseID;

    public DeleteExerciseCommand(String athleteName, int sessionID, int exerciseID) {
        this.athleteName = athleteName;
        this.sessionID = sessionID;
        this.exerciseID = exerciseID;
    }

    @Override
    public void execute(Coach coachController, Ui view){
        try {
            coachController.deleteExerciseFromSession(athleteName, sessionID, exerciseID);
        } catch (InvalidAthleteException | InvalidSessionException |InvalidExerciseException e){
            throw new RuntimeException(e);
        }
        view.printWithDivider("Exercise " + exerciseID + " deleted from Session " + sessionID +
                " for " + athleteName);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}