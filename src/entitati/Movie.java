package entitati;

import java.util.ArrayList;

public final class Movie {
    private final String name;
    private final int year;
    private final int duration;
    private ArrayList<String> genres;
    private ArrayList<String> cast;
    private int numberOfViews;
    private double rating;

    public Movie(final String name, final int year, final int duration,
                 final ArrayList<String> genres, final ArrayList<String> cast) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.cast = cast;
        this.numberOfViews = 0;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }
}
