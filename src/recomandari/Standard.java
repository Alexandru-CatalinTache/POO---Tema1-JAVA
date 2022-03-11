package recomandari;

import entitati.Movie;
import entitati.User;

import java.util.ArrayList;

public final class Standard {
    public Standard(final String username) {
    }

    public String standardRecommand(final ArrayList<User> users, final String username,
                                    final ArrayList<Movie> movies) {
        User u = null;
        int i, j;
        for (i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                u = users.get(i);
            }
        }

        String numeFilm = null;

        for (j = 0; j < movies.size(); j++) {
            if (u != null && u.getHistory().get(movies.get(j).getName()) == null) {
                numeFilm = movies.get(j).getName();
                break;
            }
        }
        String message;
        message = "StandardRecommendation result: " + numeFilm;
        return message;
    }
}
