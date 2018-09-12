package com.example.ribani.herokuapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Actors {
    @Expose
    @SerializedName("height")
    private int height;
    @Expose
    @SerializedName("spouse")
    private String spouse;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("dob")
    private String dob;
    @Expose
    @SerializedName("children")
    private String children;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("country")
    private String country;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewFormatDob() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat newFormat = new SimpleDateFormat("EEEE, MMM d, yyyy");
        String newDobFormat = "";

        try {
            Date date = format.parse(getDob());
            newDobFormat = newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newDobFormat;
    }
}
