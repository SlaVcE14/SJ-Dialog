# MessageDialog Documentation
Message dialog example
```java
MessageDialog messageDialog = new MessageDialog();
messageDialog.Builder(context)
	.setTitle("Title")
       	.setMessage("Message")
       	.show();
```
![MessageDialog example](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/MessageDialog%20example.png)
## Builder
Apply the default theme to a dialog
```java
messageDialog.Builder(context)
```
![MessageDialog day-night](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/MessageDialog%20day-night.png)
Apply the app theme to a dialog **(only works with material3 theme)**
```java
messageDialog.Builder(context,true)
```
![MessageDialog day-night theme](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/MessageDialog%20day-night.gif)

Apply the custom theme to a dialog **(only works with material3 theme)**
```java
messageDialog.Builder(context,theme)
```
## Old Dialog theme
By default dialog colors will be set to material3 dynamic colors. With this method you can set the dialog color for the background and buttons to the older non-dynamic colors
```java
messageDialog.setOldTheme();
```
![MessageDialog oldTheme](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/MessageDialog%20oldTheme.png)
## Add onClick Listener
```java
messageDialog.onButtonClick(new DialogButtonEvent() {
	@Override
      	public void onButtonClick() {
      		// Do something
      	}
});

//or

messageDialog.onButtonClick(() -> {
	// Do something
});
```
## Apply Insets
supported values: `INSETS_LEFT`, `INSETS_RIGHT`, `INSETS_BOTTOM`, `INSETS_HORIZONTAL`, `INSETS_ALL` or `INSETS_NONE`.
```java
messageDialog.applyInsets(INSETS_HORIZONTAL);
```
You can combine multiple values with bitwise-OR ( | ) operator
```java
messageDialog.applyInsets(INSETS_LEFT | INSETS_RIGHT);
```
## All MessageDialog Methods
```java
//Create dialog
messageDialog.Builder(context);

//Usin the old dialog theme
messageDialog.setOldTheme();

//Set title
messageDialog.setTitle("Title");
//Set message
messageDialog.setMessage("Message");

//Set title text alignment
messageDialog.setTitleAlignment(TextAlignment);
//Set message text alignment
messageDialog.setMessageAlignment(TextAlignment);

//Set button text
messageDialog.setButtonText("Text");

//Set text color
messageDialog.setTextColor(color);
//Set title text color
messageDialog.setTitleTextColor(color);
//Set message text color
messageDialog.setMessageTextColor(color);

//Set button color
messageDialog.setButtonColor(color);

//Set button text color
messageDialog.setButtonTextColor(color);

//Set button background resource
messageDialog.setButtonBackgroundResource(drawable);

//Set dialog color
messageDialog.setDialogBackgroundColor(color);
//Set dialog background resource
messageDialog.setDialogBackgroundResource(drawable);

//Set maximum dialog width. Default is 600dp
messageDialog.setMaxDialogWidth(width);

//Get maximum dialog width
int dialogWidth = messageDialog.getMaxDialogWidth();

//Get button
Button Button = messageDialog.getButton();
        
//Set dialog animations
messageDialog.setDialogAnimations(styleRes);

//Enable or disable swipe down to dismiss dialog. 
//By default is set to true
dialog.swipeToDismiss(boolean);

//Set dialog onTouchListener.
//This method will overide swipe down to dismiss action
dialog.setOnTouchListener(onTouchListener);

//Apply insets (INSETS_LEFT, INSETS_RIGHT, INSETS_BOTTOM, INSETS_HORIZONTAL, INSETS_ALL or INSETS_NONE)
messageDialog.applyInsets(insets);

//Shew dialog
messageDialog.show();
//Dismiss dialog
messageDialog.dismiss();
```
