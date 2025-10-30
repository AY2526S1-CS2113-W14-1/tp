package seedu.fitnessone.model;

import seedu.fitnessone.exception.InvalidSessionException;

import java.util.ArrayList;

/*
 * All athletes will have 0001. 0002 .... auto created.
 * All athletes will only have one sessions arrayList.
 */
public class Athlete {
    private static int nextIntID = 1;
    private final String athleteID;
    private final String athleteName;
    private final ArrayList<Session> sessions;
    private int sessionIdCounter = 0;
    private String flagColor;
    private int achievementScore;

    public Athlete(String athleteName) {
        this.athleteID = String.format("%04d", nextIntID++);
        this.athleteName = athleteName;
        this.sessions = new ArrayList<>();
        this.flagColor = "";
        this.achievementScore = 0;
    }

    /**
     * Package-private constructor used when loading athletes from storage/export.
     * Preserves the provided athlete ID instead of auto-generating one.
     */
    public Athlete(String athleteID, String athleteName) {
        this.athleteID = athleteID;
        this.athleteName = athleteName;
        this.sessions = new ArrayList<>();
        // ensure nextIntID stays ahead of any manually restored IDs
        try {
            int numeric = Integer.parseInt(athleteID);
            if (numeric >= nextIntID) {
                nextIntID = numeric + 1;
            }
        } catch (NumberFormatException e) {
            // ignore - keep nextIntID as-is
        }
    }

    public String toString() {
        String flag = (flagColor != null && !flagColor.isEmpty()) ? flagColor : " ";
        return "[" + flag + "] " + athleteName + " (" + athleteID + ")";
    }

    public String getAthleteID() {
        return athleteID;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void addSession(Session session) {
        assert session != null : "ERROR: Session can not be null.";
        int before = sessions.size();
        sessions.add(session);
        assert sessions.size() == before + 1 : "ERROR: Session not added correctly. Verify.";
    }

    public int getSessionID() {
        sessionIdCounter++;
        return sessionIdCounter;
    }

    public int getAchievementScore() {
        setAchievementScore();
        return achievementScore;
    }

    public void setAchievementScore() {
        achievementScore = 0;
        for (Session session : sessions) {
            for (Exercise exercise : session.getExercises()) {
                if (exercise.isCompleted()){
                    achievementScore++;
                }
            }
            if (session.isCompleted()){
                achievementScore++;
            }
        }
    }

    public void removeSession(String sessionID) throws InvalidSessionException {
        // Find and remove the session with the given ID
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getSessionIdString().equals(sessionID)) {
                sessions.remove(i);
                renumberSessions(); // Renumber remaining sessions
                return;
            }
        }
        throw new InvalidSessionException("Session not found: " + sessionID);
    }
    
    /**
     * Renumber all sessions to ensure sequential IDs starting from 001
     */
    private void renumberSessions() {
        for (int i = 0; i < sessions.size(); i++) {
            Session oldSession = sessions.get(i);
            // Create new session with sequential ID but same properties
            Session newSession = new Session(i + 1, oldSession.getTrainingNotes());
            
            // Copy over completion status
            if (oldSession.isCompleted()) {
                newSession.setCompleted();
            }
            
            // Transfer all exercises
            for (Exercise exercise : oldSession.getExercises()) {
                Exercise newExercise = newSession.addExercise(
                    exercise.getExerciseDescription(),
                    exercise.getSets(),
                    exercise.getReps()
                );
                if (exercise.isCompleted()) {
                    newExercise.setCompleted();
                }
            }
            
            // Replace the old session
            sessions.set(i, newSession);
        }
        sessionIdCounter = sessions.size() + 1;
    }
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    /**
     * Set the session ID counter (used when restoring from storage) so that
     * subsequent auto-generated session IDs won't collide with restored ones.
     */
    public void setSessionIdCounter(int value) {
        this.sessionIdCounter = value;
    }

    public String getFlagColor() {
        return flagColor;
    }

    public void setFlagColor(String flagColor) {
        this.flagColor = flagColor;
    }
}
