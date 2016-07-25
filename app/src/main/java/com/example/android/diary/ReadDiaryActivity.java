package com.example.android.diary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.android.diary.data.DiaryContract;
import com.example.android.diary.data.DiaryDbHelper;

public class ReadDiaryActivity extends AppCompatActivity {

    private TextView diaryView;
    private DiaryDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_diary);
        diaryView = (TextView) this.findViewById(R.id.textView);
        diaryView.setMovementMethod(new ScrollingMovementMethod());
        db = new DiaryDbHelper(this);
        Cursor data = db.getAllEntries();
        String allText = "";
        while (data.moveToNext()) {
            String timestamp = data.getString(data.getColumnIndex(DiaryContract.DiaryEntry.COLUMN_DATETIME));
            String entry = data.getString(data.getColumnIndex(DiaryContract.DiaryEntry.COLUMN_ENTRY));
            allText += timestamp + ":\n" + entry + "\n\n";
        }
        data.close();
        diaryView.setText(allText);
    }
}
