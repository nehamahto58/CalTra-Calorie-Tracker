package com.example.afinal;

public class Model {
    String title;
    String desc;
    int icon;
    String type;

    //constructor
    public Model(String title, String desc, int icon, String type) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
        this.type = type;
    }

    //getters


    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getType() { return this.type;}
}