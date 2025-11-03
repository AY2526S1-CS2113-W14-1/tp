# Halil Cokeren - Project Portfolio Page

## Overview

## Project: FitnessONE

FitnessONE is a desktop training management application designed for professional fitness coaches to efficiently track and manage multiple athletes and their training programs. The application uses a CLI (Command Line Interface) for fast, keyboard-driven interactions.

Given below are my contributions to the project.

### Summary of Contributions

#### Code Contributed

Repository: [https://github.com/Sheeeesh-code/tp](https://github.com/Sheeeesh-code/tp)

Code Sense: [https://nus-cs2113-ay2526s1.github.io/tp-dashboard/?search=sheeeesh-code&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-09-19T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=](https://nus-cs2113-ay2526s1.github.io/tp-dashboard/?search=sheeeesh-code&breakdown=true)

### Features Implemented

#### New Feature: New Exercise Command

* Allows user to add an exercise to a specific athlete's session using athlete ID, session ID, exercise description, sets and reps
* Error handling: Validates that athlete ID (4 digits) and session ID (3 digits) exist, ensures sets and reps are positive integers
* Code: NewExerciseCommand.java

#### New Feature: Delete Exercise Command

* Allows user to delete a specific exercise from an athlete's session using athlete ID, session ID, and exercise ID
* Error handling: Validates input has exactly 4 parts, ensures all IDs exist in the system
* Code: DeleteExerciseCommand.java

#### New Feature: Flag Athlete Command

* Allows user to flag an athlete with a color for priority tracking (red, yellow, green)
* Error handling: Validates athlete ID (4 digits), ensures specified color is valid
* Code: FlagAthleteCommand.java

#### New Feature: Help Command

* Displays all available commands in the application, organized by categories (athlete commands, session commands, exercise commands, others)
* Error handling: Ensures no arguments are provided with the command
* Code: HelpCommand.java


### Enhancements to Existing Features

**Automatic command assistance** with standardized help messages. Implemented the `help()` method across all commands, providing instant syntax guidance when errors occur. Each command now includes dedicated constants for usage, examples, and formatting rules, eliminating guesswork for users.

**Centralized exception management** with clear, specific error messages. Removed redundant try-catch blocks from command classes to ensure proper exception propagation. Replaced generic error messages with context-specific guidance, providing users actionable feedback. Simplified constructors while maintaining validation, leveraging the main application's centralized error handling.

**Fixed bugs** including exercise completion errors for non-existent exercises and infinite loop scenarios, same for undo exercise. Resolved completion command failures that caused application crashes. ALso Fixed a lot of bugs after the PED : as the storage wasn't saving and loading the flag color, there was a date time issue when loading a date it was not loading the time when it was created but the time when it was loaded,

#### Tests and quality
Junits test added for : FlagAthlete


#### Documentation

**User Guide:**

**Authored and maintained** a significant part of the User Guide, ensuring all features were accurately documented and regularly updated throughout development.

**Developper Guide:**

**Authored** including the complete Appendix Requirement: Requirements section with Product Scope, User Stories, Use Cases, Non-Functional Requirements, and Glossary.

