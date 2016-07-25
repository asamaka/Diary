package com.example.android.diary.data;

import android.provider.BaseColumns;

public class DiaryContract {

    /* Inner class that defines the table contents of the diary table */
    public static final class DiaryEntry implements BaseColumns {

        public static final String TABLE_NAME = "Diary";

        public static final String COLUMN_DATETIME = "DateTime";

        public static final String COLUMN_ENTRY = "Entry";

    }
}
