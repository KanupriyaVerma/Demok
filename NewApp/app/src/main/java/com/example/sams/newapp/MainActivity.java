package com.example.sams.newapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   EditText editText;
   Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edittext =  findViewById(R.id.editText);
        Button button =  findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edittext.length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter any number",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                int value = Integer.valueOf(edittext.getText().toString());

                if (value % 2 == 0)
                    Toast.makeText(MainActivity.this, "EVEN NUMBER", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "ODD NUMBER", Toast.LENGTH_LONG).show();
            }
        });

    }
}
