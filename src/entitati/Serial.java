package entitati;

import entertainment.Season;

import java.util.ArrayList;

public final class Serial {
    private final String name;
    private final ArrayList<Season> seasons;
    private int year;
    private ArrayList<String> genres;
    private ArrayList<String> cast;

    private int nrSeasons;

    private final ArrayList<Double> ratingsSeasons = new ArrayList<>(nrSeasons);

    public Serial(final String name, final int year, final ArrayList<String> genres,
                  final ArrayList<String> cast, final int nrSeasons,
                  final ArrayList<Season> seasons) {
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.cast = cast;
        this.nrSeasons = nrSeasons;
        this.seasons = seasons;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void modifyRating(final int seasonNum, final double rating, final Serial s) {
        int i;
        int size = s.ratingsSeasons.size();
        while (size < s.nrSeasons) {
            s.ratingsSeasons.add(0.0);
            size++;
        }
        for (i = 0; i < s.nrSeasons; i++) {
            if (seasonNum == i) {
                s.ratingsSeasons.add(i, rating);
            }
        }
    }

}
