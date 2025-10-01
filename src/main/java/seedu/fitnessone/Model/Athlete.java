package seedu.fitnessone.Model;

import java.util.ArrayList;
import java.util.List;

public class Athlete {
    private int ID;
    private String name;
    private List<Session> sessions;

    public Athlete(int athleteId, String name, List<Session> sessions) {
        this.ID = athleteId;
        this.name = name;
        this.sessions = (sessions == null || sessions.isEmpty()) ? new ArrayList<Session>() : sessions;
    }

    public String toString() {
        return this.name;
    }
}
