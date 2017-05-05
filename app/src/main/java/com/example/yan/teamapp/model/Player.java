package com.example.yan.teamapp.model;

import java.io.Serializable;

/**
 * Created by Yan on 28/04/2017.
 */

public class Player implements Serializable {

    private int id;
    private String name;
    private String number;
    private String positionID;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}