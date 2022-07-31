package com.sjapps.library.customdialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextThemeWrapper;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringDef;
import androidx.annotation.StyleRes;

import com.sjapps.library.R;

@SuppressWarnings({"unused","UnusedReturnValue"})
public abstract class SJDialog {

    public static final String RED_BUTTON = "RedBtn";
    public static final String OLD_BUTTON_COLOR = "OldBtnColor";

    private @LayoutRes int Btn1Resource = R.layout.button_template;
    private @LayoutRes int Btn2Resource = R.layout.button_template;

    private final int defaultTheme = R.style.Theme_SJDialog;
    private boolean usesDefaultTheme = true;

    public Dialog dialog;
    protected Button button1, button2;
    private int maxDialogWidth = 600;
    Context context;

    @StringDef({RED_BUTTON, OLD_BUTTON_COLOR})
    public @interface ButtonColor {
    }

    protected boolean onlyOneButton = false;
    protected boolean twoButtons = false;

    protected SJDialog Builder(Context context, @LayoutRes int layoutResID) {
        Builder(context,layoutResID,defaultTheme,false);
        return this;
    }
    protected SJDialog Builder(Context context, @LayoutRes int layoutResID,boolean useAppTheme) {
        Builder(context,layoutResID,defaultTheme, useAppTheme);
        return this;
    }
    protected SJDialog Builder(Context context, @LayoutRes int layoutResID, @StyleRes int theme, boolean useAppTheme) {
        this.context = context;
        dialog = useAppTheme ?
                new Dialog(new ContextThemeWrapper(context, context.getTheme())) :
                new Dialog(new ContextThemeWrapper(context, theme));
        setContentView(layoutResID);
        setDialogSize();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.SJDialogAnimation;
        setButtons();
        if (theme != defaultTheme || useAppTheme) {
            usesDefaultTheme = false;
            regenerateButtons();
        }
        return this;
    }

    protected void setDialogSize() {
        if (context == null)
            throw new NullPointerException("context is null");
        if (dialog == null) {
            throw new NullPointerException("dialog is null");
        }
        functions.SetDialogSize(context, dialog, maxDialogWidth);
    }


    /**
     * By default dialog colors will be set to material3 dynamic colors.
     * With this method you can set the dialog color for the background
     * and buttons to the older non-dynamic colors
     *
     * @return current class
     */
    protected SJDialog setOldTheme() {
        setButtonsColor(OLD_BUTTON_COLOR);
        setDialogBackgroundResource(R.drawable.dialog_background_old);
        return this;
    }

    /**
     * Set a dialog title.
     *
     * @param title title of a dialog
     * @return current class
     */
    protected SJDialog setTitle(String title) {
        TextView TitleTv = getTitleTv();
        TitleTv.setText(title);
        return this;
    }

