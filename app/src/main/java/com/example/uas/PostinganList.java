package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PostinganList extends ArrayAdapter<Postingan> {
    Activity context;
    List<Postingan> postinganList;
    Date waktu;

    public PostinganList(Activity context, List<Postingan> postinganList) {
        super(context, R.layout.activity_postingan_list, postinganList);
        this.context = context;
        this.postinganList = postinganList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_postingan_list, null, true);
        TextView textjudul = (TextView) listViewItem.findViewById(R.id.textjudul);
        TextView textdeskripsi = (TextView) listViewItem.findViewById(R.id.textdeskripsi);
        TextView tanggal = (TextView) listViewItem.findViewById(R.id.tanggal);

        Postingan postingan = postinganList.get(position);
        textjudul.setText(postingan.getJudul());
        textdeskripsi.setText(postingan.getDeskripsi());
        tanggal.setText(postingan.getTanggal());
        return listViewItem;
    }

}
