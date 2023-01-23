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
![GitHub repo size](https://img.shields.io/github/repo-size/slavce14/sj-dialog?color=0064ff)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/slavce14/sj-dialog?color=0064ff)](../../releases/latest)
## Documentation
* [SetupDialogDoc](/SJDialog/SetupDialogDoc.md): Documentation for SetupDialog
* [MessageDialogDoc](/SJDialog/MessageDialogDoc.md): Documentation for MessageDialog
* [ListDialogDoc](/SJDialog/ListDialogDoc.md): Documentation for ListDialog
* [CustomViewDialogDoc](/SJDialog/CustomViewDialogDoc.md): Documentation for CustomViewDialog

## License:
```
MIT License

Copyright (c) 2022 SlaVcE

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
