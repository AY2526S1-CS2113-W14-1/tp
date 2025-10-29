package seedu.fitnessone.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.Session;
import seedu.fitnessone.ui.Ui;

class ViewSessionsCommandTest {

    private static class CoachStub extends Coach {
        Athlete testAthlete;

        public CoachStub() {
            testAthlete = new Athlete("Jonas Hardwell");
            testAthlete.addSession(new Session(1, "chest"));
            testAthlete.addSession(new Session(2, "legs"));
        }

        @Override
        public Athlete accessAthleteID(String athleteID) throws InvalidAthleteException {
            if (athleteID.equals(testAthlete.getAthleteID())) {
                return testAthlete;
            }
            throw new InvalidAthleteException("Athlete not found");
        }
    }

    private static class UiStub extends Ui {
        StringBuilder output = new StringBuilder();

        @Override
        public void println(String message) {
            output.append(message).append("\n");
        }

        @Override
        public void divider() {
            output.append("=========================================================\n");
        }
    }

    @Test
    void viewSessions_validID_printsCorrectly() throws InvalidCommandException, InvalidAthleteException {
        CoachStub coach = new CoachStub();
        UiStub ui = new UiStub();

        ViewSessionsCommand command = new ViewSessionsCommand(coach.testAthlete.getAthleteID(), true);
        command.execute(coach, ui);

        String out = ui.output.toString();
        assertTrue(out.contains(
                "Athlete ID | Name: "
                        + coach.testAthlete.getAthleteID()
                        + " - "
                        + coach.testAthlete.getAthleteName()
        ));
        assertTrue(out.contains("Session 1. | Notes: chest") || out.contains("chest"));
        assertTrue(out.contains("Session 2. | Notes: legs") || out.contains("legs"));
    }

    @Test
    void viewSessions_invalidID_throwsException() {
        CoachStub coach = new CoachStub();
        UiStub ui = new UiStub();

        assertThrows(InvalidCommandException.class, () -> {
            ViewSessionsCommand command = new ViewSessionsCommand("9999", true);
            command.execute(coach, ui);
        });
    }
}
