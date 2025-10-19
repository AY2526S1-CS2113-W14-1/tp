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

    public Athlete(String athleteName) {
        this.athleteID = String.format("%04d", nextIntID++);
        this.athleteName = athleteName;
        this.sessions = new ArrayList<>();
    }

    public String toString() {
        return getAthleteID() + ": " + getAthleteName();
    }

    public String getAthleteID() {
        return athleteID;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public int getSessionID() {
        sessionIdCounter++;
        return sessionIdCounter;
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
}
