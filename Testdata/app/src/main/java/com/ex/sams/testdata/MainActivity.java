package com.ex.sams.testdata;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.*;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



   TextView tv, tv2;
    String email;
    int a;
    String password;

    JSONParser jsonParser = new JSONParser();

    private ProgressDialog dialog;

    private static String register_user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
    }

    public void Login(View view)
    {
        email = tv.getText().toString().trim();
        password = tv2.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            if(TextUtils.isEmpty(email))
            {
                tv.setError("This field cannot be empty");
            }
            if(TextUtils.isEmpty(password))
            {
                tv2.setError("This field cannot be empty");
            }
        }
        else
        {
            //build Params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("password", password));

            CreateNewProduct productTask = new CreateNewProduct(params);
            productTask.execute();
        }
    }

    class CreateNewProduct extends AsyncTask<String, String, JSONObject>
    {
        List<NameValuePair> params;

        public CreateNewProduct(List<NameValuePair> params)
        {
            this.params = params;
        }

        protected void onPreExecute()
        {
            super.onPreExecute();
            dialog = new ProgressDialog(Login.this);
            dialog.setMessage("We are Logging in. Please wait . . .");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        protected JSONObject doInBackground(String... args)
        {
            JSONObject json = jsonParser.makeHttpRequest(register_user, "POST", this.params);

            return json;
        }

        protected void onPostExecute(JSONObject result)
        {
            dialog.dismiss();
            //this assumes that the response looks like this:
            //{"success" : true }
            String message = null;
            try {
                message = result.getString("message");
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            boolean success = false;
            try {
                success = result.getBoolean("success");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Toast.makeText(getBaseContext(), success ? "We are good to go." : "Something went wrong!",
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}