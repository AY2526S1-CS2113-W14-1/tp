# User Guide : FitnessONE

## Introduction

FitnessONE is a powerful CLI application designed for professional fitness coaches to streamline the monitoring and management of their students' training routines. The platform provides an efficient, data-driven solution for exercise logging, tracking training schedules, and analyzing team performance.

Target Users: Professional fitness coaches who need to manage multiple athletes' training programs, track progress, and maintain detailed training records.

Value Proposition: FitnessONE offers coaches a simple yet comprehensive platform with deep data integration capabilities, enabling efficient progress reports and long-term training planning

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `FitnessONE` from [here](https://github.com/AY2526S1-CS2113-W14-1/tp).

## Features 

{Give detailed description of each feature}

### Adding a new Athlete: `/newAthlete`
Creates a new Log for a new Athlete with a specified name.

Format: `/newAthlete <Athlete Name>`

Example of usage: 

```
/newAthlete Jonas Hardwell
```
Output:

```
=========================================================
New athlete created: Jonas Hardwell
=========================================================
```

### Viewing Athlete Details: `/viewAthlete`
Views the Sessions that are assigned to the specified Athlete

Format: `/viewAthlete <Athlete ID>`

Example of usage: 

```
/viewAthlete 0001
```
Output:

```
=========================================================
Athlete ID: 0001
Athlete Name: jonas hardwell
Sessions: 2
List:
   Session 1. | Notes: chest
   Exercises:
      1. | Notes: bench press| sets x reps: 5 x 3
      2. | Notes: cable-press| sets x reps: 5 x 15
      3. | Notes: leg-press| sets x reps: 5 x 15
   Session 2. | Notes: legs
   Exercises:
      1. | Notes: squat| sets x reps: 5 x 5
=========================================================

```

### Adding to an Athlete a new Session: `/newSession`
Adds a Session to the specified Athlete
Specifies the Description for the Session

Format: `/newSession <Athlete ID> <Description>`

Example of usage: 

```
/newSession 0001 Legs

```

Output:

```
=========================================================
New session created:
Athlete Name: jonas hardwell | ID: 0001

Session ID: 002
Session Description: legs
=========================================================

```

### Adding to a Session a new Exercise : `/newExercise`
Adds an Exercise to the specified Session.
Sessions are specified via Athlete ID + Session ID

Format: `/newExercise <Athlete ID> <Session ID> <Exercise Description> <set> <rep>` 

Example of usage: 

```
/newExercise 0001 001 leg-press 5 15

```

Output:

```
=========================================================
New exercise created!

Athlete (ID) : 0001
Athlete name: jonas hardwell

Session (ID): 001
Session Description: chest

Exercise (ID): 03
Exercise Description: leg-press
sets x reps: 5 x 15
=========================================================

```

### Updating Session Trainings Notes : `/updateSessionNotes`
Update training notes to specified Session.

Format: `/updateSessionNote <Athlete ID> <Session ID> <New Notes>`

Example of usage: 

```
/updateSessionNote 0001 001 Chest-Day

```

Output:

```
=========================================================

"Successfully updated Athlete (ID: 0001) session: 002 with notes: Chest-Day.
=========================================================

```

### Marking a Session as Completed : `/completeSession`
Marks the specified Session as completed.


Format: `/completeSession <Athlete ID> <Session ID>`

Example of usage: 

```
/completeSession 0001 003

```

Output:

```
=========================================================
Session (ID: 003) completed by jonas hardwell (ID: 0001).
=========================================================
```

### Marking an Exercise as Completed : `/completeExercise`
Marks the specified Exercise as completed


Format: `/completeExercise <Athlete Name> <Session ID> <Exercise ID>`

Example of usage: 

```
/completeExercise 0001 003 02
```

Output:

```
=========================================================
Exercise (ID: 02) completed by Jonas Hardwell (ID: 0001).
=========================================================

```

### Marking a Session as not completed : `/undoSession`
Marks the specified Session as not completed.


Format: `/undoSession <Athlete ID> <Session ID>`

Example of usage:

```
/undoSession 0001 003

```

Output:

```
=========================================================
Session (ID: 003) was marked as not completed for jonas hardwell (ID: 0001).
=========================================================
```

### Marking an Exercise as not completed : `/undoExercise`
Marks the specified Exercise as not completed.


Format: `/undoExercise <Athlete ID> <Session ID> <Exercise ID`

Example of usage:

```
/undoExercise 0001 003 02

```

Output:

```
=========================================================
Exercise (ID: 02), Session (ID: 003) has been marked as not completed for jonas hardwell (ID: 0001).
=========================================================
```

### Viewing Athlete's all Sessions  : `/viewSession`
View the Session ID which is used in other commands


Format: `/viewSessions <Athlete ID>`

Example of usage: 

```
/viewSession 0001

```

Output:

```
=========================================================
Athlete ID:  0001 | Name: Jonas Hardwell

Status | Session ID | Date | Notes:
1. [ ].   Session: 001 | Date: 29-10-2025 08:00 | morning session
4. [X].   Session: 002 | Date: 29-10-2025 10:00 | pre training warmup
5. [ ].   Session: 003 | Date: 29-10-2025 15:00 | post training cool down
6. [ ].   Session: 004 | Date: 29-10-2025 18:00 | conditioning
=========================================================

```

### Viewing Session's all Exercises  : `/viewExercises`
This function is used to view  the Exercises that are assigned to a specific Session ID and a specific Athlete Name


Format: `/viewExercise <Athlete ID> <Session ID>`

Example of usage: 

```
/viewExercises 0001 003

```

Output:

```
=========================================================
Athlete ID: 0001
Athlete Name: Jonas Hardwell
Session ID : 003
Session Note: keep_energetic :)

1.   [X] Push Ups 10 x 3
2.   [ ] Dips 5 x 2
3.   [ ] Leg 10 x 4
=========================================================

```

### Deleting Exercise from Session  : `/deleteExercise`
This function is used to delete a specific exercise that is assigned to a specific session ID and a specific Athlete Name


Format: `/deleteExercise <Athlete ID> <Session ID> <Exercise ID>`

Example of usage: 

```
/deleteExercise 0001 003 05

```

Output:

```
=========================================================
Exercise (ID: 05) deleted in Session (ID:003) for Jonas Hardwell (ID: 0001)
=========================================================

```

### Deleting Session from Athlete  : `/deleteSession`
This function is used to delete a specific session that is assigned to a specific athlete


Format: `/deleteSession <Athlete ID> <Session ID>`

Example of usage: 

```
/deleteSession 0001 003

```

Output:

```
=========================================================
Session (ID: 003) deleted for Jonas Hardwell (ID: 0001)
=========================================================

```

### Viewing leaderboard  : `/leaderboard`
This function is used to view the achievements of all athletes by ranking them


Format: `/leaderboard`

Example of usage:

```
/leaderboard

```

Output:

```
____________________________________________________________
athleteName     athleteId score
____________________________________________________________
xi              0003      2
trump           0002      1
john doe        0001      0

____________________________________________________________

```

### Flagging an Athlete with a Color: `/flagathlete`
Flags a specific athlete with a chosen color for priority tracking (for example, marking important or high-priority athletes)


Format: `/flagathlete <Athlete ID> <Color>`

Example of usage:

```
/flagathlete 0001 red

```

Output:

```
____________________________________________________________
Athlete 0001 flagged as: 🟥
____________________________________________________________

```

### Deleting an Athlete: `/deleteathlete`
Deletes a specific athlete and all associated data (sessions, exercises, and progress records)


Format: `/deleteathlete <Athlete ID>`

Example of usage:

```
/deleteathlete 0001

```


Output:

```
____________________________________________________________
Deleted athlete with ID 0001
____________________________________________________________

```

### Listing All Athletes: `/listathletes`
Displays a list of all registered athletes currently stored in the system, along with their corresponding IDs and their priority flag.


Format: `/listathletes`

Example of usage:

```
/listathletes

```


Output:

```
____________________________________________________________
    1.     [🟥] jonas hardwell (0001)
    2.     [🟨] john doe (0002)
    3.     [ ] jonhattan well (0003)
____________________________________________________________

```

### Viewing All Available Commands: `/help`
Displays a list of all available commands in FitnessONE, categorized by their functionality (Athlete, Session, Exercise, and Others)


Format: `/help`

Example of usage:

```
/help

```


Output:

```
=========================================================
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
/newexercise <Athlete ID> <Session ID> <Name> - Create exercise
/deleteexercise <Athlete ID> <Session ID> <Exercise ID> - Delete exercise
/viewexercises <Athlete ID> <Session ID> - View all exercises
/completeexercise <Athlete ID> <Session ID> <Exercise ID> - Complete exercise
/undoexercise <Athlete ID> <Session ID> <Exercise ID> - Undo completion

=== OTHER COMMANDS ===
/help - Show this help message
/leaderboard - Show the leaderboard
bye - Exit the application

Tip: Each command has its own help. Try using an incorrect format to see details!
=========================================================

```

### Exit FitnessONE  : `bye`
This function exits the program.

Example of usage:

```
bye

```

Output:

```
=========================================================
Bye. Hope to see you again soon!
=========================================================

```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add a new Athlete `/newAthlete <Athlete ID>`
* View Athlete Details `/viewAthlete <Athlete ID>`
* List all Athletes `/listAthletes`
* Add a Session to an Athlete `/newSession <Athlete ID> <Description>`
* Add an Exercise to a Session `/newExercise <Athlete ID> <Session ID> <Description> <set> <rep>`
* Update a Sessions Notes `/updaetSessionNotes <Athlete ID> <Session ID> <Notes>`
* Mark a Session as Completed `/completeSession <Athlete ID> <Session ID>`
* Mark an Exercise as Completed `/completeExercise <Athlete ID> <Session ID> <Exercise ID>`
* Mark a Session as Not Completed `/undoSession <Athlete ID> <Session ID>`
* Mark an Exercise as Not Completed `/undoExercise <Athlete ID> <Session ID> <Exercise ID`
* View Athletes all Session `/viewSessions <Athlete ID>`
* View all Exercise of a Session `/viewExercise <Athlete ID> <Session ID>`
* Delete Exercise from Session `/deleteExercise <Athlete ID> <Session ID> <Exercise ID>`
* Delete Session from Athlete `/deleteSession <Athlete ID> <Session ID>`
* Delete an Athlete `/deleteAthlete <Athlete ID>`
* Flag an Athlete `/flagathlete <Athlete ID> <Color>`
* View Leaderboard /leaderboard `/leaderboard`
* View all available commands  `/help`
* Exit FitnessONE `bye`