    /**
     * Set a dialog message.
     *
     * @param message message of a dialog
     * @return current class
     */
    protected SJDialog setMessage(String message) {
        TextView msg = getMessageTv();
        msg.setText(message);
        msg.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * Set a text to left button.
     *
     * @param text button text
     * @return current class
     */
    protected SJDialog setLeftButtonText(String text) {
        button1.setText(text);
        return this;
    }

    /**
     * Set a text to right button.
     *
     * @param text button text
     * @return current class
     */
    protected SJDialog setRightButtonText(String text) {
        if (!twoButtons)
            throw OneButtonException();
        button2.setText(text);
        return this;
    }

    /**
     * Set text color for all buttons.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     */
    protected SJDialog setButtonsTextColor(@ColorInt int color) {
        button1.setTextColor(color);
        if (twoButtons)
            button2.setTextColor(color);
        return this;
    }

    /**
     * Set text color for left button.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     */
    protected SJDialog setLeftButtonTextColor(@ColorInt int color) {
        button1.setTextColor(color);
        return this;
    }

    /**
     * Set text color for right button.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     */
    protected SJDialog setRightButtonTextColor(@ColorInt int color) {
        if (!twoButtons)
            throw OneButtonException();
        button2.setTextColor(color);
        return this;
    }

    /**
     * Set background color for all buttons.
     *
     * @param color Color to use for tinting buttons
     * @return current class
     */
    protected SJDialog setButtonsColor(@ColorInt int color) {
        checkButtonResource(0);
        button1.getBackground().setTint(color);
        if (twoButtons){
            checkButtonResource(1);
            button2.getBackground().setTint(color);
        }
        return this;
    }

    /**
     * Set background color for left button.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     */
    protected SJDialog setLeftButtonColor(@ColorInt int color) {
        checkButtonResource(0);
        button1.getBackground().setTint(color);
        return this;
    }

    /**
     * Set background color for right button.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     */
    protected SJDialog setRightButtonColor(@ColorInt int color) {
        if (!twoButtons)
            throw OneButtonException();

        checkButtonResource(1);
        button2.getBackground().setTint(color);
        return this;
    }

    protected SJDialog setButtonColor(@ButtonColor String color) {
        checkButtonResource(0);
        setLeftButtonColor(color);
        return this;
    }

    protected SJDialog setButtonsColor(@ButtonColor String color) {
        setLeftButtonColor(color);
        if (twoButtons)
            setRightButtonColor(color);

        return this;
    }

    protected SJDialog setLeftButtonColor(@ButtonColor String color) {
        checkButtonResource(0);
        switch (color) {
            case RED_BUTTON:
                setLeftButtonBackgroundResource(R.drawable.ripple_button_red);
                setLeftButtonTextColor(context.getResources().getColor(R.color.md_theme_onError, context.getTheme()));
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

    protected SJDialog setRightButtonColor(@ButtonColor String color) {
        if (!twoButtons)
            throw OneButtonException();
        checkButtonResource(1);
        switch (color) {
            case RED_BUTTON:
                setRightButtonBackgroundResource(R.drawable.ripple_button_red);
                setRightButtonTextColor(context.getResources().getColor(R.color.md_theme_onError, context.getTheme()));
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

    /**
     * Set background resource for dialog.
     *
     * @param drawable resource id
     * @return current class
     */
    protected SJDialog setDialogBackgroundResource(@DrawableRes int drawable) {
        LinearLayout background = dialog.findViewById(R.id.dialogBackground);
        background.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for all buttons.
     *
     * @param drawable resource id
     * @return current class
     */
    protected SJDialog setButtonsBackgroundResource(@DrawableRes int drawable) {
        button1.setBackgroundResource(drawable);
        if (twoButtons)
            button2.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for left button.
     *
     * @param drawable resource id
     * @return current class
     */
    protected SJDialog setLeftButtonBackgroundResource(@DrawableRes int drawable) {
        button1.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for right button.
     *
     * @param drawable resource id
     * @return current class
     */
    protected SJDialog setRightButtonBackgroundResource(@DrawableRes int drawable) {
        if (!twoButtons)
            throw OneButtonException();

        button2.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set onClick listener for right button
     *
     * @param dialogButtonEvent dialog button events
     * @return current class
     */
    protected SJDialog onButtonClick(DialogButtonEvent dialogButtonEvent) {
        button1.setOnClickListener(v -> dialog.dismiss());
        button2.setOnClickListener(v -> dialogButtonEvent.onButtonClick());
        return this;
    }

    /**
     * Set onClick listener for both buttons
     *
     * @param dialogButtonEvents dialog button events
     * @return current class
     */
    protected SJDialog onButtonClick(DialogButtonEvents dialogButtonEvents) {
        button1.setOnClickListener(v -> dialogButtonEvents.onLeftButtonClick());
        button2.setOnClickListener(v -> dialogButtonEvents.onRightButtonClick());
        return this;
    }

    /**
     * Set onClick listener for left button
     *
     * @param dialogButtonEvent dialog button event
     * @return current class
     */
    protected SJDialog onLeftButtonClick(DialogButtonEvent dialogButtonEvent) {
        button1.setOnClickListener(v -> dialogButtonEvent.onButtonClick());
        return this;
    }

    /**
     * Creating dialog with two buttons. Use this method before changing buttons attributes
     * */
    protected SJDialog dialogWithTwoButtons() {
        twoButtons = true;
        return this;
    }

    /**
     * show dialog
     */
    protected SJDialog show() {
        dialog.show();
        return this;
    }

    /**
     * dismiss dialog
     */
    public void dismiss() {
        dialog.dismiss();
    }

    private void setContentView(@LayoutRes int layoutResID) {
        dialog.setContentView(layoutResID);
    }

    protected void setButton1(@IdRes int id) {
        this.button1 = dialog.findViewById(id);
    }

    protected void setButton2(@IdRes int id) {
        this.button2 = dialog.findViewById(id);
    }

    protected abstract @IdRes int setButtonsRootLayoutID();

    protected abstract void setButtons();


    private void regenerateButtons() {
        LinearLayout buttons = dialog.findViewById(setButtonsRootLayoutID());
        regenerateLeftBtn(buttons);
        if (onlyOneButton) {
            return;
        }
        regenerateRightBtn(buttons);
    }

    @SuppressLint("SetTextI18n")
    private void regenerateLeftBtn(LinearLayout buttons){
        if (buttons == null)
            buttons = dialog.findViewById(setButtonsRootLayoutID());

        buttons.removeView(button1);
        Activity activity = (Activity) context;
        button1 = (Button) activity.getLayoutInflater().inflate(Btn1Resource, buttons, false);
        button1.setText("Cancel");
        buttons.addView(button1,0);
    }

    @SuppressLint("SetTextI18n")
    private void regenerateRightBtn(LinearLayout buttons){
        if (buttons == null)
            buttons = dialog.findViewById(setButtonsRootLayoutID());

        int btn2Visibility = button2.getVisibility();
        buttons.removeView(button2);
        Activity activity = (Activity) context;
        button2 = (Button) activity.getLayoutInflater().inflate(Btn2Resource, buttons, false);
        button2.setVisibility(btn2Visibility);
        button2.setText("Ok");
        buttons.addView(button2,1);
    }

    private void checkButtonResource(int i) {
        if (usesDefaultTheme)
            return;

        if (i == 0){
            if (Btn1Resource == R.layout.button_template){
                Btn1Resource = R.layout.button_template_1;
                regenerateLeftBtn(null);
            }
            return;
        }
        if (i == 1){
            if (Btn2Resource == R.layout.button_template) {
                Btn2Resource = R.layout.button_template_1;
                regenerateRightBtn(null);
            }
        }
    }

    protected void setButton2Visibility(int visibility) {
        button2.setVisibility(visibility);
    }

    protected TextView getTitleTv() {
        return dialog.findViewById(R.id.titleText);
    }

    private TextView getMessageTv() {
        return dialog.findViewById(R.id.messageTxt);
    }

    protected Button getLeftButton() {
        return button1;
    }

    protected Button getRightButton() {
        return button2;
    }

    public int getMaxDialogWidth() {
        return this.maxDialogWidth;
    }

    /**
     * Set the maximum width for dialog
     *
     * @param maxDialogWidth set value for {@link #maxDialogWidth}. Default is 600dp
     * @return current class
     */
    protected SJDialog setMaxDialogWidth(int maxDialogWidth) {
        this.maxDialogWidth = maxDialogWidth;
        setDialogSize();
        return this;
    }


    /**
     * Set animation for a dialog
     * @param styleRes style resource
     * @return current class
     */
    protected SJDialog setDialogAnimations(@StyleRes int styleRes){
        dialog.getWindow().getAttributes().windowAnimations = styleRes;
        return this;
    }

    private OneButtonException OneButtonException() {
        return new OneButtonException("Trying to access right button when dialog has only one button. Use 'dialogWithTwoButtons()' to fix the problem.");
    }
}
 class OneButtonException extends RuntimeException{
     public OneButtonException(String message){
         super(message);
     }
}
