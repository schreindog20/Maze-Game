/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: User.java
 */

package model;

/**
 * Purpose: This class represents the user, holding variables for user name and
 * score, along with getter and setter methods for those values.
 */
public class User {

    private String userName; // username 
    private double score; // user's score

    /**
     * Default constructor for User Class. Set's userName to "" and score to 0.
     */
    public User() {
        this.userName = "";
        this.score = 0;
    }

    /**
     * Returns the User object's userName variable.
     *
     * @return the user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the User object's score variable.
     *
     * @return the user's score
     */
    public double getScore() {
        return score;
    }

    /**
     * Sets the User object's userName variable to the parameter.
     *
     * @param userName is the user's name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the User object's score variable to the parameter.
     *
     * @param score is the user's score
     */
    public void setScore(double score) {
        this.score = score;
    }

}
