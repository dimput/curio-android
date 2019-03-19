package com.example.mutiarafitritasir.sigeo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void keluar(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void profilePage(View view) {
        Intent profile = new Intent(this,ProfileActivity.class);
        startActivity(profile);
    }
}
