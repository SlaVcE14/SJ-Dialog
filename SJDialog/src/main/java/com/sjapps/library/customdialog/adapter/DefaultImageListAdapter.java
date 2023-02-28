package com.sjapps.library.customdialog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.library.R;
import com.sjapps.library.customdialog.ImageListItem;
import com.sjapps.library.customdialog.list.events.ListItemClickObj;

import java.util.ArrayList;

public class DefaultImageListAdapter extends RecyclerView.Adapter<DefaultImageListAdapter.ViewHolder> {

    public ArrayList<ImageListItem> arrayListItems;

    boolean isSelectable;

    int itemBgRes;
    int itemBgResSelected;
    int textColor;

    ListItemClickObj<ImageListItem> itemClick;
    ArrayList<ImageListItem> selectedItems;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView valTxt;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            valTxt = itemView.findViewById(R.id.value1Txt);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public TextView getTxt() {
            return valTxt;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public View getView() {
            return itemView.findViewById(R.id.layoutItem);
        }

    }

    public DefaultImageListAdapter(ArrayList<ImageListItem> arrayList,
                                   boolean isSelectable,
                                   @Nullable ListItemClickObj<ImageListItem> itemClick,
                                   ArrayList<ImageListItem> selectedItems,
                                   int itemBgRes,
                                   int itemBgResSelected,
                                   int textColor) {
        this.itemClick = itemClick;
        this.isSelectable = isSelectable;
        this.selectedItems = selectedItems;
        this.itemBgRes = itemBgRes;
        this.itemBgResSelected = itemBgResSelected;
        this.textColor = textColor;
        this.arrayListItems = arrayList;
    }


    @NonNull
    @Override
    public DefaultImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_image_list_item, parent, false);
        return new DefaultImageListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setValues(holder, position);
        setBackground(holder, position);

        if (textColor != 1) {
            holder.getTxt().setTextColor(textColor);
        }

        if (isSelectable) {
            holder.getView().setOnClickListener(v ->
                checkSelected(holder, arrayListItems.get(position))
            );

            return;
        }

        holder.getView().setOnClickListener(v -> itemClick.onClick(position, arrayListItems.get(position)));

    }

    private void setBackground(@NonNull ViewHolder holder, int position) {
        if (!isSelectable) {
            setItemColor(holder, itemBgRes);
            return;
        }

        if (selectedItems.contains(arrayListItems.get(position)))
            setItemColor(holder, itemBgResSelected);
        else setItemColor(holder, itemBgRes);

    }

    private void setValues(@NonNull ViewHolder holder, int position) {
        holder.getTxt().setText(arrayListItems.get(position).getName());
        holder.getImageView().setImageDrawable(arrayListItems.get(position).getImage());

    }

    private void checkSelected(@NonNull ViewHolder holder,ImageListItem obj) {
        if (!selectedItems.contains(obj))
            selectItem(holder, obj);
        else deselectItem(holder, obj);
    }

    private void selectItem(@NonNull ViewHolder holder,ImageListItem obj) {
        selectedItems.add(obj);
        setItemColor(holder, itemBgResSelected);

    }

    private void deselectItem(@NonNull ViewHolder holder,ImageListItem obj) {
        selectedItems.remove(obj);
        setItemColor(holder, itemBgRes);

    }

    private void setItemColor(@NonNull ViewHolder holder, int drawable) {
        holder.getView().setBackgroundResource(drawable);
    }

    @Override
    public int getItemCount() {
        return arrayListItems.size();
    }
}
