package com.example.ribani.herokuapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseItem {

    @Expose
    @SerializedName("actors")
    private List<Actors> actors;

    public List<Actors> getActors() {
        return actors;
    }

    public void setActors(List<Actors> actors) {
        this.actors = actors;
    }
}
