package com.example.j.module2studentlist;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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


        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtStud = (TextView) view.findViewById(R.id.txtStudNr);
                startActivity(new Intent(getApplicationContext(), MapsActivity.class).putExtra("StudNr", txtStud.getText().toString()));
                return true;
            }
        });
    }



}
