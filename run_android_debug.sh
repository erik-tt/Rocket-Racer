#!/bin/sh

# First launch the emulator, then run this script

# Change the following paths according to your setup
PROJECT_PATH="set the path to the project" # ex. "/Users/sindreoyen/Desktop/GitRepos/tdt4240-g21"
ANDROID_SDK_PATH="set the path to the sdk" # ex. "/Users/sindreoyen/Library/Android/sdk"

# Replace com.example.mygame/.AndroidLauncher with your package name and launcher activity
PACKAGE_NAME_AND_ACTIVITY="com.rocketracer.game/.AndroidLauncher"

# Navigate to the project directory and install the APK on the emulator
cd $PROJECT_PATH
./gradlew android:installDebug

# Launch the app on the emulator
$ANDROID_SDK_PATH/platform-tools/adb shell am start -n $PACKAGE_NAME_AND_ACTIVITY
