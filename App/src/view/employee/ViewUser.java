package view.employee;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewUser extends Application {

    private Stage primaryStage;
    private TextField clientIdTextField;
    private Label label;
    private Button searchButton;
    private Button cancelButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("View Cliente");

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));

        label = new Label("Cliente ID");
        label.setStyle("-fx-font-size: 20;");
        layout.getChildren().add(label);

        clientIdTextField = new TextField();
        clientIdTextField.setPromptText("Enter cliente ID");
        layout.getChildren().add(clientIdTextField);

        searchButton = new Button("Search");
        searchButton.setOnAction(e -> handleSearchButton(clientIdTextField.getText()));
        layout.getChildren().add(searchButton);

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> handleCancelButton());
        layout.getChildren().add(cancelButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSearchButton(String idClient) {
        
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new PrintData(idClient).start(new Stage());
    }
    private void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
        
    }
}
