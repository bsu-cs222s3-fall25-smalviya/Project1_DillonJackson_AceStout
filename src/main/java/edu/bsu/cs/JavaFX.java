package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JavaFX extends Application {

    private static final int RESULTS_LIMIT = 15;

    @Override
    public void start(Stage stage) {
        TextField input = new TextField();
        Button button = new Button("Search");
        HBox top = new HBox(8, new Label("Search:"), input, button);

        TextArea output = new TextArea();
        output.setEditable(false);
        output.setPrefColumnCount(120);
        output.setPrefRowCount(40);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(output);

        Runnable runSearch = () -> {
            String title = input.getText();
            if (title == null || title.isBlank()){
                output.setText("Error: No page requested.");
                return;
            }
        }
    }
}
