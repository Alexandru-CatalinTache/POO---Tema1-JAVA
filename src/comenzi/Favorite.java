package comenzi;

import entitati.User;

public final class Favorite {
    private final User userFav;
    private final String title;

    public Favorite(final User userFav, final String title) {
        this.userFav = userFav;
        this.title = title;
    }

    public String fav() {
        int i;
        int size = this.userFav.getFavorite().size();
        String message = "";
        int ok = 1;
        this.userFav.getHistory().get(this.title);
        if (this.userFav.getHistory().get(this.title) == null) {
            ok = 2;
            message = "error -> " + this.title + " is not seen";

        }
        if (ok == 1) {
            for (i = 0; i < size; i++) {
                if (this.userFav.getFavorite().get(i).equals(this.title)) {
                    message = "error -> " + this.title + " is already in favourite list";
                    ok = 0;
                    break;
                }
            }
        }
        if (ok == 1) {
            this.userFav.getFavorite().add(this.title);
            message = "success -> " + this.title + " was added as favourite";

        }
        return message;
    }

}
