package comenzi;

import entitati.Serial;
import entitati.User;

import java.util.ArrayList;

public final class Rating {
    public Rating(double rating) {
    }

    public String addRatingToMovie(final String username, final double rating, final String movieTitle,
                                   final ArrayList<User> users) {

        String message;
        User u;
        int i, ii;

        for (ii = 0; ii < users.size(); ii++) {
            if (users.get(ii).getUsername().equals(username)) {
                u = users.get(ii);
                u.setNumberOfRatings(u.getNumberOfRatings() + 1);
            }
        }

        int j;
        int ok = 1;
        for (i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                for (j = 0; j < users.get(i).getMovieList().size(); j++) {
                    if (users.get(i).getMovieList().get(j).getName().equals(movieTitle)) {
                        users.get(i).getMovieList().get(j).setRating(rating);
                        ok = 0;
                        break;
                    }
                }
            }
            if (ok == 0) {
                break;
            }
        }
        message = "success -> " + movieTitle + " was rated with " + rating + " by " + username;
        return message;

    }

    public String addRatingToSerial(final String username, final double rating,
                                    final String serialTitle,
                                    final ArrayList<User> users, final int seasonNumber) {
        String message;
        int i, j;
        int ok = 1;
        for (i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                for (j = 0; j < users.get(i).getSerialList().size(); j++) {
                    if (users.get(i).getSerialList().get(j).getName().equals(serialTitle)) {
                        users.get(i).setNumberOfRatings(users.get(i).getNumberOfRatings() + 1);
                        Serial s = users.get(i).getSerialList().get(j);
                        s.modifyRating(seasonNumber, rating, s);
                        ok = 0;
                        break;
                    }
                }
            }
            if (ok == 0) {
                break;
            }
        }
        message = "success -> " + serialTitle + " was rated with " + rating + " by " + username;
        return message;

    }
}
