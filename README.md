<div align="center">

# avaj-launcher

### 42 — Enter the Java Path

![42 School](https://img.shields.io/badge/42-School-000000?style=for-the-badge&logo=42&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Design_Patterns-6DB33F?style=for-the-badge)
![UML](https://img.shields.io/badge/UML-Class_Diagram-0078D4?style=for-the-badge)

</div>

---

## Table of Contents

- [Overview](#overview)
- [Design Patterns](#design-patterns)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [Scenario File](#scenario-file)
- [Aircraft Behaviour](#aircraft-behaviour)
- [Simulation Rules](#simulation-rules)
- [Bonus](#bonus)

---

## Overview

**avaj-launcher** is an aircraft simulation program built as an introduction to Java and object-oriented design at 42. The goal is not just to write working code, but to write *good* OO code — clean, readable, and easy to extend.

The simulator reads a scenario file, registers aircraft on a weather tower, and runs a configurable number of weather cycles. Each aircraft reacts to weather according to its type, adjusting position and altitude, logging messages, and eventually landing when it reaches the ground.

Tournament of design patterns, UML diagrams, and the Observer/Singleton/Factory trio.

---

## Design Patterns

| Pattern | Usage |
|---|---|
| **Singleton** | `WeatherTower` — only one tower exists throughout the simulation |
| **Observer** | Aircraft register/unregister to the tower and are notified on each weather change |
| **Factory** | `AircraftFactory` — creates the correct aircraft subclass from a string type |

---

## Architecture

The class hierarchy follows the UML diagram provided by the subject. No access modifiers or class relationships were altered.
<img width="1920" height="633" alt="uml" src="https://github.com/user-attachments/assets/85b096b2-24c2-4888-8c32-2b4be8cfaeb8" />


Each aircraft implements `Flyable` and registers itself as an observer on the `WeatherTower`. When a weather cycle runs, the tower calls `updateConditions()` on each registered aircraft. Aircraft that reach height `0` unregister themselves automatically.

---

## Getting Started

**Requirements**

- `javac` and `java` available in your terminal (any Java LTS version)
- No external libraries, build tools, or IDEs required

**Compile**

```bash
find * -name "*.java" > sources.txt
javac @sources.txt
```

**Run**

```bash
java ro.academyplus.avaj.simulator.Simulator scenario.txt
```

This generates a `simulation.txt` file with the full simulation log.

---

## Scenario File

```
4
Balloon B1 3 4 59
JetPlane J1 0 0 20
Helicopter H1 5 5 13
Helicopter H4 23 12 100
```

- **Line 1** — number of weather cycles to simulate
- **Subsequent lines** — one aircraft per line: `TYPE NAME LONGITUDE LATITUDE HEIGHT`

Valid types: `Balloon`, `JetPlane`, `Helicopter`

If the input file is invalid (wrong format, out-of-range values, unknown type), the program prints an error and exits immediately.

---

## Aircraft Behaviour

Weather is generated per-coordinate. Each aircraft type reacts differently to each weather condition:

| Aircraft | SUN | RAIN | FOG | SNOW |
|---|---|---|---|---|
| **JetPlane** | Latitude +10, Height +2 | Latitude +5 | Latitude +1 | Height -7 |
| **Helicopter** | Longitude +10, Height +2 | Longitude +5 | Longitude +1 | Height -12 |
| **Balloon** | Longitude +2, Height +4 | Height -5 | Height -3 | Height -15 |

Each weather event triggers a log message to `simulation.txt` in the format:

```
TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE
```

---

## Simulation Rules

- Coordinates are always positive numbers
- Height is clamped to the `[0, 100]` range
- Each aircraft is assigned a **unique ID** at creation — no two aircraft share an ID
- If height would exceed `100`, it stays at `100`
- If height reaches `0` or would go below, the aircraft **lands**: it logs a landing message and unregisters from the tower
- Registering and unregistering both produce a tower log:
  ```
  Tower says: TYPE#NAME(ID) registered to weather tower.
  Tower says: TYPE#NAME(ID) unregistered from weather tower.
  ```

---

## Bonus

- ✅ **Custom exceptions** — invalid input, unknown aircraft type, and out-of-range values each throw dedicated exception classes instead of generic errors, giving precise feedback on what went wrong

---

<div align="center">
42 School
</div>
