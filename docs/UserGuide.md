# FitnessONE User Guide

FitnessONE is a **desktop application for fitness coaches to manage multiple athletes and their training sessions**, optimized for use via a **Command Line Interface (CLI)**. If you can type fast, FitnessONE can help you manage your athletes' training programs more efficiently than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
  * [Athlete Management](#athlete-management)
    * [Adding a new athlete: `/newathlete`](#adding-a-new-athlete-newathlete)
    * [Viewing athlete details: `/viewathlete`](#viewing-athlete-details-viewathlete)
    * [Listing all athletes: `/listathletes`](#listing-all-athletes-listathletes)
    * [Flagging an athlete: `/flagathlete`](#flagging-an-athlete-flagathlete)
    * [Deleting an athlete: `/deleteathlete`](#deleting-an-athlete-deleteathlete)
  * [Session Management](#session-management)
    * [Adding a new session: `/newsession`](#adding-a-new-session-newsession)
    * [Viewing all sessions: `/viewsessions`](#viewing-all-sessions-viewsessions)
    * [Updating session notes: `/updatesessionnote`](#updating-session-notes-updatesessionnote)
    * [Completing a session: `/completesession`](#completing-a-session-completesession)
    * [Undoing session completion: `/undosession`](#undoing-session-completion-undosession)
    * [Deleting a session: `/deletesession`](#deleting-a-session-deletesession)
  * [Exercise Management](#exercise-management)
    * [Adding a new exercise: `/newexercise`](#adding-a-new-exercise-newexercise)
    * [Viewing all exercises: `/viewexercises`](#viewing-all-exercises-viewexercises)
    * [Completing an exercise: `/completeexercise`](#completing-an-exercise-completeexercise)
    * [Undoing exercise completion: `/undoexercise`](#undoing-exercise-completion-undoexercise)
    * [Deleting an exercise: `/deleteexercise`](#deleting-an-exercise-deleteexercise)
  * [Other Features](#other-features)
    * [Viewing the leaderboard: `/leaderboard`](#viewing-the-leaderboard-leaderboard)
    * [Viewing help: `/help`](#viewing-help-help)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
    * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `17` or above installed on your computer.

2. Download the latest `FitnessONE.jar` file from [here](https://github.com/AY2526S1-CS2113-W14-1/tp/releases).

3. Copy the file to the folder you want to use as the home folder for FitnessONE.

4. Open a command terminal, navigate (`cd`) into the folder you put the jar file in, and use the `java -jar FitnessONE.jar` command to run the application.

5. Type a command in the terminal and press Enter to execute it. For example:
   * `/help` : Shows a list of all available commands.
   * `/newathlete John Doe` : Adds a new athlete named "John Doe".
   * `/listathletes` : Lists all athletes.
   * `bye` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by you.<br>
  e.g. in `/newathlete <Athlete Name>`, `<Athlete Name>` is a parameter which can be used as `/newathlete John Doe`.

* Items in square brackets are optional.<br>
  e.g. `<Athlete Name> [Color]` can be used as `John Doe red` or as `John Doe`.

* Parameters must be in the specified order.<br>
  e.g. if the command specifies `/newexercise <Athlete ID> <Session ID> <Description> <Sets> <Reps>`, the order must be followed.

* **ID Formats:**
  * Athlete ID: 4 digits (e.g., `0001`)
  * Session ID: 3 digits (e.g., `001`)
  * Exercise ID: 2 digits (e.g., `01`)

* Extraneous parameters for commands that do not take parameters (such as `/help`, `/listathletes`, and `bye`) will cause an error.

</div>

--------------------------------------------------------------------------------------------------------------------

### Athlete Management

#### Adding a new athlete: `/newathlete`

Creates a new athlete profile with a specified name. The system automatically assigns a unique 4-digit athlete ID.

**Format:** `/newathlete <Athlete Name>`

**Example:**
```
/newathlete Jonas Hardwell
```

**Expected output:**
```
____________________________________________________________
athlete added.
     [  ] Jonas Hardwell (0001)
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Viewing athlete details: `/viewathlete`

Displays detailed information about a specific athlete, including all their sessions and exercises.

**Format:** `/viewathlete <Athlete ID>`

**Example:**
```
/viewathlete 0001
```

**Expected output:**
```
____________________________________________________________
    Athlete Name: Jonas Hardwell
    Sessions: 2
    List:
       Session 1. | Notes: Chest day
       Exercises:
          1. | Notes: Bench Press | sets x reps: 5 x 3
          2. | Notes: Cable Press | sets x reps: 5 x 15
       Session 2. | Notes: Leg day
       Exercises:
          1. | Notes: Squat | sets x reps: 5 x 5
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Listing all athletes: `/listathletes`

Displays a list of all registered athletes with their IDs and priority flags.

**Format:** `/listathletes`

**Example:**
```
/listathletes
```

**Expected output:**
```
____________________________________________________________
    1. [🟥] Jonas Hardwell (0001)
    2. [🟨] John Doe (0002)
    3. [ ] Jonathan Well (0003)

____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Flagging an athlete: `/flagathlete`

Assigns a color flag to an athlete for priority tracking.

**Format:** `/flagathlete <Athlete ID> <Color>`

**Supported colors:** `red`, `yellow`, `green`, `blue`

**Example:**
```
/flagathlete 0001 red
```

**Expected output:**
```
____________________________________________________________
Athlete 0001 flagged as: 🟥
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Deleting an athlete: `/deleteathlete`

Deletes an athlete and all associated data (sessions and exercises).

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This action cannot be undone. All data associated with the athlete will be permanently deleted.
</div>

**Format:** `/deleteathlete <Athlete ID>`

**Example:**
```
/deleteathlete 0001
```

**Expected output:**
```
____________________________________________________________
Deleted athlete with ID 0001
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

### Session Management

#### Adding a new session: `/newsession`

Creates a new training session for a specific athlete.

**Format:** `/newsession <Athlete ID> <Session Notes>`

**Example:**
```
/newsession 0001 Leg day workout
```

**Expected output:**
```
____________________________________________________________
New session created:
Athlete Name: Jonas Hardwell | ID: 0001

Session ID: 001
Session Description: Leg day workout
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Viewing all sessions: `/viewsessions`

Displays all sessions for a specific athlete, showing completion status, date, and notes.

**Format:** `/viewsessions <Athlete ID>`

**Example:**
```
/viewsessions 0001
```

**Expected output:**
```
____________________________________________________________
Athlete ID: 0001 | Name: Jonas Hardwell

Status | Session ID | Date | Notes:
1. [ ]   Session: 001 | Date: 29-10-2025 08:00 | Morning session
2. [X]   Session: 002 | Date: 29-10-2025 10:00 | Pre training warmup
3. [ ]   Session: 003 | Date: 29-10-2025 15:00 | Post training cool down
____________________________________________________________
```

<div markdown="span" class="alert alert-info">:bulb: **Tip:**
`[X]` indicates a completed session, while `[ ]` indicates an incomplete session.
</div>

--------------------------------------------------------------------------------------------------------------------

#### Updating session notes: `/updatesessionnote`

Updates the training notes for a specific session.

**Format:** `/updatesessionnote <Athlete ID> <Session ID> <New Notes>`

**Example:**
```
/updatesessionnote 0001 001 Upper body strength training
```

**Expected output:**
```
____________________________________________________________
Successfully updated Athlete (ID: 0001) session: 001 with notes: Upper body strength training.
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Completing a session: `/completesession`

Marks a session as completed.

**Format:** `/completesession <Athlete ID> <Session ID>`

**Example:**
```
/completesession 0001 001
```

**Expected output:**
```
____________________________________________________________
Session (ID: 001) completed by Jonas Hardwell (ID: 0001).
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Undoing session completion: `/undosession`

Marks a previously completed session as not completed.

**Format:** `/undosession <Athlete ID> <Session ID>`

**Example:**
```
/undosession 0001 001
```

**Expected output:**
```
____________________________________________________________
Session (ID: 001) was marked as not completed for Jonas Hardwell (ID: 0001).
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Deleting a session: `/deletesession`

Deletes a specific session and all its associated exercises.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This action cannot be undone. All exercises in this session will also be deleted.
</div>

**Format:** `/deletesession <Athlete ID> <Session ID>`

**Example:**
```
/deletesession 0001 003
```

**Expected output:**
```
____________________________________________________________
Session (ID: 003) deleted for Jonas Hardwell (ID: 0001)
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

### Exercise Management

#### Adding a new exercise: `/newexercise`

Adds a new exercise to a specific session with sets and reps.

**Format:** `/newexercise <Athlete ID> <Session ID> <Exercise Description> <Sets> <Reps>`

**Example:**
```
/newexercise 0001 001 Bench Press 5 10
```

**Expected output:**
```
____________________________________________________________
New exercise created!

Athlete (ID): 0001
Athlete name: Jonas Hardwell

Session (ID): 001
Session Description: Chest day

Exercise (ID): 01
Exercise Description: Bench Press
sets x reps: 5 x 10
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Viewing all exercises: `/viewexercises`

Displays all exercises in a specific session.

**Format:** `/viewexercises <Athlete ID> <Session ID>`

**Example:**
```
/viewexercises 0001 001
```

**Expected output:**
```
____________________________________________________________
Athlete ID: 0001
Athlete Name: Jonas Hardwell
Session ID: 001
Session Note: Chest day

1. [X] Bench Press 5 x 10
2. [ ] Cable Fly 3 x 12
3. [ ] Push Ups 3 x 15
____________________________________________________________
```

<div markdown="span" class="alert alert-info">:bulb: **Tip:**
`[X]` indicates a completed exercise, while `[ ]` indicates an incomplete exercise.
</div>

--------------------------------------------------------------------------------------------------------------------

#### Completing an exercise: `/completeexercise`

Marks an exercise as completed.

**Format:** `/completeexercise <Athlete ID> <Session ID> <Exercise ID>`

**Example:**
```
/completeexercise 0001 001 01
```

**Expected output:**
```
____________________________________________________________
Exercise (ID: 01) completed by Jonas Hardwell (ID: 0001).
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Undoing exercise completion: `/undoexercise`

Marks a previously completed exercise as not completed.

**Format:** `/undoexercise <Athlete ID> <Session ID> <Exercise ID>`

**Example:**
```
/undoexercise 0001 001 01
```

**Expected output:**
```
____________________________________________________________
Exercise (ID: 01), Session (ID: 001) has been marked as not completed for Jonas Hardwell (ID: 0001).
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Deleting an exercise: `/deleteexercise`

Deletes a specific exercise from a session.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This action cannot be undone.
</div>

**Format:** `/deleteexercise <Athlete ID> <Session ID> <Exercise ID>`

**Example:**
```
/deleteexercise 0001 001 02
```

**Expected output:**
```
____________________________________________________________
Deleted exercise (ID: 02) from session 001 for Jonas Hardwell (ID: 0001).
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

### Other Features

#### Viewing the leaderboard: `/leaderboard`

Displays a ranked list of all athletes based on their achievement scores (completed exercises and sessions).

**Format:** `/leaderboard`

**Example:**
```
/leaderboard
```

**Expected output:**
```
____________________________________________________________
athleteName     athleteId score
    ____________________________________________________________
    Jonas Hardwell  0001      15
    John Doe        0002      10
    Jane Smith      0003      5

____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Viewing help: `/help`

Shows a comprehensive list of all available commands with their formats.

**Format:** `/help`

**Example:**
```
/help
```

**Expected output:**
```
____________________________________________________________
FitnessONE - Available Commands

=== ATHLETE COMMANDS ===
/newathlete <Name> - Create a new athlete
/viewathlete <Athlete ID> - View athlete details
/listathletes - List all athletes
/deleteathlete <Athlete ID> - Delete an athlete
/flagathlete <Athlete ID> <Color> - Flag an athlete

=== SESSION COMMANDS ===
/newsession <Athlete ID> <Notes> - Create a new session
/deletesession <Athlete ID> <Session ID> - Delete a session
/completesession <Athlete ID> <Session ID> - Complete a session
/viewsessions <Athlete ID> - View all sessions
/updatesessionnote <Athlete ID> <Session ID> <Notes> - Update notes
/undosession <Athlete ID> <Session ID> - Undo completion

=== EXERCISE COMMANDS ===
/newexercise <Athlete ID> <Session ID> <Description> <Sets> <Reps> - Create exercise
/deleteexercise <Athlete ID> <Session ID> <Exercise ID> - Delete exercise
/viewexercises <Athlete ID> <Session ID> - View all exercises
/completeexercise <Athlete ID> <Session ID> <Exercise ID> - Complete exercise
/undoexercise <Athlete ID> <Session ID> <Exercise ID> - Undo completion

=== OTHER COMMANDS ===
/help - Show this help message
/leaderboard - Show the leaderboard
bye - Exit the application

Tip: Each command has its own help. Try using an incorrect format to see details!
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Exiting the program: `bye`

Exits FitnessONE and saves all data automatically.

**Format:** `bye`

**Example:**
```
bye
```

**Expected output:**
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

#### Saving the data

FitnessONE data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

The data file is located at `[JAR file location]/data/athletes_export.txt`.

<div markdown="span" class="alert alert-info">:bulb: **Tip:**
FitnessONE creates the data folder and file automatically if they don't exist.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q: How do I transfer my data to another computer?**  
A: Install FitnessONE on the other computer and replace the empty `athletes_export.txt` file it creates with the file from your previous FitnessONE home folder (located in the `data` directory).

**Q: Can I edit the data file directly?**  
A: Advanced users can edit the `athletes_export.txt` file directly, but this is not recommended. If the file format becomes invalid, FitnessONE will start with an empty data file. Always back up your data file before editing.

**Q: What happens if I delete an athlete?**  
A: All sessions and exercises associated with that athlete will be permanently deleted. This action cannot be undone.

**Q: Why are athlete IDs always 4 digits?**  
A: FitnessONE uses fixed-length IDs for consistency and to make it easier to reference athletes in commands. The system supports up to 9,999 athletes.

**Q: Can I have multiple athletes with the same name?**  
A: Yes, each athlete is uniquely identified by their athlete ID, not their name. You can have multiple athletes with the same name.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action | Command | Example |
|--------|---------|---------|
| **Add athlete** | `/newathlete <Name>` | `/newathlete Jonas Hardwell` |
| **View athlete** | `/viewathlete <Athlete ID>` | `/viewathlete 0001` |
| **List athletes** | `/listathletes` | `/listathletes` |
| **Flag athlete** | `/flagathlete <Athlete ID> <Color>` | `/flagathlete 0001 red` |
| **Delete athlete** | `/deleteathlete <Athlete ID>` | `/deleteathlete 0001` |
| **Add session** | `/newsession <Athlete ID> <Notes>` | `/newsession 0001 Leg day` |
| **View sessions** | `/viewsessions <Athlete ID>` | `/viewsessions 0001` |
| **Update session notes** | `/updatesessionnote <Athlete ID> <Session ID> <Notes>` | `/updatesessionnote 0001 001 Upper body` |
| **Complete session** | `/completesession <Athlete ID> <Session ID>` | `/completesession 0001 001` |
| **Undo session** | `/undosession <Athlete ID> <Session ID>` | `/undosession 0001 001` |
| **Delete session** | `/deletesession <Athlete ID> <Session ID>` | `/deletesession 0001 001` |
| **Add exercise** | `/newexercise <Athlete ID> <Session ID> <Description> <Sets> <Reps>` | `/newexercise 0001 001 Bench Press 5 10` |
| **View exercises** | `/viewexercises <Athlete ID> <Session ID>` | `/viewexercises 0001 001` |
| **Complete exercise** | `/completeexercise <Athlete ID> <Session ID> <Exercise ID>` | `/completeexercise 0001 001 01` |
| **Undo exercise** | `/undoexercise <Athlete ID> <Session ID> <Exercise ID>` | `/undoexercise 0001 001 01` |
| **Delete exercise** | `/deleteexercise <Athlete ID> <Session ID> <Exercise ID>` | `/deleteexercise 0001 001 01` |
| **View leaderboard** | `/leaderboard` | `/leaderboard` |
| **View help** | `/help` | `/help` |
| **Exit** | `bye` | `bye` |