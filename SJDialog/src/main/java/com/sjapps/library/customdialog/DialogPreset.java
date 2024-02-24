package com.sjapps.library.customdialog;

/**
 * Presets for the {@link SJDialog SJDialogs}
 * @see SJDialog
 * @see BasicDialog
 * @see MessageDialog
 * @see ListDialog
 * @see CustomViewDialog
 * */
public interface DialogPreset<D extends SJDialog> {
    /**
     * Add presets for the dialog
     * @param dialog presets will be applied to that dialog
     * */
    void addPresets(D dialog);
}
