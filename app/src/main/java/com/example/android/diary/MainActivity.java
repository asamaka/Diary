package com.example.android.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.diary.data.DiaryDbHelper;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView entryView;
    private DiaryDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entryView = (TextView) this.findViewById(R.id.editText);
        db = new DiaryDbHelper(this);
    }


    public void OnReadClicked(View view) {
        startActivity(new Intent(this, ReadDiaryActivity.class));
    }

    public void OnSaveClicked(View view) {
        Date date = new Date();
        String entry = entryView.getText().toString();
        db.addNewEntry(date, entry);
        startActivity(new Intent(this, ReadDiaryActivity.class));
    }
}
