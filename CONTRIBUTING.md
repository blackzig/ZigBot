# Contributing to ZigBot

Thanks for helping build ZigBot.

## Development setup

- Java 21+
- Gradle 8+

Run:

```bash
gradle test
gradle run
```

## Guidelines

- Keep the simulation core independent from vendor-specific robotics libraries.
- Add tests for behavior changes.
- Prefer small, focused classes and commits.
- Document architectural decisions that introduce new dependencies or layers.
- Do not copy code from third-party robotics repositories unless its license explicitly permits that use and all required notices are preserved.

## Pull requests

A pull request should explain:

- what changed;
- why it changed;
- how it was tested;
- whether it affects architecture or compatibility.
