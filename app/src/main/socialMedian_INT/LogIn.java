package com.example.socialmediaintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity {
TextView name, email;


    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Google Sign In");
        name = (TextView)findViewById(R.id.textView);
        email = (TextView)findViewById(R.id.textView2);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Button signout = (Button)findViewById(R.id.button);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            //String personGivenName = acct.getGivenName();
            //String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            //String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            Toast.makeText(Login.this,"You have Signed in Successfully",Toast.LENGTH_LONG).show();
            SpannableStringBuilder builder = new SpannableStringBuilder();
            String nme="Name: ";
            SpannableString nam= new SpannableString(nme);
            nam.setSpan(new ForegroundColorSpan(Color.BLUE), 0, nam.length(), 0);
            builder.append(nam);
            SpannableString red= new SpannableString(personName);
            red.setSpan(new ForegroundColorSpan(Color.RED), 0, red.length(), 0);
            builder.append(red);
            name.setText(builder, TextView.BufferType.SPANNABLE);

            SpannableStringBuilder build = new SpannableStringBuilder();
            String mail="Mail Id: ";
            SpannableString mid= new SpannableString(mail);
            mid.setSpan(new ForegroundColorSpan(Color.BLUE), 0, mid.length(), 0);
            build.append(mid);
            SpannableString id= new SpannableString(personEmail);
            id.setSpan(new ForegroundColorSpan(Color.RED), 0, id.length(), 0);
            build.append(id);
            email.setText(build, TextView.BufferType.SPANNABLE);
            ImageView pic = (ImageView) findViewById(R.id.imageView);
            Glide.with(this).load(String.valueOf(personPhoto)).into(pic);
        }
    }
    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Login.this,"You have signed out of this mailid",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

}
