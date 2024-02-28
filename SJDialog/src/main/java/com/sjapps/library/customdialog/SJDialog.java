package com.sjapps.library.customdialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringDef;
import androidx.annotation.StyleRes;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sjapps.library.R;

/**
 * @see BasicDialog
 * @see MessageDialog
 * @see ListDialog
 * @see CustomViewDialog
 * */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class SJDialog {

    public static final String RED_BUTTON = "RedBtn";
    public static final String MATERIAL3_RED_BUTTON = "Material3RedBtn";
    public static final String OLD_BUTTON_COLOR = "OldBtnColor";
    /** Sets text alignment to {@link View#TEXT_ALIGNMENT_VIEW_START}
     * @since 1.6
     * */
    public static final int TEXT_ALIGNMENT_LEFT = View.TEXT_ALIGNMENT_VIEW_START;
    /** Sets text alignment to {@link View#TEXT_ALIGNMENT_VIEW_END}
     * @since 1.6
     * */
    public static final int TEXT_ALIGNMENT_RIGHT = View.TEXT_ALIGNMENT_VIEW_END;
    /** Sets text alignment to {@link View#TEXT_ALIGNMENT_CENTER}
     * @since 1.6
     * */
    public static final int TEXT_ALIGNMENT_CENTER = View.TEXT_ALIGNMENT_CENTER;

    private @LayoutRes int Btn1Resource = R.layout.button_template;
    private @LayoutRes int Btn2Resource = R.layout.button_template;

    @ColorInt int defaultOldThemeTextColor;
    @ColorInt int defaultOldColorWhite = 0xFFE5E5E5;
    @ColorInt int defaultOldColorBlack = 0xFF333333;

    private final int defaultTheme = R.style.Theme_SJDialog;
    private int newTheme = -1;
    private boolean usesDefaultTheme = true;
    private boolean isSwipeToDismiss = true;
    private boolean isDefaultOnTouchListener = true;

    private boolean isNotDefaultInsets = false;
    private boolean setInsets = true;
    private boolean leftInsets;
    private boolean rightInsets;
    private boolean bottomInsets;

    /**
     * Don't apply any {@link Insets}
     * @since 1.6.1
     * */
    public static final int INSETS_NONE = 0;
    /**
     * Apply left {@link Insets}
     * @since 1.6.1
     * */
    public static final int INSETS_LEFT = 1;
    /**
     * Apply right {@link Insets}
     * @since 1.6.1
     * */
    public static final int INSETS_RIGHT = 4;
    /**
     * Apply bottom {@link Insets}
     * @since 1.6.1
     * */
    public static final int INSETS_BOTTOM = 8;
    /**
     * Apply horizontal {@link Insets} ({@link #INSETS_LEFT} and {@link #INSETS_RIGHT})
     * @since 1.6.1
     * */
    public static final int INSETS_HORIZONTAL = 5;
    /**
     * Apply all {@link Insets} ({@link #INSETS_LEFT}, {@link #INSETS_RIGHT} and {@link #INSETS_BOTTOM})
     * @since 1.6.1
     * */
    public static final int INSETS_ALL = 13;


    private View.OnTouchListener dialogOnTouchListener = null;

    public Dialog dialog;
    protected Button button1, button2;
    private LinearLayout background;
    private int maxDialogWidth = 600;
    Context context;

    DialogButtonEvent dialogButtonEvent;
    DialogButtonEvents dialogButtonEvents;


    /**
     * values for <b>DialogInsets</b>: {@link #INSETS_LEFT}, {@link #INSETS_RIGHT},
     *       {@link #INSETS_BOTTOM}, {@link #INSETS_HORIZONTAL}, {@link #INSETS_ALL} and {@link #INSETS_NONE}
     */
    @IntDef(flag = true,
            value = {
                    INSETS_LEFT,
                    INSETS_RIGHT,
                    INSETS_BOTTOM,
                    INSETS_HORIZONTAL,
                    INSETS_ALL,
                    INSETS_NONE
            }
    )

    public @interface DialogInsets{}

    @StringDef({RED_BUTTON, MATERIAL3_RED_BUTTON, OLD_BUTTON_COLOR})
    public @interface ButtonColor {
    }

    @IntDef({TEXT_ALIGNMENT_LEFT, TEXT_ALIGNMENT_RIGHT, TEXT_ALIGNMENT_CENTER})
    public @interface TextAlignment {
    }

    protected boolean onlyOneButton = false;
    protected boolean twoButtons = false;
    private boolean leftBtnOnClick = false;

    protected SJDialog Builder(Context context, @LayoutRes int layoutResID) {
        Builder(context, layoutResID, defaultTheme, false);
        return this;
    }

    protected SJDialog Builder(Context context, @LayoutRes int layoutResID, boolean useAppTheme) {
        Builder(context, layoutResID, defaultTheme, useAppTheme);
        return this;
    }

    protected SJDialog Builder(Context context, @LayoutRes int layoutResID, @StyleRes int theme, boolean useAppTheme) {
        this.context = context;
        defaultOldThemeTextColor = context.getResources().getColor(R.color.SJDialog_OldThemeTextColor, context.getTheme());
        dialog = useAppTheme ?
                new Dialog(new ContextThemeWrapper(context, context.getTheme())) :
                new Dialog(new ContextThemeWrapper(context, theme));
        setContentView(layoutResID);
        setDialogSize();
        dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.SJDialogAnimation;
        background = dialog.findViewById(R.id.dialogBackground);
        setButtons();
        if (theme != defaultTheme || useAppTheme) {
            usesDefaultTheme = false;
            newTheme = !useAppTheme ? theme : -1;
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
     * @since 1.5
     */
    protected SJDialog setOldTheme() {
        setButtonsColor(OLD_BUTTON_COLOR);
        setDialogBackgroundResource(R.drawable.dialog_background_old);
        setTextColor(defaultOldThemeTextColor);
        return this;
    }

    /**
     * Set a dialog title.
     *
     * @param title title of a dialog
     * @return current class
     * @since 1.3
     */
    protected SJDialog setTitle(String title) {
        TextView TitleTv = getTitleTextView();
        TitleTv.setText(title);
        return this;
    }

    /**
     * Set a dialog message.
     *
     * @param message message of a dialog
     * @return current class
     * @since 1.3
     */
    protected SJDialog setMessage(String message) {
        TextView msg = getMessageTextView();
        msg.setText(message);
        msg.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * Set the text alignment for Title TextView. Default is set to {@link SJDialog#TEXT_ALIGNMENT_CENTER}
     * @param textAlignment The text alignment to set. Supported types: {@link SJDialog#TEXT_ALIGNMENT_LEFT}, {@link SJDialog#TEXT_ALIGNMENT_RIGHT} and {@link SJDialog#TEXT_ALIGNMENT_CENTER}
     * @return current class
     * @since 1.6
     * */
    protected SJDialog setTitleAlignment(@TextAlignment int textAlignment){
        TextView title = getTitleTextView();
        title.setGravity(Gravity.RIGHT);
        title.setTextAlignment(textAlignment);

        return this;
    }

    /**
     * Set the text alignment for Message TextView. Default is set to {@link SJDialog#TEXT_ALIGNMENT_LEFT}
     * @param textAlignment The text alignment to set. Supported types: {@link SJDialog#TEXT_ALIGNMENT_LEFT}, {@link SJDialog#TEXT_ALIGNMENT_RIGHT} and {@link SJDialog#TEXT_ALIGNMENT_CENTER}
     * @return current class
     * @since 1.6
     * */
    protected SJDialog setMessageAlignment(@TextAlignment int textAlignment){
        TextView msg = getMessageTextView();
        msg.setTextAlignment(textAlignment);
        return this;
    }

    /**
     * Set text color.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     * @since 1.6
     */
    protected SJDialog setTextColor(@ColorInt int color) {
        setTitleTextColor(color);
        setMessageTextColor(color);
        return this;
    }

    /**
     * Set title text color.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     * @since 1.6
     */
    protected SJDialog setTitleTextColor(@ColorInt int color) {
        getTitleTextView().setTextColor(color);
        return this;
    }

    /**
     * Set message text color.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     * @since 1.6
     */
    protected SJDialog setMessageTextColor(@ColorInt int color) {
        getMessageTextView().setTextColor(color);
        return this;
    }

    /**
     * Set a text to left button.
     *
     * @param text button text
     * @return current class
     * @since 1.4
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
     * @since 1.4
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
     * @since 1.4
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
     * @since 1.4
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
     * @since 1.4
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
     * @since 1.4
     */
    protected SJDialog setButtonsColor(@ColorInt int color) {
        checkButtonResource(0);
        button1.getBackground().setTint(color);
        if (twoButtons) {
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
     * @since 1.4
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
     * @since 1.4
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
                setLeftButtonTextColor(Color.WHITE);
                break;
            case MATERIAL3_RED_BUTTON:
                setLeftButtonBackgroundResource(R.drawable.ripple_button_material3_red);
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
                setRightButtonTextColor(Color.WHITE);
                break;
            case MATERIAL3_RED_BUTTON:
                setRightButtonBackgroundResource(R.drawable.ripple_button_material3_red);
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
     * Change dialog color
     * @param color {@link ColorInt}
     * @return current class
     * @since 1.6
     */
    protected SJDialog setDialogBackgroundColor(@ColorInt int color){
        background.getBackground().mutate().setTint(color);
        return this;
    }

    /**
     * Set background resource for dialog.
     *
     * @param drawable resource id
     * @return current class
     * @since 1.3
     */
    protected SJDialog setDialogBackgroundResource(@DrawableRes int drawable) {
        background.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for all buttons.
     *
     * @param drawable resource id
     * @return current class
     * @since 1.3
     */
    protected SJDialog setButtonsBackgroundResource(@DrawableRes int drawable) {
        checkButtonResource(0);
        button1.setBackgroundResource(drawable);
        if (twoButtons) {
            checkButtonResource(1);
            button2.setBackgroundResource(drawable);
        }
        return this;
    }

    /**
     * Set background resource for left button.
     *
     * @param drawable resource id
     * @return current class
     * @since 1.3
     */
    protected SJDialog setLeftButtonBackgroundResource(@DrawableRes int drawable) {
        checkButtonResource(0);
        button1.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for right button.
     *
     * @param drawable resource id
     * @return current class
     * @since 1.3
     */
    protected SJDialog setRightButtonBackgroundResource(@DrawableRes int drawable) {
        if (!twoButtons)
            throw OneButtonException();

        checkButtonResource(1);
        button2.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set onClick listener for right button
     *
     * @param dialogButtonEvent dialog button events
     * @return current class
     * @since 1.0
     */
    protected SJDialog onButtonClick(DialogButtonEvent dialogButtonEvent) {
        leftBtnOnClick = false;
        this.dialogButtonEvent = dialogButtonEvent;

        return this;
    }

    /**
     * Set onClick listener for both buttons
     *
     * @param dialogButtonEvents dialog button events
     * @return current class
     * @since 1.0
     */
    protected SJDialog onButtonClick(DialogButtonEvents dialogButtonEvents) {
        this.dialogButtonEvents = dialogButtonEvents;

        return this;
    }

    /**
     * Set onClick listener for left button
     *
     * @param dialogButtonEvent dialog button event
     * @return current class
     */
    protected SJDialog onLeftButtonClick(DialogButtonEvent dialogButtonEvent) {
        leftBtnOnClick = true;
        this.dialogButtonEvent = dialogButtonEvent;

        return this;
    }

    /**
     * Set dialog {@link DialogInterface.OnDismissListener OnDismissListener}
     * @since 1.7
     */
    protected SJDialog onDismissListener(DialogInterface.OnDismissListener listener){
        this.dialog.setOnDismissListener(listener);
        dialog.dismiss();
        return this;
    }

    /**
     * Set dialog {@link DialogInterface.OnShowListener OnShowListener}
     * @since 1.7
     */
    protected SJDialog onShowListener (DialogInterface.OnShowListener listener){
        this.dialog.setOnShowListener(listener);
        return this;
    }

    /**
     * Creating dialog with two buttons. Use this method before changing buttons attributes
     * @since 1.5
     */
    protected SJDialog dialogWithTwoButtons() {
        twoButtons = true;
        return this;
    }

    /**
     * show dialog
     * @since 1.3
     */
    protected SJDialog show() {
        setDialogTouchListener();
        addOnClickListener();
        if (!isNotDefaultInsets)
            applyInsets(true);
        if (setInsets)
            applyWindowInsets();
        dialog.show();
        return this;
    }

    /**
     * Apply default {@link Insets}
     *
     * @param applyInsets If it's true it will apply {@link Insets} to all sides otherwise it will remove all insets. Default value is <b>true</b>
     * @return current class
     * @since 1.6.1
     * */
    protected SJDialog applyInsets(boolean applyInsets){
        if (applyInsets)
            return applyInsets(INSETS_ALL);
        return applyInsets(INSETS_NONE);
    }

    /**
     * Apply {@link Insets} to a dialog <br>
     * supported values: {@link #INSETS_LEFT}, {@link #INSETS_RIGHT},
     *       {@link #INSETS_BOTTOM}, {@link #INSETS_HORIZONTAL}, {@link #INSETS_ALL} or {@link #INSETS_NONE}.
     *       You can combine multiple values with bitwise-OR (<b> | </b>) operator
     * @param insets bitwise-OR combination of {@link DialogInsets}
     *
     * @return current class
     * @since 1.6.1
     * */
    protected SJDialog applyInsets(@DialogInsets int insets){
        isNotDefaultInsets = true;

        if (insets == INSETS_NONE){
            setInsets = false;
            return this;
        }

        setInsets = true;
        if ((insets & INSETS_LEFT) == INSETS_LEFT)
            leftInsets = true;
        if ((insets & INSETS_RIGHT) == INSETS_RIGHT)
            rightInsets = true;
        if ((insets & INSETS_BOTTOM) == INSETS_BOTTOM)
            bottomInsets = true;

        return this;
    }

    private void applyWindowInsets() {

        ViewCompat.setOnApplyWindowInsetsListener(dialog.getWindow().getDecorView(),(v, insets) -> {
            Insets insetsSB = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets insetsDC = insets.getInsets(WindowInsetsCompat.Type.displayCutout());

            Insets insetsFinal = Insets.of(
                    insetsSB.left + insetsDC.left,
                    0,
                    insetsSB.right + insetsDC.right,
                    insetsSB.bottom + insetsDC.bottom
            );

            v.setPadding(
                    leftInsets ? insetsFinal.left : 0,
                    0,
                    rightInsets ? insetsFinal.right : 0,
                    bottomInsets ? insetsFinal.bottom : 0
            );

            return WindowInsetsCompat.CONSUMED;
        });
    }



    private void addOnClickListener() {
        if (dialogButtonEvents == null && dialogButtonEvent == null)
            return;

        if (dialogButtonEvents != null) {
            button1.setOnClickListener(v -> dialogButtonEvents.onLeftButtonClick());
            button2.setOnClickListener(v -> dialogButtonEvents.onRightButtonClick());
            return;
        }

        if (!twoButtons) {
            button1.setOnClickListener(v -> dialogButtonEvent.onButtonClick());
            return;
        }

        if (leftBtnOnClick) {
            button1.setOnClickListener(v -> dialogButtonEvent.onButtonClick());
            return;
        }

        button1.setOnClickListener(v -> dismiss());
        button2.setOnClickListener(v -> dialogButtonEvent.onButtonClick());
    }

    /**
     * dismiss dialog
     * @since 1.3
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
    private void regenerateLeftBtn(LinearLayout buttons) {
        if (buttons == null)
            buttons = dialog.findViewById(setButtonsRootLayoutID());

        String txt = button1.getText().toString();
        buttons.removeView(button1);
        button1 = (Button) LayoutInflater
                .from(newTheme != -1 ? new ContextThemeWrapper(context, newTheme) : context)
                .inflate(Btn1Resource,buttons,false);
        button1.setText(txt);
        buttons.addView(button1, 0);
    }

    @SuppressLint("SetTextI18n")
    private void regenerateRightBtn(LinearLayout buttons) {
        if (buttons == null)
            buttons = dialog.findViewById(setButtonsRootLayoutID());

        int btn2Visibility = button2.getVisibility();
        String txt = button2.getText().toString();
        buttons.removeView(button2);
        button2 = (Button) LayoutInflater
                .from(newTheme != -1 ? new ContextThemeWrapper(context, newTheme) : context)
                .inflate(Btn2Resource,buttons,false);
        button2.setVisibility(btn2Visibility);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button2.getLayoutParams();
        params.setMarginStart(functions.dpToPixels(context,10));
        button2.setLayoutParams(params);
        button2.setText(txt);
        buttons.addView(button2, 1);
    }

    private void checkButtonResource(int i) {
        if (usesDefaultTheme)
            return;

        if (i == 0) {
            if (Btn1Resource == R.layout.button_template) {
                Btn1Resource = R.layout.button_template_1;
                regenerateLeftBtn(null);
            }
            return;
        }
        if (i == 1) {
            if (Btn2Resource == R.layout.button_template) {
                Btn2Resource = R.layout.button_template_1;
                regenerateRightBtn(null);
            }
        }
    }

    protected void setButton2Visibility(int visibility) {
        button2.setVisibility(visibility);
    }

    protected TextView getTitleTextView() {
        return dialog.findViewById(R.id.titleText);
    }

    protected TextView getMessageTextView() {
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
     * @since 1.4
     */
    protected SJDialog setMaxDialogWidth(int maxDialogWidth) {
        this.maxDialogWidth = maxDialogWidth;
        setDialogSize();
        return this;
    }


    /**
     * Set animation for a dialog
     *
     * @param styleRes style resource
     * @return current class
     * @since 1.5
     */
    protected SJDialog setDialogAnimations(@StyleRes int styleRes) {
        dialog.getWindow().getAttributes().windowAnimations = styleRes;
        return this;
    }

    /**
     * Enable or disable swipe down to dismiss dialog. Default is set to true
     *
     * @param isSwipeToDismiss Enable or disable swipe action
     * @return current class
     * @since 1.6
     */
    protected SJDialog swipeToDismiss(boolean isSwipeToDismiss){
        this.isSwipeToDismiss = isSwipeToDismiss;
        return this;
    }

    /**
     * Set dialog OnTouchListener. by default is set to {@link SwipeDismissTouchListener}.
     * If this method is used, swipe to dismiss will not work.
     *
     * @param onTouchListener onTouchListener for dialog view
     * @return current class
     * @since 1.6
     */
    protected SJDialog setOnTouchListener(View.OnTouchListener onTouchListener) {
        dialogOnTouchListener = onTouchListener;
        isDefaultOnTouchListener = false;
        return this;
    }

    private void setDialogTouchListener() {

        if (isDefaultOnTouchListener)
            dialogOnTouchListener = new SwipeDismissTouchListener(
                    dialog.getWindow().getDecorView(),
                    new SwipeDismissTouchListener.DismissCallbacks() {
                        @Override
                        public boolean canDismiss() {
                            return isSwipeToDismiss;
                        }

                        @Override
                        public void onDismiss(View view) {
                            dialog.dismiss();
                        }
                    }
            );

        dialog.getWindow().getDecorView().setOnTouchListener(dialogOnTouchListener);
    }

    private OneButtonException OneButtonException() {
        return new OneButtonException("Trying to access right button when dialog has only one button. Use 'dialogWithTwoButtons()' to fix the problem.");
    }
}

class OneButtonException extends RuntimeException {
    public OneButtonException(String message) {
        super(message);
    }
}
