package view.user;

import controller.user.RegisterController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.utils.ButtonComponent;

public class RegisterPage extends Application {
    private Stage stage;
    private RegisterController controller;

    private TextField nameTextField, emailTextField;
    private PasswordField passwordTextField;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        controller = new RegisterController(this);
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

        Label nameLabel = new Label("Nome:");
        gridPane.add(nameLabel, 0, 1);

        nameTextField = new TextField();
        gridPane.add(nameTextField, 1, 1);

        Label emailLabel = new Label("Email:");
        gridPane.add(emailLabel, 0, 2);

        emailTextField = new TextField();
        gridPane.add(emailTextField, 1, 2);

        Label passwordLabel = new Label("Senha:");
        gridPane.add(passwordLabel, 0, 3);

        passwordTextField = new PasswordField();
        gridPane.add(passwordTextField, 1, 3);

        Button submitButton = new ButtonComponent("Enviar", "#1E488F", "white");
        HBox submitHBox = new HBox(10);
        submitHBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitHBox.getChildren().add(submitButton);
        gridPane.add(submitHBox, 0, 6, 2, 1);

        submitButton.setOnAction(e -> controller.insertAddUser());

        Hyperlink loginLink = new Hyperlink("Voltar para a página de login");
        loginLink.setOnAction(controller::handleLoginLink);
        loginLink.setStyle( "-fx-text-fill:#1E488F ;");
        gridPane.add(loginLink, 1, 7);
    }

    
    public Stage getStage() {
        return stage;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public PasswordField getPasswordTextField() {
        return passwordTextField;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
