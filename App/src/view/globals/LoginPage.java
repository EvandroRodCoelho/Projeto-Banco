package view.globals;

import java.sql.ResultSet;

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
import model.AppSession;
import model.Usuario;
import view.utils.ButtonComponent;
import view.user.RegisterPage;
import view.admin.MainPage;
import view.admin.conn;
import view.user.DetailsUserPage;
import view.utils.AlertUtil;

public class LoginPage extends Application {
    private Stage stage;
    private TextField emailField;
    private PasswordField senhaField;
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

        loginButton.setOnAction(e -> login());
        cancelButton.setOnAction(e -> stage.close());
        registerLink.setOnAction(e -> register());
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
    }

    private GridPane createLoginForm() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        Label emaiLabel = new Label("Email:");
        emailField = new TextField();

        Label senhaLabel = new Label("Senha:");
        senhaField = new PasswordField();

        gridPane.add(emaiLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(senhaLabel, 0, 1);
        gridPane.add(senhaField, 1, 1);

        return gridPane;
    }

    private Button createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    private void login() {
        String email = emailField.getText();
		String senha = senhaField.getText();

        if (email == "" || senha == "") {
            AlertUtil.showErrorAlert(stage, "Login inválido!");
            return;
        }
        try {
            conn c1 = new conn();
            emailField.setDisable(true);
            senhaField.setDisable(true);
            cancelButton.setDisable(true);
			String query = "select * from usuario where email='"+ email +"' and senha='"+ senha +"' ";
            ResultSet result = c1.st.executeQuery(query);
            if (result.next()) {
                int acesso = result.getInt("acesso");
                int id = result.getInt("id");                    
                String userName = result.getString("nome");
                String userEmail = result.getString("email");
                String userAccountType = result.getString("senha");
                int accountNum = result.getInt("acesso");

                Usuario user = new Usuario(id, userName, userEmail, userAccountType, accountNum);
                AppSession.setUsuarioLogado(user); 
                if (acesso == 2) {                    
                    new MainPage().start(new Stage());
                    stage.close();
                } else {
                    new DetailsUserPage().start(new Stage());
                    stage.close();
                }
            } else {
                AlertUtil.showErrorAlert(stage, "Login inválido!");
                emailField.setDisable(false);
                senhaField.setDisable(false);
                cancelButton.setDisable(false);
            }
        } catch (Exception ex) {
            AlertUtil.showErrorAlert(stage,"Ocorreu um erro inesperado!");
        }
    }

    private Hyperlink createRegisterLink() {
        Hyperlink link = new Hyperlink("Ainda não possui conta? Se registrar");
        link.setStyle( "-fx-text-fill:#1E488F ;");
        return link;
    }

    private void register() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new RegisterPage().start(new Stage());
    } 
    
}
