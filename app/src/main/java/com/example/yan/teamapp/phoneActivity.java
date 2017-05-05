package com.example.yan.teamapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Yan on 04/05/2017.
 */

public class phoneActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        ImageButton newButton = (ImageButton) findViewById(R.id.phone_callButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buildCallNumber("83 987654321");
            }
        });

    }

    private void buildCallNumber(String number) {
        Intent callNumberIntent = new Intent(Intent.ACTION_CALL);
        if (number.startsWith("+")) {
            callNumberIntent.setData(Uri.parse("tel:" + number));
        } else if (number.length() == 11 || number.length() == 10) {
            callNumberIntent.setData(Uri.parse("tel:+55" + number));
        } else {
            callNumberIntent.setData(Uri.parse("tel:+5583" + number));
        }
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
        startActivity(callNumberIntent);

    }


}
