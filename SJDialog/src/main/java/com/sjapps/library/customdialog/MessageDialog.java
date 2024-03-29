package com.sjapps.library.customdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;

import com.sjapps.library.R;
/**
 * Creating a simple message dialog. Use the {@link #Builder(Context)}, {@link #Builder(Context, int)} or {@link #Builder(Context, boolean)} to build a dialog
 * <pre>
 * MessageDialog messageDialog = new MessageDialog();
 * messageDialog.Builder(context)
 * 	    .setTitle("Title")
 *      .setMessage("Message")
 *      .show();
 * </pre>
 * @see #setOldTheme()
 * @see #setTitle(String)
 * @see #setMessage(String)
 * @see #setTitleAlignment(int)
 * @see #setMessageAlignment(int)
 * @see #setButtonText(String)
 * @see #setTextColor(int)
 * @see #setTitleTextColor(int)
 * @see #setMessageTextColor(int)
 * @see #setButtonColor(int)
 * @see #setButtonTextColor(int)
 * @see #setButtonBackgroundResource(int)
 * @see #setDialogBackgroundColor(int)
 * @see #setDialogBackgroundResource(int)
 * @see #setMaxDialogWidth(int)
 * @see #setDialogAnimations(int)
 * @see #swipeToDismiss(boolean)
 * @see #onButtonClick(DialogButtonEvent)
 * @see #setOnTouchListener(View.OnTouchListener)
 * @see #onShowListener(DialogInterface.OnShowListener)
 * @see #onDismissListener(DialogInterface.OnDismissListener)
 * @see #applyInsets(int)
 * @since 1.3*/
@SuppressWarnings("unused")
public class MessageDialog extends SJDialog{

    private boolean isErrorDialog;

    public MessageDialog(){
        onlyOneButton = true;
    }
    /**
     * Build a dialog with the default theme
     * @param context the base context
     * @return current class
     * */
    public MessageDialog Builder(Context context){
        super.Builder(context,R.layout.message_dialog);
        onButtonClick(() -> dialog.dismiss());
        return this;
    }
    /**
     * Build a dialog with a custom theme <b>(only works with Material3 themes)</b>
     * @param context the base context
     * @param theme theme that will be applied to the dialog
     * @return current class
     * */
    public MessageDialog Builder(Context context,@StyleRes int theme){
        super.Builder(context,R.layout.message_dialog,theme, false);
        onButtonClick(() -> dialog.dismiss());
        return this;
    }
    /**
     * Build a dialog with the app theme <b>(only works with Material3 themes)</b>
     * @param context the base context
     * @param useAppTheme if it's true, the app theme will be applied to the dialog
     * @return current class
     * */
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

    /**{@inheritDoc}
     * @since 1.5*/
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

    /**{@inheritDoc}
     * @since 1.3*/
   @Override
    public MessageDialog setTitle(String title){
        super.setTitle(title);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.3*/
    @Override
    public MessageDialog setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     * */
    @Override
    public MessageDialog setTitleAlignment(int textAlignment) {
        super.setTitleAlignment(textAlignment);
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     * */
    @Override
    public MessageDialog setMessageAlignment(int textAlignment) {
        super.setMessageAlignment(textAlignment);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public MessageDialog setTextColor(int color) {
        super.setTextColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public MessageDialog setTitleTextColor(int color) {
        super.setTitleTextColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public MessageDialog setMessageTextColor(int color) {
        super.setMessageTextColor(color);
        return this;
    }

    /**
     * Set a button text.
     * @param text button text
     * @return current class
     * @since 1.4
     * */
    public MessageDialog setButtonText(String text){
        super.setLeftButtonText(text);
        return this;
    }

    /**
     * Set button text color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * @since 1.4
     * */
    public MessageDialog setButtonTextColor(@ColorInt int color){
        super.setLeftButtonTextColor(color);
        return this;
    }

    /**
     * Set button color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * @since 1.4
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

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public MessageDialog setDialogBackgroundColor(int color) {
        super.setDialogBackgroundColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.3*/
    @Override
    public MessageDialog setDialogBackgroundResource(@DrawableRes int drawable){
        super.setDialogBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for button.
     * @param drawable resource id
     * @return current class
     * @since 1.3
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

    /**{@inheritDoc}
     * @since 1.7*/
    @Override
    public MessageDialog onDismissListener(DialogInterface.OnDismissListener listener) {
        super.onDismissListener(listener);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.7*/
    @Override
    public MessageDialog onShowListener(DialogInterface.OnShowListener listener) {
        super.onShowListener(listener);
        return this;
    }

    @Override
    protected void setButtons() {
        setButton1(R.id.btn);
    }

    public Button getButton() {
        return super.getLeftButton();
    }

    /**@since 1.6*/
    @Override
    public TextView getTitleTextView() {
        return super.getTitleTextView();
    }

    /**@since 1.6*/
    @Override
    public TextView getMessageTextView() {
        return super.getMessageTextView();
    }

    /**{@inheritDoc}
     * @since 1.4*/
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

    /**{@inheritDoc}
     * @since 1.3*/
    @Override
    public MessageDialog show(){
        super.show();
        return this;
    }

    @Override
    protected int setButtonsRootLayoutID() {
        return R.id.buttonRoot;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public MessageDialog setOnTouchListener(View.OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public MessageDialog swipeToDismiss(boolean isSwipeToDismiss) {
        super.swipeToDismiss(isSwipeToDismiss);
        return this;
    }


    /**{@inheritDoc}
     * @since 1.6.1*/
    @Override
    public MessageDialog applyInsets(boolean applyInsets) {
        super.applyInsets(applyInsets);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6.1*/
    @Override
    public MessageDialog applyInsets(int insets) {
        super.applyInsets(insets);
        return this;
    }

    /**
     * Apply presets on a dialog
     * @param presets {@link DialogPreset presets} that will be applied to the dialog
     * @return current class
     * @since 1.7
     * */
    public MessageDialog setPresets(DialogPreset<MessageDialog> presets){
        presets.addPresets(this);
        return this;
    }
}
