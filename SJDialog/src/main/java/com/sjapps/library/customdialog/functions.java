package com.sjapps.library.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class functions {
    public static void SetDialogSize(@NonNull Context context, Dialog dialog, int maxWidth){
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration.screenWidthDp > maxWidth)
            dialog.getWindow().setLayout(dpToPixels(context,maxWidth), ViewGroup.LayoutParams.WRAP_CONTENT);
        else dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }
    private static int dpToPixels(@NonNull Context context, float dp) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
