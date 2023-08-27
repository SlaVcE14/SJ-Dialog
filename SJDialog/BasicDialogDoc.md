# BasicDialog Documentation
Create dialog example
```java
BasicDialog dialog = new BasicDialog();
dialog.Builder(context)
	.setTitle("Title")
  	.setLeftButtonText("button1")
  	.setRightButtonText("button2")
  	.onButtonClick(() -> {
		// Do something
	})
       	.show();
```
![BasicDialog example](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/BasicDialog%20example%201.png)
## Builder
Apply the default theme to a dialog
```java
dialog.Builder(context)
```
![BasicDialog day-night](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/BasicDialog%20day-night.png)

Apply the app theme to a dialog **(only works with material3 theme)**
```java
dialog.Builder(context,true)
```
![BasicDialog day-night theme](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/BasicDialog%20day-night.gif)

Apply the custom theme to a dialog **(only works with material3 theme)**
```java
dialog.Builder(context,theme)
```
## Old Dialog theme
By default dialog colors will be set to material3 dynamic colors. With this method you can set the dialog color for the background and buttons to the older non-dynamic colors
```java
dialog.setOldTheme();
```
![BasicDialog oldTheme](https://raw.githubusercontent.com/SlaVcE14/SJ-Dialog/master/SJDialog/images/BasicDialog%20oldTheme.png)
## Add onClick Listener
onClickListener for right button. The left button is for dismissing dialog
```java
dialog.onButtonClick(new DialogButtonEvent() {
	@Override
      	public void onButtonClick() {
      		// Do something
      	}
});

//or

dialog.onButtonClick(() -> {
	// Do something
});
```
onClickListener for left and right button
```java
dialog.onButtonClick(new DialogButtonEvents() {
	@Override
       	public void onLeftButtonClick() {
       		// Do something
       	}

     	@Override
      	public void onRightButtonClick() {
       		// Do something
      	}
});
```
## Apply Insets
supported values: `INSETS_LEFT`, `INSETS_RIGHT`, `INSETS_BOTTOM`, `INSETS_HORIZONTAL`, `INSETS_ALL` or `INSETS_NONE`.
```java
dialog.applyInsets(INSETS_HORIZONTAL);
```
You can combine multiple values with bitwise-OR ( | ) operator
```java
dialog.applyInsets(INSETS_LEFT | INSETS_RIGHT);
```
## All BasicDialog Methods
```java
//Create dialog
dialog.Builder(context);

//Usin the old dialog theme
dialog.setOldTheme();

//Set title
dialog.setTitle("Title");
//Set message
dialog.setMessage("Message");
//Set title text alignment
dialog.setTitleAlignment(TextAlignment);
//Set message text alignment
dialog.setMessageAlignment(TextAlignment);
//Set left button text
dialog.setLeftButtonText("Text");
//Set right button text
dialog.setRightButtonText("Text");

//Set text color
dialog.setTextColor(color);
//Set title text color
dialog.setTitleTextColor(color);
//Set message text color
dialog.setMessageTextColor(color);

//Set buttons color
dialog.setButtonsColor(color);
//Set left button color
dialog.setLeftButtonColor(color);
//Set right button color
dialog.setRightButtonColor(color);

//Set buttons text color
dialog.setButtonsTextColor(color);
//Set text color for left button
dialog.setLeftButtonTextColor(color);
//Set text color for right button
dialog.setRightButtonTextColor(color);

//Set buttons background resource
dialog.setButtonsBackgroundResource(drawable);
//Set left button background resource
dialog.setLeftButtonBackgroundResource(drawable);
//Set right button background resource
dialog.setRightButtonBackgroundResource(drawable);

//Set dialog color
dialog.setDialogBackgroundColor(color);
//Set dialog background resource
dialog.setDialogBackgroundResource(drawable);

//Set maximum dialog width. Default is 600dp
dialog.setMaxDialogWidth(width);
        
//Get maximum dialog width
int dialogWidth = dialog.getMaxDialogWidth();
        
//Get left button
Button leftButton = dialog.getLeftButton();
//Get right button
Button rightButton = dialog.getRightButton();

//Set dialog animations
dialog.setDialogAnimations(styleRes);

//Enable or disable swipe down to dismiss dialog. 
//By default is set to true
dialog.swipeToDismiss(boolean);

//Set dialog onTouchListener.
//This method will overide swipe down to dismiss action
dialog.setOnTouchListener(onTouchListener);

//Apply insets (INSETS_LEFT, INSETS_RIGHT, INSETS_BOTTOM, INSETS_HORIZONTAL, INSETS_ALL or INSETS_NONE)
dialog.applyInsets(insets);

//Shew dialog
dialog.show();
//Dismiss dialog
dialog.dismiss();
```
