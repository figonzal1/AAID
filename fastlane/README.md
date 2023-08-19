fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing
_fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android ui_test

```sh
[bundle exec] fastlane android ui_test
```

AndroidTest Instrumentation

### android build

```sh
[bundle exec] fastlane android build
```

Build test apks (DEBUG)

### android capture

```sh
[bundle exec] fastlane android capture
```

Capture screenshots

### android upload_screenshots

```sh
[bundle exec] fastlane android upload_screenshots
```

Upload screenshots to Google Play Store

### android release

```sh
[bundle exec] fastlane android release
```

Build release version

### android googleplay

```sh
[bundle exec] fastlane android googleplay
```

Deploy a new version to the Google Play

----

This README.md is auto-generated and will be re-generated every time [
_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
