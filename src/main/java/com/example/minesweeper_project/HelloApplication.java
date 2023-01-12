package com.example.minesweeper_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import java.text.FieldPosition;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    public static final int Y_FIELDS = 16;
    public static final int X_FIELDS = 16;


    private static com.example.minesweeper_project.Field [][] grid = new com.example.minesweeper_project.Field[X_FIELDS][Y_FIELDS];
    public static final int FIELD_SIZE = 16;


    static List<Field> getNeighbours(Field field){
        List<Field> neighbours = new ArrayList<>();


        int[] points = new int[]{
                -1,-1,
                -1,0,
                -1,1,
                0,-1,
                0,1,
                1,-1,
                1,0,
                1,1
        };
        for (int i = 0; i<points.length; i++){
            int dx = points[i];     //delta X
            int dy = points[i+1];   //delta Y

            int newX = field.getX()+dx;     //get the field on the specific position
            int newY = field.getY()+dy;




            if(newX >= 0 && newX <X_FIELDS && newY >= 0 && newY < Y_FIELDS){
                neighbours.add(grid[newX][newY]);


            }
            i=i+1;
        }
        return neighbours;
    }
    private Parent createField(){
        Pane root = new Pane();
        root.setPrefSize(320, 320);

        for (int y = 0; y < Y_FIELDS; y++) {
            for (int x = 0; x < X_FIELDS; x++){
                Field field = new com.example.minesweeper_project.Field(x,y,Math.random() < 0.2);        //20% sollen Bomben sein! (Es liefert 20% true, 80% false)
                grid[x][x]=field;
                root.getChildren().add(field);//

            }
        }
        return root;
    }



    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createField());
        stage.setTitle("Minesweeper!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}