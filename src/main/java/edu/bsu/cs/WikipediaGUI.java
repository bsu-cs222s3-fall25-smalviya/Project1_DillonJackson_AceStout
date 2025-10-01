package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WikipediaGUI extends Application {

    private static final int RESULTS_LIMIT = 15;

    @Override
    public void start(Stage stage) {
        TextField input = new TextField();
        Button button = new Button("Search");
        HBox top = new HBox(8, new Label("Search:"), input, button);

        TextArea output = new TextArea();
        output.setEditable(false);
        output.setPrefColumnCount(1000);
        output.setPrefRowCount(500);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(output);

        Runnable runSearch = () -> {
            String title = input.getText();
            if (title == null || title.isBlank()){
                showError("Error: No page requested.");
                return;
            }
            try {
                WikipediaSearcher searcher = new WikipediaSearcher();
                String json = searcher.getPageRevisions(title, RESULTS_LIMIT);

                if (json.contains("\"missing\":true") || json.contains("\"missing\": true")) {
                        showError("No page found");
                return;
                }

                JsonPathParser parser = new JsonPathParser();
                PageResults results = parser.parse(title, json);

                String text = new Formatter().format(results);
                output.setText(text);
            } catch (Errors.Network error) {
                showError("Error: Network error while contacting Wikipedia.");
            } catch (Errors.BadRequest error) {
                showError("Error: " + error.getMessage() );
            } catch (Exception error) {
                showError("Error: Something went wrong");
            }
        };


        button.setOnAction(error -> runSearch.run());
        input.setOnAction(error -> runSearch.run());

        stage.setTitle("Wikipedia Recent Changes\n");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
