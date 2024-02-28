package com.sjapps.library.customdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.library.R;
import com.sjapps.library.customdialog.adapter.DefaultImageListAdapter;
import com.sjapps.library.customdialog.adapter.DefaultListAdapter;
import com.sjapps.library.customdialog.adapter.DefaultListAdapterGeneric;
import com.sjapps.library.customdialog.list.events.ListItemClick;
import com.sjapps.library.customdialog.list.events.ListItemClickObj;

import java.util.ArrayList;
/**
 * Creating a dialog with a list. Use the {@link #Builder(Context)}, {@link #Builder(Context, int)} or {@link #Builder(Context, boolean)} to build a dialog
 *
 *  <h2>Examples</h2>
 * <h3>List of String array</h3>
 * <pre>
 * String[] strings = {"item1","item2","item3"};
 *
 * ListDialog listDialog = new ListDialog();
 * listDialog.Builder(this)
 * 	.setItems(strings,(position, value) -> {
 *        		// Do something
 *                        })
 *        	.show();
 * </pre>
 *
 * <h3>List of Objects</h3>
 * <pre>
 * class ExampleObject{
 *     	String value;
 *
 *     	public ExampleObject(String value) {
 *         	this.value = value;
 *      }
 * }
 * </pre>
 * <pre>
 * ExampleObject[] objects = {new ExampleObject("object1"),new ExampleObject("object2"),new ExampleObject("object3")};
 *
 * ListDialog listDialog = new ListDialog();
 * listDialog.Builder(this)
 * 	    .setItems(
 *        		objects,
 *              obj -> obj.value, // get value from object
 *              (position, value) -> {
 *                 	// Do something
 *              })
 *      .show();
 * </pre>
 *
 * <h3>ArrayList of Objects with two values</h3>
 * <pre>
 * class ExampleObject{
 *     	String value1;
 *     	String value2;
 *
 * 	    public ExampleObject(String value1, String value2) {
 *         	this.value1 = value1;
 *         	this.value2 = value2;
 *      }
 * }
 * </pre>
 * <pre>
 * ArrayList<ExampleObject> arrayList = new ArrayList<>();
 * arrayList.add(new ExampleObject("object1","value1"));
 * arrayList.add(new ExampleObject("object2","value2"));
 * arrayList.add(new ExampleObject("object3","value3"));
 *
 * ListDialog listDialog = new ListDialog();
 * listDialog.Builder(this)
 * 	    .setItems(
 *    		arrayList,
 *          new ListItemValues<ExampleObject>() {
 *              {@code @Override}
 *              public String getValue1(ExampleObject obj) {
 *                  return obj.value1;
 *              }
 *
 *              {@code @Override}
 *              public String getValue2(ExampleObject obj) {
 *                  return obj.value2;
 *              }
 *          },
 *       	(position, value) -> {
 *              // Do something
 *           })
 *      .show();
 * </pre>
 *
 *
 * @see #dialogWithTwoButtons()
 * @see #setOldTheme()
 * @see #setTitle(String)
 * @see #setMessage(String)
 * @see #setTitleAlignment(int)
 * @see #setMessageAlignment(int)
 * @see #setLeftButtonText(String)
 * @see #setRightButtonText(String)
 * @see #setTextColor(int)
 * @see #setTitleTextColor(int)
 * @see #setMessageTextColor(int)
 * @see #setButtonsColor(int)
 * @see #setButtonsTextColor(int)
 * @see #setButtonsBackgroundResource(int)
 * @see #setDialogBackgroundColor(int)
 * @see #setDialogBackgroundResource(int)
 * @see #setSelectableList()
 * @see #setListItemTextColor(int) 
 * @see #setListItemBackgroundColor(int)  
 * @see #setListItemSelectedBackgroundColor(int)   
 * @see #setListItemBackgroundResource(int)    
 * @see #setListItemSelectedBackgroundResource(int)     
 * @see #setListBackgroundResource(int)      
 * @see #setAdapter(RecyclerView.Adapter)       
 * @see #setLayoutManager(RecyclerView.LayoutManager)        
 * @see #setItems(String[])
 * @see #setItems(String[], ListItemClick) 
 * @see #setItems(Object[], ListItemValue)  
 * @see #setItems(Object[], ListItemValue, ListItemClickObj)
 * @see #setItems(Object[], ListItemValues)
 * @see #setItems(Object[], ListItemValues, ListItemClickObj)
 * @see #setItems(Object[], ListItemValues, ListItemClickObj)
 * @see #setItems(ArrayList, ListItemValue) 
 * @see #setItems(ArrayList, ListItemValue,ListItemClickObj) 
 * @see #setItems(ArrayList, ListItemValues)  
 * @see #setItems(ArrayList, ListItemValues, ListItemClickObj)  
 * @see #setImageItems(ArrayList)   
 * @see #setImageItems(ArrayList, ListItemClickObj)    
 * @see #selectItem(int)    
 * @see #updateList()     
 * @see #hideEmptyListText()     
 * @see #setEmptyListText(String)     
 * @see #setMaxDialogWidth(int)
 * @see #setDialogAnimations(int)
 * @see #swipeToDismiss(boolean)
 * @see #setOnTouchListener(View.OnTouchListener)
 * @see #onShowListener(DialogInterface.OnShowListener)
 * @see #onDismissListener(DialogInterface.OnDismissListener)
 * @see #applyInsets(int)
 * @since 1.5*/
