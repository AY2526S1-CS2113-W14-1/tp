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
/viewAthlete Jonas Hardwell
```
Output:

```
=========================================================
Athlete: Jonas Hardwell
Sessions:
-   1 Chest Workout
-   2 Legs Day
-   3 mystery input
=========================================================

```

### Adding to an Athlete a new Session: `/newSession`
Adds a Session to the specified Athlete
Specifies the Description for the Session

Format: `/newSession <Athlete ID> <Description>`

Example of usage: 

```
/newSession 0001 chest workout

```

Output:

```
=========================================================
New session created: chest workout
Session ID: 001
=========================================================

```

### Adding to a Session a new Exercise : `/newExercise`
Adds an Exercise to the specified Session.
Sessions are specified via Athlete ID + Session ID

Format: `/newExercise <Athlete ID> <Session ID> <Description>`

Example of usage: 

```
/newExercise 0001 002 Benchpress 5x5 80kg

```

Output:

```
=========================================================
New Exercise for Jonas Hardwell (ID: 0001) created for session (ID: 003):
Benchpress 5x5 80kg (ID: 01)
=========================================================

```

### Adding to a Session Trainings Notes : `/trainingNotes`
Adds training notes to specified Session.

Format: `/trainingNotes <Athlete ID> <Session ID> <Notes>`

Example of usage: 

```
/trainingNotes 0001 003 keep_energetic:)

```

Output:

```
=========================================================
Training notes for Jonas Hardwell (ID: 0001) created for session (ID: 003):
keep_energetic:)
=========================================================

```

### Marking a Session as Completed : `/complete`
Marks the specified Session as completed.


Format: `/complete <Athlete ID> <Session ID>`

Example of usage: 

```
/complete 0001 003

```

Output:

```
=========================================================
Session (ID: 003) completed by Jonas Hardwell
=========================================================
```

### Marking an Exercise as Completed : `/complete`
Marks the specified Exercise as completed


Format: `/complete <Athlete Name> <Session ID> <Exercise ID>`

Example of usage: 

```
/complete 0001 003 01
```

Output:

```
=========================================================
Exercise (ID: 01) “Push Ups (10x3)” completed.
Athlete Name: Jonas Hardwell
Session: conditioning

1.   [X] Chest Workout
2.   [ ] Legs Day
3.   [ ] mystery input
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
Athlete Name: Jonas Hardwell

4.   [X] pre training warmup
5.   [ ] post training cool down
6.   [ ] conditioning
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
Athlete Name: Jonas Hardwell
Session Note: keep_energetic :)
Session ID : 003


7.   [X] Push Ups (10x3)
8.   [ ] Dips (5x2)
9.   [ ] Ben (10x4)
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
* Add a Session to an Athlete `/newSession <Athlete ID> <Session ID> <Description>`
* Add to a Exercise to a Session `/newExercise <Athlete ID> <Session ID> <Description>`
* Add a Training Note to a Session `/trainingNotes <Athlete ID> <Session ID> <Notes>`
* Mark a Session as Completed `/complete <Athlete ID> <Session ID>`
* Mark an Exercise as Completed `/complete <Athlete ID> <Session ID> <Exercise ID>`
* View Athletes all Session `/viewSession <Athlete ID>`
* View all Exercise of a Session `/viewExercise <Athlete ID> <Session ID>`
* Delete Exercise from Session `/deleteExercise <Athlete ID> <Session ID> <Exercise ID>`
* Delete Session from Athlete `/deleteSession <Athlete ID> <Session ID>`
