package com.sjapps.library.customdialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

import com.sjapps.library.R;

/**
 * by SlaVcE
 * Setting up custom dialog.
 */
@SuppressWarnings("unused")
public class SetupDialog extends SJDialog{
    @Deprecated
    public static final String LONG_TYPE = "long";
    @Deprecated
    public static final String SHORT_TYPE = "short";

    private boolean isDeleteDialog;

    public SetupDialog (){
        twoButtons = true;
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
        return DialogBuilder(context,Title,null,null,null).deleteAttributes();
    }
    public SetupDialog Delete(Context context){
        return DialogBuilder(context).deleteAttributes();
    }
    public SetupDialog Delete(Context context, int theme){
        return DialogBuilder(context,theme).deleteAttributes();
    }
    public SetupDialog Delete(Context context, boolean useAppTheme){
        return DialogBuilder(context,useAppTheme).deleteAttributes();
    }
    private SetupDialog deleteAttributes(){
        isDeleteDialog = true;
        return setTitle("Delete?")
                .setRightButtonColor(MATERIAL3_RED_BUTTON)
                .setRightButtonText("Delete");
    }
    public SetupDialog DialogBuilder(Context context){
        super.Builder(context,R.layout.dialog);
        return this;
    }
    public SetupDialog DialogBuilder(Context context,boolean useAppTheme){
        super.Builder(context,R.layout.dialog,useAppTheme);
        return this;
    }
    public SetupDialog DialogBuilder(Context context,int theme){
        super.Builder(context,R.layout.dialog,theme,false);
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
        super.Builder(context,R.layout.dialog);
        TextView TitleTv = dialog.findViewById(R.id.titleText);
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

    @Override
    public SetupDialog setOldTheme(){
        super.setOldTheme();
        if (isDeleteDialog)
            super.setRightButtonColor(RED_BUTTON);
        return this;
    }

    @Override
    public SetupDialog setTitle(String title){
        super.setTitle(title);
        return this;
    }

    @Override
    public SetupDialog setMessage(String message){
        super.setMessage(message);
        return this;
    }

    @Override
    public SetupDialog setTextColor(int color) {
        super.setTextColor(color);
        return this;
    }

    @Override
    public SetupDialog setTitleTextColor(int color) {
        super.setTitleTextColor(color);
        return this;
    }

    @Override
    public SetupDialog setMessageTextColor(int color) {
        super.setMessageTextColor(color);
        return this;
    }

    @Override
    public SetupDialog setLeftButtonText(String text){
        super.setLeftButtonText(text);
        return this;
    }

    @Override
    public SetupDialog setRightButtonText(String text){
        super.setRightButtonText(text);
        return this;
    }

    @Override
    public SetupDialog setButtonsTextColor(@ColorInt int color){
        super.setButtonsTextColor(color);
        return this;
    }

    @Override
    public SetupDialog setLeftButtonTextColor(@ColorInt int color){
        super.setLeftButtonTextColor(color);
        return this;
    }

    @Override
    public SetupDialog setRightButtonTextColor(@ColorInt int color){
        super.setRightButtonTextColor(color);
        return this;
    }

    @Override
    public SetupDialog setButtonsColor(@ColorInt int color){
        super.setButtonsColor(color);
        return this;
    }

    @Override
    public SetupDialog setLeftButtonColor(@ColorInt int color){
        super.setLeftButtonColor(color);
        return this;
    }

    @Override
    public SetupDialog setRightButtonColor(@ColorInt int color){
        super.setRightButtonColor(color);
        return this;
    }

    @Override
    public SetupDialog setButtonsColor(@ButtonColor String color){
        super.setButtonsColor(color);
        return this;
    }

    @Override
    public SetupDialog setLeftButtonColor(@ButtonColor String color){
        super.setLeftButtonColor(color);
        return this;
    }

    @Override
    public SetupDialog setRightButtonColor(@ButtonColor String color){
        super.setRightButtonColor(color);
        return this;
    }

    @Override
    public SetupDialog setDialogBackgroundResource(@DrawableRes int drawable){
        super.setDialogBackgroundResource(drawable);
        return this;
    }

    @Override
    public SetupDialog setButtonsBackgroundResource(@DrawableRes int drawable){
        super.setButtonsBackgroundResource(drawable);
        return this;
    }

    @Override
    public SetupDialog setLeftButtonBackgroundResource(@DrawableRes int drawable){
        super.setLeftButtonBackgroundResource(drawable);
        return this;
    }

    @Override
    public SetupDialog setRightButtonBackgroundResource(@DrawableRes int drawable){
        super.setRightButtonBackgroundResource(drawable);
        return this;
    }

    @Override
    public SetupDialog onButtonClick(DialogButtonEvent dialogButtonEvent){
        super.onButtonClick(dialogButtonEvent);
        return this;
    }

    @Override
    public SetupDialog onButtonClick(DialogButtonEvents dialogButtonEvents) {
        super.onButtonClick(dialogButtonEvents);
        return this;
    }

    @Override
    public SetupDialog show() {
        super.show();
        return this;
    }

    @Override
    protected int setButtonsRootLayoutID() {
        return R.id.buttons;
    }

    @Override
    public SetupDialog setMaxDialogWidth(int maxDialogWidth) {
        super.setMaxDialogWidth(maxDialogWidth);
        return this;

    }

    @Override
    public SetupDialog setOnTouchListener(View.OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
        return this;
    }

    @Override
    public SetupDialog swipeToDismiss(boolean isSwipeToDismiss) {
        super.swipeToDismiss(isSwipeToDismiss);
        return this;
    }

    @Override
    public SetupDialog setDialogAnimations(int styleRes) {
        super.setDialogAnimations(styleRes);
        return this;
    }

    @Override
    protected void setButtons() {
        setButton1(R.id.btn1);
        setButton2(R.id.btn2);
    }

    @Override
    public Button getLeftButton() {
        return super.getLeftButton();
    }

    @Override
    public Button getRightButton() {
        return super.getRightButton();
    }

}

