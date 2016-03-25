/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: DeltaGroupMazeGameProject.java
 */
package deltagroupmazegameproject;

import view.GameFrame;
import java.awt.*;

public class DeltaGroupMazeGameProject {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame gameFrame = new GameFrame();
            }
        });
    }
}
