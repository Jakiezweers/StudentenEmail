package com.example.j.module2studentlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by J on 9/23/2016.
 */
public class Student {
    private String naam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    private String postcode;
    private String plaats;
    private String klas;
    private String studentnr;


    public Student(){}

    public Student(String naam, String tussenvoegsel, String achternaam, String email, String postcode, String plaats, String klas, String studentnr) {
        this.naam = naam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.email = email;
        this.postcode = postcode;
        this.plaats = plaats;
        this.klas = klas;
        this.studentnr = studentnr;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStudentnr() {
        return studentnr;
    }

    public void setStudentnr(String studentnr) {
        this.studentnr = studentnr;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        this.klas = klas;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public static ArrayList<Student> getStudentList(){
        ArrayList<Student> studList = new ArrayList<>();
        studList.add(new Student("Laurens", "", "Tel", "ltel01@student.rocvantwente.nl", "7577 MD", "Oldenzaal", "I4AO1", "0179028"));
        studList.add(new Student("Tim", "", "Bruntink", "tbruntink01@student.rocvantwente.nl", "7556 DS", "Hengelo", "I4AO1", "0267599"));
        studList.add(new Student("Kelvin", "", "Cornelissens", "kcornelissens01@student.rocvantwente.nl", "7622 KV", "Borne", "I4AO1", "0267772"));
        studList.add(new Student("Martijn", "", "Dekker", "mdekker04@student.rocvantwente.nl", "7471 ZJ", "Goor", "I4AO1", "0256907"));
        studList.add(new Student("Dylan", "", "Doornbos", "ddoornbos01@student.rocvantwente.nl", "7545 KH", "Enschede", "I4AO1", "0265788"));
        studList.add(new Student("Bart", "van", "Es", "bvanes01@student.rocvantwente.nl", "7534 ME", "Enschede", "I4AO1", "0264650"));
        studList.add(new Student("Loek", "", "Gosen", "lgosen01@student.rocvantwente.nl", "7574 ZV", "Oldenzaal", "I4AO1", "0267853"));
        studList.add(new Student("Bas", "", "Grave", "bgrave01@student.rocvantwente.nl", "7557 GE", "Hengelo", "I4AO1", "0267617"));
        studList.add(new Student("Dylan", "", "Hofstra", "dhofstra02@student.rocvantwente.nl", "7534 JX", "Enschede", "I4AO1", "0263413"));
        studList.add(new Student("Jordy", "", "Mengerink", "jmengerink03@student.rocvantwente.nl", "7559 KT", "Hengelo", "I4AO1", "0265597"));
        studList.add(new Student("James", "", "Morsink", "jmorsink01@student.rocvantwente.nl", "7557 JB", "Hengelo", "I4AO1", "0267422"));
        studList.add(new Student("Robin", "", "Tatlici", "rtatlici01@student.rocvantwente.nl", "7512 XM", "Enschede", "I4AO1", "0187199"));
        studList.add(new Student("Carlo", "", "Verver", "cverver01@student.rocvantwente.nl", "7205 BH", "Zutphen", "I4AO1", "0269264"));
        studList.add(new Student("Jake", "", "Zweers", "jzweers05@student.rocvantwente.nl", "7462 BD", "Rijssen", "I4AO1", "0257956"));
        return studList;
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        } catch( Exception ex){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        }
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
