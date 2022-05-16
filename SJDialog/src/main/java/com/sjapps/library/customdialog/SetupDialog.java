package com.sjapps.library.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.sjapps.library.R;

/**
 * by SlaVcE
 * Setting up custom dialog.
 */
public class SetupDialog {

    public static final String LONG_TYPE = "long";
    public static final String SHORT_TYPE = "short";

    public Dialog dialog;
    private Button button1, button2;
    private int maxWidth = 600;
    Context context;
    private String selectedDialogType = null;

    public SetupDialog (){
    }

    public SetupDialog Long(Context context,String Title,String Text, String Btn1Txt, String Btn2Txt){
        return DialogBuilder(context,Title,Text,Btn1Txt,Btn2Txt);
    }
    public SetupDialog Long(Context context,String Title,String Text, String Btn2Txt){
        return Long(context,Title,Text,null,Btn2Txt);
    }
    public SetupDialog Short(Context context, String Title, String Btn1Txt, String Btn2Txt){
        return DialogBuilder(context,Title,null,Btn1Txt,Btn2Txt);
    }
    public SetupDialog Short(Context context, String Title,  String Btn2Txt){
        return Short(context,Title,null,Btn2Txt);
    }
    public SetupDialog Delete(Context context,String Title){
        return DialogBuilder(context,Title,null,null,null);
    }
    public SetupDialog Delete(Context context){
        return DialogBuilder(context,null,null,null,null);
    }
    public SetupDialog DialogBuilder(Context context){
        this.context = context;
        dialog = new Dialog(context);
        return this;
    }

    public SetupDialog DialogBuilder(Context context, String dialogType){
        this.context = context;
        dialog = new Dialog(context);
        if (dialogType.equals(SHORT_TYPE)){
            dialog.setContentView(R.layout.popup_panel);
        }else if (dialogType.equals(LONG_TYPE)){
            dialog.setContentView(R.layout.popup_panel_long);
        }else throw new IllegalArgumentException("Invalid dialog type");

        this.selectedDialogType = dialogType;
        SetDialogSize(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        button1 = dialog.findViewById(R.id.Btn1);
        button2 = dialog.findViewById(R.id.Btn2);
        return this;
    }


    public SetupDialog DialogBuilder(Context context,String Title,String Text, String Btn1Txt, String Btn2Txt){

        dialog = new Dialog(context);
        if (Text == null){
            dialog.setContentView(R.layout.popup_panel);
        }else{
            dialog.setContentView(R.layout.popup_panel_long);
            TextView msg = dialog.findViewById(R.id.textView);
            msg.setText(Text);
        }
        SetDialogSize(context);
        TextView TitleTv = dialog.findViewById(R.id.TitleText);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        button1 = dialog.findViewById(R.id.Btn1);
        button2 = dialog.findViewById(R.id.Btn2);
        if (Btn1Txt == null) {
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }else button1.setText(Btn1Txt);
        if (Btn2Txt != null) {
            button2.setText(Btn2Txt);
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
     * */
    public SetupDialog setDialogType(String dialogType){
        if (dialogType.equals(SHORT_TYPE)){
            dialog.setContentView(R.layout.popup_panel);
        }else if (dialogType.equals(LONG_TYPE)){
            dialog.setContentView(R.layout.popup_panel_long);
        }else throw new IllegalArgumentException("Invalid dialog type!");

        this.selectedDialogType = dialogType;
        SetDialogSize(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        button1 = dialog.findViewById(R.id.Btn1);
        button2 = dialog.findViewById(R.id.Btn2);
        return this;
    }
    /**
     * Set a dialog title.
     * @param title title of a dialog
     * @return current class
     * */
    public SetupDialog setTitle(String title){
        checkDialogType();

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
        checkDialogType();

        if (selectedDialogType.equals(LONG_TYPE)) {
            TextView msg = dialog.findViewById(R.id.textView);
            msg.setText(message);
            return this;
        }
        throw new IllegalArgumentException("This view is not available in this dialog type!");
    }
    /**
     * Set a text to left button.
     * @param text button text
     * @return current class
     * */
    public SetupDialog setLeftButtonText(String text){
        checkDialogType();
        button1.setText(text);
        return this;
    }
    /**
     * Set a text to right button.
     * @param text button text
     * @return current class
     * */
    public SetupDialog setRightButtonText(String text){
        checkDialogType();
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
     * Set onClick listener for both buttons
     * @param dialogButtonEvents dialog button events
     *
     * @return current class
     * */
    public SetupDialog onButtonClick(DialogButtonEvents dialogButtonEvents){

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogButtonEvents.onLeftButtonClick();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogButtonEvents.onRightButtonClick();
            }
        });
        return this;
    }
    /**
     * Set onClick listener for right button
     * @param dialogButtonEvent dialog button events
     * @return current class
     * */
    public SetupDialog onButtonClick(DialogButtonEvent dialogButtonEvent){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogButtonEvent.onButtonClick();
            }
        });
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
    private void checkDialogType(){
        if (selectedDialogType == null)
            throw new NullPointerException("Dialog type is not selected! Use 'setDialogType(type)' to set dialog type.");
    }
}

