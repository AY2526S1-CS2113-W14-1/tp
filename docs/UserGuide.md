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

Format: `/newExercise <Athlete ID> <Session ID> <Description>`

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
Exercise (ID: 01) completed by Jonas Hardwell (ID: 001).
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
Athlete ID | Name: 0001 - Jonas Hardwell
Status | Session ID | Notes:

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
