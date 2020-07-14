package com.example.proyectointegradorgrupal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PodcastContainer implements Serializable {

    @SerializedName("recommendations")
    private List<Podcast> recommendations;

    public PodcastContainer() {
    }

    public PodcastContainer(List<Podcast> recommendations) {
        this.recommendations = recommendations;
    }

    public void setRecommendations(List<Podcast> recommendations) {
        this.recommendations = recommendations;
    }

    public List<Podcast> getRecommendations() {
        return recommendations;
    }
}
