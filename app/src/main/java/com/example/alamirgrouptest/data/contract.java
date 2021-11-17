package com.example.alamirgrouptest.data;

import android.provider.BaseColumns;

public class contract {

    private contract(){}

    public static final class usertable implements BaseColumns
    {
        public static final String TABLE_NAME = "users";

        public static final String COLUMN_USER_ID = BaseColumns._ID;
        public static final String COLUMN_USER_USERNAME ="username";
        public static final String COLUMN_USER_PHONE ="phone";
        public static final String COLUMN_USER_GENDER ="gender";
    }
}
