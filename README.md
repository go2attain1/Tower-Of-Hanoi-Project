# Tower of Hanoi

A Java implementation of the classic Tower of Hanoi puzzle, built around a custom linked-list `LinkedStack` and a recursive solver. A Swing-based window animates each disk move as the algorithm runs.

The project separates the **back-end** (puzzle logic) from the **front-end** (display) using the Observer pattern: `HanoiSolver` extends `Observable`, and `PuzzleWindow` implements `Observer`. When a disk moves, the solver notifies the window, which redraws the disk.

<!-- Add a screenshot or GIF of the running program here:
![Tower of Hanoi demo](docs/demo.gif)
-->

## Features

- Custom `LinkedStack<T>` built from singly linked nodes (no library stack used)
- `Tower` enforces the puzzle's rule: a disk can only be placed on a larger disk
- Recursive solver that moves the full stack from the left pole to the right pole
- Animated GUI with randomly colored disks and a **Solve** button
- Configurable disk count via a command-line argument (default: 6)
- Unit tests for every back-end class

## How It Works

The solver uses the standard recursive algorithm. To move `n` disks from a start pole to an end pole:

1. Move the top `n - 1` disks from the start pole to the temporary pole.
2. Move the largest disk from the start pole to the end pole.
3. Move the `n - 1` disks from the temporary pole to the end pole.

The base case is a single disk, which is moved directly. Solving `n` disks takes `2^n - 1` moves.

Each call to `move()` pops a disk from the source tower, pushes it onto the destination tower, and notifies observers with the destination `Position`. `PuzzleWindow.update()` receives that position, repositions the disk on screen, and pauses briefly so the move is visible. The solver runs on its own thread so the window stays responsive while animating.

## Project Structure

All classes live in the `towerofhanoi` package.

| File | Role |
|------|------|
| `Disk.java` | A `Shape` (rectangle) with a width and random color. Implements `Comparable<Disk>`; equality and ordering are based on width. |
| `LinkedStack.java` | Generic stack backed by a private inner `Node` class. Tracks its own size and supports `push`, `pop`, `peek`, `clear`, `isEmpty`, `size`, and `toString`. |
| `Position.java` | Enum for tower locations: `LEFT`, `CENTER`, `RIGHT`, `DEFAULT`. |
| `Tower.java` | Extends `LinkedStack<Disk>` and overrides `push` to reject illegal moves. |
| `HanoiSolver.java` | Back-end. Holds the three towers, runs the recursive solution, and notifies observers on each move. |
| `PuzzleWindow.java` | Front-end. Builds the window, poles, disks, and Solve button, and animates moves in response to notifications. |
| `ProjectRunner.java` | Contains `main`. Creates the solver and window. |
| `*Test.java` | Test classes for `Disk`, `LinkedStack`, `Tower`, and `HanoiSolver`. |

### Behavior notes

- `LinkedStack.pop()` and `peek()` throw `java.util.EmptyStackException` on an empty stack.
- `LinkedStack.push(null)` is ignored.
- `Tower.push()` throws `IllegalArgumentException` for a `null` disk and `IllegalStateException` if the disk is larger than the one on top.
- `LinkedStack.toString()` lists items from top to bottom, e.g. `[C, B, A]`, and `HanoiSolver.toString()` concatenates the three towers, e.g. `[5, 15][25][]`.
- `HanoiSolver.getTower()` returns the center tower for `DEFAULT` or `null`.

## Requirements

- Java 8 or later (the project uses `java.util.Observable` / `Observer`, which are deprecated in newer JDKs but still available; compiler deprecation warnings can be ignored)
- The following external JARs on your build path:
  - `student.jar` (provides `student.TestCase` and `student.TestableRandom`)
  - `CarranoDataStructures.jar` (provides `stack.StackInterface`)
  - `CS2-GraphWindowLib.jar` (provides the `cs2` window, shape, and button classes)

> Only `StackInterface` is used from the data structures library. `LinkedStack` and `Node` are implemented in this project.

## Getting Started

### Eclipse

1. Create a Java project and place the source files in a package named `towerofhanoi`.
2. Add the three JAR files to the project's build path.
3. Run `ProjectRunner` as a Java Application.

### Command line

Place the JARs in a `lib/` folder next to your source files, then compile and run:

```bash
# Compile (use ';' instead of ':' on Windows)
javac -cp "lib/*" -d bin towerofhanoi/*.java

# Run with the default 6 disks
java -cp "bin:lib/*" towerofhanoi.ProjectRunner

# Run with a custom number of disks
java -cp "bin:lib/*" towerofhanoi.ProjectRunner 4
```

Click **Solve** in the window to watch the puzzle being solved. If the argument is not a valid integer, a message is printed and the default disk count is used.

## Running the Tests

The test classes extend `student.TestCase` (JUnit 3 style). Run them from your IDE as JUnit tests, or from the command line:

```bash
junit_runner="org.junit.runner.JUnitCore"
java -cp "bin:lib/*" $junit_runner \
  towerofhanoi.DiskTest \
  towerofhanoi.LinkedStackTest \
  towerofhanoi.TowerTest \
  towerofhanoi.HanoiSolverTest
```

(This assumes a JUnit JAR is available on the classpath; `student.jar` may already bundle one.)

| Test class | Covers |
|------------|--------|
| `DiskTest` | `compareTo`, `toString`, `equals` |
| `LinkedStackTest` | `push`, `pop`, `peek`, `size`, `isEmpty`, `clear`, `toString` |
| `TowerTest` | `position`, valid and invalid `push` |
| `HanoiSolverTest` | `disks`, `getTower`, `toString`, and `solve` for 1 to 6 disks |

`PuzzleWindow` and `ProjectRunner` are GUI and entry-point classes and are not unit tested.

## Possible Extensions

- Step-by-step button to advance one move at a time
- Adjustable animation speed based on disk count
- Drag-and-drop or click-to-move so users can solve the puzzle themselves
- Poles and bases sized dynamically to the number of disks

