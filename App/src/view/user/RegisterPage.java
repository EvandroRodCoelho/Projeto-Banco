package view.user;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.utils.ButtonComponent;
import view.admin.conn;
import view.globals.LoginPage;
import view.utils.AlertUtil;

import java.sql.SQLException;

public class RegisterPage extends Application {
    private Stage stage;

    private TextField nomeTextField, emailTextField, senhaTextField;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Registro");

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
        Label titleLabel = new Label("Registro");
        titleLabel.setFont(new Font("serif", 25));
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label nomeLabel = new Label("Nome:");
        gridPane.add(nomeLabel, 0, 1);

        nomeTextField = new TextField();
        gridPane.add(nomeTextField, 1, 1);

        Label emailLabel = new Label("Email:");
        gridPane.add(emailLabel, 0, 2);

        emailTextField = new TextField();
        gridPane.add(emailTextField, 1, 2);

        Label senhaLabel = new Label("Senha:");
        gridPane.add(senhaLabel, 0, 3);

        senhaTextField = new TextField();
        gridPane.add(senhaTextField, 1, 3);

        Button submitButton = new ButtonComponent("Enviar", "#1E488F", "white");
        HBox submitHBox = new HBox(10);
        submitHBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitHBox.getChildren().add(submitButton);
        gridPane.add(submitHBox, 0, 6, 2, 1);

        submitButton.setOnAction(e -> insertAddUser());

        Hyperlink loginLink = new Hyperlink("Voltar para a página de login");
        loginLink.setOnAction(this::handleLoginLink);
        loginLink.setStyle( "-fx-text-fill:#1E488F ;");
        gridPane.add(loginLink, 1, 7);
    }

    private void insertAddUser() {
        setInputsEnabled(false);
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String senha = senhaTextField.getText();

        try {
            conn c1 = new conn();
            String query = "INSERT INTO usuario (nome, email, senha, acesso) VALUES ('"
                + nome + "', '" + email + "', '" + senha + "', '" + 1 + "')";
            int rowsAffected = c1.st.executeUpdate(query);

            if (rowsAffected > 0) {
                AlertUtil.showSuccessAlert(stage, "Adicionado com sucesso");
            }
        } catch (SQLException e) {
            AlertUtil.showErrorAlert(stage, "Ocorreu um erro");
        }
        setInputsEnabled(true);
    }

    private void handleLoginLink(ActionEvent event) {
        Stage stage = (Stage) emailTextField.getScene().getWindow();
        stage.close();
        new LoginPage().start(new Stage());
    }

    private void setInputsEnabled(boolean enabled) {
        nomeTextField.setDisable(!enabled);
        emailTextField.setDisable(!enabled);
        senhaTextField.setDisable(!enabled);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
