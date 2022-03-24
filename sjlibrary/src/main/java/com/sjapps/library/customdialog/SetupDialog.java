package com.sjapps.library.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sjapps.setupdialog.R;

/**
 * by SlaVcE
 * Setting up custom dialog.
 */
public class SetupDialog {
    public Dialog dialog;
    public Button button1, button2;

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
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

