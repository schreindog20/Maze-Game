/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: GameFrame.java
 */
package view;

import controller.EventController;
import controller.TimeScoreListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import model.LeaderBoard;
import model.Maze;
import model.Player;
import model.User;

/**
 * Purpose: Creates window frame for maze game using the JFrame components.
 * The EventController class uses the methods of the GameFrame
 * class. 
 */
public class GameFrame {
    private final JFrame frame;
    private final int WIDTH = 595, HEIGHT = 600;  // in pixels
    private final GameComponent gameComponent;
    private final EventController eventController;
    private Timer elapsedTimer;
    private boolean gameEndsSignal;
    private final LeaderBoard leaderBoard = new LeaderBoard();
    
    /**
     * GameFrame Constructor loads the saved leaderboard to the arraylist,
     * then creates the window frame.
     * Initializes the gameComponent and eventController attributes.
     */
    public GameFrame() {
        this.loadLeaderBoardToArrayList();
        frame = new JFrame();
        frame.setTitle("Maze Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(100, 100);
        frame.setLayout(new BorderLayout());
        gameComponent = new GameComponent();
        frame.add(gameComponent.getGamePanel());
        frame.setVisible(true);
        eventController = new EventController(this, gameComponent, 
                gameComponent.getUsernameTextField(),
                gameComponent.getSaveNameButton(), gameComponent.getPlayButton(),
                gameComponent.getHelpButton(), gameComponent.getLeaderBoardButton(),
                gameComponent.getQuitButton());
    }
    
    /**
     * This method is called by eventController when user clicks on play button
     * It set up the game and draws the maze to the screen.
     * @param mazeModel
     * @param playerObject1
     * @param playerObject2 
     */
    public void startMazeGame(Maze mazeModel, Player playerObject1, 
            Player playerObject2) {
        DrawMaze drawMaze = new DrawMaze(mazeModel, playerObject1, 
                playerObject2, gameComponent);
        gameComponent.getGamePanel().add(drawMaze, BorderLayout.CENTER);
        drawMaze.drawing();
        frame.setVisible(true);
    }
    
    /**
     * This method is called when user clicks on Help button.
     * It reads the Help.txt file and displays it in the message dialog.
     * @throws IOException 
     */
    public void openHelpFile() throws IOException {
        String input = "";
        // Setup the reader
        try (BufferedReader reader = new BufferedReader(new FileReader("Help.txt"))) {
            String line = null;
            // Loop through every line in the .txt file
            while ((line = reader.readLine()) != null) {
                // Add the line and then "\n" indicating a new line
                input += line + "\n";
            }
        }
        JTextArea textArea = new JTextArea(input);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "HELP ", 
                JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * This method is called when the user clicks on Quit button,
     * and ends the program.
     */
    public void exitGame() {
        int answer = JOptionPane.showConfirmDialog(frame,
                "Are you sure to quit?");
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Creates and starts the timer for TimeScoreListener
     * @param mazeModel
     * @param playerObject1
     * @param playerObject2
     * @param user 
     */
    public void startElapsedTime(Maze mazeModel, Player playerObject1, 
            Player playerObject2, User user) {
        elapsedTimer = new Timer(1000, new TimeScoreListener(this, gameComponent, 
                mazeModel, playerObject1, playerObject2, user));
        elapsedTimer.start();
    }
    
    /**
     * Stops the elapsed time when the game is over.
     * Displays new dialog to confirm for exit or cancel.
     * If user clicks Yes then game is terminated.
     */
    public void stopElapsedTime() {
        elapsedTimer.stop(); 
        int answer = JOptionPane.showConfirmDialog(frame,
                "Game Over! Click Yes to Exit. Click Cancel to stay.");
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Opens and display the Leaderboard with username and their score
     * @throws IOException 
     */
    public void openLeaderBoardFile() throws IOException {
        String input = "";
        // Setup the reader
        try (BufferedReader reader = new BufferedReader(new FileReader("LeaderBoard.txt"))) {
            String line = null;
            // Loop through every line in the .txt file
            while ((line = reader.readLine()) != null) {
                // Add the line and then "\n" indicating a new line
                input += line + "\n";
            }
        }
        JTextArea textArea = new JTextArea(input);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "LEADERBOARD ", 
                JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Adds new user to the Leaderboard
     * @param user 
     */
    public void addNewUser(User user) {
        leaderBoard.addUser(user);
        leaderBoard.sortByHighestScore();
        try {
            leaderBoard.write();
        } catch (IOException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Loads the username and score from text files to the arrayList.
     */
    private void loadLeaderBoardToArrayList() {
        leaderBoard.read();
    }
}
