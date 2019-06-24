package com.vodafone.team.vodafoneteam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText phone;
    public String mMember;
    public Button myButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //We must use findViewById within or after the on create method
        myButton = findViewById(R.id.mybutton);
        phone = findViewById(R.id.phone);
        phone.setRawInputType(Configuration.KEYBOARD_QWERTY);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMember = phone.getText().toString().trim().replace(" ", "").replace("+2", "");
                addMember(mMember);
            }
        });

    }


    public void addMember(String member) {
// this method executes the USSD code using phone number provided from user
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String ussd = "*10*" + member + Uri.encode("#");
        Log.e("PHONE LOG", member);
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussd)));
    }
}
