package com.sjapps.library.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjapps.library.R;

public class MessageDialog {

    public static final String RED_BUTTON = "RedBtn";

    public Dialog dialog;
    private Button button;
    Context context;
    private int maxWidth = 600;

    public MessageDialog(){

    }

    public MessageDialog Builder(Context context){
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.message_dialog);
        button = dialog.findViewById(R.id.btn);
        SetDialogSize(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        button.setOnClickListener(v -> dialog.dismiss());
        return this;
    }
    /**
     * Set a dialog title.
     * @param title title of a dialog
     * @return current class
     * */
    public MessageDialog setTitle(String title){
        
        TextView TitleTv = dialog.findViewById(R.id.titleText);
        TitleTv.setText(title);
        return this;
    }
    /**
     * Set a dialog message.
     * @param message message of a dialog
     * @return current class
     * */
    public MessageDialog setMessage(String message) {
        TextView msg = dialog.findViewById(R.id.messageTxt);
        msg.setVisibility(View.VISIBLE);
        msg.setText(message);
        return this;
    }
    /**
     * Set a button text.
     * @param text button text
     * @return current class
     * */
    public MessageDialog setButtonText(String text){
        button.setText(text);
        return this;
    }
    /**
     * show dialog
     * */
    public MessageDialog show(){
        dialog.show();
        return this;
    }
    /**
     * dismiss dialog
     * */
    public void dismiss(){
        dialog.dismiss();
    }
    /**
     * Set onClick listener for a button
     * @param dialogButtonEvent dialog button events
     * @return current class
     * */
    public MessageDialog onButtonClick(DialogButtonEvent dialogButtonEvent){
        button.setOnClickListener(v -> dialogButtonEvent.onButtonClick());
        return this;
    }
    /**
     * Set background resource for dialog.
     * @param drawable resource id
     * @return current class
     * */
    public MessageDialog setDialogBackgroundResource(int drawable){
        LinearLayout background = dialog.findViewById(R.id.dialogBackground);
        background.setBackgroundResource(drawable);
        return this;
    }
    /**
     * Set background resource for button.
     * @param drawable resource id
     * @return current class
     * */
    public MessageDialog setButtonBackgroundResource(int drawable){
        button.setBackgroundResource(drawable);
        return this;
    }
    /**
     * Set button color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public MessageDialog setButtonColor(int color){
        button.getBackground().setTint(color);
        return this;
    }
    /**
     * Set button text color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public MessageDialog setButtonTextColor(int color){
        button.setTextColor(color);
        return this;
    }

    public MessageDialog setButtonColor(String color){
        switch (color){
            case RED_BUTTON:
                setButtonBackgroundResource(R.drawable.ripple_button_red);
                setButtonTextColor(context.getResources().getColor(R.color.md_theme_onError,context.getTheme()));
                break;
            default:
                throw new IllegalArgumentException(color + " is not a valid argument");
        }
        return this;
    }

    public Button getButton() {
        return button;
    }

    private void SetDialogSize(Context context){
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration.screenWidthDp > maxWidth)
            dialog.getWindow().setLayout(dpToPixels(context,maxWidth), ViewGroup.LayoutParams.WRAP_CONTENT);
        else dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    private int dpToPixels(Context context, float dp) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
