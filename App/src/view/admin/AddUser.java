package view.admin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.database.Conn;
import view.utils.ButtonComponent;
import view.utils.AlertUtil;

import java.sql.SQLException;

import controller.utils.validador.ValidadorAccountUser;
import controller.utils.validador.ValidadorAccountUser.ValidationException;

public class AddUser extends Application {
    private Stage stage;

    private TextField nameTextField, emailTextField, passwordTextField, accessLevelTextField;
    private Button cancelButton;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Adicionar Usuário");

        GridPane gridPane = createGridPane();
        addUIControls(gridPane);

        Scene scene = new Scene(gridPane, 900, 700);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        Label titleLabel = new Label("Adicionar Usuário");
        titleLabel.setFont(new Font("serif", 25));
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label nomeLabel = new Label("Nome:");
        gridPane.add(nomeLabel, 0, 1);

        nameTextField = new TextField();
        gridPane.add(nameTextField, 1, 1);

        Label EmailLabel = new Label("Email:");
        gridPane.add(EmailLabel, 0, 2);

        emailTextField = new TextField();
        gridPane.add(emailTextField, 1, 2);

        Label senhaLabel = new Label("Senha:");
        gridPane.add(senhaLabel, 0, 3);

        passwordTextField = new TextField();
        gridPane.add(passwordTextField, 1, 3);

        Label acessoLabel = new Label("Acesso:");
        gridPane.add(acessoLabel, 0, 4);

        accessLevelTextField = new TextField();
        gridPane.add(accessLevelTextField, 1, 4);

        Button submitButton = new ButtonComponent("Enviar", "#1E488F", "white");
        HBox submitHBox = new HBox(10);
        submitHBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitHBox.getChildren().add(submitButton);
        gridPane.add(submitHBox, 0, 12, 2, 1);

        submitButton.setOnAction(e -> insertEmployeeDetails());
        Button addButton = new ButtonComponent("Adicionar", "#1E488F", "white");
        addButton.setOnAction(e -> {
            insertEmployeeDetails();
        });
    
        HBox addButtonHBox = new HBox(10);
        addButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        addButtonHBox.getChildren().add(addButton);
    
        cancelButton = new ButtonComponent("Cancelar", "red", "white");
        cancelButton.setOnAction(this::handleCancelButton);
    
        HBox cancelHBox = new HBox(10);
        cancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        cancelHBox.getChildren().addAll(addButtonHBox, cancelButton);
    
        gridPane.add(cancelHBox, 1, 12);
    }

    private void insertEmployeeDetails() {
        setInputsAndButtonsEnabled(false);
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String accessLevel = accessLevelTextField.getText();
  

        try {
            ValidadorAccountUser.validateName(name);
            ValidadorAccountUser.validateEmail(email);
            ValidadorAccountUser.validatePassword(password);
            
            Conn conn = new Conn();
            String query = "INSERT INTO usuario (nome, email, senha, acesso) VALUES ('"
                    + name + "', '" + email + "', '" + password + "', '" + Integer.parseInt(accessLevel) + "')";
            int rowsAffected = conn.getStatement().executeUpdate(query);

            if (rowsAffected > 0) {
                AlertUtil.showSuccessAlert(stage, "Adicionado com sucesso");
            }
            conn.close();
            clearInputs();
        } catch (SQLException e) {
            AlertUtil.showErrorAlert(stage, "Ocorreu um erro");
        } catch (ValidationException e) {
            AlertUtil.showErrorAlert(null, e.getMessage());
        }
        setInputsAndButtonsEnabled(true);
    }

    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }
    
    private void setInputsAndButtonsEnabled(boolean enabled) {
        nameTextField.setDisable(!enabled);
        emailTextField.setDisable(!enabled);
        passwordTextField.setDisable(!enabled);
        accessLevelTextField.setDisable(!enabled);
    }
    
    private void clearInputs() {
        nameTextField.clear();
        emailTextField.clear();
        passwordTextField.clear();
        accessLevelTextField.clear();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
