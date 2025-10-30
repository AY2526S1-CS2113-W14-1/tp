package seedu.fitnessone.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.model.Athlete;
import seedu.fitnessone.model.CaloriesIntake;
import seedu.fitnessone.ui.Ui;

public class CaloriesIntakeCommandTest {

    private static class CoachStub extends Coach {
        Athlete testAthlete = new Athlete("John Doe");

        @Override
        public void setCaloriesIntake(String athleteID, LocalDate date, int carbs, int protein, int fat)
                throws InvalidAthleteException {
            if (!athleteID.equals("0001")) {
                throw new InvalidAthleteException("Athlete not found: " + athleteID);
            }
            testAthlete.addCaloriesIntake(new CaloriesIntake(date, carbs, protein, fat));
        }

        @Override
        public Athlete accessAthleteID(String athleteID) throws InvalidAthleteException {
            if (athleteID.equals(testAthlete.getAthleteID())) {
                return testAthlete;
            }
            throw new InvalidAthleteException("Athlete not found: " + athleteID);
        }
    }

    private static class UiStub extends Ui {
        String lastPrinted = null;

        @Override
        public void printWithDivider(String message) {
            lastPrinted = message;
        }
    }

    @Test
    public void constructor_validInput() throws InvalidCommandException {
        String input = "/caloriesIntake 0001 2025-10-28 200 150 50";
        CaloriesIntakeCommand cmd = new CaloriesIntakeCommand(input);
        assertNotNull(cmd);
    }

    @Test
    public void constructor_invalidDateFormat_throwsException() {
        String input = "/caloriesIntake 0001 28-10-2025 200 150 50";
        InvalidCommandException e = assertThrows(InvalidCommandException.class, () -> {
            new CaloriesIntakeCommand(input);
        });
        assertEquals("Invalid date format. Must be YYYY-MM-DD.", e.getMessage());
    }

    @Test
    public void execute_validInput_addsCaloriesIntake() throws Exception, InvalidCommandException {
        CoachStub coach = new CoachStub();
        UiStub ui = new UiStub();
        String input = "/caloriesIntake 0001 2025-10-28 200 150 50";
        CaloriesIntakeCommand cmd = new CaloriesIntakeCommand(input);

        cmd.execute(coach, ui);

        CaloriesIntake intake = coach.testAthlete.getCaloriesIntakeByDate(LocalDate.of(2025, 10, 28));
        assertEquals("Calories intake recorded for Athlete 0001 on 2025-10-28 (Carbs: 200, Protein: 150, " +
                "Fat: 50)", ui.lastPrinted);
    }

    @Test
    public void execute_invalidAthlete_throwsException() {
        CoachStub coach = new CoachStub();
        UiStub ui = new UiStub();
        String input = "/caloriesIntake 9999 2025-10-28 200 150 50";
        InvalidCommandException cmd = assertThrows(InvalidCommandException.class, () -> {
            new CaloriesIntakeCommand(input).execute(coach, ui);
        });
    }
}
