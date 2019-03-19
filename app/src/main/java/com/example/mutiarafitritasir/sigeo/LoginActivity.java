package com.example.mutiarafitritasir.sigeo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView email;
    EditText password;
    TextView sudahpunya;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Firebase Authen
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!= null){
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
        }
        email = (AutoCompleteTextView) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sudahpunya = (TextView) findViewById(R.id.sudahpunya);

        final SpannableStringBuilder sb = new SpannableStringBuilder("Tidak punya akun? Register");

        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
        sb.setSpan(bss, 17, 26, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make Register Bold

        sudahpunya.setText(sb);
    }

    public void signin(View view) {

        String emailku = email.getText().toString().trim();
        String passwordku = password.getText().toString().trim();

        if(emailku.isEmpty()){
            email.setError("Email Harus Diisi !");
        }

        if(passwordku.isEmpty()){
            password.setError("Password Harus Diisi !");
        }

        mAuth.signInWithEmailAndPassword(emailku, passwordku)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("wow","success");
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("kambing","failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void lupa(View view) {
        Intent x = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(x);
    }

    public void register(View view) {
        Intent x = new Intent(LoginActivity.this,RegisterActivity.class);
        x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(x);
    }
}
