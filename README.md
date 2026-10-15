# RakshaAI

AI-assisted personal safety and emergency response app for Android, built with Kotlin and Jetpack Compose.

> RakshaAI is a portfolio project. It does not replace official emergency services. In India, call **112** in an emergency.

## Status

| Milestone | Scope | State |
|---|---|---|
| 1 | Project setup, Material 3 theme, dashboard, navigation | Done |
| 2 | Incident reports with Room, repository, MVVM, validation | Planned |
| 3 | Trusted contacts, SOS workflow (MVI) with demo mode | Planned |
| 4 | Retrofit backend client, AI summarization, AI assistant | Planned |
| 5 | Firebase Auth, Firestore sync, Crashlytics, Analytics | Planned |
| 6 | Java interop component, extra tests, polish | Planned |

## What works in Milestone 1

* Home dashboard with SOS entry point, quick actions, trusted contacts and recent incidents sections (real empty states), and an AI assistant card
* Bottom navigation across Home, Incidents, Contacts and Assistant with saved tab state
* Emergency screen that opens the phone dialer for Indian helplines (112, 1091, 181, 108, 1098). The dialer never places a call on its own
* Settings screen describing the app's current behaviour
* Light and dark themes with a fixed, high contrast SOS color
* Features that are not built yet are labeled "Coming next" in the UI instead of being simulated

## Tech stack

Kotlin 2.1, Jetpack Compose (Material 3), Navigation Compose with type-safe routes, ViewModel and StateFlow, Gradle Kotlin DSL with a version catalog. Room, Retrofit and Firebase arrive in later milestones.

## Architecture

```
com.example.rakshaai
├── MainActivity.kt          single activity, edge to edge
├── domain/model             plain Kotlin models (Incident, TrustedContact)
├── ui
│   ├── RakshaApp.kt         root scaffold and bottom bar
│   ├── navigation           routes, NavHost, top level destinations
│   ├── theme                colors, typography, theme
│   ├── components           reusable composables
│   ├── home | incidents | contacts | emergency | assistant | settings
└── util                     dialer helper, emergency resources, time formatting
```

Standard screens follow MVVM: a ViewModel exposes a `StateFlow` of UI state, and a stateless composable renders it. The emergency workflow will use an MVI style reducer in Milestone 3.

## Setup

1. Install the latest stable Android Studio.
2. `File > Open` and choose this folder.
3. Let Gradle sync. Install Android SDK Platform 36 if prompted.
4. Run the `app` configuration on an emulator or device running Android 8.0 (API 26) or newer.

## Tests

```
./gradlew testDebugUnitTest
```

On Windows use `gradlew.bat testDebugUnitTest`. In Android Studio, right click `app/src/test` and choose **Run Tests**.

## Screenshots

_Add screenshots of the dashboard, emergency screen and dark theme here._

## Known limitations

* No data is stored yet; incident and contact screens show empty states
* Emergency alerts to trusted contacts are not implemented yet
* Helpline numbers are for India only

## Future improvements

See the milestone table above.
