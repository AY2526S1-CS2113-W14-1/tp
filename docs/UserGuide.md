# User Guide : FitnessONE

## Introduction

FitnessONE is a powerful CLI application designed for professional fitness coaches to streamline the monitoring and management of their students' training routines. The platform provides an efficient, data-driven solution for exercise logging, tracking training schedules, and analyzing team performance.

Target Users: Professional fitness coaches who need to manage multiple athletes' training programs, track progress, and maintain detailed training records.

Value Proposition: FitnessONE offers coaches a simple yet comprehensive platform with deep data integration capabilities, enabling efficient progress reports and long-term training planning

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
2. Down the latest version of `FitnessONE` from [here](https://github.com/AY2526S1-CS2113-W14-1/tp).

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

Format: `/newExercise <Athlete ID> <Session ID> <Exercise Description>`

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
Marks the specified Session as completed.


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
Marks the specified Session as completed.


Format: `/undoSession <Athlete ID> <Session ID>`

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

### Recording Daily Calories Intake: `/caloriesIntake`
Allows the coach to log or update an athlete's daily calorie intake.  
If an intake is already recorded for that date, it will be replaced, with a warning shown.

Format: `/caloriesIntake <Athlete ID> <YYYY-MM-DD> <Calories>`

Example of usage:

```
/caloriesIntake 0001 2025-10-28 50 20 20
```

Output:

```
=========================================================
Calories intake recorded for Athlete 0001 on 2025-10-28 (Carbs: 50, Protein: 20, Fat: 20)
=========================================================
```

### Viewing Daily Calories Intake: `/viewCaloriesIntake`
Allows the coach to view an athlete's calorie intake for a specific date.

Format: `/viewCaloriesIntake <Athlete ID> <YYYY-MM-DD>`

Example of usage:

```
/viewCaloriesIntake 0001 2025-10-28
```

Output:

```
=========================================================
Calories intake for Athlete 0001 on 2025-10-28 (Carbs: 50, Protein: 20, Fat: 20)
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
* Add a Session to an Athlete `/newSession <Athlete ID> <Description>`
* Add to an Exercise to a Session `/newExercise <Athlete ID> <Session ID> <Description>`
* Update a Sessions Notes `/updaetSessionNotes <Athlete ID> <Session ID> <Notes>`
* Mark a Session as Completed `/completeSession <Athlete ID> <Session ID>`
* Mark an Exercise as Completed `/completeExercise <Athlete ID> <Session ID> <Exercise ID>`
* View Athletes all Session `/viewSessions <Athlete ID>`
* View all Exercise of a Session `/viewExercise <Athlete ID> <Session ID>`
* Delete Exercise from Session `/deleteExercise <Athlete ID> <Session ID> <Exercise ID>`
* Delete Session from Athlete `/deleteSession <Athlete ID> <Session ID>`
* Record daily calories intake `/caloriesIntake <Athlete ID> <YYYY-MM-DD> <Calories>`
* View daily calories intake `/viewCaloriesIntake <Athlete ID> <YYYY-MM-DD>`