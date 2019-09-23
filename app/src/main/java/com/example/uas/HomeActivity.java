package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    List<Postingan> postinganList;
    DatabaseReference databasePostingan;
    ListView listpostingan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databasePostingan = FirebaseDatabase.getInstance().getReference("Postingan");
        listpostingan = (ListView) findViewById(R.id.listview);
        postinganList = new ArrayList<>();

        listpostingan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Postingan postingan = postinganList.get(i);
                showDialog(postingan.getPostId(), postingan.getJudul(),postingan.getDeskripsi(),postingan.getTanggal());
                return;

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        databasePostingan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postinganList.clear();
                for(DataSnapshot postinganSnapshot : dataSnapshot.getChildren()){
                    Postingan postingan = postinganSnapshot.getValue(Postingan.class);
                    postinganList.add(postingan);
                }
                PostinganList adapter = new PostinganList(HomeActivity.this, postinganList);
                listpostingan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void showDialog(final String postId, final String judul, final String deskripsi, final String tanggal){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View  dialogview =  inflater.inflate(R.layout.dialog,null);
        dialogBuilder.setView(dialogview);
        final Button buttondelete = (Button) dialogview.findViewById(R.id.btndelete);
        final Button buttonupdate = (Button) dialogview.findViewById(R.id.btnupdate);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,UpdatePostingan.class);
                intent.putExtra("id", postId);
                intent.putExtra("judul", judul);
                intent.putExtra("deskripsi", deskripsi);
                intent.putExtra("tanggal", tanggal);
                startActivity(intent);
            }

        });
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePostingan(postId);
            }
        });
    }
    private void deletePostingan(String postId){
        DatabaseReference drPostingan = FirebaseDatabase.getInstance().getReference("Postingan").child(postId);
        drPostingan.removeValue();
        Toast.makeText(this, "Terhapus", Toast.LENGTH_LONG).show();
    }
        public void onclick(View view){
            Intent explicit = new Intent( this, TambahPostingan.class);
            startActivity(explicit);
        }

}

