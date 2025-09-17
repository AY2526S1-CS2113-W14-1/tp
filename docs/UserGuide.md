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

New athlete created: Jonas Hardwell
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

Athlete: Jonas Hardwell  
 Sessions:  
  - 1 Chest Workout  
  - 2 Legs Day  
  - 3 mystery input
```



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
