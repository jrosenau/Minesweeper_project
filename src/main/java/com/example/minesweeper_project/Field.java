package com.example.minesweeper_project;

import com.example.minesweeper_project.HelloApplication;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Field extends StackPane {

    private int x;
    private int y;
    private boolean hasBomb;
    private String text = "";
    public Text bombCount;
    private Rectangle fieldNode = null;

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
        fieldNode = new Rectangle(HelloApplication.FIELD_SIZE - 2, HelloApplication.FIELD_SIZE - 2);
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
        bombCount.setVisible(true); //field default not opened






        getChildren().addAll(fieldNode, bombCount); //Die Verdeckung und die Eigenschaften auf das Fieldobjekt schicken
        setTranslateX(x* HelloApplication.FIELD_SIZE);
        setTranslateY(y*HelloApplication.FIELD_SIZE);
        setOnMouseClicked(e -> onFieldClicked(e));



        }

    private void onFieldClicked(MouseEvent e) {
        open();
    }





        public void open(){
            if(this.isOpen)
                return;
            this.isOpen = true;
            bombCount.setVisible(true);
            fieldNode.setFill(Color.LIGHTGRAY);
            if(bombCount.getText().isEmpty()){
                HelloApplication.getNeighbours(this).forEach(Field::open);
        }



    }
}
