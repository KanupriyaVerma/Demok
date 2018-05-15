package com.ex.sams.json;

/**
 * Created by Sam's on 4/26/2018.
 */

public class contacts {

    private String id,title,subtitle,description;
public contacts(String id,String title, String subtitle, String description){
               this.setId(id);
               this.setTitle(title);
               this.setSubtitle(subtitle);
               this.setDescription(description);
}




    public String getId(){
        return id;
    }
    public void setId( String id){
        this.id=id;
        System.out.println(id);
    }

    public String getTitle(){
        return title;
    }
    public void setTitle( String title){
        this.title=title;
    }
    public String getSubtitle(){
        return subtitle;
    }
    public void setSubtitle( String subtitle){
        this.subtitle=subtitle;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription( String description){
        this.description=description;
    }





}
