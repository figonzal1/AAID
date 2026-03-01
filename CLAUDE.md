# CLAUDE.md

## Language preferences

- **GitHub content** (commits, PR titles, PR bodies, PR comments, issue titles, issue bodies): always write in **English**.
- **Chat / conversation with the user**: always respond in **Spanish**.

## Project overview

**AAID** is a single-module Android app that displays the Google Advertising ID (AAID) of the device. It also handles UMP consent (EU users) and shows AdMob banner ads.

- **Min SDK**: 27 | **Target/Compile SDK**: 36
- **Language**: Kotlin 2.3.10
- **UI**: Jetpack Compose + Material3 + Navigation Compose
- **Architecture**: MVVM (`AAIDViewModel`) with Compose state (`mutableStateOf`)
- **DI**: None — ViewModel instantiated via `viewModel()` composable

## Key files

| File | Purpose |
|---|---|
| `app/src/main/java/cl/figonzal/aaid/MainActivity.kt` | Entry point; UMP consent + MobileAds init |
| `app/src/main/java/cl/figonzal/aaid/AppNavHost.kt` | Navigation graph |
| `app/src/main/java/cl/figonzal/aaid/ui/screens/main/AAIDViewModel.kt` | Fetches AAID; exposes `AaidState` |
| `app/src/main/java/cl/figonzal/aaid/core/ApplicationController.kt` | Timber init |
| `gradle/libs.versions.toml` | Version catalog (single source of truth for all deps) |
| `keys/keystore.properties` | Signing config (not in git) |
| `secrets.properties` | AdMob IDs injected via Secrets Gradle Plugin (not in git) |

## Architecture pattern

`AaidState` is a sealed interface that drives the entire main UI:

```kotlin
sealed interface AaidState {
    data object Loading : AaidState
    data class Success(val aaid: String) : AaidState
    data class Error(val message: String) : AaidState
}
```

## Build commands

```bash
# Assemble debug APK
./gradlew assembleDebug

# Assemble release AAB
./gradlew bundleRelease

# Run unit tests
./gradlew test

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedDebugAndroidTest

# Lint
./gradlew lint

# Check for dependency updates
./gradlew dependencyUpdates

# Update version catalog
./gradlew versionCatalogUpdate
```

## Fastlane lanes

```bash
bundle exec fastlane ui_test          # Run instrumented tests on connected device
bundle exec fastlane build            # Clean + assemble debug + androidTest APKs
bundle exec fastlane capture          # Capture screenshots via screengrab
bundle exec fastlane upload_screenshots  # Upload screenshots to Google Play
bundle exec fastlane release          # Build signed release AAB
bundle exec fastlane googleplay       # Upload AAB to Google Play (draft)
```

## Gotchas

- **Signing**: `keys/keystore.properties` must exist locally with `storeFile`, `storePassword`, `keyAlias`, `keyPassword`. Not in git.
- **AdMob IDs**: `secrets.properties` at root holds `ADMOB_APP_ID` and related keys, injected at build time via the Secrets Gradle Plugin. Debug build uses test AdMob IDs automatically via `resValue`.
- **UMP consent**: `MobileAds.initialize()` is only called after `consentInformation.canRequestAds()` returns true. Do not move or skip this check.
- **Firebase aliases**: Firebase libraries use non-`-ktx` aliases (e.g., `firebase-analytics`, not `firebase-analytics-ktx`) since the BOM ≥ 33.x unifies APIs.
- **Compose state**: `AAIDViewModel.state` uses `mutableStateOf` (not `StateFlow`) — Compose reads it directly without `collectAsState()`.
