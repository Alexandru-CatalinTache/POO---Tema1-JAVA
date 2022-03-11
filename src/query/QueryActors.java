package query;

import actor.ActorsAwards;
import entitati.Actor;
import entitati.Movie;

import java.util.ArrayList;
import java.util.List;

public final class QueryActors {
    private final int number;
    private String criteria;
    private String sortType;

    public QueryActors(final int number, final String criteria, final String sortType) {
        this.number = number;

        this.criteria = criteria;
        this.sortType = sortType;
    }

    public static int strstr(final String x, final String y) {
        // if x is null or if x's length is less than that of y's
        if (x == null || y.length() > x.length()) {
            return -1;
        }

        // if y is null or is empty
        if (y.length() == 0) {
            return 0;
        }

        for (int i = 0; i <= x.length() - y.length(); i++) {
            int j;
            for (j = 0; j < y.length(); j++) {
                if (y.charAt(j) != x.charAt(i + j)) {
                    break;
                }
            }

            if (j == y.length()) {
                return i;
            }
        }

        return -1;
    }

    public ArrayList<Double> actorsAverage(final ArrayList<Actor> actors,
                                           final ArrayList<Movie> movieList) {
        int i;
        int j;
        double media = 0.0;
        int l;
        int contor = 0;

        ArrayList<Double> averageList = new ArrayList<>();
        for (i = 0; i < actors.size(); i++) {
            for (j = 0; j < actors.get(i).getFilmography().size(); j++) {
                String movieName = actors.get(i).getFilmography().get(j);
                for (l = 0; l < movieList.size(); l++) {
                    if (movieList.get(l).getName().equals(movieName)) {
                        if (movieList.get(l).getRating() != 0) {
                            media = media + movieList.get(l).getRating();
                            contor++;
                        }
                    }
                }
            }
            media = media / contor;
            actors.get(i).setMedia(media);
            averageList.add(media);
        }
        return averageList;

    }

    public void bubbleSort(final ArrayList<Double> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // swap

                    list.set(j, list.get(j + 1));
                    list.set(j + 1, list.get(j));
                }
            }
        }
    }

    /**
     * Returneaza rezultatul query-ului Average Actors
     */
    public String messageTopAverageActors(final ArrayList<Double> averages,
                                          final ArrayList<Actor> actors,
                                          final String type, final int number) {
        ArrayList<String> ordine = new ArrayList<>();
        int i, j;
        bubbleSort(averages);
        if (type.equals("asc")) {
            for (i = 0; i < number; i++) {
                for (j = 0; j < actors.size(); j++) {
                    if (actors.get(j).getMedia() == averages.get(i)) {
                        ordine.add(actors.get(j).getName());
                        break;
                    }
                }
            }
        }

        String message;
        message = "Query result: " + ordine;
        return message;

    }

    public String awardsReturn(final ArrayList<Actor> actors) {
        int i;
        String message;
        ArrayList<String> actori = new ArrayList<>();
        int k;
        for (i = 0; i < actors.size(); i++) {
            k = 0;
            if (actors.get(i).getAwards().get(ActorsAwards.BEST_PERFORMANCE) != null) {
                k++;
            }
            if (actors.get(i).getAwards().get(ActorsAwards.BEST_SUPPORTING_ACTOR) != null) {
                k++;
            }
            if (actors.get(i).getAwards().get(ActorsAwards.BEST_DIRECTOR) == null) {
                k++;
            }
            if (actors.get(i).getAwards().get(ActorsAwards.BEST_SCREENPLAY) == null) {
                k++;
            }
            if (actors.get(i).getAwards().get(ActorsAwards.PEOPLE_CHOICE_AWARD) == null) {
                k++;
            }
            if (k == 5) {
                actori.add(actors.get(i).getName());
            }
        }
        if (actori.size() == 0) {
            message = "Query result: []";
        } else {
            message = "Query result: " + actori;
        }
        return message;
    }

    public String filterDescriptionActors(final ArrayList<Actor> actors, final List<String> words,
                                          final String sortType) {
        ArrayList<String> actorsName = new ArrayList<>();
        ArrayList<String> ascOrder;
        ArrayList<String> descOrder = new ArrayList<>();
        String message;
        if (words == null) {
            message = "Query result: []";
            return message;
        }
        if (words.isEmpty()) {
            message = "Query result: []";
            return message;
        }
        int k;
        int i, j;
        for (i = 0; i < actors.size(); i++) {
            k = 0;
            for (j = 0; j < words.size(); j++) {
                if (strstr(actors.get(i).getCareer(), words.get(j)) > 0) {
                    k++;
                }
            }
            if (k == words.size()) {
                actorsName.add(actors.get(i).getName());
            }
        }
        if (actorsName.size() == 0) {
            message = "Query result: []";
        } else {
            ascOrder = sortString(actorsName);
            if (sortType.equals("asc")) {
                message = "Query result: [" + ascOrder + "]";
            } else {
                for (i = ascOrder.size() - 1; i > 0; i--) {
                    descOrder.add(ascOrder.get(i));
                }
                message = "Query result: [" + descOrder + "]";
            }
        }
        return message;

    }

    public ArrayList<String> sortString(final ArrayList<String> myArray) {
        int size = myArray.size();

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < myArray.size(); j++) {
                if (myArray.get(i).compareTo(myArray.get(j)) > 0) {
                    myArray.set(j, myArray.get(j + 1));
                    myArray.set(j + 1, myArray.get(j));
                }
            }
        }
        return myArray;
    }
}
