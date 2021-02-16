package com.example.cbr.fragments.clientlist;

import com.example.cbr.R;

public class ClientListClientInfo {
    private String name;
    private String id;
    private String location;
    private int photo;


    public ClientListClientInfo(String name, String id, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.photo = R.drawable.ic_baseline_list_24;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhoto() {
        return photo;
    }

    private int matchLogo() {
        //name = this.getName();
        return R.drawable.ic_baseline_list_24;
    }
}
