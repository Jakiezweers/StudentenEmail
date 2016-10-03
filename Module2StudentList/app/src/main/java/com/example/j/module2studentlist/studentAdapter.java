package com.example.j.module2studentlist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by J on 9/26/2016.
 */
public class studentAdapter extends ArrayAdapter<Student> {

    public studentAdapter(Context context, List<Student> stdList) {
        super(context, 0, stdList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        com.example.j.module2studentlist.Student std = getItem(position);
        TextView txtNaam = (TextView) convertView.findViewById(R.id.txtNaam);
        txtNaam.setText(std.getNaam());

        TextView txtStudnr = (TextView) convertView.findViewById(R.id.txtStudNr);
        txtStudnr.setText(std.getStudentnr());

        TextView txtKlas = (TextView) convertView.findViewById(R.id.txtKlas);
        txtKlas.setText(std.getKlas());

        String name = ("n"+std.getStudentnr()).trim();
        int id = convertView.getResources().getIdentifier(name, "drawable", getContext().getPackageName());
        ImageView image = (ImageView) convertView.findViewById(R.id.ivStudent);
        image.setImageDrawable(convertView.getResources().getDrawable(id, null));
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getNaam().hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
