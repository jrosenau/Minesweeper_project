module com.example.minesweeper_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.minesweeper_project to javafx.fxml;
    exports com.example.minesweeper_project;
}