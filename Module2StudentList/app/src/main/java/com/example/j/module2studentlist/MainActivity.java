package com.example.j.module2studentlist;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    Button btnSearch, btnSend;
    EditText edtEmail, edtSubject, edtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSend = (Button) findViewById(R.id.btnSend);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSubject = (EditText) findViewById(R.id.edtSubject);
        edtMessage = (EditText) findViewById(R.id.edtMessage);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SelectStudentActivity.class));
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emptyCheck()){
                    //code voor versturen email
                    String[] TO = {edtEmail.getText().toString()};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, edtSubject.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, edtMessage.getText().toString());
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        finish();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Controleert of bundle leeg is, zoniet vult die email in uit lijstselectie
        Bundle b = this.getIntent().getExtras();
        if(b != null)
        {
            for(Student std : Student.getStudentList()){
                if(std.getStudentnr().equals(b.getString("StudNr"))){
                    edtEmail.setText(std.getEmail());
                }
            }
        }
    }
    public boolean emptyCheck(){
        //Controle of checkboxes in layout leeg zijn, zo ja, dan geeft die een Toast
        RelativeLayout layoutMain = (RelativeLayout) findViewById(R.id.layoutMain);
        for( int i = 0; i < layoutMain.getChildCount(); i++ ){
            if(layoutMain.getChildAt(i) instanceof EditText)
            {
                EditText txt = (EditText)layoutMain.getChildAt(i);
                if(TextUtils.isEmpty(txt.getText())){
                    Toast.makeText(this, "Vul alle gegevens in", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }
}
