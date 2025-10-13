package seedu.fitnessone.controller;


import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Exercise;
import seedu.fitnessone.model.Session;

import seedu.fitnessone.exception.InvalidIDException;
import seedu.fitnessone.ui.Ui;


import java.util.ArrayList;
import java.util.List;

public class Coach {
    private List<Athlete> athletes;

    /**
     * Constructs an empty TaskList.
     */
    public Coach() {
        this.athletes = new ArrayList<>();
    }


    public void addSessionToAthlete(String athleteName, int sessionID, String sessionTrainingNotes)
            throws InvalidAthleteException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                Session newSession = new Session(sessionID, sessionTrainingNotes);
                athlete.addSession(newSession);
                return;
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteName);
    }

    public void deleteSessionFromAthlete(String athleteName, int sessionID)
            throws InvalidAthleteException, InvalidSessionException {

        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                athlete.removeSession(sessionID);
                return;
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteName);
    }

    public String newAthlete (String athleteName) {
        Athlete athlete = new Athlete(athleteName);
        athletes.add(athlete);

        return athlete.toString();
    }

    public String deleteAthlete(String athleteID) throws InvalidIDException {
        for (int i = 0; i < athletes.size(); i++) {
            Athlete athlete = athletes.get(i);
            if (athlete.getAthleteID().equals(athleteID)) {
                athletes.remove(i);
                return athlete.toString();
            }
        }
        throw new InvalidIDException("athleteID " + athleteID + " not found.");
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

    public Session accessSession(Athlete athlete, int sessionID) throws InvalidSessionException {
        ArrayList<Session> sessions = athlete.getSessions();
        for (Session session : sessions) {
            if (sessionID == session.getSessionId()) {
                return session;
            }
        }
        throw new InvalidSessionException("Session not found: " + sessionID);
    }

    public void completeSession(String athleteName, int sessionID, String sessionTrainingNotes)
            throws InvalidAthleteException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                Session newSession = new Session(sessionID, sessionTrainingNotes);
                athlete.addSession(newSession);
                return;
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteName);
    }

    public void addExerciseToSession(String athleteName, int sessionID, String exerciseDescription,
                                     int sets, int reps) throws InvalidAthleteException, InvalidSessionException,
            InvalidExerciseException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                ArrayList<Session> sessions = athlete.getSessions();
                for (Session session : athlete.getSessions()) {
                    if (session.getSessionId() == sessionID) {
                        Exercise newExercise = new Exercise(exerciseDescription, sets, reps);
                        session.addExercise(newExercise);
                        return;

                    }
                } throw new InvalidSessionException("Session not found: " + sessionID);
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteName);
    }

    public void deleteExerciseFromSession(String athleteName, int sessionID, int exerciseID)
            throws InvalidAthleteException, InvalidSessionException, InvalidExerciseException {
        for (Athlete athlete : athletes) {
            if (athlete.getAthleteName().equals(athleteName)) {
                ArrayList<Session> sessions = athlete.getSessions();
                for (Session session : athlete.getSessions()) {
                    if (session.getSessionId() == sessionID) {
                        session.removeExercise(exerciseID);
                        return;
                    }
                } throw new InvalidSessionException("Session not found: " + sessionID);
            }
        }
        throw new InvalidAthleteException("Athlete not found: " + athleteName);
    }
}



