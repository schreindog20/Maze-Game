/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: TimeScoreListener.java
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Maze;
import model.Player;
import model.User;
import view.GameComponent;
import view.GameFrame;

/**
 * TimeScoreListener represents and implements ActionListener for displaying
 * time elapsed since the game has started. It act as a controller to update the
 * time that is constantly running.
 */
public class TimeScoreListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameComponent gameComponent;
    private final Maze mazeModel;
    private final Player playerObject1, playerObject2;
    private final User user;
    private final SimpleDateFormat timeFormat;
    private final long startTime;
    private long elapsedTime;
    private Calendar calendar;
    private int seconds;
    private double currentScore;
    private final double highestScorePossible = 100.0;
    private final int interval = 10;
    private int stepsTaken = 0;
    String score;
    private final DecimalFormat decimalFormat;

    /**
     * TimeListener Constructor sets the gameFrame, gameComponent, mazeModel,
     * playerObject1, and playerObject2. Creates SimpleDateFormat object to
     * format the time, and initializes the startTime to current time (in
     * milliseconds).
     *
     * @param gameFrame
     * @param gameComponent
     * @param mazeModel
     * @param playerObject1
     * @param playerObject2
     * @param user
     */
    public TimeScoreListener(GameFrame gameFrame,
            GameComponent gameComponent, Maze mazeModel,
            Player playerObject1, Player playerObject2, User user) {
        this.gameFrame = gameFrame;
        this.gameComponent = gameComponent;
        this.mazeModel = mazeModel;
        this.playerObject1 = playerObject1;
        this.playerObject2 = playerObject2;
        this.user = user;
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");
        this.startTime = System.currentTimeMillis();
        this.seconds = 0;
        this.currentScore = 0;
        this.score = "";
        this.decimalFormat = new DecimalFormat("0.00");

    }

    /**
     * actionPerformed method operates when the maze game is in play mode. It
     * gets the instance of calendar and sets instances HOUR_OF_DAY, MINUTE, and
     * SECOND to zero. Calculates elapsed time and update the time value
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long nowTime = System.currentTimeMillis();
        elapsedTime = nowTime - this.startTime;
        calendar.setTimeInMillis(calendar.getTime().getTime() + elapsedTime);
        gameComponent.updateTimeValueLabel(timeFormat.format(calendar.getTime()));
        
        
        /*Algorithm for score starts here*/
        seconds++;
        if (playerObject1.hasMoved() || playerObject2.hasMoved()) {
            stepsTaken++;
        }

        if (stepsTaken <= 0) {
            currentScore = 0;
        } else if (stepsTaken == 1) {
            currentScore = highestScorePossible;
        }
        if (seconds % 10 == 0) {
            currentScore -= 5;
        }
        score = decimalFormat.format(currentScore);
        /*algorithm for score ends here*/

        gameComponent.updateScoreValueLabel(score + "");
        if (mazeModel.checkPosition(playerObject1.getXcoordinate(),
                playerObject1.getYcoordinate()) == mazeModel.END
                && mazeModel.checkPosition(playerObject2.getXcoordinate(),
                        playerObject2.getYcoordinate()) == mazeModel.END) {
            gameFrame.stopElapsedTime();
            user.setScore(currentScore);
            gameFrame.addNewUser(user);
        }
    }
}
