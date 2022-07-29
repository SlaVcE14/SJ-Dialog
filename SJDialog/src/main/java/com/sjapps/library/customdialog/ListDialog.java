package com.sjapps.library.customdialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.library.R;
import com.sjapps.library.customdialog.adapter.DefaultListAdapter;
import com.sjapps.library.customdialog.adapter.DefaultListAdapterGeneric;
import com.sjapps.library.customdialog.list.events.ListItemClick;
import com.sjapps.library.customdialog.list.events.ListItemClickObj;

import java.util.ArrayList;

@SuppressWarnings({"unused","unchecked"})
public class ListDialog extends SJDialog{

    RecyclerView listRV;
    boolean isSelectableList = false;

    private @DrawableRes int listItemBgRes = R.drawable.ripple_list;
    private @DrawableRes int listItemBgResSelected = R.drawable.ripple_list_selected;
    private int listItemTextColor = 1;

    RecyclerView.Adapter<?> adapter;
    ArrayList<?> selectedItems = new ArrayList<>();

    public ListDialog(){

    }

    public ListDialog Builder(Context context){
        return Builder(context,false);
    }

    public ListDialog Builder(Context context,@StyleRes int theme){
        super.Builder(context,R.layout.list_dialog,theme, false);
        listRV = dialog.findViewById(R.id.list);
        onLeftButtonClick(dialog::dismiss);
        return this;
    }

    public ListDialog Builder(Context context,boolean useAppTheme){
        super.Builder(context,R.layout.list_dialog,useAppTheme);
        listRV = dialog.findViewById(R.id.list);
        onLeftButtonClick(dialog::dismiss);
        return this;
    }

    @Override
    public ListDialog setOldTheme(){
        super.setOldTheme();
        setListOldColor();
        return this;
    }

    @Override
    public ListDialog setTitle(String title){
        super.setTitle(title);
        return this;
    }

    @Override
    public ListDialog setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    /**
     * Set a button text.
     *
     * @param text button text
     * @return current class
     * */
    public ListDialog setButtonText(String text){
        return setLeftButtonText(text);
    }

    @Override
    public ListDialog setLeftButtonText(String text){
        super.setLeftButtonText(text);
        return this;
    }

    @Override
    public ListDialog setRightButtonText(String text){
        super.setRightButtonText(text);
        return this;
    }

    /**
     * Set button text color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public ListDialog setButtonTextColor(@ColorInt int color){
        return setLeftButtonTextColor(color);
    }

    @Override
    public ListDialog setButtonsTextColor(@ColorInt int color){
        super.setButtonsTextColor(color);
        return this;
    }

    @Override
    public ListDialog setLeftButtonTextColor(@ColorInt int color){
        super.setLeftButtonTextColor(color);
        return this;
    }

    @Override
    public ListDialog setRightButtonTextColor(@ColorInt int color){
        super.setRightButtonTextColor(color);
        return this;
    }

    /**
     * Set button color.
     * @param color Color to use for tinting this drawable
     * @return current class
     * */
    public ListDialog setButtonColor(@ColorInt int color){
        return setLeftButtonColor(color);
    }

    @Override
    public ListDialog setButtonsColor(@ColorInt int color){
        super.setButtonsColor(color);
        return this;
    }

    @Override
    public ListDialog setLeftButtonColor(@ColorInt int color){
        super.setLeftButtonColor(color);
        return this;
    }

    @Override
    public ListDialog setRightButtonColor(@ColorInt int color){
        super.setRightButtonColor(color);
        return this;
    }

    @Override
    public ListDialog setButtonColor(@ButtonColor String color) {
        super.setButtonColor(color);
        return this;
    }

    @Override
    public ListDialog setButtonsColor(@ButtonColor String color){
        super.setButtonsColor(color);
        return this;
    }

    @Override
    public ListDialog setLeftButtonColor(@ButtonColor String color){
        super.setLeftButtonColor(color);
        return this;
    }

    @Override
    public ListDialog setRightButtonColor(@ButtonColor String color){
        super.setRightButtonColor(color);
        return this;
    }

