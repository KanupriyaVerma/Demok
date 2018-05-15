package com.ex.sams.json;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam's on 4/26/2018.
 */

public class contactAdapter extends ArrayAdapter {
 List list=new ArrayList();


    public contactAdapter( Context context, int resource) {
        super(context, resource);
    }


    public void add( contacts object) {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
@Override
    public View getView(int position, View convertView, ViewGroup parent){
        View  row;
        row=convertView;
        ContactHolder contactHolder;
        if(row==null){
            LayoutInflater  layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       row=layoutInflater.inflate(R.layout.row_layout,parent,false);
           contactHolder =new  ContactHolder();
       contactHolder.tx_id=(TextView)row.findViewById(R.id.tx_id);
            contactHolder.tx_title=(TextView)row.findViewById(R.id.tx_title);
            contactHolder.tx_subtitle=(TextView)row.findViewById(R.id.tx_subtitle);
            contactHolder.tx_description=(TextView)row.findViewById(R.id.tx_description);
row.setTag(contactHolder);

        }
        else{
            contactHolder=(ContactHolder)row.getTag();
        }
        contacts contacts=(contacts)this.getItem(position);
         contactHolder.tx_id.setText(contacts.getId());
         contactHolder.tx_title.setText(contacts.getTitle());
         contactHolder.tx_subtitle.setText(contacts.getSubtitle());
         contactHolder.tx_description.setText(contacts.getDescription());

        return  row;
    }


    static class ContactHolder{
        TextView tx_id,tx_title,tx_subtitle,tx_description;
    }
}
