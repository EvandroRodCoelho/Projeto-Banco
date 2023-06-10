package view.globals;

import controller.globals.LoginController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import view.utils.ButtonComponent;


public class LoginPage extends Application {
    public Stage stage;
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton, cancelButton;
    private Hyperlink registerLink;

    public static void main(String[] args) throws Exception{
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Login");

        GridPane gridPane = createLoginForm();
        loginButton = createButton("Login", "#1E488F", "white");
        cancelButton = createButton("Cancelar", "#dc3545", "white");
        registerLink = createRegisterLink();

        VBox buttonBox = new VBox(10, loginButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox hyperlinkBox = new VBox(registerLink);
        hyperlinkBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(gridPane, buttonBox, hyperlinkBox);

        Scene scene = new Scene(vbox, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        LoginController loginController = new LoginController(this);

        loginButton.setOnAction(loginController::handleLoginButtonAction);
        cancelButton.setOnAction(loginController::handleCancelAction);
        registerLink.setOnAction(loginController::handleRegisterAction);
        
    }

    private GridPane createLoginForm() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        Label emailLabel = new Label("Email:");
        emailField = new TextField();

        Label senhaLabel = new Label("Senha:");
        passwordField = new PasswordField();

        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(senhaLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);

        return gridPane;
    }

    private Button createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

  
    private Hyperlink createRegisterLink() {
        Hyperlink link = new Hyperlink("Ainda não possui conta? Se registrar");
        link.setStyle("-fx-text-fill:#1E488F;");
        return link;
    }
    public Stage getStage() {
        return stage;
    }

    public TextField getEmaTextField() {
        return emailField;
    }

    public TextField getPasswordTextField() {
        return passwordField;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

}
