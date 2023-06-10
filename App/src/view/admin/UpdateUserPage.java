package view.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.utils.ButtonComponent;
import controller.admin.UpdateUserController;

public class UpdateUserPage extends Application {
    private TextField idTextField;
    private TextField nameTextField;
    private TextField emailTextField;
    private TextField passwordTextField;
    private ComboBox<String> accessLevelComboBox;
    private Button searchButton;
    private Button updateButton;
    private ButtonComponent cancelButton;
    private UpdateUserController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        controller = new UpdateUserController(this);
        primaryStage.setTitle("Atualizar Usuário");
        primaryStage.setResizable(false);

        Label idLabel = new Label("Usuário ID:");
        idTextField = new TextField();
        searchButton = new ButtonComponent("Procurar", "#007bff", "white");

        Label nomeLabel = new Label("Nome:");
        nameTextField = new TextField();
        Label emailLabel = new Label("Email:");
        emailTextField = new TextField();
        Label senhaLabel = new Label("Senha:");
        passwordTextField = new TextField();

        Label acessoLabel = new Label("Acesso:");
        accessLevelComboBox = new ComboBox<>();
        accessLevelComboBox.getItems().addAll("1", "2");

        updateButton = new ButtonComponent("Atualizar", "#1E488F", "white");
        cancelButton = new ButtonComponent("Cancelar", "#dc3545", "white");

        controller = new UpdateUserController(this);

        searchButton.setOnAction(controller::handleSearchButton);
        updateButton.setOnAction(controller::handleUpdateButton);
        cancelButton.setOnAction(controller::handleCancelButton);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(searchButton, 2, 0);
        gridPane.add(nomeLabel, 0, 1);
        gridPane.add(nameTextField, 1, 1);
        gridPane.add(emailLabel, 0, 5);
        gridPane.add(emailTextField, 1, 5);
        gridPane.add(senhaLabel, 0, 6);
        gridPane.add(passwordTextField, 1, 6);
        gridPane.add(acessoLabel, 0, 7);
        gridPane.add(accessLevelComboBox, 1, 7);
        gridPane.add(updateButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public TextField getIdTextField() {
        return idTextField;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public ComboBox<String> getAccessLevelComboBox() {
        return accessLevelComboBox;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public ButtonComponent getCancelButton() {
        return cancelButton;
    }
}
