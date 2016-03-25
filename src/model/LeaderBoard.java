
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * Purpose: This class represents the LeaderBoard of high scores. It contains an
 * ArrayList of the User objects with the highest scores and sorts them in order
 * of highest score.
 */
public class LeaderBoard {

    /**
     * List of Users with high scores
     */
    private ArrayList<User> userScoreList;

    /**
     * Default constructor for the LeaderBoard class. Instantiates its
     * ArrayList<User> with its default constructor.
     */
    public LeaderBoard() {
        userScoreList = new ArrayList<User>();
        //this.read();
    }

    /**
     * If the User has a high score (top 10), then it is added to the
     * LeaderBoard object's ArrayList. Once the User object is added to the
     * list, the list is sorted by calling the sortByHighScore() method.
     *
     * @param user is the User object attempting to be added to the LeaderBoard
     */
    public void addUser(User user) {
        
        userScoreList.add(user);

    }

    /**
     * Sorts the LeaderBoard object's ArrayList of User objects (consisting of
     * users with the top 10 high scores). Implements a selection sort (easy to
     * code / limited size of list so not much efficiency lost.
     */
    public void sortByHighestScore() {
        User max = new User();
        User temp = null;
        User current = null;
        User next = null;

        if (userScoreList.size() > 1) {
            for (int i = 0; i < userScoreList.size(); i++) {
                for (int j = 0; j < userScoreList.size() - 1; j++) {
                    current = userScoreList.get(i);
                    next = userScoreList.get(j + 1);
                    Double a = userScoreList.get(i).getScore();
                    Double b = userScoreList.get(j + 1).getScore();
                    if (a.compareTo(b) <= 0) {
                        userScoreList.set(i, next);
                        userScoreList.set(j + 1, current);
                    }
                }
            }
        }
        System.out.println("\nsorted....");
        for (int i = 0; i < userScoreList.size(); i++) {
            System.out.println(userScoreList.get(i).getUserName() + ""
                    + userScoreList.get(i).getScore());
        }
    }

    /**
     * Returns the LeaderBoard object's ArrayList of User objects.
     *
     * @return the ArrayList<User> of user's with high scores (sorted)
     */
    public ArrayList<User> getUserScoreList() {
        return userScoreList;
    }

    /**
     * Reads the username and it's belonging score from text files and adds it
     * the arraylist.
     */
    public final void read() {
        Scanner inputStreamNames = null;
        Scanner inputStreamScore = null;
        try {
            String username;
            double score;
            inputStreamNames = new Scanner(new FileInputStream("LeaderboardNames.txt"));
            inputStreamScore = new Scanner(new FileInputStream("LeaderboardScores.txt"));
            while (inputStreamNames.hasNext()) {
                User usr = new User();
                usr.setUserName(inputStreamNames.next());
                usr.setScore(inputStreamScore.nextDouble());
                userScoreList.add(usr);
            }
            inputStreamNames.close();
            inputStreamScore.close();
        } catch (FileNotFoundException e) {
        }
    }

    public void write() throws IOException {

        PrintWriter outputStreamNames = null;
        PrintWriter outputStreamScore = null;
        PrintWriter outputStreamTable = null;
        try {
            outputStreamNames = new PrintWriter(new FileOutputStream("LeaderboardNames.txt", false), false);
            outputStreamScore = new PrintWriter(new FileOutputStream("LeaderboardScores.txt", false), false);
            outputStreamTable = new PrintWriter(new FileOutputStream("LeaderBoard.txt", false), false);
            outputStreamTable.println("Username" + "\t| " + "Score");
            outputStreamTable.println("---------------------------------------");

            for (int i = 0; i < userScoreList.size(); i++) {
                if (userScoreList.get(i) != null) {
                    User user = userScoreList.get(i);
                    outputStreamNames.println(user.getUserName());
                    outputStreamScore.println(user.getScore());
                    outputStreamTable.println(user.getUserName() + "\t| " + user.getScore());
                }
            }
            outputStreamNames.flush();
            outputStreamNames.close();
            outputStreamScore.flush();
            outputStreamScore.close();
            outputStreamTable.flush();
            outputStreamTable.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }
}
