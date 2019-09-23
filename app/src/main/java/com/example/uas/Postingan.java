package com.example.uas;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class Postingan {
String postId,judul,deskripsi,tanggal;


   Postingan(){

   }

public Postingan(String postId, String judul, String deskripsi,String tanggal){
    this.postId = postId;
    this.judul = judul;
    this.deskripsi = deskripsi;
    this.tanggal = tanggal;
}

    public String getPostId() {
        return postId;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }
}
