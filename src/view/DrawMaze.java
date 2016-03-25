/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: DrawMaze.java
 */
package view;

import controller.ArrowKeyAction;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import model.Maze;
import model.Player;
import static model.Player.EAST;
import static model.Player.NORTH;
import static model.Player.SOUTH;
import static model.Player.WEST;

/**
 * Purpose: Creates the panel that displays the graphical view of 
 * the maze.
 */
public class DrawMaze extends JPanel implements ActionListener {

    private final Maze mazeModel;
    private final Player playerObject1, playerObject2;
    private final GameComponent gameComponent;
    private Image startIcon, spaceIcon, wallIcon, endIcon, playerIcon;
    private ImageIcon icon;
    private Action upKeyAction;
    private Action rightKeyAction;
    private Action leftKeyAction;
    private Action downKeyAction;
    private final Timer timer = new Timer(10, this);

    public DrawMaze(Maze mazeModel, Player playerObject1, Player playerObject2,
            GameComponent gameComponent) {
        this.mazeModel = mazeModel;
        this.playerObject1 = playerObject1;
        this.playerObject2 = playerObject2;
        this.gameComponent = gameComponent;
        this.timer.start();
        this.createImageIcon();
        this.createArrowKeyAction();
        this.requestFocusInWindow(true);
    }

    private void createImageIcon() {
        icon = new ImageIcon("start.jpg");
        startIcon = icon.getImage();
        icon = new ImageIcon("grass.jpg");
        spaceIcon = icon.getImage();
        icon = new ImageIcon("wall.jpg");
        wallIcon = icon.getImage();
        icon = new ImageIcon("spaceship.jpg");
        endIcon = icon.getImage();
        icon = new ImageIcon("alien.jpg");
        playerIcon = icon.getImage();
    }
    
    public Image getStartIcon() {
        return startIcon;
    }
    public Image getSpaceIcon() {
        return spaceIcon;
    }

    public Image getWallIcon() {
        return wallIcon;
    }

    public Image getEndIcon() {
        return endIcon;
    }

    private Image getPlayerIcon() {
        return playerIcon;
    }

    public void drawing() {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
        if (mazeModel.checkPosition(playerObject1.getXcoordinate(),
                    playerObject1.getYcoordinate()) == mazeModel.END &&
                mazeModel.checkPosition(playerObject2.getXcoordinate(),
                    playerObject2.getYcoordinate()) == mazeModel.END) {
            timer.stop();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (mazeModel.checkPosition(x, y) == mazeModel.START) {
                    g2.drawImage(getStartIcon(), x * 50, y * 50, null);
                } else if (mazeModel.checkPosition(x, y) == mazeModel.SPACE) {
                    g2.drawImage(getSpaceIcon(), x * 50, y * 50, null);
                } else if (mazeModel.checkPosition(x, y) == mazeModel.WALL) {
                    g2.drawImage(getWallIcon(), x * 50, y * 50, null);
                } else if (mazeModel.checkPosition(x, y) == mazeModel.END) {
                    g2.drawImage(getEndIcon(), x * 50, y * 50, null);
                }
            }
        }
        g2.drawImage(getPlayerIcon(), playerObject1.getXcoordinate() * 50,
                playerObject1.getYcoordinate() * 50, this);
        g2.drawImage(getPlayerIcon(), playerObject2.getXcoordinate() * 50,
                playerObject2.getYcoordinate() * 50, this);
    }
    
    /**
     * Creates the arrow key binding actions
     * for up, left, right, and down buttons
     */
    private void createArrowKeyAction() {
        upKeyAction = new ArrowKeyAction(NORTH, mazeModel, playerObject1,
                playerObject2, gameComponent);
        rightKeyAction = new ArrowKeyAction(EAST, mazeModel, playerObject1, 
                playerObject2, gameComponent);
        leftKeyAction = new ArrowKeyAction(WEST, mazeModel, playerObject1, 
                playerObject2, gameComponent);
        downKeyAction = new ArrowKeyAction(SOUTH, mazeModel, playerObject1, 
                playerObject2, gameComponent);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), NORTH);
        this.getActionMap().put(NORTH, upKeyAction);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), EAST);
        this.getActionMap().put(EAST, rightKeyAction);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), WEST);
        this.getActionMap().put(WEST, leftKeyAction);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), SOUTH);
        this.getActionMap().put(SOUTH, downKeyAction);
    }
}
