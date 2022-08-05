# SJ-Dialog
Android Studio library for dialog builder

## Download
### Step 1. Add the JitPack repository 
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```
or in settings.gradle file:

```gradle
dependencyResolutionManagement {
	repositories {
		maven { url 'https://jitpack.io' }
    	}
}
```

### Step 2. Add the dependency
```gradle
dependencies {
	implementation 'com.github.slavce14:SJ-Dialog:1.5'
}
```
[![](https://jitpack.io/v/slavce14/SJ-Dialog.svg)](https://jitpack.io/#slavce14/SJ-Dialog)
## Documentation
* [SetupDialogDoc](/SJDialog/SetupDialogDoc.md): Documentation for SetupDialog
* [MessageDialogDoc](/SJDialog/MessageDialogDoc.md): Documentation for MessageDialog
* [ListDialogDoc](/SJDialog/ListDialogDoc.md): Documentation for ListDialog
* [CustomViewDialogDoc](/SJDialog/CustomViewDialogDoc.md): Documentation for CustomViewDialog
