# Halil Cokeren - Project Portfolio Page

## Overview

## Project: FitnessONE

FitnessONE is a desktop training management application designed for professional fitness coaches to efficiently track and manage multiple athletes and their training programs. The application uses a CLI (Command Line Interface) for fast, keyboard-driven interactions.

Given below are my contributions to the project.

### Summary of Contributions

#### Code Contributed

Repository: [https://github.com/Sheeeesh-code/tp](https://github.com/Sheeeesh-code/tp)

Code Sense: [https://nus-cs2113-ay2526s1.github.io/tp-dashboard/?search=sheeeesh-code&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-09-19T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=](https://nus-cs2113-ay2526s1.github.io/tp-dashboard/?search=sheeeesh-code&breakdown=true)

#### Enhancements implemented

**Created and maintained commands: **NewExerciseCommand**, **DeleteExerciseCommand**, **FlagAthleteCommand**, **HelpCommand**.**


**Automatic command assistance** with standardized help messages. Implemented the `help()` method across all commands, providing instant syntax guidance when errors occur. Each command now includes dedicated constants for usage, examples, and formatting rules, eliminating guesswork for users.

**Centralized exception management** with clear, specific error messages. Removed redundant try-catch blocks from command classes to ensure proper exception propagation. Replaced generic error messages with context-specific guidance, providing users actionable feedback. Simplified constructors while maintaining validation, leveraging the main application's centralized error handling.

**Fixed bugs** including exercise completion errors for non-existent exercises and infinite loop scenarios. Resolved completion command failures that caused application crashes.

#### Tests and quality
Junits test added for : FlagAthlete


#### Documentation

**User Guide:**

**Authored and maintained** a significant part of the User Guide, ensuring all features were accurately documented and regularly updated throughout development.

**Authored** including the complete Appendix Requirement: Requirements section with Product Scope, User Stories, Use Cases, Non-Functional Requirements, and Glossary.

