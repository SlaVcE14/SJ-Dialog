package com.sjapps.library.customdialog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.library.R;
import com.sjapps.library.customdialog.ListItemValue;
import com.sjapps.library.customdialog.ListItemValues;
import com.sjapps.library.customdialog.list.events.ListItemClickObj;

import java.util.ArrayList;

public class DefaultListAdapterGeneric<T> extends RecyclerView.Adapter<DefaultListAdapterGeneric.ViewHolder> {

    public ArrayList<T> arrayListItems;
    public T[] listObj;

    private ListItemValue<T> value;
    private ListItemValues<T> values;

    boolean isArrList;
    boolean isObjArr;
    boolean hasTwoVal;
    boolean isSelectable;

    int itemBgRes;
    int itemBgResSelected;
    int textColor;

    ListItemClickObj<T> itemClick;
    ArrayList<T> selectedItems;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView val1Txt;
        TextView val2Txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            val1Txt = itemView.findViewById(R.id.value1Txt);
            val2Txt = itemView.findViewById(R.id.value2Txt);
        }

        public TextView getVal1Txt() {
            return val1Txt;
        }

        public TextView getVal2Txt() {
            return val2Txt;
        }

        public View getView(){
            return itemView.findViewById(R.id.layoutItem);
        }

    }

    public DefaultListAdapterGeneric(T[] objArray,
                                     ListItemValue<T> value,
                                     boolean isSelectable,
                                     @Nullable ListItemClickObj<T> itemClick,
                                     ArrayList<T> selectedItems,
                                     int itemBgRes,
                                     int itemBgResSelected,
                                     int textColor){
        this.value = value;
        this.itemClick = itemClick;
        this.isSelectable = isSelectable;
        this.selectedItems = selectedItems;
        this.itemBgRes = itemBgRes;
        this.itemBgResSelected = itemBgResSelected;
        this.textColor = textColor;
        ObjArrayAdapter(objArray);
    }

    public DefaultListAdapterGeneric(T[] objArray,
                                     ListItemValues<T> values,
                                     boolean isSelectable,
                                     @Nullable ListItemClickObj<T> itemClick,
                                     ArrayList<T> selectedItems,
                                     int itemBgRes,
                                     int itemBgResSelected,
                                     int textColor){
        this.values = values;
        this.itemClick = itemClick;
        hasTwoVal = true;
        this.isSelectable = isSelectable;
        this.selectedItems = selectedItems;
        this.itemBgRes = itemBgRes;
        this.itemBgResSelected = itemBgResSelected;
        this.textColor = textColor;
        ObjArrayAdapter(objArray);
    }

    public DefaultListAdapterGeneric(ArrayList<T> arrayList,
                                     ListItemValue<T> value,
                                     boolean isSelectable,
                                     @Nullable ListItemClickObj<T> itemClick,
                                     ArrayList<T> selectedItems,
                                     int itemBgRes,
                                     int itemBgResSelected,
                                     int textColor){
        this.value = value;
        this.itemClick = itemClick;
        this.isSelectable = isSelectable;
        this.selectedItems = selectedItems;
        this.itemBgRes = itemBgRes;
        this.itemBgResSelected = itemBgResSelected;
        this.textColor = textColor;
        ArrayListAdapter(arrayList);
    }

    public DefaultListAdapterGeneric(ArrayList<T> arrayList,
                                     ListItemValues<T> values,
                                     boolean isSelectable,
                                     @Nullable ListItemClickObj<T> itemClick,
                                     ArrayList<T> selectedItems,
                                     int itemBgRes,
                                     int itemBgResSelected,
                                     int textColor){
        this.values = values;
        this.itemClick = itemClick;
        hasTwoVal = true;
        this.isSelectable = isSelectable;
        this.selectedItems = selectedItems;
        this.itemBgRes = itemBgRes;
        this.itemBgResSelected = itemBgResSelected;
        this.textColor = textColor;
        ArrayListAdapter(arrayList);
    }

    private void ObjArrayAdapter(T[] objArray){
        this.listObj = objArray;
        isObjArr = true;

    }

    private void ArrayListAdapter(ArrayList<T> arrayList){
        this.arrayListItems = arrayList;
        isArrList = true;
    }

    @NonNull
    @Override
    public DefaultListAdapterGeneric.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultListAdapterGeneric.ViewHolder holder, int position) {
        setValues(holder,position);
        setBackground(holder,position);

        if (textColor != 1){
            holder.getVal1Txt().setTextColor(textColor);
            holder.getVal2Txt().setTextColor(textColor);
        }

        if (isSelectable){
            holder.getView().setOnClickListener(v -> {
                if (isArrList)
                    checkSelected(holder, arrayListItems.get(position));
                else if (isObjArr)
                    checkSelected(holder, listObj[position]);
            });

            return;
        }

        if (isArrList)
            holder.getView().setOnClickListener(v -> itemClick.onClick(position,arrayListItems.get(position)));

        if (isObjArr)
            holder.getView().setOnClickListener(v -> itemClick.onClick(position,listObj[position]));

    }

    private void setBackground(@NonNull DefaultListAdapterGeneric.ViewHolder holder, int position) {
        if (!isSelectable) {
            setItemColor(holder,itemBgRes);
            return;
        }
        if (isArrList) {
            if (selectedItems.contains(arrayListItems.get(position)))
                setItemColor(holder, itemBgResSelected);
            else setItemColor(holder, itemBgRes);
            return;
        }
        if (isObjArr)
            if (selectedItems.contains(listObj[position]))
                setItemColor(holder,itemBgResSelected);
            else setItemColor(holder, itemBgRes);
    }

    private void setValues(@NonNull DefaultListAdapterGeneric.ViewHolder holder, int position){
        if (isArrList){
            if (!hasTwoVal) {
                holder.getVal1Txt().setText(value.getValue(arrayListItems.get(position)));
                return;
            }
            holder.getVal1Txt().setText(values.getValue1(arrayListItems.get(position)));
            holder.getVal2Txt().setText(values.getValue2(arrayListItems.get(position)));
            holder.getVal2Txt().setVisibility(View.VISIBLE);
            return;
        }
        if (isObjArr) {
            if (!hasTwoVal) {
                holder.getVal1Txt().setText(value.getValue(listObj[position]));
                return;
            }
            holder.getVal1Txt().setText(values.getValue1(listObj[position]));
            holder.getVal2Txt().setText(values.getValue2(listObj[position]));
            holder.getVal2Txt().setVisibility(View.VISIBLE);
        }
    }

    private void checkSelected(@NonNull DefaultListAdapterGeneric.ViewHolder holder, T obj){
        if (!selectedItems.contains(obj))
            selectItem(holder,obj);
        else deselectItem(holder,obj);
    }

    private void selectItem(@NonNull DefaultListAdapterGeneric.ViewHolder holder, T obj){
        selectedItems.add(obj);
        setItemColor(holder, itemBgResSelected);

    }

    private void deselectItem(@NonNull DefaultListAdapterGeneric.ViewHolder holder, T obj){
        selectedItems.remove(obj);
        setItemColor(holder,itemBgRes);

    }

    private void setItemColor(@NonNull DefaultListAdapterGeneric.ViewHolder holder, int drawable){
        holder.getView().setBackgroundResource(drawable);
    }

    @Override
    public int getItemCount() {
        if (isArrList)
            return arrayListItems.size();
        if (isObjArr)
            return listObj.length;
        return 0;
    }
}
