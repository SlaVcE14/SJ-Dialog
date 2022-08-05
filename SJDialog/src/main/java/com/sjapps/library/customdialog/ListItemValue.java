package com.sjapps.library.customdialog;

public interface ListItemValue<T> {
    /**
     * Get String value of an Object for using in a list
     * @param obj Current Object for getting String value of it
     * @return String value
     */
    String getValue(T obj);
}
