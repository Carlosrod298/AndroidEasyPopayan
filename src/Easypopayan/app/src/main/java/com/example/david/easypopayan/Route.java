package com.example.david.easypopayan;

/**
 * Created by david on 12/03/18.
 */

public class Route {
    private String Name, genre, timeEstimated;


    public Route() {
    }

    public Route(String title, String genre, String year) {
        this.Name = title;
        this.genre = genre;
        this.timeEstimated = year;
    }

    public String getTitle() {
        return Name;
    }

    public void setTitle(String name) {
        this.Name = name;
    }

    public String getYear() {
        return timeEstimated;
    }

    public void setYear(String year) {
        this.timeEstimated = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
