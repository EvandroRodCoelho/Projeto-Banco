package view.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.utils.AlertUtil;
import view.utils.ButtonComponent;

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
        clientIdTextField.setPromptText("Cliente ID");
        layout.getChildren().add(clientIdTextField);

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        searchButton = new ButtonComponent("Pesquisar", "#1E488F", "white");
        searchButton.setOnAction(e -> handleSearchButton(clientIdTextField.getText()));
        buttonBox.getChildren().add(searchButton);

        cancelButton = new ButtonComponent("Cancelar", "#dc3545", "white");
        cancelButton.setOnAction(e -> handleCancelButton());
        buttonBox.getChildren().add(cancelButton);

        layout.getChildren().add(buttonBox);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSearchButton(String idClient) {
        if (idClient.isEmpty()) {
            AlertUtil.showErrorAlert(primaryStage, "Digite um id");
            return;
        }
        PrintData printData = new PrintData(idClient,primaryStage);
    }

    private void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }
}