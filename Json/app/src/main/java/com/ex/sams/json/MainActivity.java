package com.ex.sams.json;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void getJSON(View view){

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String json_url;
        protected void onPreExecute(){
            json_url="http://it-ebooks-api.info/v1/search/android";
        }

        protected String doInBackground(Void... voids){

            try{
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((JSON_STRING =bufferedReader.readLine())!= null)
                {
                    stringBuilder.append(JSON_STRING+"/n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){

                e.printStackTrace();
            }
return null;
        }

        protected void onProgressUpdate(Void... values){super.onProgressUpdate(values);}
    protected void onPostExecute(String result){
        TextView textView=(TextView)findViewById(R.id.textview);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(result);
         JSON_STRING=result;
    }

    }

    public void parseJSON(View view){
              if(JSON_STRING==null)
           {
    Toast.makeText(getApplicationContext(),"first get jon here",Toast.LENGTH_LONG).show();
           }
           else{
                  Intent intent= new Intent(this,DisplayListView.class);
                  intent.putExtra("json_data",JSON_STRING);
                  startActivity(intent);
              }
    }




}
