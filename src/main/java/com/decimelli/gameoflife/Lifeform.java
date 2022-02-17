package com.decimelli.gameoflife;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Lifeform extends Rectangle {

    private final int row;
    private final int col;

    Lifeform(Random r, int i, int j) {
        super();
        this.row = i;
        this.col = j;
        this.setWidth(5);
        this.setHeight(5);
        this.setX(5);
        this.setY(5);
        if (r.nextInt(7) == 0) { // One in 7 chance of living
            this.reproduce();
        } else {
            this.kill();
        }
    }

    public void reproduce() {
        this.setFill(Color.BLACK);
    }

    public void kill() {
        this.setFill(Color.WHITE);
    }

    public boolean isAlive() {
        return this.getFill().equals(Color.BLACK);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return (this.isAlive() ? "Alive" : "dead") + " Lifeform{" + row + "x" + col + '}';
    }
}