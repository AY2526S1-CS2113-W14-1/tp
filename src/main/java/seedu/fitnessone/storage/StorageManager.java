package seedu.fitnessone.storage;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.model.Exercise;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;

/**
 * Storage manager responsible for persisting and loading Coach data to/from a file.
 * This class centralises file I/O so other parts of the app don't deal with paths or IO directly.
 */
public class StorageManager {
    private final String filePath;

    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load Coach data from storage. Caller should handle IOException.
     */
    public Coach load() throws IOException {
        Coach coach = new Coach();
        File file = new File(filePath);
        // ensure parent directory exists so later saves won't fail
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        if (!file.exists()) {
            // no data file yet, return empty coach
            return coach;
        }

        // if file exists but is empty, treat as no saved data
        if (file.length() == 0) {
            return coach;
        }
        Map<String, Athlete> athleteMap = new HashMap<>();
        Map<String, Integer> athleteMaxSession = new HashMap<>();
        Map<String, Map<String, Integer>> sessionMaxExercise = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|", -1);
                if (parts.length == 0) {
                    continue;
                }
                String type = parts[0];
                switch (type) {
                case "ATHLETE": {
                    if (parts.length < 2) {
                        // malformed ATHLETE line, skip
                        continue;
                    }
                    String id = parts[1];
                    String name = parts.length > 2 ? parts[2] : "";
                    Athlete athlete = new Athlete(id, name);
                    coach.addAthlete(athlete);
                    athleteMap.put(id, athlete);
                    athleteMaxSession.put(id, 0);
                    sessionMaxExercise.put(id, new HashMap<>());
                    break;
                }
                case "SESSION": {
                    String athleteId = parts[1];
                    String sessionId = parts[2];
                    String notes = parts.length > 3 ? parts[3].replace("\\n", "\n") : "";
                    boolean completed = parts.length > 4 && Boolean.parseBoolean(parts[4]);
                    Athlete a = athleteMap.get(athleteId);
                    if (a == null) {
                        a = new Athlete(athleteId, "");
                        coach.addAthlete(a);
                        athleteMap.put(athleteId, a);
                        athleteMaxSession.put(athleteId, 0);
                        sessionMaxExercise.put(athleteId, new HashMap<>());
                    }
                    int sessIndex = Integer.parseInt(sessionId);
                    Session s = new Session(sessIndex, notes);
                    if (completed) {
                        s.setCompleted();
                    }
                    a.addSession(s);
                    int prevMax = athleteMaxSession.getOrDefault(athleteId, 0);
                    int newMax = Math.max(prevMax, sessIndex);
                    athleteMaxSession.put(athleteId, newMax);
                    break;
                }
                case "EXERCISE": {
                    String athleteId = parts[1];
                    String sessionId = parts[2];
                    String exerciseId = parts[3];
                    String desc = parts.length > 4 ? parts[4].replace("\\n", "\n") : "";
                    int sets = parts.length > 5 ? Integer.parseInt(parts[5]) : 0;
                    int reps = parts.length > 6 ? Integer.parseInt(parts[6]) : 0;
                    boolean completed = parts.length > 7 && Boolean.parseBoolean(parts[7]);
                    Athlete a = athleteMap.get(athleteId);
                    if (a == null) {
                        a = new Athlete(athleteId, "");
                        coach.addAthlete(a);
                        athleteMap.put(athleteId, a);
                        athleteMaxSession.put(athleteId, 0);
                        sessionMaxExercise.put(athleteId, new HashMap<>());
                    }
                    // find session
                    Session targetSession = null;
                    for (Session s : a.getSessions()) {
                        if (s.getSessionIdString().equals(sessionId)) {
                            targetSession = s;
                            break;
                        }
                    }
                    if (targetSession == null) {
                        int sessIdx = Integer.parseInt(sessionId);
                        targetSession = new Session(sessIdx, "");
                        a.addSession(targetSession);
                        int prev = athleteMaxSession.getOrDefault(athleteId, 0);
                        athleteMaxSession.put(athleteId, Math.max(prev, sessIdx));
                    }
                    int exIdx = Integer.parseInt(exerciseId);
                    Exercise e = new Exercise(exIdx, desc, sets, reps);
                    if (completed) {
                        e.setCompleted();
                    }
                    targetSession.getExercises().add(e);
                    Map<String, Integer> map = sessionMaxExercise.get(athleteId);
                    String key = targetSession.getSessionIdString();
                    map.put(key, Math.max(map.getOrDefault(key, 0), exIdx));
                    break;
                }
                default:
                    break;
                }
            }
        }

        // fix counters to avoid future ID collisions
        for (Athlete a : coach.getAthletes()) {
            int maxSession = 0;
            for (Session s : a.getSessions()) {
                try {
                    int idx = Integer.parseInt(s.getSessionIdString());
                    maxSession = Math.max(maxSession, idx);
                } catch (NumberFormatException ignored) {
                    // malformed session id; skip this session
                    continue;
                }
            }
            a.setSessionIdCounter(maxSession);
            Map<String, Integer> sessMap = sessionMaxExercise.get(a.getAthleteID());
            if (sessMap != null) {
                for (Session s : a.getSessions()) {
                    String sid = s.getSessionIdString();
                    int maxEx = sessMap.getOrDefault(sid, 0);
                    s.setNextExerciseIndex(maxEx);
                }
            }
        }

        return coach;
    }

    /**
     * Save Coach data to storage. Caller should handle IOException.
     */
    public void save(Coach coach) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Athlete athlete : coach.getAthletes()) {
                writer.write(String.join("|", "ATHLETE", athlete.getAthleteID(),
                        athlete.getAthleteName()));
                writer.newLine();
                for (Session session : athlete.getSessions()) {
                    String notes = session.getTrainingNotes().replace("\n", "\\n");
                    String sessionLine = String.join("|", "SESSION", athlete.getAthleteID(),
                            session.getSessionIdString(), notes, Boolean.toString(session.isCompleted()));
                    writer.write(sessionLine);
                    writer.newLine();
                    for (Exercise exercise : session.getExercises()) {
                        String desc = exercise.getExerciseDescription().replace("|", " ")
                                .replace("\n", "\\n");
                        String exerciseLine = String.join("|", "EXERCISE", athlete.getAthleteID(),
                                session.getSessionIdString(), exercise.getExerciseIDString(), desc,
                                Integer.toString(exercise.getSets()), Integer.toString(exercise.getReps()),
                                Boolean.toString(exercise.isCompleted()));
                        writer.write(exerciseLine);
                        writer.newLine();
                    }
                }
            }
        }
    }
}
