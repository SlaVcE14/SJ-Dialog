# MessageDialog Documentation
Message dialog example
```java
MessageDialog messageDialog = new MessageDialog();
messageDialog.Builder(context)
	.setTitle("Title")
       	.setMessage("Message")
       	.show();
```
## Builder
Apply the default theme to a dialog
```java
messageDialog.DialogBuilder(context)
```
Apply the app theme to a dialog (only works with material3 theme)
```java
messageDialog.DialogBuilder(context,true)
```
Apply the custom theme to a dialog (only works with material3 theme)
```java
messageDialog.DialogBuilder(context,theme)
```
## Old Dialog theme
By default dialog colors will be set to material3 dynamic colors. With this method you can set the dialog color for the background and buttons to the older non-dynamic colors
```java
messageDialog.setOldTheme();
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

//Usin the old dialog theme
messageDialog.setOldTheme();

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

//Set maximum dialog width. Default is 600dp
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
