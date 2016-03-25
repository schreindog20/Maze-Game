/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: ArrowKeyAction.java
 */

package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Maze;
import model.Player;
import view.GameComponent;

/**
 * Represents controller that listens to arrow key pressed 
 * by user and perform move action for player object
 * in the specific direction such as NORTH, EAST, WEST, SOUTH.
 */
public class ArrowKeyAction extends AbstractAction {

    private final int directionKey;
    private final Maze mazeModel;
    private final Player playerObject1;
    private final Player playerObject2;
    private final GameComponent gameComponent;

    public ArrowKeyAction(int directionKey, Maze mazeModel,
            Player playerObject1, Player playerObject2,
            GameComponent gameComponent) {
        this.directionKey = directionKey;
        this.mazeModel = mazeModel;
        this.playerObject1 = playerObject1;
        this.playerObject2 = playerObject2;
        this.gameComponent = gameComponent;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        playerObject1.move(mazeModel, directionKey);
        playerObject2.move(mazeModel, directionKey);
    }
}
