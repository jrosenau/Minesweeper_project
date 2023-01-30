package com.example.minesweeper_project;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MinesweeperApplication is the main class for a minesweeper game in JavaFX.
 *
 * @author (Johannes Rosenauer)
 * @version (30.01.2023)
 */
public class MinesweeperApplication extends Application {
    /**
     * Y_FIELDS is the number of fields on the y-axis.
     */
    public static final int Y_FIELDS = 16;
    /**
     * X_FIELDS is the number of fields on the x-axis.
     */
    public static final int X_FIELDS = 16;
    /**
     * FIELD_SIZE is the size of each field.
     */
    public static final int FIELD_SIZE = 16;
    /**
     * grid is a 2D array of type Field, representing the game grid.
     */
    private static final com.example.minesweeper_project.Field[][] grid = new com.example.minesweeper_project.Field[X_FIELDS][Y_FIELDS];

    /**
     * Gets a list of neighbouring fields for a given field.
     *
     * @param field the field for which to get the neighbours.
     * @return a list of neighbouring fields.
     */
    static List<Field> getNeighbours(Field field) {
        List<Field> neighbours = new ArrayList<>();
        int[] points = new int[]{
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };
        for (int i = 0; i < points.length; i++) {
            int dx = points[i]; //delta X
            int dy = points[i + 1]; //delta Y
            int newX = field.getX() + dx; //get the field on the specific position
            int newY = field.getY() + dy;
            if (newX >= 0 && newX < X_FIELDS && newY >= 0 && newY < Y_FIELDS) {
                neighbours.add(grid[newX][newY]);
            }
            i = i + 1;
        }
        return neighbours;
    }

    /**
     * Creates the game field by adding fields to a Pane.
     *
     * @return the game field as a Parent object.
     */
    private Parent createField() {
        Pane root = new Pane();
        int counter;
        root.setPrefSize(256, 256);
        //1. Durchlauf: Platzierung der Bomben
        for (int y = 0; y < Y_FIELDS; y++) {
            for (int x = 0; x < X_FIELDS; x++) {
                Field field = new com.example.minesweeper_project.Field(x, y, Math.random() < 0.2);        //20% sollen Bomben sein! (Es liefert 20% true, 80% false)
                grid[x][y] = field;
                root.getChildren().add(field);
            }
        }

        //2. Durchlauf: Ermitteln angrenzender Bomben für jedes Feld
        for (int y = 0; y < Y_FIELDS; y++) {
            for (int x = 0; x < X_FIELDS; x++) {
                counter = 0;
                if (!grid[x][y].hasBomb) {
                    List<Field> neighbours = getNeighbours(grid[x][y]);
                    for (Field f : neighbours) {
                        if (f.hasBomb) {
                            counter++;
                        }
                    }
                    if (counter != 0) {
                        grid[x][y].bombCount.setText(String.valueOf(counter));
                    }
                }
            }
        }

        return root;
    }

    /**
     * The {@code start} method sets up the game window and sets the scene.
     * It creates the game board and starts the game.
     *
     * @param stage The JavaFX stage where the game window is created
     * @throws IOException if the game resources are not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createField());
        stage.setTitle("Minesweeper!");
        stage.setScene(scene);
        stage.show();
    }
}
