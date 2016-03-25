/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: GameComponent.java
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Represents graphical components such as buttons,
 * panels, and textfield.
 */
public class GameComponent extends JComponent {
    private final JPanel gamePanel;
    private final JPanel userNamePanel;
    private final JPanel scorePanel;
    private final JPanel timePanel;
    private final JPanel buttonPanel;

    private final JButton playButton;
    private final JButton helpButton;
    private final JButton leaderBoardButton;
    private final JButton quitButton;
    private final JButton saveNameButton;

    private final JLabel scoreLabel;
    private final JLabel timeLabel;
    private final JLabel scoreValueLabel;
    private final JLabel timeValueLabel;
    private final JLabel usernameLabel;
    private final JTextField usernameTextField;
    private DrawMaze mazePanel;
    private final JLabel timeFormatLabel;
    
    /**
     * GameComponent constructor creates gamePanel
     * and adds the various components such as buttons, 
     * textfield, and other panels in the gamePanel.
     */
    public GameComponent() {
        gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(Color.BLACK);

        userNamePanel = new JPanel();
        usernameLabel = new JLabel("Your Name:");
        usernameTextField = new JTextField();
        saveNameButton = new JButton("Save");
        userNamePanel.setLayout(new BoxLayout(userNamePanel, BoxLayout.X_AXIS));
        userNamePanel.add(usernameLabel);
        userNamePanel.add(usernameTextField);
        userNamePanel.add(saveNameButton);
        userNamePanel.setBackground(Color.GREEN);

        scorePanel = new JPanel();
        scoreLabel = new JLabel("Score: ");
        scoreValueLabel = new JLabel();
        scoreValueLabel.setText("0.00");
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.add(scoreLabel);
        scorePanel.add(scoreValueLabel);
        scorePanel.setBackground(Color.GREEN);

        timePanel = new JPanel();
        timeLabel = new JLabel("Time Elapsed:");
        timeFormatLabel = new JLabel("HH:mm:ss");
        timeValueLabel = new JLabel();                         
        timeValueLabel.setText("00:00:00");
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));
        timePanel.add(timeLabel);
        timePanel.add(timeFormatLabel);
        timePanel.add(timeValueLabel);
        timePanel.setBackground(Color.GREEN);

        buttonPanel = new JPanel();
        playButton = new JButton("Play");
        helpButton = new JButton("Help");
        leaderBoardButton = new JButton("Leaderboard");
        quitButton = new JButton("Quit");
        buttonPanel.add(playButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(leaderBoardButton);
        buttonPanel.add(quitButton);
        buttonPanel.setBackground(Color.GREEN);

        gamePanel.add(userNamePanel, BorderLayout.NORTH);
        gamePanel.add(scorePanel, BorderLayout.EAST);
        gamePanel.add(timePanel, BorderLayout.WEST);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JButton getSaveNameButton() {
        return saveNameButton;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getHelpButton() {
        return helpButton;
    }

    public JButton getLeaderBoardButton() {
        return leaderBoardButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JPanel getLabelPanel() {
        return scorePanel;
    }

    public JPanel getUserNamePanel() {
        return userNamePanel;
    }

    public JPanel getScorePanel() {
        return scorePanel;
    }

    public JPanel getTimePanel() {
        return timePanel;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JLabel getTimeValueLabel() {
        return timeValueLabel;
    }
    
    public void updateTimeValueLabel(String timeValue) {
        this.timeValueLabel.setText(timeValue);
    }
    
    public JLabel getScoreValueLabel() {
        return scoreValueLabel;
    }
    
    public void updateScoreValueLabel(String scoreValue) {
        this.scoreValueLabel.setText(scoreValue);
    }    
}
