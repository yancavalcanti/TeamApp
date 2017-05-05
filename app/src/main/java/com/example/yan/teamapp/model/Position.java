package com.example.yan.teamapp.model;


import java.io.Serializable;

/**
 * Created by Yan on 28/04/2017.
 */

public class Position implements Serializable {

    private int id;
    private String name;
    private int functionID;
    private int number;

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

    public int getNumber() {
        return number;
    }

    public void setFunctionID(int functionID) {
        this.functionID = functionID;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFunctionID() {
        return functionID;
    }

}
