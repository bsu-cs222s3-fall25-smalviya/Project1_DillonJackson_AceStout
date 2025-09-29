package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GUI extends Application {

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
            try {
                WikipediaSearcher searcher = new WikipediaSearcher();
                String json = searcher.getPageRevisions(title, RESULTS_LIMIT);

                if (json.contains("\"missing\":true") || json.contains("\"missing\": true")) {
                        output.setText("No page found");
                return;
                }

                JsonPathParser parser = new JsonPathParser();
                PageResults results = parser.parse(title, json);

                String text = new Formatter().format(results);
                output.setText(text);
            } catch (Errors.Network error) {
                output.setText("Error: Network error while contacting Wikipedia.");
            } catch (Errors.BadRequest error) {
                output.setText("Error: " + error.getMessage() );
            } catch (Exception error) {
                output.setText("Error: Something went wrong");
            }
        };


        button.setOnAction(error -> runSearch.run());
        input.setOnAction(error -> runSearch.run());

        stage.setTitle("Wikipedia Recent Changes\n");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
