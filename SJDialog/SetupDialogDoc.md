# SetupDialog Documentation
Create dialog example
```java
SetupDialog dialog = new SetupDialog();
dialog.DialogBuilder(context)
	.setTitle("Title")
  	.setLeftButtonText("text")
  	.setRightButtonText("text")
  	.onButtonClick(() -> {
	// Do something
	})
       	.show();
```
## Add onClick Listener
onClickListener for right button
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

## All SetupDialog Functions
```java
//Create dialog
dialog.DialogBuilder(context);

//Set title
dialog.setTitle("Title");
//Set message
dialog.setMessage("Message");
//Set left button text
dialog.setLeftButtonText("Text");
//Set right button text
dialog.setRightButtonText("Text");

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

//Shew dialog
dialog.show();
//Dismiss dialog
dialog.dismiss();
```
