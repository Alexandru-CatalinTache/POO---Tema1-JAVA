package entitati;

import java.util.ArrayList;
import java.util.Map;

public final class User {
    private final String username;
    private final String subscription;
    private final Map<String, Integer> history;
    private final ArrayList<String> favorite;
    private final ArrayList<Movie> movieList = new ArrayList<>();
    private final ArrayList<Serial> serialList = new ArrayList<>();
    public ArrayList<String> viewedVideos = new ArrayList<>();
    private int numberOfRatings;

    public User(final String username, final String subscription,
                final Map<String, Integer> history,
                final ArrayList<String> favorite) {
        this.username = username;
        this.subscription = subscription;
        this.history = history;
        this.favorite = favorite;
        this.numberOfRatings = 0;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Serial> getSerialList() {
        return serialList;
    }

    public String getUsername() {
        return username;
    }

    public String getSubscription() {
        return subscription;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public ArrayList<String> getFavorite() {
        return favorite;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
