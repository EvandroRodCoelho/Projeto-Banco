package view.admin;

import java.sql.ResultSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.utils.AlertUtil;
import view.utils.ButtonComponent;

public class UpdatePage extends Application {
    private TextField idTextField;
    private TextField nomeTextField;
    private TextField emailTextField;
    private TextField senhaTextField;
    private TextField acessoTextField;
    private Button searchButton;
    private Button updateButton;
    private ButtonComponent cancelButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Update Cliente");
        primaryStage.setResizable(false);

        // Create UI controls
        Label idLabel = new Label("Usuário ID:");
        idTextField = new TextField();
        searchButton = new ButtonComponent("Procurar", "#007bff", "white");

        Label nomeLabel = new Label("Nome:");
        nomeTextField = new TextField();
        Label emailLabel = new Label("Email:");
        emailTextField = new TextField();
        Label senhaLabel = new Label("Senha:");
        senhaTextField = new TextField();
        Label acessoLabel = new Label("Acesso:");
        acessoTextField = new TextField();

        updateButton = new ButtonComponent("Atualizar", "#1E488F", "white");
        cancelButton = new ButtonComponent("Cancelar", "#dc3545", "white");

        // Configure event handlers
        searchButton.setOnAction(this::handleSearchButton);
        updateButton.setOnAction(this::handleUpdateButton);
        cancelButton.setOnAction(this::handleCancelButton);

        // Create grid pane and set its properties
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Add UI controls to grid pane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(searchButton, 2, 0);
        gridPane.add(nomeLabel, 0, 1);
        gridPane.add(nomeTextField, 1, 1);
        gridPane.add(emailLabel, 0, 5);
        gridPane.add(emailTextField, 1, 5);
        gridPane.add(senhaLabel, 0, 6);
        gridPane.add(senhaTextField, 1, 6);
        gridPane.add(acessoLabel, 0, 7);
        gridPane.add(acessoTextField, 1, 7);
        gridPane.add(updateButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

        // Create scene and set it in the stage
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSearchButton(ActionEvent event) {
        String employeeId = idTextField.getText();
        if (!employeeId.isEmpty()) {
            try {
                // Execute SQL query to retrieve employee information
                String query = "SELECT * FROM usuario WHERE id = '" + employeeId + "'";
                conn c1 = new conn();
                ResultSet result = c1.st.executeQuery(query);
                if (result.next()) {
                    String nome = result.getString("nome");
                    String email = result.getString("email");
                    String senha = result.getString("senha");
                    String acesso = result.getString("acesso");

                    // Update UI text fields with retrieved information
                    nomeTextField.setText(nome);
                    emailTextField.setText(email);
                    senhaTextField.setText(senha);
                    acessoTextField.setText(acesso);
                } else {
                    AlertUtil.showErrorAlert(null, "Cliente não achado");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Digite um id");
        }
    }

    private void handleUpdateButton(ActionEvent event) {
        String id = idTextField.getText();
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String senha = senhaTextField.getText();
        String acesso = acessoTextField.getText();

        if (!id.isEmpty()) {
            try {
                // Execute SQL query to update the employee details
                String query = "UPDATE usuario SET nome='" + nome + "', email='" + email + "', senha='" + senha + "', acesso='" + acesso + "' WHERE id='" + id + "'";
                conn c1 = new conn();
                int rowsAffected = c1.st.executeUpdate(query);

                if (rowsAffected > 0) {
                    // Employee details updated successfully
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atualizar dados do Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Atualizado com sucesso!");
                    alert.showAndWait();

                    // Clear text fields
                    idTextField.setText("");
                    nomeTextField.setText("");
                    emailTextField.setText("");
                    senhaTextField.setText("");
                    acessoTextField.setText("");
                } else {
                    // Employee not found, show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Atualizar dados do Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuário não encontrado!");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }
}
