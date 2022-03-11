package comenzi;

import entitati.Movie;
import entitati.User;

import java.util.ArrayList;

public final class View {
    private final String videoTitle;
    private final User user;

    public View(final String videoTitle, final User user) {
        this.videoTitle = videoTitle;
        this.user = user;
    }

    public String adaugareVideo(final ArrayList<Movie> movies) {
        int nrViews = 1;
        this.user.viewedVideos.add(videoTitle);
        if (this.user.getHistory().get(this.videoTitle) != null) {
            nrViews = this.user.getHistory().get(this.videoTitle);
            nrViews++;
            for (Movie movie : movies) {
                if (this.videoTitle.equals(movie.getName())) {
                    movie.setNumberOfViews(movie.getNumberOfViews() + 1);

                }
            }
        } else {
            this.user.getHistory().put(this.videoTitle, 1);
        }
        return "success -> " + this.videoTitle + " was viewed with total views of " + nrViews;
    }
}
