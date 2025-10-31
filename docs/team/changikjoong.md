# ChangIkJoong - Project Portfolio Page

## Overview
- FitnessONE is a CLI (Command Line Interface) training planner for tracking Athletes schedules and training sessions.
- The application supports creating, updating, completing and deleting entities with ID-based validation and undo functionality.

### Summary of Contributions
- Code contributed
    - Repository: https://github.com/ChangIkJoong/tp
    - Code Sense: https://nus-cs2113-ay2526s1.github.io/tp-dashboard/?search=changikjoong
    - Major commits and PRs available on the repo PR list.

- Enhancements implemented
    - Created and maintained commands: `CompleteExerciseCommand`, `CompleteSessionCommand`, `DeleteExerciseCommand`, `DeleteSessionCommand`, `UndoSessionCommand`, `UpdateSessionCommand`.
    - Reworked full project structure to adopt explicit IDs for `Athlete`, `Session`, and `Exercise`.
    - Implemented session date-time handling and persistence (added date-time field to `Session`).
    - Added runtime assertions and validation in mutators (e.g., `addSession`, `addExercise`) to ensure collection sizes change as expected.
    - Parser improvements: stricter ID validation and clearer error messages.
    - Streamlined exception hierarchy and consistent throwing behavior.

- Tests and quality
    - JUnit tests added for: `CompleteExercise`, `DeleteAthlete`, `CompleteSession`, `NewExercise`, `Coach`, `Parser`, `Athlete` and `Session`.
    - Added tests covering ID validation and parser error cases.