# Developer Guide

## Acknowledgements

We would like to express our gratitude to:

* Professor **Akshay Narayan** for his guidance and supervision of this project
* Teaching Assistant **Hing Yen Xing** for their valuable feedback and support

And our team members who contributed to this project:

* **Halil Cokeren**
* **Toh Ee Sen Izen**
* **Yeung Ho / Gordon**
* **Philip Hansson** 
* **Ma Zhiheng** 

## Design & implementation

### Architecture Overview

The diagram below provides a comprehensive view of FitnessONE's architecture and class interactions:

![Overall Architecture](diagrams/overall_UML.png)



### Program Flow

#### Main Execution Loop

![Main program loop](diagrams/main_loop.png)

The sequence diagram above shows how FitnessONE processes commands in its main loop, from user input through execution and persistence.

#### Storage Operations

![Storage sequence diagram](diagrams/storage_sequence.png)

This diagram illustrates the data persistence flow, showing startup loading and per-command saving operations.

## Product scope

Product name: FitnessONE

### Target user profile

FitnessONE is designed for professional fitness coaches who need a compact, scriptable CLI tool to manage and monitor multiple students' training routines and nutrition. Typical users:

- Individual coaches running small teams or classes
- Strength & conditioning coaches tracking sessions and macros
- Coaches who prefer lightweight CLI tools and version-controlled data

### Value proposition

FitnessONE provides coaches an efficient, data-driven platform for exercise logging, macronutrient tracking, and automatic recommendations for diets and exercises. It is simple to use and integrates persistent storage so coaches can generate progress reports and plan long-term training efficiently.

### Key features (current / planned)

- Track athletes (create, view, update athlete records)
- Track training sessions per athlete (notes, completed flag)
- Track exercises within sessions (description, sets, reps, completed)
- Track macronutrients (protein/carbs/fats) per athlete and per day (planned)
- Recommend diet plans and exercise suggestions based on logged data (planned)
- Persistent storage to `data/athletes_export.txt` (load on startup, save after successful commands)
- Import/export and round-trip loading for reproducible testing

### Brainstorming and references

- Week 5 brainstorming notes (planning and options considered): https://docs.google.com/spreadsheets/d/1CCIecnaanB5N0eg2bsRAJShKHbCECX9t7Hzhew0F9aI/edit?usp=sharing
- Input model options considered:
	- Predetermined plans (pre-built programs coaches can assign)
	- Fully user-input exercises (coach enters exercise name, sets, reps, weight)

### Non-goals (out of scope for current release)

- Real-time sync across devices or multi-user concurrent editing
- Complex GUI client (this is a CLI-first tool)


## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|coach|create and manage athlete records; add training sessions and exercises; log daily macronutrients|track each student's training and nutrition data and preserve session history|
|v2.0|coach|receive diet and exercise recommendations; export/import athlete data|adapt plans automatically based on tracked data and share/archive team progress|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Appendix E: Instructions for Manual Testing (storage)

This appendix provides a short path a tester can follow to verify the storage feature (startup load and save-on-command).

1. Start with a fresh/known state:
	- If you already have a `data/athletes_export.txt` file, back it up or delete it to observe creation from scratch.

2. Run the application (from project root):

```powershell
./gradlew run
```

3. Verify startup behaviour:
	- With no `data/athletes_export.txt`: observe the welcome message and the printed fallback message ("No saved data found, starting with empty data.").
	- With an existing file containing saved data: the app should report "Loaded saved athletes data." and the athletes/sessions/exercises should be accessible via view commands.

4. Test save-on-command:
	- Add an athlete using the appropriate command (see User Guide). For example (copy-paste):
	  - `addAthlete A01 John Doe`  (replace with actual command used in the app)
	- Add a session/exercise for that athlete.
	- Exit the application using the `exit` command.
	- Confirm that `data/athletes_export.txt` was created/updated and contains lines starting with `ATHLETE|`, `SESSION|`, `EXERCISE|`.

5. Test load-on-startup round-trip:
	- Restart the application.
	- Confirm the previously added athlete and sessions are present.

6. Edge checks:
	- Make sure multi-line notes are preserved across save/load (newlines are escaped as `\n` on save and restored on load).
	- Confirm that saving overwrites the data file (it does not append duplicate entries for the same runtime state).

If any of the above steps fail, capture the console output and the contents of `data/athletes_export.txt` and file an issue with those artifacts.

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
