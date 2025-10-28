package seedu.fitnessone.model;

import seedu.fitnessone.exception.InvalidSessionException;

import java.util.ArrayList;
import java.util.Iterator;

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
        setAchivementScore();
        return achievementScore;
    }

    public void setAchivementScore() {
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
        Iterator<Session> iterator = sessions.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if (session.getSessionIdString().equals(sessionID)) {
                iterator.remove();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidSessionException("Session not found: " + sessionID);
        }
    }
    public ArrayList<Session> getSessions() {
        return sessions;
    }


    public String getFlagColor() {
        return flagColor;
    }

    public void setFlagColor(String flagColor) {
        this.flagColor = flagColor;
    }
}
