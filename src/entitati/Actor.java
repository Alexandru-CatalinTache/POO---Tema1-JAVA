package entitati;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Map;

public final class Actor {
    private final String name;
    private final String career;
    private final ArrayList<String> filmography;
    private final Map<ActorsAwards, Integer> awards;
    private double media;

    public Actor(final String name, final String career, final ArrayList<String> filmography,
                 final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.career = career;
        this.filmography = filmography;
        this.awards = awards;
        this.media = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getCareer() {
        return career;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(final double media) {
        this.media = media;
    }
}
