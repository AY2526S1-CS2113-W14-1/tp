package seedu.fitnessone.model;

import seedu.fitnessone.exception.InvalidCommandException;

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

    public Athlete(String athleteName) {
        this.athleteID = String.format("%04d", nextIntID++);
        this.athleteName = athleteName;
        this.sessions = new ArrayList<>();
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

    public void removeSession(int sessionID) throws InvalidCommandException {
        Iterator<Session> iterator = sessions.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if (session.getSessionId() == sessionID) {
                iterator.remove();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidCommandException("Session not found");
        }
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }
}
