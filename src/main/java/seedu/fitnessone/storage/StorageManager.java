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
import java.util.List;
import java.util.ArrayList;

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
     * Validate that an ID is strictly numeric and of the expected length.
     * Returns the integer value if valid, otherwise records a parse error and returns null.
     */
    private Integer validateNumericId(
            String rawId, int expectedLength, int lineNumber,
            List<String> parseErrors, String line, String fieldName) {
        if (rawId == null) {
            String error = String.format("Line %d: missing %s -> '%s'", lineNumber, fieldName, line);
            parseErrors.add(error);
            return null;
        }
        if (rawId.length() != expectedLength) {
            String error = String.format("Line %d: %s must be %d characters long -> '%s'",
                    lineNumber, fieldName, expectedLength, line);
            parseErrors.add(error);
            return null;
        }
        for (int i = 0; i < rawId.length(); i++) {
            if (!Character.isDigit(rawId.charAt(i))) {
                String error = String.format("Line %d: invalid %s '%s' -> '%s'",
                        lineNumber, fieldName, rawId, line);
                parseErrors.add(error);
                return null;
            }
        }
        try {
            int val = Integer.parseInt(rawId);
            if (val < 0) {
                String error = String.format("Line %d: negative %s '%s' -> '%s'",
                        lineNumber, fieldName, rawId, line);
                parseErrors.add(error);
                return null;
            }
            return val;
        } catch (NumberFormatException nfe) {
            String error = String.format("Line %d: invalid %s digits '%s' -> '%s'",
                    lineNumber, fieldName, rawId, line);
            parseErrors.add(error);
            return null;
        }
    }

    /**
     * Load Coach data from storage. Caller should handle IOException.
     */
    public Coach load() throws IOException {
        Coach coach = new Coach();
        assert filePath != null : "Storage filePath must not be null";
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

        List<String> parseErrors = new ArrayList<>();
        int lineNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|", -1);
                if (parts.length == 0) {
                    continue;
                }
                String type = parts[0];
                try {
                    switch (type) {
                    case "ATHLETE": {
                        if (parts.length < 2) {
                            String error = String.format("Line %d: ATHLETE line has too few fields -> '%s'",
                                    lineNumber, line);
                            parseErrors.add(error);
                            break;
                        }
                        String rawId = parts[1];
                        Integer aid = validateNumericId(rawId, 4, lineNumber, parseErrors, line, "athlete id");
                        if (aid == null) {
                            break;
                        }
                        String id = String.format("%04d", aid);
                        String name = parts.length > 2 ? parts[2].replace("\\n", "\n") : "";
                        String flag = parts.length > 3 ? parts[3] : "";
                        Athlete athlete = new Athlete(id, name);
                        if (!flag.isEmpty()) {
                            athlete.setFlagColor(flag);
                        }
                        coach.addAthlete(athlete);
                        athleteMap.put(id, athlete);
                        athleteMaxSession.put(id, 0);
                        sessionMaxExercise.put(id, new HashMap<>());
                        break;
                    }
                    case "SESSION": {
                        if (parts.length < 3) {
                            String error = String.format("Line %d: SESSION line has too few fields -> '%s'",
                                    lineNumber, line);
                            parseErrors.add(error);
                            break;
                        }
                        String rawAthleteId = parts[1];
                        Integer athleteAid = validateNumericId(
                                rawAthleteId, 4, lineNumber, parseErrors, line, "athlete id");
                        if (athleteAid == null) {
                            break;
                        }
                        String athleteId = String.format("%04d", athleteAid);
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
                        Integer sessIndexObj = validateNumericId(sessionId, 3, lineNumber,
                                parseErrors, line, "session id");
                        if (sessIndexObj == null) {
                            break;
                        }
                        int sessIndex = sessIndexObj;
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
                        if (parts.length < 4) {
                            String error = String.format("Line %d: EXERCISE line has too few fields -> '%s'",
                                    lineNumber, line);
                            parseErrors.add(error);
                            break;
                        }
                        String rawAthleteIdEx = parts[1];
                        Integer athleteAidEx = validateNumericId(
                                rawAthleteIdEx, 4, lineNumber, parseErrors, line, "athlete id");
                        if (athleteAidEx == null) {
                            break;
                        }
                        String athleteId = String.format("%04d", athleteAidEx);
                        String sessionId = parts[2];
                        String exerciseId = parts[3];
                        String desc = parts.length > 4 ? parts[4].replace("\\n", "\n") : "";
                        int sets = 0;
                        int reps = 0;
                        try {
                            sets = parts.length > 5 ? Integer.parseInt(parts[5]) : 0;
                        } catch (NumberFormatException nfe) {
                            String error = String.format("Line %d: invalid sets value '%s' -> '%s'",
                                    lineNumber, parts[5], line);
                            parseErrors.add(error);
                            // continue parsing with default 0
                        }
                        try {
                            reps = parts.length > 6 ? Integer.parseInt(parts[6]) : 0;
                        } catch (NumberFormatException nfe) {
                            String error = String.format("Line %d: invalid reps value '%s' -> '%s'",
                                    lineNumber, parts[6], line);
                            parseErrors.add(error);
                        }
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
                            Integer sessIdxObj = validateNumericId(
                                    sessionId, 3, lineNumber, parseErrors, line, "session id");
                            if (sessIdxObj == null) {
                                break;
                            }
                            int sessIdx = sessIdxObj;
                            targetSession = new Session(sessIdx, "");
                            a.addSession(targetSession);
                            int prev = athleteMaxSession.getOrDefault(athleteId, 0);
                            athleteMaxSession.put(athleteId, Math.max(prev, sessIdx));
                        }
                        Integer exIdxObj = validateNumericId(
                                exerciseId, 2, lineNumber, parseErrors, line, "exercise id");
                        if (exIdxObj == null) {
                            break;
                        }
                        int exIdx = exIdxObj;
                        // basic sanity assertions
                        assert sets >= 0 : "sets must be non-negative";
                        assert reps >= 0 : "reps must be non-negative";
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
                        // unknown record type: warn and continue
                        String error = String.format("Line %d: unknown record type '%s' -> '%s'",
                                lineNumber, type, line);
                        parseErrors.add(error);
                        break;
                    }
                } catch (Exception ex) {
                    // Catch any unexpected exception for this line and continue
                    String error = String.format("Line %d: %s - %s -> '%s'",
                            lineNumber,
                            ex.getClass().getSimpleName(),
                            ex.getMessage(),
                            line);
                    parseErrors.add(error);
                }
            }
        }

        if (!parseErrors.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Warnings while parsing storage file '").append(filePath).append("':\n");
            for (String err : parseErrors) {
                sb.append(err).append('\n');
            }
            // Print to stderr so callers (and logs) can see detailed diagnostics
            System.err.println(sb.toString());
        }

        // final basic assertions to validate loaded model integrity
        assert coach != null : "Coach should not be null after load";
        for (Athlete a : coach.getAthletes()) {
            assert a.getAthleteID() != null : "Athlete id must not be null";
            for (Session s : a.getSessions()) {
                assert s.getSessionIdString() != null : "Session id must not be null";
                for (Exercise ex : s.getExercises()) {
                    assert ex.getExerciseIDString() != null : "Exercise id must not be null";
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
        assert filePath != null : "Storage filePath must not be null";
        assert coach != null : "coach must not be null when saving";
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Athlete athlete : coach.getAthletes()) {
                assert athlete.getAthleteID() != null : "Athlete id must not be null";
                String flag = athlete.getFlagColor() != null ? athlete.getFlagColor() : "";
                writer.write(String.join("|", "ATHLETE", athlete.getAthleteID(),
                        athlete.getAthleteName(), flag));
                writer.newLine();
                for (Session session : athlete.getSessions()) {
                    String notes = session.getTrainingNotes().replace("\n", "\\n");
                    String[] sessionFields = {
                        "SESSION",
                        athlete.getAthleteID(),
                        session.getSessionIdString(),
                        notes,
                        Boolean.toString(session.isCompleted())
                        };
                    String sessionLine = String.join("|", sessionFields);
                    writer.write(sessionLine);
                    writer.newLine();
                    for (Exercise exercise : session.getExercises()) {
                        String desc = exercise.getExerciseDescription().replace("|", " ")
                                .replace("\n", "\\n");
                        String[] exerciseFields = {
                            "EXERCISE",
                            athlete.getAthleteID(),
                            session.getSessionIdString(),
                            exercise.getExerciseIDString(),
                            desc,
                            Integer.toString(exercise.getSets()),
                            Integer.toString(exercise.getReps()),
                            Boolean.toString(exercise.isCompleted())
                        };
                        String exerciseLine = String.join("|", exerciseFields);
                        writer.write(exerciseLine);
                        writer.newLine();
                    }
                }
            }
        }
    }
}
