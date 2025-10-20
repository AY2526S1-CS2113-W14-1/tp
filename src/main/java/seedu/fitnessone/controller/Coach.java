package seedu.fitnessone.controller;


import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;

import seedu.fitnessone.ui.Ui;


import java.util.ArrayList;
import java.util.List;

public class Coach {
    private final List<Athlete> athletes;

    /**
     * Constructs an empty TaskList.
     */
    public Coach() {
        this.athletes = new ArrayList<>();
    }


    public Session addSessionToAthlete(String athleteID, String sessionTrainingNotes)
            throws InvalidAthleteException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteID().equals(athleteID)) {
                int index = athlete.getSessionID();
                Session newSession = new Session(index, sessionTrainingNotes);
                athlete.addSession(newSession);
                return newSession;
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteID);
    }

    public void deleteSessionFromAthlete(String athleteID, String sessionID)
            throws InvalidAthleteException, InvalidSessionException {

        for (Athlete athlete : athletes) {
            if (athlete.getAthleteID().equals(athleteID)) {
                athlete.removeSession(sessionID);
                return;
            }
        }
        throw new InvalidAthleteException("Athlete not found (ID): " + athleteID);
    }

    public String newAthlete(String athleteName) {
        Athlete athlete = new Athlete(athleteName);
        athletes.add(athlete);

        return athlete.toString();
    }

    public String deleteAthlete(String athleteID) throws InvalidAthleteException {
        for (int i = 0; i < athletes.size(); i++) {
            Athlete athlete = athletes.get(i);
            if (athlete.getAthleteID().equals(athleteID)) {
                athletes.remove(i);
                return athlete.toString();
            }
        }
        throw new InvalidAthleteException("athleteID " + athleteID + " not found.");
    }

    public void printAthletes(Ui view) {
        view.divider();
        for (int i = 0; i < athletes.toArray().length; i++) {
            int index = i + 1;
            view.print(index + ". ");
            view.println(athletes.get(i).toString());
        }
        view.println("");
        view.divider();
    }

    public Athlete accessAthlete(String athleteName) throws InvalidAthleteException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                return athlete;
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteName);
    }


    public Athlete accessAthleteID(String athleteID) throws InvalidAthleteException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteID().equals(athleteID)) {
                return athlete;
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteID);
    }

    public Session accessSessionID(Athlete athlete, String sessionID) throws InvalidSessionException {
        ArrayList<Session> sessions = athlete.getSessions();
        for (Session session : sessions) {
            if (session.getSessionIdString().equals(sessionID)) {
                return session;
            }
        }
        throw new InvalidSessionException("Session not found: " + sessionID);
    }

    public Exercise accessExerciseID(Session session, String exerciseID) throws InvalidExerciseException {
        ArrayList<Exercise> exercises = session.getExercises();
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseIDString().equals(exerciseID)) {
                return exercise;
            }
        }
        throw new InvalidExerciseException("Exercise not found: " + exerciseID);
    }


    public void deleteExerciseFromSession(Session session, Exercise exercise) throws InvalidSessionException, InvalidExerciseException {
        try {
            session.getExercises().remove(exercise);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}


