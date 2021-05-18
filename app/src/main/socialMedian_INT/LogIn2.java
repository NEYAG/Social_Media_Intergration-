package com.example.socialmediaintegration;


        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.graphics.drawable.RoundedBitmapDrawable;
        import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Build;
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
        import com.facebook.AccessToken;
        import com.facebook.AccessTokenTracker;
        import com.facebook.CallbackManager;
        import com.facebook.GraphRequest;
        import com.facebook.GraphResponse;
        import com.facebook.HttpMethod;
        import com.facebook.login.LoginManager;

        import org.json.JSONException;

        import java.util.ArrayList;
        import java.util.List;


public class Login2<logout> extends AppCompatActivity {
    private Button logout;
    private ImageView ivprofile;
    private TextView tvname;
    private String imageurl;
    private TextView tvemail;
    private TextView tvidnumber;
    private String name;
    private String email;
    private String idnumber;


    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        this.setTitle("Facebook Login");
        Bundle ibundle = getIntent().getExtras();
        name = ibundle.getString("name");
        email = ibundle.getString("email");
        idnumber = ibundle.getString("id");
        imageurl = ibundle.getString("imageurl");


        tvname = (TextView) findViewById(R.id.textView);
        tvidnumber = (TextView) findViewById(R.id.textView2);
        tvemail = (TextView) findViewById(R.id.textView4);
        tvname.setText(name);
        tvemail.setText(email);
        tvidnumber.setText(idnumber);

        Toast.makeText(Login2.this, "You have Logged in Successfully", Toast.LENGTH_LONG).show();
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String nme = "Name: ";
        SpannableString nam = new SpannableString(nme);
        nam.setSpan(new ForegroundColorSpan(Color.BLUE), 0, nam.length(), 0);
        builder.append(nam);
        SpannableString red = new SpannableString(name);
        red.setSpan(new ForegroundColorSpan(Color.RED), 0, red.length(), 0);
        builder.append(red);
        tvname.setText(builder, TextView.BufferType.SPANNABLE);

        SpannableStringBuilder build = new SpannableStringBuilder();
        String mail = "Mail Id: ";
        SpannableString mid = new SpannableString(mail);
        mid.setSpan(new ForegroundColorSpan(Color.BLUE), 0, mid.length(), 0);
        build.append(mid);
        SpannableString id = new SpannableString(email);
        id.setSpan(new ForegroundColorSpan(Color.RED), 0, id.length(), 0);
        build.append(id);
        tvemail.setText(build, TextView.BufferType.SPANNABLE);

        SpannableStringBuilder build1 = new SpannableStringBuilder();
        String idnum = "Facebook Id: ";
        SpannableString idno = new SpannableString(idnum);
        idno.setSpan(new ForegroundColorSpan(Color.BLUE), 0, idno.length(), 0);
        build1.append(idno);
        SpannableString fid = new SpannableString(idnumber);
        fid.setSpan(new ForegroundColorSpan(Color.RED), 0, fid.length(), 0);
        build1.append(fid);
        tvidnumber.setText(build1, TextView.BufferType.SPANNABLE);
        ivprofile = (ImageView) findViewById(R.id.imageView);

        Bundle params = new Bundle();
        params.putBoolean("redirect", false);
        params.putString("type", "large");

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "me/picture",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                        try {
                            String str_facebookimage = (String) response.getJSONObject().getJSONObject("data").get("url");

                            Glide.with(Login2.this).load(str_facebookimage).skipMemoryCache(true).into(ivprofile);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
        ).executeAsync();

        logout = (Button) findViewById(R.id.button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(Login2.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(Login2.this, "You have logged out of this account", Toast.LENGTH_LONG).show();
                finish();


            }
        });
    }
}
