package seedu.fitnessone.command;

import seedu.fitnessone.controller.Coach;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.ui.Ui;

public class DeleteAthleteCommand implements Command{
    private String athleteID;

    public DeleteAthleteCommand(String trimmedInput) throws InvalidCommandException {
        try {

            String numberStr = trimmedInput.substring(11).trim();
            if (numberStr.isEmpty()) {
                throw new InvalidCommandException("No athleteID provided.");
            }

            int idInput = Integer.parseInt(numberStr);

            if (idInput <= 0) {
                throw new InvalidCommandException("athleteID must be a positive integer.");
            }

            // Convert integer input to zero-padded string to match athleteID format ("0001", "0002", ...)
            this.athleteID = String.format("%04d", idInput);

        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid athleteID format. Please enter a positive integer.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException("No athleteID provided.");
        }
    }

    @Override
    public void execute(Coach coachController, Ui view) throws InvalidCommandException {

        try {
            coachController.deleteAthlete(athleteID);
            view.printWithDivider("Deleted athlete with ID " + athleteID);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("invalid athleteID.");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
