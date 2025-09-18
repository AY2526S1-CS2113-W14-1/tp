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

Format: `/viewAthlete <Athlete Name>`

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

Format: `/newSession <Athlete Name> <Session ID> <Description>`

Example of usage: 

```
/newSession Jonas Hardwell 3 chest workout

```

Output:

```
=========================================================
New session created: Jonas Hardwell
Session ID: 3
=========================================================

```

### Adding to a Session a new Exercise : `/newExercise`
Adds an Exercise to the specified Session.
Sessions are specified via Athlete Name + Session ID

Format: `/newExercise <Athlete Name> <Session ID> <Description>`

Example of usage: 

```
/newExercise Jonas Hardwell 3 Benchpress 5x5 80kg

```

Output:

```
=========================================================
New Exercise for Jonas Hardwell created for session 3:
Benchpress 5x5 80kg
=========================================================

```

### Adding to a Session Trainings Notes : `/trainingNotes`
Adds training notes to specified Session.

Format: `/trainingNotes <Athlete Name> <Session ID> <Notes>`

Example of usage: 

```
/trainingNotes Jonas Hardwell 3 keep_energetic:)

```

Output:

```
=========================================================
Training notes for Jonas Hardwell added for session 3:
keep_energetic:)
=========================================================

```

### Marking a Session as Completed : `/complete`
Marks the specified Session as completed.


Format: `/complete <Athlete Name> <Session ID>`

Example of usage: 

```
/complete Jonas Hardwell 3

```

Output:

```
=========================================================
Session 3 completed by Jonas Hardwell
=========================================================
```

### Marking an Exercise as Completed : `/complete`
Marks the specified Exercise as completed


Format: `/complete <Athlete Name> <Session ID> <Exercise ID>`

Example of usage: 

```
/complete Jonas Hardwell 3 1

```

Output:

```
=========================================================
Exercise “Push Ups (10x3)” completed.
Athlete Name: Jonas Hardwell
Session: conditioning

1.   [X] Chest Workout
2.   [ ] Legs Day
3.   [ ] mystery input
=========================================================

```

### Viewing Athlete's all Sessions  : `/viewSession`
View the Session ID which is used in other commands


Format: `/viewSession <Athlete Name>`

Example of usage: 

```
/viewSession Jonas Hardwell

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

### Viewing Session's all Exercises  : `/viewExercise`
This function is used to view  the Exercises that are assigned to a specific Session ID and a specific Athlete Name


Format: `/viewExercise <Athlete Name> <Session ID>`

Example of usage: 

```
/viewExercise Jonas Hardwell 3

```

Output:

```
=========================================================
Athlete Name: Jonas Hardwell
Session Note: keep_energetic :)
Session ID : 3


7.   [X] Push Ups (10x3)
8.   [ ] Dips (5x2)
9.   [ ] Ben (10x4)
=========================================================

```

### Deleting Exercise from Session  : `/deleteExercise`
This function is used to delete a specific exercise that is assigned to a specific session ID and a specific Athlete Name


Format: `/deleteExercise <Athlete Name> <Session ID> <Exercise ID>`

Example of usage: 

```
/deleteExercise Jonas Hardwell 3 5

```

Output:

```
=========================================================
Exercise 5 deleted in Session 3 for Jonas Hardwell
=========================================================

```

### Deleting Session from Athlete  : `/deleteSession`
This function is used to delete a specific session that is assigned to a specific athlete


Format: `/deleteSession <Athlete Name> <Session ID>`

Example of usage: 

```
/deleteExercise Jonas Hardwell 3

```

Output:

```
=========================================================
Session 3 deleted for Jonas Hardwell
=========================================================

```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add a new Athlete `/newAthlete <Athlete Name>`
* View Athlete Details `/viewAthlete <Athlete Name>`
* Add a Session to an Athlete `/newSession <Athlete Name> <Session ID> <Description>`
* Add to a Exercise to a Session `/newExercise <Athlete Name> <Session ID> <Description>`
* Add a Training Note to a Session `/trainingNotes <Athlete Name> <Session ID> <Notes>`
* Mark a Session as Completed `/complete <Athlete Name> <Session ID>`
* Mark an Exercise as Completed `/complete <Athlete Name> <Session ID> <Exercise ID>`
* View Athletes all Session `/viewSession <Athlete Name>`
* View all Exercise of a Session `/viewExercise <Athlete Name> <Session ID>`
* Delete Exercise from Session `/deleteExercise <Athlete Name> <Session ID> <Exercise ID>`
* Delete Session from Athlete `/deleteSession <Athlete Name> <Session ID>`
