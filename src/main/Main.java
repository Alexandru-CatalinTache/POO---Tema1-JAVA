package main;

import checker.Checker;
import checker.Checkstyle;
import comenzi.Favorite;
import comenzi.Rating;
import comenzi.View;
import common.Constants;
import entitati.Actor;
import entitati.Movie;
import entitati.Serial;
import entitati.User;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.ShowInput;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import query.QueryActors;
import query.QueryMovies;
import query.QueryUsers;
import recomandari.Standard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation
        int i;
        int j;
        int k;

        ArrayList<User> listaUseri = new ArrayList<>();
        ArrayList<Actor> listaActori = new ArrayList<>();
        ArrayList<Movie> listaMovies = new ArrayList<>();
        ArrayList<Serial> listaSerials = new ArrayList<>();

        for (i = 0; i < input.getUsers().size(); i++) {
            UserInputData user = input.getUsers().get(i);
            User newUser = new User(user.getUsername(), user.getSubscriptionType(),
                    user.getHistory(), user.getFavoriteMovies());
            listaUseri.add(newUser);
        }

        for (i = 0; i < input.getActors().size(); i++) {
            ActorInputData actor = input.getActors().get(i);
            Actor newActor = new Actor(actor.getName(), actor.getCareerDescription(),
                    actor.getFilmography(), actor.getAwards());
            listaActori.add(newActor);
        }

        for (i = 0; i < input.getMovies().size(); i++) {
            MovieInputData movie = input.getMovies().get(i);
            ShowInput show = input.getMovies().get(i);
            Movie newMovie = new Movie(movie.getTitle(), movie.getYear(),
                    movie.getDuration(), show.getGenres(), show.getCast());
            listaMovies.add(newMovie);
        }

        for (i = 0; i < input.getSerials().size(); i++) {
            SerialInputData serial = input.getSerials().get(i);
            ShowInput shows = input.getSerials().get(i);
            Serial newSerial = new Serial(serial.getTitle(), serial.getYear(),
                    shows.getGenres(), shows.getCast(),
                    serial.getNumberSeason(), serial.getSeasons());
            listaSerials.add(newSerial);
        }
        // pentru separarea filmelor de seriale
        for (i = 0; i < listaMovies.size(); i++) {
            //adaugare lista de filme pentru user
            for (j = 0; j < listaUseri.size(); j++) {
                User u = listaUseri.get(j);
                String titluFilm = listaMovies.get(i).getName();
                if (u.getHistory().get(titluFilm) != null) {
                    u.getMovieList().add(listaMovies.get(i));
                }
            }
        }

        for (i = 0; i < listaSerials.size(); i++) {
            // adaugare lista de seriale pentru user
            for (j = 0; j < listaUseri.size(); j++) {
                User u = listaUseri.get(j);
                String titluSerial = listaSerials.get(i).getName();
                if (u.getHistory().get(titluSerial) != null) {
                    u.getSerialList().add(listaSerials.get(i));
                }
            }
        }


        for (i = 0; i < input.getCommands().size(); i++) {
            ActionInputData action = input.getCommands().get(i);
            if (action.getActionType().equals("command")) {
                if (action.getType() != null) {
                    if (action.getType().equals("favorite")) {
                        for (j = 0; j < listaUseri.size(); j++) {
                            if (listaUseri.get(j).getUsername()
                                    .equals(action.getUsername())) {
                                Favorite favorit =
                                        new Favorite(listaUseri.get(j), action.getTitle());
                                String m = favorit.fav();
                                String field = null;
                                JSONObject object =
                                        fileWriter.writeFile(action.getActionId(), field, m);
                                arrayResult.add(object);
                                break;
                            }
                        }
                    }


                    if (action.getType().equals("view")) {
                        for (k = 0; k < listaUseri.size(); k++) {
                            if (listaUseri.get(k).getUsername().equals(action.getUsername())) {
                                View view = new View(action.getTitle(), listaUseri.get(k));
                                String m = view.adaugareVideo(listaMovies);
                                String field = null;
                                JSONObject object = fileWriter.writeFile(action.getActionId(),
                                        field, m);
                                arrayResult.add(object);
                                break;
                            }
                        }
                    }

                    if (action.getType().equals("rating")) {
                        int id = action.getActionId();
                        Rating r = new Rating(action.getGrade());
                        String m;
                        if (action.getSeasonNumber() == 0) {
                            m = r.addRatingToMovie(action.getUsername(), action.getGrade(),
                                    action.getTitle(), listaUseri);
                            String field = null;
                            JSONObject object = fileWriter.writeFile(id, field, m);
                            arrayResult.add(object);
                        }
                        if (action.getSeasonNumber() > 0) {
                            m = r.addRatingToSerial(action.getUsername(), action.getGrade(),
                                    action.getTitle(), listaUseri, action.getSeasonNumber());
                            String field = null;
                            JSONObject object = fileWriter.writeFile(id, field, m);
                            arrayResult.add(object);
                        }
                    }
                }
            }
            if (action.getActionType().equals("query")) {
                if (action.getObjectType().equals("actors")) {
                    QueryActors q = new QueryActors(action.getNumber(),
                            action.getCriteria(), action.getSortType());
                    String m;
                    if (action.getCriteria().equals("average")) {
                        ArrayList<Double> listActorsAverage = q.actorsAverage(listaActori,
                                listaMovies);
                        m = q.messageTopAverageActors(listActorsAverage, listaActori,
                                action.getSortType(), action.getNumber());
                        int id = action.getActionId();
                        String field = null;
                        JSONObject object = fileWriter.writeFile(id, field, m);
                        arrayResult.add(object);
                    }
                    if (action.getCriteria().equals("awards")) {
                        m = q.awardsReturn(listaActori);
                        int id = action.getActionId();
                        String field = null;
                        JSONObject object = fileWriter.writeFile(id, field, m);
                        arrayResult.add(object);
                    }
                    if (action.getCriteria().equals("filter_description")) {
                        List<String> words = action.getFilters().get(3);
                        m = q.filterDescriptionActors(listaActori, words, action.getSortType());
                        int id = action.getActionId();
                        String field = null;
                        JSONObject object = fileWriter.writeFile(id, field, m);
                        arrayResult.add(object);
                    }

                }
                if (action.getObjectType().equals("movies")) {
                    QueryMovies q = new QueryMovies(action.getNumber(), action.getCriteria(),
                            action.getSortType());
                    String m;
                    if (action.getCriteria().equals("ratings")) {
                        m = q.ratingMovie();
                        int id = action.getActionId();
                        String field = null;
                        JSONObject object = fileWriter.writeFile(id, field, m);
                        arrayResult.add(object);
                    }
                    if (action.getCriteria().equals("longest")) {
                        String genre = action.getFilters().get(1).get(0);
                        List<String> year = action.getFilters().get(0);
                        m = q.longestCriteria(listaMovies, genre,
                                action.getNumber(), year, action.getSortType());
                        int id = action.getActionId();
                        String field = null;
                        JSONObject object = fileWriter.writeFile(id, field, m);
                        arrayResult.add(object);
                    }
                }
                if (action.getObjectType().equals("users")) {
                    String m;
                    QueryUsers q = new QueryUsers(action.getNumber(), action.getSortType());
                    m = q.usersRatingOrder(listaUseri);
                    int id = action.getActionId();
                    String field = null;
                    JSONObject object = fileWriter.writeFile(id, field, m);
                    arrayResult.add(object);
                }
            }
            if (action.getActionType().equals("recommendation")) {
                if (action.getType().equals("standard")) {
                    String m;
                    Standard s = new Standard(action.getUsername());
                    m = s.standardRecommand(listaUseri, action.getUsername(), listaMovies);
                    int id = action.getActionId();
                    String field = null;
                    JSONObject object = fileWriter.writeFile(id, field, m);
                    arrayResult.add(object);
                }
                if (action.getType().equals("popular")) {
                    String description = null;
                    for (User user : listaUseri) {
                        if (user.getUsername().equals(action.getUsername())) {
                            description = user.getSubscription();
                            break;
                        }
                    }
                    String m = null;
                    if (description != null && description.equals("BASIC")) {
                        m = "PopularRecommendation cannot be applied!";
                    }
                    int id = action.getActionId();
                    String field = null;
                    JSONObject object = fileWriter.writeFile(id, field, m);
                    arrayResult.add(object);

                }
            }
        }

        fileWriter.closeJSON(arrayResult);
    }

}
