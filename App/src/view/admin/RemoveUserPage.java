package view.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.utils.ButtonComponent;
import controller.admin.RemoveUserController;

public class RemoveUserPage extends Application {
    private TextField idTextField;
    private Label idLabel, nameLabel, emailLabel, passwordLabel, acessoLabel;
    private Button searchButton, removeButton, cancelButton;
    private RemoveUserController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Remover usuário");
        primaryStage.setResizable(false);

        idLabel = new Label("Usuário ID:");
        idTextField = new TextField();
        searchButton = new ButtonComponent("Procurar", "#007bff", "white");

        nameLabel = new Label("Nome:");
        emailLabel = new Label("Email:");
        passwordLabel = new Label("Senha:");
        acessoLabel = new Label("Acesso:");

        removeButton = new ButtonComponent("Remover", "#dc3545", "white");
        cancelButton = new ButtonComponent("Cancelar", "#6c757d", "white");

        controller = new RemoveUserController(this);

        searchButton.setOnAction(controller::handleSearchButton);
        removeButton.setOnAction(controller::handleRemoveButton);
        cancelButton.setOnAction(controller::handleCancelButton);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(searchButton, 2, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(acessoLabel, 0, 4);
        gridPane.add(removeButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

        Scene scene = new Scene(gridPane, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public TextField getIdTextField() {
        return idTextField;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public Label getAcessoLabel() {
        return acessoLabel;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
