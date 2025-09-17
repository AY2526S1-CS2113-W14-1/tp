# User Guide : FitnessONE

## Introduction

FitnessONE is designed for professional fitness coaches to streamline the monitoring and managing of their student’s training routines. This involves their training schedules, nutritional intake, and overall fitness progress. 

(The CLI Application offers coaches an efficient and data-driven platform for exercise logging, macronutrients consumption, and provides deep analysis of team performance. It is simple to use, with deep data integration to be used for progress reports and long-term planning.)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

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

### Adding to a Session a new Exercise : `/newSession`
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

### Adding to a Session isCompleted : `/complete`
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



















### Adding to a Exercise isCompleted : `/complete`
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


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
