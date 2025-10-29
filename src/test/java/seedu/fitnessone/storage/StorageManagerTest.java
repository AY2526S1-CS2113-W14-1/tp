package seedu.fitnessone.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.model.Exercise;

public class StorageManagerTest {

    @Test
    public void saveAndLoad_roundTrip_preservesData() throws Exception {
        Coach coach = new Coach();
        // create athlete, session, exercise
        coach.newAthlete("Alice");
        Athlete a = coach.getAthletes().get(0);
        String athleteId = a.getAthleteID();

        Session s;
        try {
            s = coach.addSessionToAthlete(athleteId, "training notes");
        } catch (seedu.fitnessone.exception.InvalidAthleteException ex) {
            fail("Unexpected InvalidAthleteException: " + ex.getMessage());
            return;
        }
        Exercise e = s.addExercise("Pushups", 3, 10);

        Path tmp = Files.createTempFile("storage-test", ".txt");
        String path = tmp.toString();
        try {
            StorageManager storage = new StorageManager(path);
            storage.save(coach);

            Coach loaded = storage.load();
            assertNotNull(loaded);
            assertEquals(1, loaded.getAthletes().size(), "Should have one athlete after load");
            Athlete la = loaded.getAthletes().get(0);
            assertEquals(athleteId, la.getAthleteID());
            assertEquals(a.getAthleteName(), la.getAthleteName());
            assertEquals(1, la.getSessions().size());
            Session ls = la.getSessions().get(0);
            assertEquals(s.getTrainingNotes(), ls.getTrainingNotes());
            assertEquals(1, ls.getExercises().size());
            Exercise le = ls.getExercises().get(0);
            assertEquals(e.getExerciseDescription(), le.getExerciseDescription());
            assertEquals(e.getSets(), le.getSets());
            assertEquals(e.getReps(), le.getReps());
        } finally {
            try {
                Files.deleteIfExists(tmp);
            } catch (IOException ignore) {
            }
        }
    }
}
