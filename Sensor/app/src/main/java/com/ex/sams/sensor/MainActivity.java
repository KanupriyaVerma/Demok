package com.ex.sams.sensor;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.os.Build.*;

public class MainActivity extends Activity implements AccelerometerListener {

    @Override


    public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub

    }

    public void onShake(float force) {
        String phone = "7694036633";
        String message = "helllooooooo deeps";
        // Do your stuff here
        final int SEND_SMS_PERMISSION_REQUEST_CODE = 111;

        // Called when Motion Detected
        Toast.makeText(getBaseContext(), "Motion detected",
                Toast.LENGTH_SHORT).show();


//
//        if (checkSelfPermission(android.Manifest.permission.SEND_SMS)
//                == PackageManager.PERMISSION_DENIED)
//
//        {
//            Log.d("permission", "permission denied to SEND_SMS - requesting it");
//            String[] permissions = {Manifest.permission.SEND_SMS};
//
//            if (VERSION.SDK_INT >= VERSION_CODES.M) {
//
//                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
//
//            }
//        }
//sendSMS(phone,message);






       /* SmsManager smsMgr = SmsManager.getDefault();
        smsMgr.sendTextMessage("phoneNo", null, "message", null, null);*/
      /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));

        intent.putExtra("sms_body", message);
        startActivity(intent);*/
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:7694036633"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
               ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},PERMISSION_REQUEST_CODE);
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        else{
                startActivity(intent);
        }
        // ActionBar.Tab mPhoneNoEt;

        
    };

    public void sendSMS(String phone, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();






        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
    private static final int PERMISSION_REQUEST_CODE = 1;



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // .../ mSendMessageBtn = (Button) findViewById(R.id.btn_send_message);
        //final EditText messagetEt = (EditText) findViewById(R.id.et_message);



    }
    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);

    }


    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getBaseContext(), "onResume Accelerometer Started",
                Toast.LENGTH_SHORT).show();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isSupported(this)) {

            //Start Accelerometer Listening
            AccelerometerManager.startListening(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

            //Start Accelerometer Listening
            AccelerometerManager.stopListening();

            Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Sensor", "Service  distroy");

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

            //Start Accelerometer Listening
            AccelerometerManager.stopListening();

            Toast.makeText(getBaseContext(), "onDestroy  Stoped",
                    Toast.LENGTH_SHORT).show();
        }

    }

}