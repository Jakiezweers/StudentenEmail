package com.example.j.module2studentlist;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.j.module2studentlist.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class SelectStudentActivity extends AppCompatActivity {

    ListView lvList;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_student);
        db = new DBHandler(this);

        lvList = (ListView) findViewById(R.id.lstStudent);
        lvList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvList.setAdapter(new studentAdapter(this, Student.getStudentList()));


        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtStud = (TextView) view.findViewById(R.id.txtStudNr);
                String studnr = txtStud.getText().toString();
                startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("StudNr", studnr));
            }
        });
    }
}