    @Override
    public ListDialog setDialogBackgroundResource(@DrawableRes int drawable){
        super.setDialogBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for button.
     * @param drawable resource id
     * @return current class
     * */
    public ListDialog setButtonBackgroundResource(@DrawableRes int drawable){
        return setLeftButtonBackgroundResource(drawable);
    }

    @Override
    public ListDialog setButtonsBackgroundResource(@DrawableRes int drawable){
        super.setButtonsBackgroundResource(drawable);
        return this;
    }

    @Override
    public ListDialog setLeftButtonBackgroundResource(@DrawableRes int drawable){
        super.setLeftButtonBackgroundResource(drawable);
        return this;
    }

    @Override
    public ListDialog setRightButtonBackgroundResource(@DrawableRes int drawable){
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
    public ListDialog onButtonClick(DialogButtonEvent dialogButtonEvent){
        if (twoButtons)
            super.onButtonClick(dialogButtonEvent);
        else
            super.onLeftButtonClick(dialogButtonEvent);
        return this;
    }

    @Override
    public ListDialog onButtonClick(DialogButtonEvents dialogButtonEvents) {
        super.onButtonClick(dialogButtonEvents);
        return this;
    }

    @Override
    public ListDialog show() {
        listRV.setLayoutManager(new LinearLayoutManager(context));
        listRV.setAdapter(adapter);

        super.show();
        return this;
    }

    @Override
    public ListDialog setMaxDialogWidth(int maxDialogWidth) {
        super.setMaxDialogWidth(maxDialogWidth);
        return this;
    }

    @Override
    public ListDialog setDialogAnimations(int styleRes) {
        super.setDialogAnimations(styleRes);
        return this;
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
     * @param drawable drawable resource
     * @return current class
     */
    public ListDialog setListBackgroundResource(@DrawableRes int drawable) {
        listRV.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Set background resource for a item in a list
     * @param drawable drawable resource
     * @return current class
     */
    public ListDialog setListItemBackgroundResource(@DrawableRes int drawable) {
        listItemBgRes = drawable;
        return this;
    }

    /**
     * Set background resource for a selected item in a list
     * @param drawable drawable resource
     * @return current class
     */
    public ListDialog setListItemSelectedBackgroundResource(@DrawableRes int drawable) {
        listItemBgResSelected = drawable;
        return this;
    }

    /**
     * Set text color for item in a list
     * @param listItemTextColor color for a text
     * @return current class
     */
    public ListDialog setListItemTextColor(int listItemTextColor) {
        this.listItemTextColor = listItemTextColor;
        return this;
    }

    /**
     * Get a dialog list
     * @return dialog list
     */
    public RecyclerView getRecycleView(){
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


    /**
     * Set items for a list
     * @param listOfItems Array of {@link String}
     * @return current class
     */
    public ListDialog setItems(String[] listOfItems) {
        return setItems(listOfItems, (ListItemClick) null);
    }


    /**
     * Set items for a list and ListItemClick event
     * @param listOfItems Array of {@link String}
     * @param itemClick ListItemClick event
     * @return current class
     */
    public ListDialog setItems(String[] listOfItems,@Nullable ListItemClick itemClick) {

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick;

        adapter = new DefaultListAdapter(listOfItems,
                isSelectableList,
                itemClick,
                (ArrayList<String>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,1);
        return this;
    }

    /**
     * Set items for a list and String value of an object for use in a list
     * @param objArray Array of {@link Object}
     * @param value {@link ListItemValue} for getting String value of an object
     * @param <T> Type of an Object
     * @return current class
     */
    public <T> ListDialog setItems(T[] objArray, ListItemValue<T> value){
        return setItems(objArray, value,null);
    }

    /**
     * Set items for a list and String values of an object for use in a list
     * @param objArray Array of {@link Object}
     * @param values {@link ListItemValues} for getting String values of an object. {@link ListItemValues#getValue1(Object)}
     *                                    for first value and {@link ListItemValues#getValue1(Object)} for second value
     * @param <T> Type of an Object
     * @return current class
     */
    public <T> ListDialog setItems(T[] objArray, ListItemValues<T> values){
        return setItems(objArray, values,null);
    }


    /**
     * Set items for a list and String value of an object for use in a list
     * @param arrayList {@link ArrayList} of {@link Object}
     * @param value {@link ListItemValue} for getting String value of an object
     * @param <T> Type of an Object
     * @return current class
     */
    public <T> ListDialog setItems(ArrayList<T> arrayList, ListItemValue<T> value){
        return setItems(arrayList, value, null);
    }


    /**
     * Set items for a list and String values of an object for use in a list
     * @param arrayList {@link ArrayList} of {@link Object}
     * @param values {@link ListItemValues} for getting String values of an object. {@link ListItemValues#getValue1(Object)}
     *                                    for first value and {@link ListItemValues#getValue1(Object)} for second value
     * @param <T> Type of an Object
     * @return current class
     */
    public <T> ListDialog setItems(ArrayList<T> arrayList, ListItemValues<T> values){
        return setItems(arrayList,values,null);
    }


    /**
     * Set items for a list, String value of an object for use in a list and ListItemClick event
     * @param objArray Array of {@link Object}
     * @param value {@link ListItemValue} for getting String value of an object
     * @param itemClick ListItemClick event
     * @param <T> Type of an Object
     * @return current class
     */
    public <T> ListDialog setItems(T[] objArray, ListItemValue<T> value, @Nullable ListItemClickObj<T> itemClick){

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick;

        adapter = new DefaultListAdapterGeneric<>(objArray,
                value,
                isSelectableList,
                itemClick,
                (ArrayList<T>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor);
        return this;
    }

    /**
     * Set items for a list, String values of an object for use in a list and ListItemClick event
     * @param objArray Array of {@link Object}
     * @param values {@link ListItemValues} for getting String values of an object. {@link ListItemValues#getValue1(Object)}
     *                                    for first value and {@link ListItemValues#getValue1(Object)} for second value
     * @param itemClick ListItemClick event
     * @param <T> Type of an Object
     * @return current class
     */
    public <T> ListDialog setItems(T[] objArray, ListItemValues<T> values, @Nullable ListItemClickObj<T> itemClick){

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick;

        adapter = new DefaultListAdapterGeneric<>(objArray,
                values,
                isSelectableList,
                itemClick,
                (ArrayList<T>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor);

        return this;

    }


    /**
     * Set items for a list, String value of an object for use in a list and ListItemClick event
     * @param arrayList {@link ArrayList} of {@link Object}
     * @param value {@link ListItemValue} for getting String value of an object
     * @param itemClick ListItemClick event
     * @param <T> Type of an Object
     * @return current class
     */
    public <T> ListDialog setItems(ArrayList<T> arrayList, ListItemValue<T> value, @Nullable ListItemClickObj<T> itemClick){

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick;

        adapter = new DefaultListAdapterGeneric<>(arrayList,
                value,
                isSelectableList,
                itemClick,
                (ArrayList<T>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor);
        return this;
    }

    /**
     * Set items for a list, String values of an object for use in a list and ListItemClick event
     * @param arrayList {@link ArrayList} of {@link Object}
     * @param values {@link ListItemValues} for getting String values of an object. {@link ListItemValues#getValue1(Object)}
     *                                    for first value and {@link ListItemValues#getValue1(Object)} for second value
     * @param itemClick ListItemClick event
     * @param <T> Type of an Object
     * @return current class
     */
    public <T> ListDialog setItems(ArrayList<T> arrayList, ListItemValues<T> values, @Nullable ListItemClickObj<T> itemClick){

        if (!isSelectableList && itemClick == null)
            throw nullListItemClick;

        adapter = new DefaultListAdapterGeneric<>(arrayList,
                values,
                isSelectableList,
                itemClick,
                (ArrayList<T>) selectedItems,
                listItemBgRes,
                listItemBgResSelected,
                listItemTextColor);
        return this;
    }

    /**
     * Set a list adapter
     * @param adapter RecycleView adapter
     * @return current class
     */
    public ListDialog setAdapter(RecyclerView.Adapter<?> adapter){
        this.adapter = adapter;
        return this;
    }

    /**
     * Get a list adapter
     * @return current class
     */
    public RecyclerView.Adapter<?> getListAdapter(){
        return adapter;
    }


    /**
     * @return Background resource for views in RecycleView
     */
    public int getListItemBgRes(){
        return listItemBgRes;
    }

    /**
     * @return Background resource for selected views in RecycleView
     */
    public int getListItemBgResSelected(){
        return listItemBgResSelected;
    }

    /**
     * Create list with multi selectable items. Use {@link #getSelectedItems()} fet getting selected items
     * @return current class
     */
    public ListDialog setSelectableList(){
        isSelectableList = true;
        return this;
    }

    public boolean isSelectableList(){
        return isSelectableList;
    }

    /**
     * Get selected items in a list
     * @return ArrayList of selected items in a list
     */
    public <T>  ArrayList<T> getSelectedItems(){
        return (ArrayList<T>) selectedItems;
    }

    private final NullPointerException nullListItemClick = new NullPointerException(
            "ListItemClick is null. Set ListItemClick event or use setSelectableList() for selecting multiple item in a list");
}
