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
	implementation 'com.github.slavce14:SJ-Dialog:1.4'
}
```
[![](https://jitpack.io/v/slavce14/SJ-Dialog.svg)](https://jitpack.io/#slavce14/SJ-Dialog)
## Documentation
* [SetupDialogDoc](https://github.com/SlaVcE14/SJ-Dialog/blob/master/SJDialog/SetupDialogDoc.md): Documentation for SetupDialog
* [MessageDialogDoc](https://github.com/SlaVcE14/SJ-Dialog/blob/master/SJDialog/MessageDialogDoc.md): Documentation for MessageDialog
