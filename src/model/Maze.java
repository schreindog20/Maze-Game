/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: Maze.java
 */

package model;

import java.io.*;
import java.util.Scanner;
/**
 * Represents the maze figure that is read from the text file.
 * 
 */
public class Maze {
    public final int START = 0;
    public final int WALL = 1;
    public final int SPACE = 2;
    public final int END = 3;
    private Scanner inputStream;
    public final int maxRow = 10;
    public final int maxCol = 10;
    public char [][] mazeFigure;
    
    /**
     * Maze constructor initilizes the 2-dimensional array
     * and calls the appropriate methods to open, read, and 
     * close the mazeFile.txt
     */
    public Maze(){
        mazeFigure = new char[maxRow][maxCol];
        openMazeFile();
        readMazeFile();
        closeMazeFile();
    }
    
    public final void openMazeFile() {
        try {
            inputStream = new Scanner(new FileInputStream("mazeFile.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }
    
    public final void readMazeFile() {
        int i, j;
        String line = null;
        char ch[];
        
        while (inputStream.hasNext()) {
            for (i = 0; i < mazeFigure.length; i++) {
                line = inputStream.nextLine();
                for (j = 0; j < mazeFigure[i].length; j++) {
                    ch = line.toCharArray();
                    mazeFigure[i][j] = ch[j];
                }
            }
        }
    }
    
    public final void closeMazeFile(){
        inputStream.close();
    }
    
    /**
     * Checks for the start, space, wall, and end attributes of the maze.
     * @param x
     * @param y
     * @return 
     */
    public int checkPosition(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            return WALL;
        }
        if (mazeFigure[y][x] == 'S' || mazeFigure[y][x] == 's') {
            return START;
        } else if (mazeFigure[y][x] == ' ') {
            return SPACE;
        } else if (mazeFigure[y][x] == '*') {
            return WALL;
        } else if (mazeFigure[y][x] == 'E') {
            return END;
        } else {
            return -123;
        }
    }
    
    /**
     * Sets the two player obejcts position within the maze.
     * @param playerObject1
     * @param playerObject2 
     */
    public void setPlayerPositionInsideMaze(Player playerObject1, Player playerObject2) {
        for (int y = 0; y < mazeFigure.length; y++) {
            for (int x = 0; x < mazeFigure[y].length; x++){
                if (mazeFigure[y][x] == 'S') {
                    playerObject1.setPosition(new Position(x,y));
                }
                if (mazeFigure[y][x] == 's') {
                    playerObject2.setPosition(new Position(x,y));
                }
            }
        }
    }
}
