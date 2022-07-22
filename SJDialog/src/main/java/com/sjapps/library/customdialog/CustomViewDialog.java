package com.sjapps.library.customdialog;

import android.content.Context;
import android.opengl.Visibility;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;

import com.sjapps.library.R;

@SuppressWarnings("unused")
public class CustomViewDialog extends SJDialog{

    private LinearLayout rootView;

    public CustomViewDialog(){

    }

    public CustomViewDialog Builder(Context context){
        return Builder(context,false);
    }

    public CustomViewDialog Builder(Context context,@StyleRes int theme){
        super.Builder(context,R.layout.custom_view_dialog,theme, false);
        rootView = dialog.findViewById(R.id.customViewRoot);
        onLeftButtonClick(dialog::dismiss);
        return this;
    }

    public CustomViewDialog Builder(Context context,boolean useAppTheme){
        super.Builder(context,R.layout.custom_view_dialog,useAppTheme);
        rootView = dialog.findViewById(R.id.customViewRoot);
        onLeftButtonClick(dialog::dismiss);
        return this;
    }

    @Override
    public CustomViewDialog setOldTheme(){
        super.setOldTheme();
        return this;
    }

    @Override
    public CustomViewDialog setTitle(String title){
        super.setTitle(title);
        return this;
    }

    @Override
    public CustomViewDialog setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    /**
     * Set a button text.
     * @param text button text
     * @return current class
     * */
    public CustomViewDialog setButtonText(String text){
        return setLeftButtonText(text);
    }

    @Override
    public CustomViewDialog setLeftButtonText(String text){
        super.setLeftButtonText(text);
        return this;
    }

    @Override
    public CustomViewDialog setRightButtonText(String text){
        super.setRightButtonText(text);
        return this;
    }

    /**
     * Set button text color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public CustomViewDialog setButtonTextColor(@ColorInt int color){
        return setLeftButtonTextColor(color);
    }

    @Override
    public CustomViewDialog setButtonsTextColor(@ColorInt int color){
        super.setButtonsTextColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setLeftButtonTextColor(@ColorInt int color){
        super.setLeftButtonTextColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setRightButtonTextColor(@ColorInt int color){
        super.setRightButtonTextColor(color);
        return this;
    }

    /**
     * Set button color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public CustomViewDialog setButtonColor(@ColorInt int color){
        return setLeftButtonColor(color);
    }

    @Override
    public CustomViewDialog setButtonsColor(@ColorInt int color){
        super.setButtonsColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setLeftButtonColor(@ColorInt int color){
        super.setLeftButtonColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setRightButtonColor(@ColorInt int color){
        super.setRightButtonColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setButtonColor(@ButtonColor String color) {
        super.setButtonColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setButtonsColor(@ButtonColor String color){
        super.setButtonsColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setLeftButtonColor(@ButtonColor String color){
        super.setLeftButtonColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setRightButtonColor(@ButtonColor String color){
        super.setRightButtonColor(color);
        return this;
    }

    @Override
    public CustomViewDialog setDialogBackgroundResource(@DrawableRes int drawable){
        super.setDialogBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for button.
     * @param drawable resource id
     * @return current class
     * */
    public CustomViewDialog setButtonBackgroundResource(@DrawableRes int drawable){
        return setLeftButtonBackgroundResource(drawable);
    }

    @Override
    public CustomViewDialog setButtonsBackgroundResource(@DrawableRes int drawable){
        super.setButtonsBackgroundResource(drawable);
        return this;
    }

    @Override
    public CustomViewDialog setLeftButtonBackgroundResource(@DrawableRes int drawable){
        super.setLeftButtonBackgroundResource(drawable);
        return this;
    }

    @Override
    public CustomViewDialog setRightButtonBackgroundResource(@DrawableRes int drawable){
        super.setRightButtonBackgroundResource(drawable);
        return this;
    }

    /**
     * Set onClick listener for a button
     *
     * @param dialogButtonEvent dialog button event
     * @return current class
     */
    @Override
    public CustomViewDialog onButtonClick(DialogButtonEvent dialogButtonEvent){
        if (twoButtons)
            super.onButtonClick(dialogButtonEvent);
        else
            super.onLeftButtonClick(dialogButtonEvent);
        return this;
    }

    @Override
    public CustomViewDialog onButtonClick(DialogButtonEvents dialogButtonEvents) {
        super.onButtonClick(dialogButtonEvents);
        return this;
    }

    @Override
    public CustomViewDialog show() {
        super.show();
        return this;
    }

    @Override
    protected int setButtonsRootLayoutID() {
        return R.id.buttons;
    }

    @Override
    public CustomViewDialog setMaxDialogWidth(int maxDialogWidth) {
        super.setMaxDialogWidth(maxDialogWidth);
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

    @Override
    public CustomViewDialog dialogWithTwoButtons() {
        super.dialogWithTwoButtons();
        setButton2Visibility(View.VISIBLE);
        return this;
    }

    @Override
    public void setButton2Visibility(int visibility) {
        super.setButton2Visibility(visibility);
    }

    public CustomViewDialog addCustomView(View view){
        rootView.addView(view);
        setDialogSize();
        return this;
    }
}
