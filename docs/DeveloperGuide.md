# Developer Guide
Format inspired by [addressbook-level3](https://se-education.org/addressbook-level3/DeveloperGuide.html#proposed-undoredo-feature)

## Table of Contents
* [Acknowledgements](#acknowledgements)
* [Setting up, getting started](#setting-up-getting-started)
* [Design](#design)
  * [Architecture](#architecture)
  * [UI component](#ui-component)
  * [Logic component](#logic-component)
  * [Model component](#model-component)
  * [Storage component](#storage-component)
  * [Common classes](#common-classes)
* [Implementation](#implementation)
  * [[Proposed] Undo/redo feature](#proposed-undoredo-feature)
    * [Proposed Implementation](#proposed-implementation)
    * [Design considerations](#design-considerations)
  * [[Proposed] Data archiving](#proposed-data-archiving)
* [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
* [Appendix: Requirements](#appendix-requirements)
  * [Product scope](#product-scope)
  * [User stories](#user-stories)
  * [Use cases](#use-cases)
  * [Non-Functional Requirements](#non-functional-requirements)
  * [Glossary](#glossary)
* [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)
  * [Launch and shutdown](#launch-and-shutdown)
  * [Deleting a person](#deleting-a-person)
  * [Saving data](#saving-data)

## Acknowledgements

## Setting up, getting started

## Design

### Architecture

### UI component
DISCLAIMER: GPT was assisted in creating this, will use as a rule of thumb later on perhaps how to structure this,
needs discssion with group...
Responsibilities
- Provide a command-line interface (CLI) for user interaction.
- Read the raw user input, display formatted output and error messages.
- Delegate input parsing to `seedu.fitnessone.ui.Parser`.
- Forward parsed commands to the Logic component and display command results.
- UI does not contain business logic; it only formats input/output and handles presentation concerns.

Key classes / modules
- `seedu.fitnessone.ui.Parser` — converts raw input strings to command objects and validates basic syntax.
- `Ui` / `TextUi` (or similarly named) — reads input from stdin, prints to stdout, and formats messages.
- Any `Message` or `UiStrings` class — centralises user-facing strings and error messages.

Input processing
- UI reads a the input parsing it to `Parser.parse(...)` and passes the resulting Command to our Logic component.
- For invalid input or domain errors (e.g. `InvalidAthleteException`), UI catches exceptions and prints a user-friendly message such as:
    - `Error: Athlete not found - 0001`
    - `Caused by: seedu.fitnessone.exception.InvalidAthleteException: Invalid Athlete ID: 0001`

Formatting and examples
- Keep prompts and responses simple and consistent. Example interaction:
    - Input: `view 0001`
    - Output: `Showing athlete 0001: <name> — <summary>`
    - Error: `Error: Athlete not found - 0001`

Testing and style
- Unit-test Parser and UI formatting methods separately.
- Ensure checkstyle rules are followed.
- Provide acceptance tests that assert end-to-end input/output behavior.

Design notes
- Keep UI code minimal so changes to presentation do not affect logic.
- Centralise all user strings to make updating messages and translations easier.

### Logic component

### Model component

### Storage component

### Common classes

## Implementation

### [Proposed] Undo/redo feature

#### Proposed Implementation

#### Design considerations

### [Proposed] Data archiving

## Documentation, logging, testing, configuration, dev-ops

## Appendix: Requirements

### Product scope

### User stories

### Use cases

### Non-Functional Requirements

### Glossary

## Appendix: Instructions for manual testing

### Launch and shutdown

### Deleting a person

### Saving data