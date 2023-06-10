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
import model.database.Conn;
import view.utils.AlertUtil;
import view.utils.ButtonComponent;

public class RemoveUser extends Application {
    private TextField idTextField;
    private String nome;
    private String email;
    private String senha;
    private String acesso;
    private Label idLabel, nameLabel, emailLabel, passwordLabel, acessoLabel;
    private Button searchButton, removeButton, cancelButton;

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

        searchButton.setOnAction(this::handleSearchButton);
        removeButton.setOnAction(this::handleRemoveButton);
        cancelButton.setOnAction(this::handleCancelButton);

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

    private void handleSearchButton(ActionEvent event) {
        String userId = idTextField.getText();
        if (!userId.isEmpty()) {
            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM usuario WHERE id = '" + userId + "'";
                ResultSet result = conn.getStatement().executeQuery(query);
                if (result.next()) {
                    nome = result.getString("nome");
                    email = result.getString("email");
                    senha = result.getString("senha");
                    acesso = result.getString("acesso");

                    // Update UI labels with retrieved information
                    nameLabel.setText("Nome: " + nome);
                    emailLabel.setText("Email: " + email);
                    passwordLabel.setText("Senha: " + senha);
                    acessoLabel.setText("Acesso: " + acesso);
                } else {
                    AlertUtil.showErrorAlert(null, "Usuário não encontrado");
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Campo vazio");
        }
    }

    private void handleRemoveButton(ActionEvent event) {
        String userId = idTextField.getText();
        if (!userId.isEmpty()) {
            try {
                Conn conn = new Conn();
                String query = "DELETE FROM usuario WHERE id = '" + userId + "'";
                int rowsAffected = conn.getStatement().executeUpdate(query);
                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Remover Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuário removido com sucesso!");
                    alert.showAndWait();

                    nameLabel.setText("Nome:");
                    emailLabel.setText("Email:");
                    passwordLabel.setText("Senha:");
                    acessoLabel.setText("Acesso:");
                    idTextField.setText("");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Remover Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Não foi possível encontrar o usuário.");
                    alert.showAndWait();
                }
                conn.close();
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
