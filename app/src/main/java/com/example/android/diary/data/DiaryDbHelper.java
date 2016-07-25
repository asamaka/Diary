package com.example.android.diary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.diary.data.DiaryContract.DiaryEntry;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DiaryDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "diary.db";

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public DiaryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold data.
        final String SQL_CREATE_QUEUE_TABLE = "CREATE TABLE " + DiaryEntry.TABLE_NAME + " (" +
                DiaryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DiaryEntry.COLUMN_DATETIME + " DATETIME NOT NULL, " +
                DiaryEntry.COLUMN_ENTRY + " NVARCHAR(10000) NOT NULL); ";

        sqLiteDatabase.execSQL(SQL_CREATE_QUEUE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DiaryEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public Cursor getAllEntries() {
        return getReadableDatabase().query(
                DiaryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DiaryEntry.COLUMN_DATETIME + " DESC"
        );
    }

    public long addNewEntry(Date timestamp, String entry) {
        final SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String timestampString = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp);
        cv.put(DiaryEntry.COLUMN_DATETIME, timestampString);
        cv.put(DiaryEntry.COLUMN_ENTRY, entry);
        long _id = db.insert(DiaryEntry.TABLE_NAME, null, cv);
        return _id;
    }

    public boolean removeEntry(long id) {
        final SQLiteDatabase db = getWritableDatabase();
        return db.delete(DiaryEntry.TABLE_NAME, DiaryEntry._ID + "=" + id, null) > 0;
    }
}