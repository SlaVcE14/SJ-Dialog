![SJ-Dialog](https://repository-images.githubusercontent.com/473367072/2dbc4ac2-711c-48e5-92f4-c55e083e81ac)
# SJ-Dialog
Android library for creating custom dialogs. You can change color and background resouce of a buttons and dialog background and color of a text. Supports Material3 theme colors. It has several types of dialogs:
* [BasicDialog](#BasicDialog)
* [MessageDialog](#MessageDialog)
* [ListDialog](#ListDialog)
* [CustomViewDialog](#CustomViewDialog)

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
	implementation 'com.github.slavce14:SJ-Dialog:1.6.1'
}
```
[![](https://jitpack.io/v/slavce14/SJ-Dialog.svg)](https://jitpack.io/#slavce14/SJ-Dialog)
![GitHub repo size](https://img.shields.io/github/repo-size/slavce14/sj-dialog?color=0064ff)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/slavce14/sj-dialog?color=0064ff)](../../releases/latest)
## Dialog types
### BasicDialog
![](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/BasicDialog%20day-night.gif)
### MessageDialog
![](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/MessageDialog%20day-night.gif)
### ListDialog
![](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/ListDialog%20day-night.gif)
### CustomViewDialog
![](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/CustomViewDialog%20day-night.gif)
## Documentation
* [BasicDialogDoc](/SJDialog/BasicDialogDoc.md): Documentation for BasicDialog
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
