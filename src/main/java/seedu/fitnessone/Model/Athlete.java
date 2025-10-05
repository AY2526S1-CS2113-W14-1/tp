package seedu.fitnessone.Model;

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


    public ArrayList<Session> getSessions() {
        return sessions;
    }
}
