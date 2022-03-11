package query;

import entitati.Movie;

import java.util.ArrayList;
import java.util.List;

public final class QueryMovies {

    public QueryMovies(final int number, final String criteria, final String sortType) {
    }

    /**
     * @return mesajul query-ului
     */
    public String ratingMovie() {
        return "Query result: []";
    }

    public String longestCriteria(final ArrayList<Movie> movies, final String genre,
                                  final int number, final List<String> year,
                                  final String sortType) {
        String message;
        ArrayList<Integer> durations = new ArrayList<>();
        ArrayList<String> movieNames = new ArrayList<>();
        ArrayList<Movie> moviesRespectingRule = new ArrayList<>();
        ArrayList<String> numeOrdonate = new ArrayList<>();
        int i, j;
        if (year.get(0) != null) {
            int an = Integer.parseInt(year.get(0));
            for (i = 0; i < movies.size(); i++) {
                Movie film = movies.get(i);
                if (film.getYear() == an) {
                    for (j = 0; j < film.getGenres().size(); j++) {
                        if (film.getGenres().get(j).equals(genre)) {
                            moviesRespectingRule.add(film);
                            break;
                        }
                    }
                }
            }
        }
        for (i = 0; i < moviesRespectingRule.size(); i++) {
            durations.add(moviesRespectingRule.get(i).getDuration());
        }
        bubbleSort(durations);
        for (i = 0; i < durations.size(); i++) {
            for (j = 0; j < moviesRespectingRule.size(); j++) {
                if (durations.get(i).equals(moviesRespectingRule.get(j).getDuration())) {
                    movieNames.add(moviesRespectingRule.get(j).getName());
                    break;
                }
            }
        }

        if (sortType.equals("asc")) {
            for (i = 0; i < number && i < movieNames.size(); i++) {
                numeOrdonate.add(movieNames.get(i));
            }
        }
        int contor = 0;
        if (sortType.equals("desc")) {
            for (i = movieNames.size() - 1; i >= 0; i--) {
                if (contor == number || contor == movieNames.size()) {
                    break;
                } else {
                    contor++;
                    numeOrdonate.add(movieNames.get(i));
                }
            }
        }
        message = "Query result: " + numeOrdonate;
        return message;
    }

    public void bubbleSort(final ArrayList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // swap
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
