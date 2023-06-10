package view.admin;

import java.sql.ResultSet;

import controller.utils.validador.ValidadorAccountUser;
import controller.utils.validador.ValidadorAccountUser.ValidationException;
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
import model.database.Conn;
import view.utils.AlertUtil;
import view.utils.ButtonComponent;

public class UpdatePage extends Application {
    private TextField idTextField;
    private TextField nameTextField;
    private TextField emailTextField;
    private TextField passwordTextField;
    private TextField accessLevelTextField;
    private Button searchButton;
    private Button updateButton;
    private ButtonComponent cancelButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
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
        accessLevelTextField = new TextField();

        updateButton = new ButtonComponent("Atualizar", "#1E488F", "white");
        cancelButton = new ButtonComponent("Cancelar", "#dc3545", "white");

        searchButton.setOnAction(this::handleSearchButton);
        updateButton.setOnAction(this::handleUpdateButton);
        cancelButton.setOnAction(this::handleCancelButton);

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
        gridPane.add(accessLevelTextField, 1, 7);
        gridPane.add(updateButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

        // Create scene and set it in the stage
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSearchButton(ActionEvent event) {
        String userId = idTextField.getText();
        if (!userId.isEmpty()) {
            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM usuario WHERE id = '" + userId + "'";
                ResultSet result = conn.getStatement().executeQuery(query);
                if (result.next()) {
                    String nome = result.getString("nome");
                    String email = result.getString("email");
                    String senha = result.getString("senha");
                    String acesso = result.getString("acesso");


                    nameTextField.setText(nome);
                    emailTextField.setText(email);
                    passwordTextField.setText(senha);
                    accessLevelTextField.setText(acesso);
                } else {
                    AlertUtil.showErrorAlert(null, "Usuário não encontrado");
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Digite um ID de usuário");
        }
    }

    private void handleUpdateButton(ActionEvent event) {
        String id = idTextField.getText();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String accessLevel = accessLevelTextField.getText();

        if (!id.isEmpty()) {
            try {
                    ValidadorAccountUser.validateName(name);
                    ValidadorAccountUser.validateEmail(email);
                    ValidadorAccountUser.validatePassword(password);
                
                    Conn conn = new Conn();
                    String query = "UPDATE usuario SET nome='" + name + "', email='" + email + "', senha='" + password + "', acesso='" + accessLevel + "' WHERE id='" + id + "'";
                    int rowsAffected = conn.getStatement().executeUpdate(query);

                    if (rowsAffected > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Atualizar dados do Usuário");
                        alert.setHeaderText(null);
                        alert.setContentText("Atualizado com sucesso!");
                        alert.showAndWait();

                        idTextField.setText("");
                        nameTextField.setText("");
                        emailTextField.setText("");
                        passwordTextField.setText("");
                        accessLevelTextField.setText("");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Atualizar dados do Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuário não encontrado!");
                    alert.showAndWait();
                }
                conn.close();
            }catch (ValidationException e) {
                AlertUtil.showErrorAlert(null, e.getMessage());
            } 
            catch (Exception e) {
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
