package com.decimelli.gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HelloApplication extends Application {

    private static final int SIZE = 64;

    private final Lifeform[][] community = new Lifeform[SIZE][SIZE];
    private final GridPane grid = new GridPane();
    private final Duration d = Duration.seconds(0.1);

    public HelloApplication() {
        super();
        Random r = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.community[i][j] = new Lifeform(r, i, j);
                grid.add(this.community[i][j], i, j);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(grid);
        root.setAlignment(Pos.CENTER);
        Timeline timeline = new Timeline(new KeyFrame(d, (ActionEvent event) -> this.evolve()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        Scene scene = new Scene(root, 640, 640);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void evolve() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                NaturalSelection.decideFate(this.community, this.community[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private static class NaturalSelection {

        public static void decideFate(Lifeform[][] community, Lifeform lifeform) {
            Set<Lifeform> neighbours = new HashSet<>();
            for (int i = lifeform.getRow() - 1; i < lifeform.getRow() + 2; i++) {
                for (int j = lifeform.getCol() - 1; j < lifeform.getCol() + 2; j++) {
                    if ((i == lifeform.getRow() && j == lifeform.getCol()) // Skip itself
                            || i < 0 // Skip left bounds
                            || i >= SIZE  // Skip right bounds
                            || j < 0 // skip bottom bounds
                            || j >= SIZE) { // skip top bounds
                        continue; // Skip boundaries
                    }
                    neighbours.add(community[i][j]);
                }
            }
            long aliveNeightbourCount = neighbours.stream().filter(Lifeform::isAlive).count();
            if (lifeform.isAlive() && aliveNeightbourCount < 2) {
                lifeform.kill();
            } else if (lifeform.isAlive() && aliveNeightbourCount > 3) {
                lifeform.kill();
            } else if (!lifeform.isAlive() && aliveNeightbourCount == 3) {
                lifeform.reproduce();
            }
        }
    }
}