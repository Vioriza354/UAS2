package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TambahPostingan extends AppCompatActivity {
    EditText editTextJudul, editTextDeskripsi;
    Button butontambah;
    DatabaseReference databasePostingan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_postingan);

        databasePostingan = FirebaseDatabase.getInstance().getReference("Postingan");
        editTextJudul = (EditText) findViewById(R.id.edittextjudul);
        editTextDeskripsi = (EditText) findViewById(R.id.edittextdeskripsi);
        butontambah = (Button) findViewById(R.id.buttontambah);

        butontambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambahPostingan();
            }
        });
    }


    private void TambahPostingan(){
        String judul = editTextJudul.getText().toString().trim();
        String deskripsi = editTextDeskripsi.getText().toString();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyy' 'hh:mm:ss");
        String tanggal = date.format(new Date());

        if (!TextUtils.isEmpty(judul)){
            String id =  databasePostingan.push().getKey();

            Postingan artist = new Postingan(id, judul, deskripsi, tanggal);
            databasePostingan.child(id).setValue(artist);
            Toast.makeText(this, "Postingan Telah Ditambahakan", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Eusian Bosku", Toast.LENGTH_LONG).show();
        }
    }
}
