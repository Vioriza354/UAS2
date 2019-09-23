package com.example.uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdatePostingan extends AppCompatActivity {
    EditText editTextJudul, editTextDeskripsi,edittanggal;
    Button butontambah;
    DatabaseReference databasePostingan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_postingan);

        final Bundle bundle = getIntent().getExtras();


        databasePostingan = FirebaseDatabase.getInstance().getReference("Postingan");
        editTextJudul = (EditText) findViewById(R.id.edittextjudul);
        editTextDeskripsi = (EditText) findViewById(R.id.edittextdeskripsi);
        edittanggal = (EditText) findViewById(R.id.edittexttanggal);
        butontambah = (Button) findViewById(R.id.buttontambah);

        editTextJudul.setText(bundle.getString("judul"));
        editTextDeskripsi.setText(bundle.getString("deskripsi"));



        butontambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Update(bundle.getString("id"),bundle.getString("tanggal"));
                return;
            }
        });
    }
    private void Update(final String postId,final String tanggal){
                String judul = editTextJudul.getText().toString().trim();
                String deskripsi = editTextDeskripsi.getText().toString();

                if (TextUtils.isEmpty(judul)){
                    editTextJudul.setError("Isi Judul");
                    return;
                }
                updatePostingan(postId, judul, deskripsi,tanggal);
    }

    private boolean updatePostingan(String postId, String judul, String deskripsi, String tanggal){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Postingan").child(postId);
        Postingan postingan = new Postingan(postId, judul, deskripsi,tanggal);
        databaseReference.setValue(postingan);
        Toast.makeText(this, "Postingan Telah Diupdate", Toast.LENGTH_LONG).show();
        return true;
    }

}
