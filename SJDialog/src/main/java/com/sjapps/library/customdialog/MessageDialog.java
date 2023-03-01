package com.sjapps.library.customdialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;

import com.sjapps.library.R;

@SuppressWarnings("unused")
public class MessageDialog extends SJDialog{

    private boolean isErrorDialog;

    public MessageDialog(){
        onlyOneButton = true;
    }

    public MessageDialog Builder(Context context){
        super.Builder(context,R.layout.message_dialog);
        onButtonClick(() -> dialog.dismiss());
        return this;
    }
    public MessageDialog Builder(Context context,@StyleRes int theme){
        super.Builder(context,R.layout.message_dialog,theme, false);
        onButtonClick(() -> dialog.dismiss());
        return this;
    }
    public MessageDialog Builder(Context context,boolean useAppTheme){
        super.Builder(context,R.layout.message_dialog,useAppTheme);
        onButtonClick(() -> dialog.dismiss());
        return this;
    }
    public MessageDialog ErrorDialogBuilder(Context context){
        return Builder(context).errorDialogAttributes();
    }
    public MessageDialog ErrorDialogBuilder(Context context,@StyleRes int theme){
        return Builder(context,theme).errorDialogAttributes();
    }
    public MessageDialog ErrorDialogBuilder(Context context,boolean useAppTheme){
        return Builder(context,useAppTheme).errorDialogAttributes();
    }

    private MessageDialog errorDialogAttributes(){
        isErrorDialog = true;
        return setDialogBackgroundResource(R.drawable.dialog_background_material3_red)
                .setTextColor(context.getResources().getColor(R.color.SJDialog_ErrorTextColor, context.getTheme()))
                .setButtonColor(MATERIAL3_RED_BUTTON)
                .setTitle("Error");
    }

    @Override
    public MessageDialog setOldTheme(){
        super.setOldTheme();
        if (isErrorDialog) {
            setDialogBackgroundResource(R.drawable.dialog_background_red);
            setTextColor(defaultOldColorWhite);
            setButtonColor(defaultOldColorWhite);
            setButtonTextColor(defaultOldColorBlack);
        }
        return this;
    }

   @Override
    public MessageDialog setTitle(String title){
        super.setTitle(title);
        return this;
    }

    @Override
    public MessageDialog setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public MessageDialog setTextColor(int color) {
        super.setTextColor(color);
        return this;
    }

    @Override
    public MessageDialog setTitleTextColor(int color) {
        super.setTitleTextColor(color);
        return this;
    }

    @Override
    public MessageDialog setMessageTextColor(int color) {
        super.setMessageTextColor(color);
        return this;
    }

    /**
     * Set a button text.
     * @param text button text
     * @return current class
     * */
    public MessageDialog setButtonText(String text){
        super.setLeftButtonText(text);
        return this;
    }

    /**
     * Set button text color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public MessageDialog setButtonTextColor(@ColorInt int color){
        super.setLeftButtonTextColor(color);
        return this;
    }

    /**
     * Set button color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public MessageDialog setButtonColor(@ColorInt int color){
        super.setLeftButtonColor(color);
        return this;
    }

    @Override
    public MessageDialog setButtonColor(@ButtonColor String color){
        super.setButtonColor(color);
        return this;
    }

    @Override
    public MessageDialog setDialogBackgroundResource(@DrawableRes int drawable){
        super.setDialogBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for button.
     * @param drawable resource id
     * @return current class
     * */
    public MessageDialog setButtonBackgroundResource(@DrawableRes int drawable){
        super.setLeftButtonBackgroundResource(drawable);
        return this;
    }

    /**
     * Set onClick listener for a button
     * @param dialogButtonEvent dialog button events
     * @return current class
     * */
    @Override
    public MessageDialog onButtonClick(DialogButtonEvent dialogButtonEvent){
        super.onLeftButtonClick(dialogButtonEvent);
        return this;
    }

    @Override
    protected void setButtons() {
        setButton1(R.id.btn);
    }

    public Button getButton() {
        return super.getLeftButton();
    }

    @Override
    public MessageDialog setMaxDialogWidth(int maxDialogWidth){
        super.setMaxDialogWidth(maxDialogWidth);
        return this;
    }

    @Override
    public MessageDialog setDialogAnimations(int styleRes) {
        super.setDialogAnimations(styleRes);
        return this;
    }

    @Override
    public MessageDialog show(){
        super.show();
        return this;
    }

    @Override
    protected int setButtonsRootLayoutID() {
        return R.id.buttonRoot;
    }

    @Override
    public MessageDialog setOnTouchListener(View.OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
        return this;
    }

    @Override
    public MessageDialog swipeToDismiss(boolean isSwipeToDismiss) {
        super.swipeToDismiss(isSwipeToDismiss);
        return this;
    }
}
