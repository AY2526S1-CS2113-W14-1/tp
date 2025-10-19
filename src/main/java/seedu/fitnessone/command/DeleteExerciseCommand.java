package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Parser;
import seedu.fitnessone.ui.Ui;

public class DeleteExerciseCommand implements Command {
    private String athleteID;
    private String sessionID;
    private String exerciseID;

    public DeleteExerciseCommand(String inputString) throws InvalidCommandException {
        this.athleteID = Parser.checkAthleteIDValidity(inputString);
        this.sessionID = Parser.checkSessionIDValidity(inputString);
        this.exerciseID = Parser.checkExerciseIDValidity(inputString);
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidAthleteException,
            InvalidSessionException, InvalidExerciseException {
        Athlete athlete = coachController.accessAthleteID(athleteID);
        Session session = coachController.accessSessionID(athlete, sessionID);
        Exercise exercise = coachController.accessExerciseID(session, exerciseID);

        try {
            coachController.deleteExerciseFromSession(session, exercise);
        } catch (InvalidSessionException | InvalidExerciseException e){
            throw new RuntimeException(e);
        }
        view.printWithDivider("Session " + sessionID + " - exercise ID "
                + exerciseID +  "deleted.");
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
