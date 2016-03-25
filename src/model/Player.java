/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: Player.java
 */

package model;

import java.util.Calendar;

/**
 * Represents the Player object that moves inside the maze.
 * 
 */
public class Player {

    private Position position;
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    public static final int SOUTH = 4;
    private final int dX = 1;
    private final int dY = 1;
    private Calendar time;
    private int steps = 0;
    private boolean moved;
    
    public Player() {
        this.steps = 0;
        this.moved = false;
    }
    
    /**
     * Player object moves in the appropriate direction based on its position
     * @param mazeModel
     * @param direction 
     */
    public void move(Maze mazeModel, int direction) {
        int tempDirection;
        moved = false;
        switch (direction) {
            case EAST:
                tempDirection = checkDirection(mazeModel, EAST);
                if (tempDirection == mazeModel.SPACE
                        || tempDirection == mazeModel.START
                        || tempDirection == mazeModel.END) {
                    moveEast(position.x);
                    moved = true;
                    steps++;
                }
                break;
            case SOUTH:
                tempDirection = checkDirection(mazeModel, SOUTH);
                if (tempDirection == mazeModel.SPACE
                        || tempDirection == mazeModel.START
                        || tempDirection == mazeModel.END) {
                    moveSouth(position.y);
                    moved = true;
                    steps++;
                }
                break;
            case WEST:
                tempDirection = checkDirection(mazeModel, WEST);
                if (tempDirection == mazeModel.SPACE
                        || tempDirection == mazeModel.START
                        || tempDirection == mazeModel.END) {
                    moveWest(position.x);
                    moved = true;
                    steps++;
                }
                break;
            case NORTH:
                tempDirection = checkDirection(mazeModel, NORTH);
                if (tempDirection == mazeModel.SPACE
                        || tempDirection == mazeModel.START
                        || tempDirection == mazeModel.END) {
                    moveNorth(position.y);
                    moved = true;
                    steps++;
                }
                break;
        }
    }

    private int checkDirection(Maze mazeModel, int direction) {
        switch (direction) {
            case EAST:
                return mazeModel.checkPosition(position.x + 1, position.y);
            case SOUTH:
                return mazeModel.checkPosition(position.x, position.y + 1);
            case WEST:
                return mazeModel.checkPosition(position.x - 1, position.y);
            case NORTH:
                return mazeModel.checkPosition(position.x, position.y - 1);
            default:
                return direction;
        }
    }
    
    public boolean hasMoved() {
        return moved;
    }
    
    public int getXcoordinate() {
        return position.getX();
    }

    public int getYcoordinate() {
        return position.getY();
    }

    void setPosition(Position position) {
        this.position = position;
    }

    private void moveNorth(int y) {
        this.position.y -= dY;
    }

    private void moveWest(int x) {
        this.position.x -= dX;
    }

    private void moveSouth(int y) {
        this.position.y += dY;
    }

    private void moveEast(int x) {
        this.position.x += dX;
    }

    public int getStepsTaken() {
        return this.steps;
    }    
}
