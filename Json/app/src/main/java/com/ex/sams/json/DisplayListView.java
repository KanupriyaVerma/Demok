package com.ex.sams.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
String json_string;
JSONObject jsonObject;
JSONArray jsonArray;
contactAdapter contactAdapter;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_list_view);
       listView=findViewById(R.id.listview);

        listView.setScrollContainer(true);

        contactAdapter=new contactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);
        json_string=getIntent().getStringExtra("json_data");
try{
    jsonObject=new JSONObject(json_string);
      jsonArray=jsonObject.getJSONArray("Books");
    int count=0;
    String id,title,subtitle,description;

    while(count<jsonObject.length())
    {
JSONObject JO =jsonArray.getJSONObject(count);
  id=JO.getString("ID");
        title=JO.getString("Title");
        subtitle=JO.getString("SubTitle");
        description=JO.getString("Description");
         contacts contacts=new contacts(id,title,subtitle,description);
            contactAdapter.add(contacts);
         count++;

    }

}catch (JSONException e){
    e.printStackTrace();
}
    }
}
