package com.sjapps.library.customdialog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.library.R;
import com.sjapps.library.customdialog.list.events.ListItemClick;

import java.util.ArrayList;

public class DefaultListAdapter extends RecyclerView.Adapter<DefaultListAdapterGeneric.ViewHolder> {

    String[] items;
    boolean isSelectable;
    ListItemClick itemClick;
    ArrayList<String> selectedItems;
    int itemBgRes;
    int itemBgResSelected;
    int textColor;


    public DefaultListAdapter(String[] items,
                              boolean isSelectable,
                              ListItemClick itemClick,
                              ArrayList<String> selectedItems,
                              int itemBgRes,
                              int itemBgResSelected,
                              int textColor) {
        this.items = items;
        this.isSelectable = isSelectable;
        this.itemClick = itemClick;
        this.selectedItems = selectedItems;
        this.itemBgRes = itemBgRes;
        this.itemBgResSelected = itemBgResSelected;
        this.textColor = textColor;


    }

    @NonNull
    @Override
    public DefaultListAdapterGeneric.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_list_item,parent,false);
        return new DefaultListAdapterGeneric.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultListAdapterGeneric.ViewHolder holder, int position) {
        holder.getVal1Txt().setText(items[position]);

        if (selectedItems.contains(items[position]))
            setItemColor(holder,itemBgResSelected);
        else setItemColor(holder,itemBgRes);

        if (textColor != 1)
            holder.getVal1Txt().setTextColor(textColor);

        if (isSelectable) {
            holder.getView().setOnClickListener(v -> {
                if (!selectedItems.contains(items[position]))
                    selectItem(holder,position);
                else deselectItem(holder, position);
            });
            return;
        }

        holder.getView().setOnClickListener(v -> itemClick.onClick(position,items[position]));

    }

    private void selectItem(@NonNull DefaultListAdapterGeneric.ViewHolder holder, int position){
        selectedItems.add(items[position]);
        setItemColor(holder,itemBgResSelected);

    }

    private void deselectItem(@NonNull DefaultListAdapterGeneric.ViewHolder holder, int position){
        selectedItems.remove(items[position]);
        setItemColor(holder,itemBgRes);
    }

    private void setItemColor(@NonNull DefaultListAdapterGeneric.ViewHolder holder, int drawable){
        holder.getView().setBackgroundResource(drawable);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

}
