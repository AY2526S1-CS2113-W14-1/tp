# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Storage: sequence diagram (timing)

The diagram below shows the interactions involved in persisting and loading the application's data (startup load, and save after each successful command). The diagram is intentionally compact and uses pseudocode-style messages to keep focus on the high-level flow (exception handling and low-level parsing loops are omitted for clarity).

![Storage sequence diagram](docs/diagrams/storage_sequence.png)

Key pointers to the implementation:

- Startup: in `FitnessONE()` (constructor) a `StorageManager` is constructed with the filepath `data/athletes_export.txt` and `load()` is invoked to populate the `Coach` model (see `src/main/java/seedu/fitnessone/FitnessONE.java`).
- Save: after each successful `Command.execute(...)`, `FitnessONE.run()` calls `storage.save(coachController)` to persist the current state (see `FitnessONE.java`).
- `StorageManager.load()` reads the file line-by-line and reconstructs `Athlete`, `Session`, and `Exercise` objects by splitting lines on `|` and unescaping `\n` sequences; it also repairs session/exercise counters to avoid future ID collisions (see `src/main/java/seedu/fitnessone/storage/StorageManager.java`).
- `StorageManager.save()` writes the full model in the following line formats (overwrite):
	- `ATHLETE|athleteId|athleteName`
	- `SESSION|athleteId|sessionId|notes|isCompleted`
	- `EXERCISE|athleteId|sessionId|exerciseId|desc|sets|reps|isCompleted`

Notes:

- The sequence diagram omits exception/IO branches for readability; the code itself catches `IOException` during load (in the `FitnessONE` constructor) and during save (in the run loop) and prints user-visible messages on failure.
- Newlines and pipe characters are escaped on save and restored on load. ID counters are recalculated at load time to avoid collisions when new sessions/exercises are created at runtime.

### Implementation notes (storage feature)

Relevant files and brief descriptions:

- `src/main/java/seedu/fitnessone/FitnessONE.java` — application entry point. Creates `StorageManager` and calls `load()`. Runs the main input loop and calls `save()` after each successful command.
- `src/main/java/seedu/fitnessone/storage/StorageManager.java` — implements `load()` and `save()`; handles file creation, parsing, escaping/unescaping and repairs ID counters.

The sequence diagram above and the short pointers here are sufficient for a developer to locate the exact code blocks that implement the storage behaviour described.

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
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.1|coach|create and manage athlete records|track each student's progress individually|
|v1.2|coach|add training sessions for an athlete|preserve session history and progress over time|
|v1.3|coach|add exercises to a session (sets/reps/weight)|record workout details per session|
|v1.4|coach|log daily macronutrient intake for an athlete|monitor nutrition alongside training|
|v1.5|coach|get diet and exercise recommendations|adapt plans based on tracked data|
|v2.0|coach|export/import athlete data|share and archive team progress data|

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
