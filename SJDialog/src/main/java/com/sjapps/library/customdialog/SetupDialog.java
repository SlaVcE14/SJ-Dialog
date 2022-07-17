package com.sjapps.library.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjapps.library.R;

/**
 * by SlaVcE
 * Setting up custom dialog.
 */
public class SetupDialog {
    @Deprecated
    public static final String LONG_TYPE = "long";
    @Deprecated
    public static final String SHORT_TYPE = "short";
    public static final String RED_BUTTON = "RedBtn";
    public static final String OLD_BUTTON_COLOR = "OldBtnColor";

    public Dialog dialog;
    private Button button1, button2;
    private int maxDialogWidth = 600;
    Context context;

    public SetupDialog (){
    }
    public SetupDialog Short(Context context, String Title){
        return Short(context,Title,null);
    }
    public SetupDialog Short(Context context, String Title, String Btn1Txt, String Btn2Txt){
        return DialogBuilder(context,Title,null,Btn1Txt,Btn2Txt);
    }
    public SetupDialog Short(Context context, String Title,  String Btn2Txt){
        return Short(context,Title,null,Btn2Txt);
    }
    public SetupDialog Long(Context context,String Title,String Message){
        return Long(context,Title,Message,null);
    }
    public SetupDialog Long(Context context,String Title,String Text, String Btn1Txt, String Btn2Txt){
        return DialogBuilder(context,Title,Text,Btn1Txt,Btn2Txt);
    }
    public SetupDialog Long(Context context,String Title,String Text, String Btn2Txt){
        return Long(context,Title,Text,null,Btn2Txt);
    }
    public SetupDialog Delete(Context context,String Title){
        return DialogBuilder(context,Title,null,null,null).setRightButtonColor(RED_BUTTON);
    }
    public SetupDialog Delete(Context context){
        return DialogBuilder(context,null,null,null,null).setRightButtonColor(RED_BUTTON);
    }
    public SetupDialog DialogBuilder(Context context){
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.popup_panel);
        setDialogSize();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        button1 = dialog.findViewById(R.id.Btn1);
        button2 = dialog.findViewById(R.id.Btn2);
        return this;
    }

    /**
     * @deprecated Dialog type is automatic. Use {@link SetupDialog#DialogBuilder(Context)}
     */
    @Deprecated
    public SetupDialog DialogBuilder(Context context, String dialogType){
        return DialogBuilder(context);
    }


    public SetupDialog DialogBuilder(Context context,String Title,String Text, String Btn1Txt, String Btn2Txt){
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.popup_panel);
        setDialogSize();
        TextView TitleTv = dialog.findViewById(R.id.TitleText);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        button1 = dialog.findViewById(R.id.Btn1);
        button2 = dialog.findViewById(R.id.Btn2);
        if (Btn1Txt == null) {
            button1.setOnClickListener(v -> dialog.dismiss());
        }else button1.setText(Btn1Txt);
        if (Btn2Txt != null) {
            button2.setText(Btn2Txt);
        }
        if (Text != null){
            setMessage(Text);
        }
        if (Title == null){
            return this;
        }
        TitleTv.setText(Title);
        return this;
    }
    /**
     * Set a dialog type.
     * @param dialogType type of a dialog. Supported types: {@link #SHORT_TYPE} and {@link #LONG_TYPE}
     * @return current class
     * @deprecated Dialog type is automatic. Remove this.
     * */
    @Deprecated
    public SetupDialog setDialogType(String dialogType){
        //Noting here!!
        return this;
    }
    public SetupDialog setOldTheme(){
        setButtonsColor(OLD_BUTTON_COLOR);
        setDialogBackgroundResource(R.drawable.dialog_background_old);
        return this;
    }

    /**
     * Set a dialog title.
     * @param title title of a dialog
     * @return current class
     * */
    public SetupDialog setTitle(String title){
        TextView TitleTv = dialog.findViewById(R.id.TitleText);
        TitleTv.setText(title);
        return this;
    }
    /**
     * Set a dialog message.
     * @param message message of a dialog
     * @return current class
     * */
    public SetupDialog setMessage(String message){
        TextView msg = dialog.findViewById(R.id.messageTxt);
        msg.setText(message);
        msg.setVisibility(View.VISIBLE);
        return this;
    }
    /**
     * Set a text to left button.
     * @param text button text
     * @return current class
     * */
    public SetupDialog setLeftButtonText(String text){
        button1.setText(text);
        return this;
    }
    /**
     * Set a text to right button.
     * @param text button text
     * @return current class
     * */
    public SetupDialog setRightButtonText(String text){
        button2.setText(text);
        return this;
    }
    /**
     * Set background resource for all buttons.
     * @param drawable resource id
     * @return current class
     * */
    public SetupDialog setButtonsBackgroundResource(int drawable){
        button1.setBackgroundResource(drawable);
        button2.setBackgroundResource(drawable);
        return this;
    }
    /**
     * Set background resource for left button.
     * @param drawable resource id
     * @return current class
     * */
    public SetupDialog setLeftButtonBackgroundResource(int drawable){
        button1.setBackgroundResource(drawable);
        return this;
    }
    /**
     * Set background resource for right button.
     * @param drawable resource id
     * @return current class
     * */
    public SetupDialog setRightButtonBackgroundResource(int drawable){
        button2.setBackgroundResource(drawable);
        return this;
    }
    /**
     * Set background resource for dialog.
     * @param drawable resource id
     * @return current class
     * */
    public SetupDialog setDialogBackgroundResource(int drawable){
        LinearLayout background = dialog.findViewById(R.id.dialogBackground);
        background.setBackgroundResource(drawable);
        return this;
    }
    /**
     * Set background color for all buttons.
     * @param color Color to use for tinting buttons
     * @return current class
     * */
    public SetupDialog setButtonsColor(int color){
        button1.getBackground().setTint(color);
        button2.getBackground().setTint(color);
        return this;
    }

    /**
     * Set background color for left button.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public SetupDialog setLeftButtonColor(int color){
        button1.getBackground().setTint(color);
        return this;
    }
    /**
     * Set background color for right button.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public SetupDialog setRightButtonColor(int color){
        button2.getBackground().setTint(color);
        return this;
    }
    /**
     * Set text color for all buttons.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public SetupDialog setButtonsTextColor(int color){
        button1.setTextColor(color);
        button2.setTextColor(color);
        return this;
    }
    /**
     * Set text color for left button.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public SetupDialog setLeftButtonTextColor(int color){
        button1.setTextColor(color);
//        button1.setTextAppearance(R.style.ButtonColor);
        return this;
    }
    /**
     * Set text color for right button.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public SetupDialog setRightButtonTextColor(int color){
        button2.setTextColor(color);
        return this;
    }


    public SetupDialog setLeftButtonColor(String color){
        switch (color){
            case RED_BUTTON:
                setLeftButtonBackgroundResource(R.drawable.ripple_button_red);
                setLeftButtonTextColor(context.getResources().getColor(R.color.md_theme_onError,context.getTheme()));
                break;
            case OLD_BUTTON_COLOR:
                setLeftButtonBackgroundResource(R.drawable.ripple_button_old);
                setLeftButtonTextColor(Color.WHITE);
                break;
            default:
                throw new IllegalArgumentException(color + " is not a valid argument");
        }
        return this;
    }

    public SetupDialog setRightButtonColor(String color){
        switch (color){
            case RED_BUTTON:
                setRightButtonBackgroundResource(R.drawable.ripple_button_red);
                setRightButtonTextColor(context.getResources().getColor(R.color.md_theme_onError,context.getTheme()));
                break;
            case OLD_BUTTON_COLOR:
                setRightButtonBackgroundResource(R.drawable.ripple_button_old);
                setRightButtonTextColor(Color.WHITE);
                break;
            default:
                throw new IllegalArgumentException(color + " is not a valid argument");
        }
        return this;
    }



    public SetupDialog setButtonsColor(String color){
        setLeftButtonColor(color);
        setRightButtonColor(color);
        return this;
    }


    /**
     * Set onClick listener for both buttons
     * @param dialogButtonEvents dialog button events
     *
     * @return current class
     * */
    public SetupDialog onButtonClick(DialogButtonEvents dialogButtonEvents){

        button1.setOnClickListener(v -> dialogButtonEvents.onLeftButtonClick());
        button2.setOnClickListener(v -> dialogButtonEvents.onRightButtonClick());
        return this;
    }
    /**
     * Set onClick listener for right button
     * @param dialogButtonEvent dialog button events
     * @return current class
     * */
    public SetupDialog onButtonClick(DialogButtonEvent dialogButtonEvent){
        button1.setOnClickListener(v -> dialog.dismiss());
        button2.setOnClickListener(v -> dialogButtonEvent.onButtonClick());
        return this;
    }
    /**
     * show dialog
     * */
    public SetupDialog show(){
        dialog.show();
        return this;
    }
    /**
     * dismiss dialog
     * */
    public void dismiss(){
        dialog.dismiss();
    }

    public Button getLeftButton() {
        return button1;
    }

    public Button getRightButton() {
        return button2;
    }

    /**
     * Set a maximum width for dialog
     * @param maxDialogWidth set value for {@link #maxDialogWidth}. Default is 600
     * @return current class
     */
    public SetupDialog setMaxDialogWidth(int maxDialogWidth){
        this.maxDialogWidth = maxDialogWidth;
        setDialogSize();
        return this;
    }

    public int getMaxDialogWidth(){
        return this.maxDialogWidth;
    }

    private void setDialogSize(){
        if (context == null)
            throw new NullPointerException("context is null");
        if (dialog == null) {
            throw new NullPointerException("dialog is null");
        }
        functions.SetDialogSize(context,dialog, maxDialogWidth);
    }

}

