package com.example.j.module2studentlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "student";
    // Contacts table name



    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE student (ID INTEGER PRIMARY KEY, Naam VARCHAR (255), Tussenvoegsel VARCHAR (255), Achternaam VARCHAR (255), Email VARCHAR (255), Postcode VARCHAR(255), Plaats VARCHAR(255), Klas VARCHAR(255), Studentnr VARCHAR(255);";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + "student");
// Creating tables again
        onCreate(db);
    }

    //(ID INTEGER PRIMARY KEY, Naam VARCHAR (255), Tussenvoegsel VARCHAR (255), Achternaam VARCHAR (255),
    // Email VARCHAR (255),
    // Postcode VARCHAR(255), Plaats VARCHAR(255), Klas VARCHAR(255), Studentnr VARCHAR(255);";
    public void addStudent(Student stud) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Naam", stud.getNaam());
        values.put("Tussenvoegsel", stud.getTussenvoegsel());
        values.put("Achternaam", stud.getAchternaam());
        values.put("Email", stud.getEmail());
        values.put("Postcode", stud.getPostcode());
        values.put("Plaats", stud.getPlaats());
        values.put("Klas", stud.getKlas());
        values.put("Studentnr", stud.getStudentnr());
        db.insert("student", null, values);
        db.close(); // Closing database connection
    }


    public List<Student> getAllFavorites() {
        List<Student> favList = new ArrayList<Student>();

//        for(int i = 0; i < 10; i++){
//            Favorites fav = new Favorites(i, "http://www.google.nl", "Omschrijving", "Nieuws", "date");
//            favList.add(fav);
//        }

//        String selectQuery = "SELECT * FROM klasAppDb";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                Favorites fav = new Favorites();
//                fav.setId(Integer.parseInt(cursor.getString(0)));
//                fav.setHyperlink(cursor.getString(1));
//                fav.setOmschrijving(cursor.getString(2));
//                fav.setCatogorie(cursor.getString(3));
//                fav.setDatumtoegevoegd(cursor.getString(4));
//                favList.add(fav);
//            } while (cursor.moveToNext());
//        }
        return favList;
    }

    public int getFavoritesCount() {
        String countQuery = "SELECT * FROM " + "fav";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }
}

