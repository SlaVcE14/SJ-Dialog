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
import android.widget.TextView;

import com.sjapps.library.R;

/**
 * by SlaVcE
 * Setting up custom dialog.
 */
public class SetupDialog {
    public Dialog dialog;
    public Button button1, button2;
    private int maxWidth = 600;

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
    public SetupDialog DialogBuilder(Context context,String Title,String Text, String Btn1Txt, String Btn2Txt){

        dialog = new Dialog(context);
        if (Text == null){
            dialog.setContentView(R.layout.popup_panel);
        }else{
            dialog.setContentView(R.layout.popup_panel_long);
            TextView msg = (TextView)dialog.findViewById(R.id.textView);
            msg.setText(Text);
        }
        SetDialogSize(context);
        TextView TitleTv = (TextView)dialog.findViewById(R.id.TitleText);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        button1 = (Button)dialog.findViewById(R.id.Btn1);
        button2 = (Button)dialog.findViewById(R.id.Btn2);
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
            dialog.show();
            return this;
        }
        TitleTv.setText(Title);
        dialog.show();
        return this;
    }

    public void dismiss(){
        dialog.dismiss();
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


    public void onButtonClick(DialogButtonEvents dialogButtonEvents){

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
    }
    public void onButtonClick(DialogButtonEvent dialogButtonEvent){

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogButtonEvent.onButtonClick();
            }
        });
    }


}

