package seedu.fitnessone.ui;

import org.junit.jupiter.api.Test;
import seedu.fitnessone.command.Command;
import seedu.fitnessone.command.CompleteExerciseCommand;
import seedu.fitnessone.command.CompleteSessionCommand;
import seedu.fitnessone.command.DeleteAthleteCommand;
import seedu.fitnessone.command.DeleteExerciseCommand;
import seedu.fitnessone.command.DeleteSessionCommand;
import seedu.fitnessone.command.ExitCommand;
import seedu.fitnessone.command.FlagAthleteCommand;
import seedu.fitnessone.command.LeaderboardCommand;
import seedu.fitnessone.command.ListAthleteCommand;
import seedu.fitnessone.command.NewAthleteCommand;
import seedu.fitnessone.command.NewExerciseCommand;
import seedu.fitnessone.command.NewSessionCommand;
import seedu.fitnessone.command.UndoExerciseCommand;
import seedu.fitnessone.command.UndoSessionCommand;
import seedu.fitnessone.command.UpdateSessionNotesCommand;
import seedu.fitnessone.command.ViewAthleteCommand;
import seedu.fitnessone.command.ViewExerciseCommand;
import seedu.fitnessone.command.ViewSessionsCommand;


import seedu.fitnessone.exception.InvalidAthleteException;
import seedu.fitnessone.exception.InvalidCommandException;
import seedu.fitnessone.exception.InvalidExerciseException;
import seedu.fitnessone.exception.InvalidSessionException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parse_nullOrspace_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse(null));
        InvalidCommandException error = assertThrows(InvalidCommandException.class, () -> Parser.parse("   "));
        assertEquals("Input cannot be empty", error.getMessage());
    }

    @Test
    void parse_unrecognizedCommand_throwsInvalidCommandException() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class, () -> Parser.parse("nonsense"));
        assertEquals("Input keyword not found.", error.getMessage());
    }

    @Test
    void parse_isCaseInsensitive_andTrimsLeadingSpaces() throws InvalidSessionException, InvalidCommandException,
            InvalidExerciseException, InvalidAthleteException {
        Command c = Parser.parse("   /NewAthlete John Doe");
        assertInstanceOf(NewAthleteCommand.class, c);
    }

    @Test
    void parse_recognizedCommands_instantiateExpectedTypes() throws InvalidSessionException, InvalidCommandException,
            InvalidExerciseException, InvalidAthleteException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));

        // Athlete commands
        assertInstanceOf(NewAthleteCommand.class, Parser.parse("/newathlete John Doe"));
        assertInstanceOf(ViewAthleteCommand.class, Parser.parse("/viewathlete 0001"));
        assertInstanceOf(ListAthleteCommand.class, Parser.parse("/listathletes"));
        assertInstanceOf(DeleteAthleteCommand.class, Parser.parse("/deleteathlete 0001"));
        assertInstanceOf(FlagAthleteCommand.class, Parser.parse("/flagathlete 0001 red"));
        assertInstanceOf(LeaderboardCommand.class, Parser.parse("/leaderboard"));

        // Session commands
        assertInstanceOf(NewSessionCommand.class, Parser.parse("/newsession 0001 001"));
        assertInstanceOf(DeleteSessionCommand.class, Parser.parse("/deletesession 0001 001"));
        assertInstanceOf(CompleteSessionCommand.class, Parser.parse("/completesession 0001 001"));
        assertInstanceOf(ViewSessionsCommand.class, Parser.parse("/viewsessions 0001"));
        assertInstanceOf(UpdateSessionNotesCommand.class, Parser.parse("/updatesessionnote 0001 001 some notes"));
        assertInstanceOf(UndoSessionCommand.class, Parser.parse("/undosession 0001 001"));

        // Exercise commands
        assertInstanceOf(NewExerciseCommand.class, Parser.parse("/newexercise 0001 001 01 pushups"));
        assertInstanceOf(DeleteExerciseCommand.class, Parser.parse("/deleteexercise 0001 001 01"));
        assertInstanceOf(ViewExerciseCommand.class, Parser.parse("/viewexercises 0001 001"));
        assertInstanceOf(CompleteExerciseCommand.class, Parser.parse("/completeexercise 0001 001 01"));
        assertInstanceOf(UndoExerciseCommand.class, Parser.parse("/undoexercise 0001 001 01"));

    }

    @Test
    void parse_bye_isCaseInsensitive() throws InvalidSessionException, InvalidCommandException, InvalidExerciseException, InvalidAthleteException {
        assertInstanceOf(ExitCommand.class, Parser.parse("BYE"));
    }

    /*
    checkAthleteIDValidity
     */
    @Test
    void checkAthleteIDValidity_SpaceChar_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkAthleteIDValidity("/cmd      0001"));
        assertEquals("Athlete ID cannot be blank.", error.getMessage());
    }

    @Test
    void checkAthleteIDValidity_valid_returnsId() throws InvalidCommandException {
        String id = Parser.checkAthleteIDValidity("/cmd 0001");
        assertEquals("0001", id);
    }

    @Test
    void checkAthleteIDValidity_missing_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkAthleteIDValidity("/cmd"));
        assertTrue(error.getMessage().contains("Input is empty"));
    }

    @Test
    void checkAthleteIDValidity_space_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkAthleteIDValidity("/cmd    "));
        assertEquals("Input is empty. Check if there's athletes or sessions created.", error.getMessage());
    }

    @Test
    void checkAthleteIDValidity_wrongLength_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkAthleteIDValidity("/cmd 123"));
        assertEquals("Athlete ID must be 4 characters long.", error.getMessage());
    }

    @Test
    void checkAthleteIDValidity_nonDigits_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkAthleteIDValidity("/cmd 12a3"));
        assertEquals("Invalid format: Athlete ID must contain only digits.", error.getMessage());
    }

    /*
    checkSessionIDValidity
    */
    @Test
    void checkSessionIDValidity_SpaceChar_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkSessionIDValidity("/cmd 0001      001"));
        assertEquals("Session ID cannot be blank.", error.getMessage());
    }

    @Test
    void checkSessionIDValidity_valid_returnsId() throws InvalidCommandException {
        String id = Parser.checkSessionIDValidity("/cmd 0001 001");
        assertEquals("001", id);
    }

    @Test
    void checkSessionIDValidity_missing_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkSessionIDValidity("/cmd 0001"));
        assertTrue(error.getMessage().contains("Invalid Command"));
    }

    @Test
    void checkSessionIDValidity_space_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkSessionIDValidity("/cmd 0001    "));
        assertEquals("Invalid Command. Check if there's athletes or sessions created.", error.getMessage());
    }

    @Test
    void checkSessionIDValidity_wrongLength_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkSessionIDValidity("/cmd 0001 01"));
        assertEquals("Session ID must be 3 characters long.", error.getMessage());
    }

    @Test
    void checkSessionIDValidity_nonDigits_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkSessionIDValidity("/cmd 0001 0a1"));
        assertEquals("Invalid format: Session ID must contain only digits.", error.getMessage());
    }

    /*
    checkExerciseIDValidity
    */
    @Test
    void checkExerciseIDValidity_wrongLength_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkExerciseIDValidity("/cmd 0001 001 001"));
        assertEquals("Exercise ID must be 2 characters long.", error.getMessage());
    }

    @Test
    void checkExerciseIDValidity_SpaceChar_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkExerciseIDValidity("/cmd 0001 001      note"));
        assertEquals("Exercise ID cannot be blank.", error.getMessage());
    }

    @Test
    void checkExerciseIDValidity_valid_returnsId() throws InvalidCommandException {
        // Current implementation expects 2 digits (even though the message says "3 characters long").
        String id = Parser.checkExerciseIDValidity("/cmd 0001 001 01");
        assertEquals("01", id);
    }

    @Test
    void checkExerciseIDValidity_missing_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkExerciseIDValidity("/cmd 0001 001"));
        assertTrue(error.getMessage().contains("Input is empty"));
    }

    @Test
    void checkExerciseIDValidity_space_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkExerciseIDValidity("/cmd 0001 001   "));
        assertEquals("Input is empty. Check if there's athletes or sessions created.", error.getMessage());
    }

    @Test
    void checkExerciseIDValidity_nonDigits_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.checkExerciseIDValidity("/cmd 0001 001 0a"));
        assertEquals("Invalid format: Exercise ID must contain only digits.", error.getMessage());
    }

    /*
    onlyDescription
    */

    @Test
    void onlyDescription_valid_returnsRest() throws InvalidCommandException {
        String d = Parser.onlyDescription("/updatesessionnote 0001 001 this is a note   ");
        assertEquals("this is a note", d);
    }

    @Test
    void onlyDescription_missing_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.onlyDescription("/updatesessionnote 0001 001   "));
        assertTrue(error.getMessage().contains("The correct format is:"),
                "Should guide correct usage when notes are empty.");
    }

    /*
    isTrainingNotes
    */

    @Test
    void isTrainingNotes_valid_returnsRest() throws InvalidCommandException {
        String n = Parser.isTrainingNotes("/trainingNotes 0001 001  do 3 sets of 10 reps ");
        assertEquals("do 3 sets of 10 reps", n);
    }

    @Test
    void isTrainingNotes_missing_throws() {
        InvalidCommandException error = assertThrows(InvalidCommandException.class,
                () -> Parser.isTrainingNotes("/trainingNotes 0001 001"));
        assertTrue(error.getMessage().startsWith("Training notes cannot be empty."),
                "Should indicate that training notes cannot be empty with the correct format hint.");
    }
}
