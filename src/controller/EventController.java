/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: EventController.java
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import model.Maze;
import model.Player;
import model.User;
import view.GameComponent;
import view.GameFrame;

public class EventController implements ActionListener {
    
    private final GameFrame gameFrame;
    private final JButton saveNameButton;
    private final JButton playButton;
    private final JButton helpButton;
    private final JButton leaderboardButton;
    private final JButton quitButton;
    private final JTextField userNameTextField;
    private final GameComponent gameComponent;
    private User user;
    public EventController(GameFrame gameFrame, GameComponent gc,
            JTextField nameField, JButton saveNameButton, JButton playButton,
            JButton helpButton, JButton leaderboardButton, JButton quitButton) {

        this.gameComponent = gc;
        this.gameFrame = gameFrame;
        this.userNameTextField = nameField;
        this.saveNameButton = saveNameButton;
        this.playButton = playButton;
        this.helpButton = helpButton;
        this.leaderboardButton = leaderboardButton;
        this.quitButton = quitButton;
        this.saveNameButton.addActionListener(this);
        this.playButton.addActionListener(this);
        this.helpButton.addActionListener(this);
        this.leaderboardButton.addActionListener(this);
        this.quitButton.addActionListener(this);

        this.disablePlayButton();
        this.enableSaveNameButton();
        this.enableHelpButton();
        this.enableLeaderBoardButton();
        this.enableQuitButton();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == saveNameButton) {
            this.enablePlayButton();
            this.disableSaveNameButton();
            this.disableUserNameTextField();
            String username = userNameTextField.getText();
            this.user = new User();
            this.user.setUserName(username);
        } else if (ae.getSource() == playButton) {
            this.disablePlayButton();
            Maze mazeModel = new Maze();
            Player playerObject1 = new Player();
            Player playerObject2 = new Player();
            mazeModel.setPlayerPositionInsideMaze(playerObject1, playerObject2);
            gameFrame.startMazeGame(mazeModel, playerObject1, playerObject2);
            gameFrame.startElapsedTime(mazeModel, playerObject1, playerObject2, user);
        } else if (ae.getSource() == helpButton) {
            try {
                gameFrame.openHelpFile();
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (ae.getSource() == leaderboardButton) {
            try {
                gameFrame.openLeaderBoardFile();
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (ae.getSource() == quitButton) {
            gameFrame.exitGame();
        } else {
            /*do nothiing */
        }
    }

    private void disablePlayButton() {
        this.playButton.setEnabled(false);
    }

    private void enablePlayButton() {
        this.playButton.setEnabled(true);
    }

    private void enableHelpButton() {
        this.helpButton.setEnabled(true);
    }

    private void enableLeaderBoardButton() {
        this.leaderboardButton.setEnabled(true);
    }

    private void enableQuitButton() {
        this.quitButton.setEnabled(true);
    }

    private void enableSaveNameButton() {
        this.saveNameButton.setEnabled(true);
    }

    private void disableSaveNameButton() {
        this.saveNameButton.setEnabled(false);
    }

    private void disableUserNameTextField() {
        this.userNameTextField.setEnabled(false);
    }
    
}
