package com.example.minesweeper_project;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {

    private int x;
    private int y;
    public boolean hasBomb;
    private String text = "";
    public Text bombCount;
    private Rectangle fieldNode = null;
    public boolean gameOver = false;

    private boolean isOpen = false;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public Field(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        //Create Node //Rectangle oder Button für Feld:
        fieldNode = new Rectangle(MinesweeperApplication.FIELD_SIZE - 2, MinesweeperApplication.FIELD_SIZE - 2);
        fieldNode.setFill(Color.LIGHTBLUE);
        fieldNode.setStroke(Color.DARKBLUE);
        fieldNode.setVisible(true);


        bombCount = new Text();
        bombCount.setText(this.hasBomb ? "X" : ""); // => if else



        if (hasBomb){
            bombCount.setText("x");
        } else {
            bombCount.setText("");
        }



        bombCount.setStroke(Color.DARKRED);
        bombCount.setVisible(false); //field default not opened


        getChildren().addAll(fieldNode, bombCount); //Die Verdeckung und die Eigenschaften auf das Fieldobjekt schicken
        setTranslateX(x* MinesweeperApplication.FIELD_SIZE);
        setTranslateY(y* MinesweeperApplication.FIELD_SIZE);
        setOnMouseClicked(e -> onFieldClicked(e));



        }

    private void onFieldClicked(MouseEvent e) {

        if(e.getButton() == MouseButton.PRIMARY){
            open();
        } else if(e.getButton() == MouseButton.SECONDARY){

if(fieldNode.getFill() != Color.YELLOWGREEN){
    fieldNode.setFill(Color.YELLOWGREEN);
}else{
    fieldNode.setFill(Color.LIGHTBLUE);
}

        }

    }

    public void open(){
            if(this.isOpen)
                return;
            this.isOpen = true;
            bombCount.setVisible(true);
            fieldNode.setFill(Color.LIGHTGRAY);
            if(bombCount.getText().isEmpty()){
                MinesweeperApplication.getNeighbours(this).forEach(Field::open);
        }


/*if(bombCount == "X" || bombCount =="x"){
    gameOver = true;

    closeGame();
}

    }*/
}}
