package seedu.fitnessone.command;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class FlagAthleteCommandTest {

    private static class UiStub extends Ui {
        String lastPrinted = null;

        @Override
        public void printWithDivider(String message) {
            lastPrinted = message;
        }

        @Override
        public void divider() {
            // Do nothing for tests
        }

        @Override
        public void println(String message) {
            // Do nothing for tests
        }
    }

    @Test
    public void constructor_validInput_createsCommand() throws InvalidCommandException {
        String input = "/flagathlete 0001 red";
        FlagAthleteCommand command = new FlagAthleteCommand(input);

        assert command != null : "Command should not be null";
        assert !command.isExit() : "Command should not be exit command";
    }

    @Test
    public void execute_validRedFlag_flagsAthleteAndPrints()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();

        NewAthleteCommand newAthlete = new NewAthleteCommand("/newathlete Jonas Hardwell");
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete("Jonas Hardwell").getAthleteID();

        FlagAthleteCommand flagCommand = new FlagAthleteCommand("/flagathlete " + athleteId + " red");
        flagCommand.execute(coachTest, uiStub);

        assert uiStub.lastPrinted != null : "UI should have printed message";
        assert uiStub.lastPrinted.equals("Athlete " + athleteId + " flagged as: \uD83D\uDFE5") :
                "Printed message should match expected";
    }

    @Test
    public void execute_missingColor_throwsInvalidCommandException()
            throws InvalidAthleteException, InvalidCommandException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();

        NewAthleteCommand newAthlete = new NewAthleteCommand("/newathlete Test Athlete");
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete("Test Athlete").getAthleteID();

        FlagAthleteCommand flagCommand = new FlagAthleteCommand("/flagathlete " + athleteId);

        try {
            flagCommand.execute(coachTest, uiStub);
            assert false : "Should have thrown InvalidCommandException";
        } catch (InvalidCommandException e) {
            // Vérifier seulement que le message contient les parties importantes
            assert e.getMessage().contains("Flag color was not specified") :
                    "Exception message should mention missing color";
            assert e.getMessage().contains("/flagathlete") :
                    "Exception message should show usage";
        }
    }

    @Test
    public void execute_invalidColor_throwsInvalidCommandException()
            throws InvalidAthleteException, InvalidCommandException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();

        NewAthleteCommand newAthlete = new NewAthleteCommand("/newathlete Test Athlete");
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete("Test Athlete").getAthleteID();

        FlagAthleteCommand flagCommand = new FlagAthleteCommand("/flagathlete " + athleteId + " pink");

        try {
            flagCommand.execute(coachTest, uiStub);
            assert false : "Should have thrown InvalidCommandException";
        } catch (InvalidCommandException e) {
            // Vérifier les parties principales du message
            assert e.getMessage().contains("Invalid color") :
                    "Exception message should mention invalid color";
            assert e.getMessage().contains("pink") :
                    "Exception message should mention the invalid color name";
        }
    }

    @Test
    public void execute_nonexistentAthlete_throwsInvalidCommandException() {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();

        FlagAthleteCommand flagCommand = new FlagAthleteCommand("/flagathlete 9999 red");

        try {
            flagCommand.execute(coachTest, uiStub);
            assert false : "Should have thrown InvalidCommandException";
        } catch (InvalidCommandException e) {
            // Vérifier seulement que le message mentionne l'athlète non trouvé
            assert e.getMessage().contains("9999") :
                    "Exception message should mention the athlete ID";
            assert e.getMessage().contains("not found") || e.getMessage().contains("does not exist") :
                    "Exception message should indicate athlete not found";
        }
    }

    @Test
    public void execute_caseInsensitiveColor_worksCorrectly()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();

        NewAthleteCommand newAthlete = new NewAthleteCommand("/newathlete Case Test");
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete("Case Test").getAthleteID();

        FlagAthleteCommand flagCommand = new FlagAthleteCommand("/flagathlete " + athleteId + " GREEN");
        flagCommand.execute(coachTest, uiStub);

        assert uiStub.lastPrinted != null : "UI should have printed message";
        assert uiStub.lastPrinted.equals("Athlete " + athleteId + " flagged as: \uD83D\uDFE9") :
                "Printed message should match expected for uppercase color";
    }

    @Test
    public void execute_validMultipleColors_workCorrectly()
            throws InvalidCommandException, InvalidAthleteException {
        Coach coachTest = new Coach();
        UiStub uiStub = new UiStub();

        NewAthleteCommand newAthlete = new NewAthleteCommand("/newathlete Multi Color Test");
        newAthlete.execute(coachTest, uiStub);

        String athleteId = coachTest.accessAthlete("Multi Color Test").getAthleteID();

        // Test yellow
        FlagAthleteCommand flagCommand1 = new FlagAthleteCommand("/flagathlete " + athleteId + " yellow");
        flagCommand1.execute(coachTest, uiStub);
        assert uiStub.lastPrinted.equals("Athlete " + athleteId + " flagged as: \uD83D\uDFE8") :
                "Yellow flag should work";

        // Test blue
        FlagAthleteCommand flagCommand2 = new FlagAthleteCommand("/flagathlete " + athleteId + " blue");
        flagCommand2.execute(coachTest, uiStub);
        assert uiStub.lastPrinted.equals("Athlete " + athleteId + " flagged as: \uD83D\uDFE6") :
                "Blue flag should work";
    }
}
