# MessageDialog Documentation
Message dialog example
```java
MessageDialog messageDialog = new MessageDialog();
messageDialog.Builder(context)
	.setTitle("Title")
       	.setMessage("Message")
       	.show();
```
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
## All MessageDialog Functions
```java
//Create dialog
messageDialog.Builder(context);

//Set title
messageDialog.setTitle("Title");
//Set message
messageDialog.setMessage("Message");
//Set button text
messageDialog.setButtonText("Text");

//Set button color
messageDialog.setButtonColor(color);

//Set button text color
messageDialog.setButtonTextColor(color);

//Set button background resource
messageDialog.setButtonBackgroundResource(drawable);
        
//Set dialog background resource
messageDialog.setDialogBackgroundResource(drawable);

//Set maximum dialog width. Default is 600
messageDialog.setMaxDialogWidth(width);

//Get maximum dialog width
int dialogWidth = messageDialog.getMaxDialogWidth();

//Get button
Button Button = messageDialog.getButton();
        
//Shew dialog
messageDialog.show();
//Dismiss dialog
messageDialog.dismiss();
```
