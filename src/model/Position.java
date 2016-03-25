/**
 * Group Name: Delta
 * CSU East Bay Fall 2015
 * Project: Maze Game
 * File name: Position.java
 */

package model;

/**
 * Represents x, y coordinated position 
 */
public class Position {
    protected int x, y; //x, y coordinates 
    
    public Position() {
        x = 0;
        y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    void changeX(int x) {
        this.x += x;
    }

    void changeY(int y) {
        this.y += y;
    }
    
}
