package query;

import entitati.User;

import java.util.ArrayList;

public final class QueryUsers {
    private final String sortType;

    public QueryUsers(final int number, final String sortType) {
        this.sortType = sortType;
    }

    public String usersRatingOrder(final ArrayList<User> users) {
        String message;
        ArrayList<Integer> numberRatings = new ArrayList<>();
        ArrayList<Integer> ordered;
        ArrayList<String> usernames = new ArrayList<>();
        int i, j;
        for (i = 0; i < users.size(); i++) {
            numberRatings.add(users.get(i).getNumberOfRatings());
        }
        if (this.sortType.equals("asc")) {
            ordered = bubblesortcresc(numberRatings);
        } else {
            ordered = bubblesortdescresc(numberRatings);
        }

        for (i = 0; i < ordered.size(); i++) {
            for (j = 0; j < users.size(); j++) {
                if (users.get(j).getNumberOfRatings() == ordered.get(i)) {
                    usernames.add(users.get(j).getUsername());
                }
            }
        }
        message = "Query result: [" + usernames + "]";
        return message;
    }

    public ArrayList<Integer> bubblesortcresc(final ArrayList<Integer> list) {
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
        return list;


    }

    public ArrayList<Integer> bubblesortdescresc(final ArrayList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) < list.get(j + 1)) {
                    // swap

                    list.set(j, list.get(j + 1));
                    list.set(j + 1, list.get(j));


                }
            }
        }
        return list;


    }
}
