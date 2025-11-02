# hyeungac - Project Portfolio Page

## Overview
FitnessOne is a system that helps coach manage athletes in his team, by controlling sessions and exercises of them

---

## Summary of Contributions

### Code Contributed
[RepoSense link](https://nus-cs2113-ay2526s1.github.io/tp-dashboard/?search=hyeungac&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-09-19T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)

### Features Implemented

#### New Feature: New Session Command
    - allowing user to add a session to an athlete with the athlete ID, the system will generate an ID for the session
    - error handling: the implementation includes input validation to ensure the athlete ID is an integer and existing.
    - code `NewSessionCommand.java`

#### New Feature: Delete Session Command
    - allowing user to delete a session to an athlete with the athlete ID and session ID
    - error handling: the implementation includes input validation to ensure the input is exactly 3 parts, and the athlete ID together with session ID are integers and existing.
    - code `DeleteSessionCommand.java`
#### New Feature: Leaderboard Command
    - allowing user to view the achivements of athletes
    - implementation: calculating the sessions and exercises completed by each athlete, rank the athletes
    - error handling: the implementation includes input validation to ensure the input is exactly 3 parts, and the athlete ID together with session ID are integers and existing.
    - `LeaderboardCommand.java`

### Testing
    - adding tests for NewSession and DeleteSession

### Documentation

#### User Guide
    - Added documentation for adding and deleting session features
    - Provided examples and usage patterns for new session and delete session commands

#### Developer Guide
    - added explanation and sequence diagram in implementation for the following parts:
        - CompleteExerciseCommand
        - CompleteSession
        - DeleteAthlete
        - DeleteExercise
        - DeleteSession
        - ExitCommand
        - FlagAthlete
        - Leaderboard
        - ListAthlete