@SuppressWarnings({"unused", "unchecked","UnusedReturnValue"})
public class ListDialog extends SJDialog {

    RecyclerView listRV;
    boolean isSelectableList = false;
    boolean hideEmptyListTxt = false;
    boolean hasAdapter = false;
    boolean waitForLayoutManager = false;

    private @DrawableRes int listItemBgRes = R.drawable.ripple_list;
    private @DrawableRes int listItemBgResSelected = R.drawable.ripple_list_selected;
    private @ColorInt int listItemBgColor = -1;
    private @ColorInt int listItemBgColorSelected = -1;
    private int listItemTextColor = 1;
    private RecyclerView.LayoutManager layoutManager = null;

    RecyclerView.Adapter<?> adapter;
    ArrayList<?> selectedItems = new ArrayList<>();
    TextView emptyListTxt;

    public ListDialog() {

    }
    /**
     * Build a dialog with the default theme
     * @param context the base context
     * @return current class
     * */
    public ListDialog Builder(Context context) {
        return Builder(context, false);
    }
    /**
     * Build a dialog with a custom theme <b>(only works with Material3 themes)</b>
     * @param context the base context
     * @param theme theme that will be applied to the dialog
     * @return current class
     * */
    public ListDialog Builder(Context context, @StyleRes int theme) {
        super.Builder(context, R.layout.list_dialog, theme, false);
        listRV = dialog.findViewById(R.id.list);
        onLeftButtonClick(dialog::dismiss);
        emptyListTxt = dialog.findViewById(R.id.emptyListTxt);
        return this;
    }
    /**
     * Build a dialog with the app theme <b>(only works with Material3 themes)</b>
     * @param context the base context
     * @param useAppTheme if it's true, the app theme will be applied to the dialog
     * @return current class
     * */
    public ListDialog Builder(Context context, boolean useAppTheme) {
        super.Builder(context, R.layout.list_dialog, useAppTheme);
        listRV = dialog.findViewById(R.id.list);
        onLeftButtonClick(dialog::dismiss);
        emptyListTxt = dialog.findViewById(R.id.emptyListTxt);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setOldTheme() {
        super.setOldTheme();
        setListOldColor();
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setTitle(String title) {
        super.setTitle(title);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     * */
    @Override
    public ListDialog setTitleAlignment(int textAlignment) {
        super.setTitleAlignment(textAlignment);
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     * */
    @Override
    public ListDialog setMessageAlignment(int textAlignment) {
        super.setMessageAlignment(textAlignment);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public ListDialog setTextColor(int color) {
        super.setTextColor(color);
        setListItemTextColor(color);
        setEmptyListTxtColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public ListDialog setTitleTextColor(int color) {
        super.setTitleTextColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public ListDialog setMessageTextColor(int color) {
        super.setMessageTextColor(color);
        return this;
    }

    /**
     * Set a button text.
     *
     * @param text button text
     * @return current class
     * @since 1.5
     */
    public ListDialog setButtonText(String text) {
        return setLeftButtonText(text);
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setLeftButtonText(String text) {
        super.setLeftButtonText(text);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setRightButtonText(String text) {
        super.setRightButtonText(text);
        return this;
    }

    /**
     * Set button text color.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     * @since 1.5
     */
    public ListDialog setButtonTextColor(@ColorInt int color) {
        return setLeftButtonTextColor(color);
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setButtonsTextColor(@ColorInt int color) {
        super.setButtonsTextColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setLeftButtonTextColor(@ColorInt int color) {
        super.setLeftButtonTextColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setRightButtonTextColor(@ColorInt int color) {
        super.setRightButtonTextColor(color);
        return this;
    }

    /**
     * Set button color.
     *
     * @param color Color to use for tinting this drawable
     * @return current class
     * @since 1.5
     */
    public ListDialog setButtonColor(@ColorInt int color) {
        return setLeftButtonColor(color);
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setButtonsColor(@ColorInt int color) {
        super.setButtonsColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setLeftButtonColor(@ColorInt int color) {
        super.setLeftButtonColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setRightButtonColor(@ColorInt int color) {
        super.setRightButtonColor(color);
        return this;
    }

    @Override
    public ListDialog setButtonColor(@ButtonColor String color) {
        super.setButtonColor(color);
        return this;
    }

    @Override
    public ListDialog setButtonsColor(@ButtonColor String color) {
        super.setButtonsColor(color);
        return this;
    }

    @Override
    public ListDialog setLeftButtonColor(@ButtonColor String color) {
        super.setLeftButtonColor(color);
        return this;
    }

    @Override
    public ListDialog setRightButtonColor(@ButtonColor String color) {
        super.setRightButtonColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6*/
    @Override
    public ListDialog setDialogBackgroundColor(int color) {
        super.setDialogBackgroundColor(color);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setDialogBackgroundResource(@DrawableRes int drawable) {
        super.setDialogBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for button.
     *
     * @param drawable resource id
     * @return current class
     * @since 1.5
     */
    public ListDialog setButtonBackgroundResource(@DrawableRes int drawable) {
        return setLeftButtonBackgroundResource(drawable);
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setButtonsBackgroundResource(@DrawableRes int drawable) {
        super.setButtonsBackgroundResource(drawable);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setLeftButtonBackgroundResource(@DrawableRes int drawable) {
        super.setLeftButtonBackgroundResource(drawable);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setRightButtonBackgroundResource(@DrawableRes int drawable) {
        super.setRightButtonBackgroundResource(drawable);
        return this;
    }

    /**
     * Set onClick listener for a button
     *
     * @param dialogButtonEvent dialog button event
     * @return current class
     * @since 1.5
     */
    @Override
    public ListDialog onButtonClick(DialogButtonEvent dialogButtonEvent) {
        if (twoButtons)
            super.onButtonClick(dialogButtonEvent);
        else
            super.onLeftButtonClick(dialogButtonEvent);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog onButtonClick(DialogButtonEvents dialogButtonEvents) {
        super.onButtonClick(dialogButtonEvents);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.7*/
    @Override
    public ListDialog onDismissListener(DialogInterface.OnDismissListener listener) {
        super.onDismissListener(listener);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.7*/
    @Override
    public ListDialog onShowListener(DialogInterface.OnShowListener listener) {
        super.onShowListener(listener);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog show() {
        if (layoutManager == null)
            layoutManager = new LinearLayoutManager(context);
        if (!waitForLayoutManager)
            listRV.setLayoutManager(layoutManager);

        listRV.setAdapter(adapter);
        if (adapter == null)
            checkListsSize(0);

        super.show();

        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setMaxDialogWidth(int maxDialogWidth) {
        super.setMaxDialogWidth(maxDialogWidth);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog setDialogAnimations(int styleRes) {
        super.setDialogAnimations(styleRes);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.5*/
    @Override
    public ListDialog dialogWithTwoButtons() {
        super.dialogWithTwoButtons();
        setButton2Visibility(View.VISIBLE);
        return this;
    }

    @Override
    public void setButton2Visibility(int visibility) {
        super.setButton2Visibility(visibility);
    }

    private void setListOldColor() {
        listItemBgRes = R.drawable.ripple_list_old;
        listItemBgResSelected = R.drawable.ripple_list_selected_old;
    }

    /**
     * Set background resource for a list
     *
     * @param drawable drawable resource
     * @return current class
     * @since 1.5
     */
    public ListDialog setListBackgroundResource(@DrawableRes int drawable) {
        listRV.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for a item in a list
     *
     * @param drawable drawable resource
     * @return current class
     * @since 1.5
     */
    public ListDialog setListItemBackgroundResource(@DrawableRes int drawable) {
        listItemBgRes = drawable;
        return this;
    }

    /**
     * Set background resource for a selected item in a list
     *
     * @param drawable drawable resource
     * @return current class
     * @since 1.5
     */
    public ListDialog setListItemSelectedBackgroundResource(@DrawableRes int drawable) {
        listItemBgResSelected = drawable;
        return this;
    }

    /**
     * Set background color for a item in a list
     *
     * @param color {@link ColorInt}
     * @return current class
     * @since 1.6
     */
    public ListDialog setListItemBackgroundColor(@ColorInt int color) {
        listItemBgColor = color;
        return this;
    }

    /**
     * Set background color for a selected item in a list
     *
     * @param color {@link ColorInt}
     * @return current class
     * @since 1.6
     */
    public ListDialog setListItemSelectedBackgroundColor(@ColorInt int color) {
        listItemBgColorSelected = color;
        return this;
    }

    /**
     * Set text color for item in a list
     *
     * @param listItemTextColor color for a text
     * @return current class
     * @since 1.6
     */
    public ListDialog setListItemTextColor(int listItemTextColor) {
        this.listItemTextColor = listItemTextColor;
        return this;
    }

    private void setEmptyListTxtColor(int color) {
        emptyListTxt.setTextColor(color);
    }

    /**
     * Hide {@link #emptyListTxt} when list is empty
     * @since 1.6
     * @return current class
     */
    public ListDialog hideEmptyListText() {
        hideEmptyListTxt = true;
        return this;
    }

    /**
     * Set text to  display when list is empty
     * @param text Text
     * @since 1.6
     * @return current class
     */
    public ListDialog setEmptyListText(String text){
        emptyListTxt.setText(text);
        return this;
    }

    /**
     * Get a dialog list
     *
     * @return dialog list
     * @since 1.5
     */
    public RecyclerView getRecycleView() {
        return listRV;
    }

    @Override
    protected int setButtonsRootLayoutID() {
        return R.id.buttons;
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

    /**
     * Set items for a list
     *
     * @param listOfItems Array of {@link String}
     * @return current class
     * @since 1.5
     */
    public ListDialog setItems(String[] listOfItems) {
        return setItems(listOfItems, (ListItemClick) null);
    }


    /**
     * Set items for a list and ListItemClick event
     *
     * @param listOfItems Array of {@link String}
     * @param itemClick   ListItemClick event
     * @return current class
     * @since 1.5
     */
    public ListDialog setItems(String[] listOfItems, @Nullable ListItemClick itemClick) {

        if (hasAdapter)
            throw tooManyAdapters;

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick(ListItemClick.class);

        checkListsSize(listOfItems.length);

        adapter = new DefaultListAdapter(listOfItems,
                isSelectableList,
                itemClick,
                (ArrayList<String>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor,
                listItemBgColor,
                listItemBgColorSelected);
        hasAdapter = true;
        return this;
    }

    /**
     * Set items for a list and String value of an object for use in a list
     *
     * @param objArray Array of {@link Object}
     * @param value    {@link ListItemValue} for getting String value of an object
     * @param <T>      Type of an Object
     * @return current class
     * @since 1.5
     */
    public <T> ListDialog setItems(T[] objArray, ListItemValue<T> value) {
        return setItems(objArray, value, null);
    }

    /**
     * Set items for a list and String values of an object for use in a list
     *
     * @param objArray Array of {@link Object}
     * @param values   {@link ListItemValues} for getting String values of an object. {@link ListItemValues#getValue1(Object)}
     *                 for first value and {@link ListItemValues#getValue1(Object)} for second value
     * @param <T>      Type of an Object
     * @return current class
     * @since 1.5
     */
    public <T> ListDialog setItems(T[] objArray, ListItemValues<T> values) {
        return setItems(objArray, values, null);
    }


    /**
     * Set items for a list and String value of an object for use in a list
     *
     * @param arrayList {@link ArrayList} of {@link Object}
     * @param value     {@link ListItemValue} for getting String value of an object
     * @param <T>       Type of an Object
     * @return current class
     * @since 1.5
     */
    public <T> ListDialog setItems(ArrayList<T> arrayList, ListItemValue<T> value) {
        return setItems(arrayList, value, null);
    }


    /**
     * Set items for a list and String values of an object for use in a list
     *
     * @param arrayList {@link ArrayList} of {@link Object}
     * @param values    {@link ListItemValues} for getting String values of an object. {@link ListItemValues#getValue1(Object)}
     *                  for first value and {@link ListItemValues#getValue1(Object)} for second value
     * @param <T>       Type of an Object
     * @return current class
     * @since 1.5
     */
    public <T> ListDialog setItems(ArrayList<T> arrayList, ListItemValues<T> values) {
        return setItems(arrayList, values, null);
    }


    /**
     * Set items for a list, String value of an object for use in a list and ListItemClick event
     *
     * @param objArray  Array of {@link Object}
     * @param value     {@link ListItemValue} for getting String value of an object
     * @param itemClick ListItemClick event
     * @param <T>       Type of an Object
     * @return current class
     * @since 1.5
     */
    public <T> ListDialog setItems(T[] objArray, ListItemValue<T> value, @Nullable ListItemClickObj<T> itemClick) {

        if (hasAdapter)
            throw tooManyAdapters;

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick(ListItemClickObj.class);

        checkListsSize(objArray.length);

        adapter = new DefaultListAdapterGeneric<>(objArray,
                value,
                isSelectableList,
                itemClick,
                (ArrayList<T>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor,
                listItemBgColor,
                listItemBgColorSelected);
        hasAdapter = true;
        return this;
    }

    /**
     * Set items for a list, String values of an object for use in a list and ListItemClick event
     *
     * @param objArray  Array of {@link Object}
     * @param values    {@link ListItemValues} for getting String values of an object. {@link ListItemValues#getValue1(Object)}
     *                  for first value and {@link ListItemValues#getValue1(Object)} for second value
     * @param itemClick ListItemClick event
     * @param <T>       Type of an Object
     * @return current class
     * @since 1.5
     */
    public <T> ListDialog setItems(T[] objArray, ListItemValues<T> values, @Nullable ListItemClickObj<T> itemClick) {

        if (hasAdapter)
            throw tooManyAdapters;

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick(ListItemClickObj.class);

        checkListsSize(objArray.length);

        adapter = new DefaultListAdapterGeneric<>(objArray,
                values,
                isSelectableList,
                itemClick,
                (ArrayList<T>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor,
                listItemBgColor,
                listItemBgColorSelected);
        hasAdapter = true;
        return this;

    }


    /**
     * Set items for a list, String value of an object for use in a list and ListItemClick event
     *
     * @param arrayList {@link ArrayList} of {@link Object}
     * @param value     {@link ListItemValue} for getting String value of an object
     * @param itemClick ListItemClick event
     * @param <T>       Type of an Object
     * @return current class
     * @since 1.5
     */
    public <T> ListDialog setItems(ArrayList<T> arrayList, ListItemValue<T> value, @Nullable ListItemClickObj<T> itemClick) {

        if (hasAdapter)
            throw tooManyAdapters;

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick(ListItemClickObj.class);

        checkListsSize(arrayList.size());

        adapter = new DefaultListAdapterGeneric<>(arrayList,
                value,
                isSelectableList,
                itemClick,
                (ArrayList<T>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor,
                listItemBgColor,
                listItemBgColorSelected);
        hasAdapter = true;
        return this;
    }

    /**
     * Set items for a list, String values of an object for use in a list and ListItemClick event
     *
     * @param arrayList {@link ArrayList} of {@link Object}
     * @param values    {@link ListItemValues} for getting String values of an object. {@link ListItemValues#getValue1(Object)}
     *                  for first value and {@link ListItemValues#getValue1(Object)} for second value
     * @param itemClick ListItemClick event
     * @param <T>       Type of an Object
     * @return current class
     * @since 1.5
     */
    public <T> ListDialog setItems(ArrayList<T> arrayList, ListItemValues<T> values, @Nullable ListItemClickObj<T> itemClick) {

        if (hasAdapter)
            throw tooManyAdapters;

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick(ListItemClickObj.class);

        checkListsSize(arrayList.size());

        adapter = new DefaultListAdapterGeneric<>(arrayList,
                values,
                isSelectableList,
                itemClick,
                (ArrayList<T>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor,
                listItemBgColor,
                listItemBgColorSelected);
        hasAdapter = true;
        return this;
    }

    /**
     * Set image items for a list
     * @param arrayList {@link ArrayList} of {@link ImageListItem}
     * @return current class
     * @since 1.6
     */
    public ListDialog setImageItems(ArrayList<ImageListItem> arrayList) {
        return setImageItems(arrayList, null);
    }

    /**
     * Set image items for a list and ListItemClick event
     * @param arrayList {@link ArrayList} of {@link ImageListItem}
     * @param itemClick ListItemClick event
     * @return current class
     * @since 1.6
     */
    public ListDialog setImageItems(ArrayList<ImageListItem> arrayList, @Nullable ListItemClickObj<ImageListItem> itemClick) {

        if (hasAdapter)
            throw tooManyAdapters;

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick(ListItemClickObj.class);

        checkListsSize(arrayList.size());

        adapter = new DefaultImageListAdapter(arrayList,
                isSelectableList,
                itemClick,
                (ArrayList<ImageListItem>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor,
                listItemBgColor,
                listItemBgColorSelected);

        hasAdapter = true;
        waitForLayoutManager = true;
        listRV.post(() -> {
            int maxItemCount = listRV.getWidth() / functions.dpToPixels(context,80);
            if (maxItemCount == 0)
                maxItemCount = 1;
            setLayoutManager(new GridLayoutManager(context, arrayList.size()!=0 ? Math.min(maxItemCount, arrayList.size()):1));
            listRV.setLayoutManager(layoutManager);
            waitForLayoutManager = false;
        });

        return this;
    }

    /**
     * Set a list adapter
     *
     * @param adapter RecycleView {@link RecyclerView.Adapter adapter}
     * @return current class
     * @since 1.5
     */
    public ListDialog setAdapter(RecyclerView.Adapter<?> adapter) {
        if (hasAdapter)
            throw tooManyAdapters;
        this.adapter = adapter;
        hasAdapter = true;
        return this;
    }

    /**
     * @param layoutManager {@link RecyclerView.LayoutManager LayoutManager }
     * @return current class
     * @since 1.6
     */
    public ListDialog setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    /**
     * Get a list adapter
     *
     * @return current class
     * @since 1.5
     */
    public RecyclerView.Adapter<?> getListAdapter() {
        return adapter;
    }

    public void updateList(){
        adapter.notifyItemRangeChanged(0,adapter.getItemCount());
    }


    /**
     * @return Background resource for views in RecycleView
     * @since 1.5
     */
    public int getListItemBgRes() {
        return listItemBgRes;
    }

    /**
     * @return Background resource for selected views in RecycleView
     * @since 1.5
     */
    public int getListItemBgResSelected() {
        return listItemBgResSelected;
    }

    /**
     * Create list with multi selectable items. Use {@link #getSelectedItems()} fet getting selected items
     *
     * @return current class
     * @since 1.5
     */
    public ListDialog setSelectableList() {
        isSelectableList = true;
        return this;
    }

    /**
     * Select an item in a list
     *
     * @param id id of a item
     * @return current class
     * @since 1.6.1
     */
    public ListDialog selectItem(int id){

        if (adapter == null)
            throw nullAdapterException;

        if (adapter instanceof DefaultListAdapterGeneric){
            ((DefaultListAdapterGeneric<?>) adapter).selectItem(id);
            return this;
        }
        if (adapter instanceof DefaultListAdapter){
            ((DefaultListAdapter) adapter).selectItem(id);
            return this;
        }
        if (adapter instanceof DefaultImageListAdapter){
            ((DefaultImageListAdapter) adapter).selectItem(id);
            return this;
        }

        throw AdapterNotSupportedException;
    }

    /**
     * Get a index of a selected item in a list
     *
     * @return index of a selected item in a list
     * @since 1.6.1
     */
    public int getSelectedItem(){
        if (adapter == null)
            throw nullAdapterException;

        if (adapter instanceof DefaultListAdapterGeneric)
            return ((DefaultListAdapterGeneric<?>) adapter).getSelectedItem();
        if (adapter instanceof DefaultListAdapter)
            return ((DefaultListAdapter) adapter).getSelectedItem();

        throw  AdapterNotSupportedException;
    }

    public boolean isSelectableList() {
        return isSelectableList;
    }

    @Override
    public ListDialog setOnTouchListener(View.OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
        return this;
    }

    @Override
    public ListDialog swipeToDismiss(boolean isSwipeToDismiss) {
        super.swipeToDismiss(isSwipeToDismiss);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6.1*/
    @Override
    public ListDialog applyInsets(boolean applyInsets) {
        super.applyInsets(applyInsets);
        return this;
    }

    /**{@inheritDoc}
     * @since 1.6.1*/
    @Override
    public ListDialog applyInsets(int insets) {
        super.applyInsets(insets);
        return this;
    }

    /**
     * Apply presets on a dialog
     * @param presets {@link DialogPreset presets} that will be applied to the dialog
     * @return current class
     * @since 1.7
     * */
    public ListDialog setPresets(DialogPreset<ListDialog> presets){
        presets.addPresets(this);
        return this;
    }

    /**
     * Get selected items in a list
     *
     * @return ArrayList of selected items in a list
     * @since 1.5
     */
    public <T> ArrayList<T> getSelectedItems() {
        return (ArrayList<T>) selectedItems;
    }



    void checkListsSize(int size) {

        if (hideEmptyListTxt) {
            emptyListTxt.setVisibility(View.GONE);
            return;
        }
        emptyListTxt.setVisibility((size > 0) ? View.GONE : View.VISIBLE);


    }

    @SuppressWarnings("rawtypes")
    private NullPointerException nullListItemClick(Class className) {
        return new NullPointerException(
                String.format("%s is null. Set %s event or use setSelectableList() for selecting multiple item in a list",
                        className.getSimpleName(),
                        className.getSimpleName())
        );

    }

    private final UnsupportedOperationException tooManyAdapters = new UnsupportedOperationException("Too many Adapters for RecyclerView");
    private final UnsupportedOperationException AdapterNotSupportedException = new UnsupportedOperationException("Current adapter for the ListDialog is not supported for this method");
    private final NullPointerException nullAdapterException = new NullPointerException("The adapter for the ListDialog is null");
}
