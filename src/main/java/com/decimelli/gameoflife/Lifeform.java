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
        this.setWidth(10);
        this.setHeight(10);
        this.setX(10);
        this.setY(10);
        if (r.nextInt(10) == 0) { // One in 10 chance of living
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