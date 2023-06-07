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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AppSession;
import model.Usuario;
import view.utils.ButtonComponent;
import view.admin.DetailsPage;
import view.admin.conn;
import view.user.DetailsUserPage;
import view.utils.AlertUtil;

public class LoginPage extends Application {
    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton, cancelButton;

    public static void main(String[] args) throws Exception{
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Login");

        GridPane gridPane = createLoginForm();
        loginButton = createButton("Login", "#1E488F", "white");
        cancelButton = createButton("Cancel", "#dc3545", "white");

        loginButton.setOnAction(e -> login());
        cancelButton.setOnAction(e -> stage.close());

        HBox buttonBox = new HBox(10, loginButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(gridPane, buttonBox);

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

        Label usernameLabel = new Label("User:");
        usernameField = new TextField();

        Label passwordLabel = new Label("Senha:");
        passwordField = new PasswordField();

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);

        return gridPane;
    }

    private Button createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    private void login() {
        String username = usernameField.getText();
		String password = passwordField.getText();

        if (username == "" || password == "") {
            AlertUtil.showErrorAlert(stage, "Login Invalido!");
            return;
        }
        try {
            conn c1 = new conn();
            usernameField.setDisable(true);
            passwordField.setDisable(true);
            cancelButton.setDisable(true);
			String query = "select * from usuario where email='"+username+"' and senha='"+password+"' ";
            ResultSet result = c1.st.executeQuery(query);
            if (result.next()) {
                int acesso = result.getInt("acesso");

                if (acesso == 2) {                    
                    new DetailsPage().start(new Stage());
                    stage.close();
                } else {
                    int id = result.getInt("id");                    
                    String nome = result.getString("nome");
                    String titular = result.getString("email");
                    String tipoConta = result.getString("senha");
                    int numConta = result.getInt("acesso");

                    Usuario usuario = new Usuario(id, nome, titular, tipoConta, numConta);
                    AppSession.setUsuarioLogado(usuario); 

                    new DetailsUserPage().start(new Stage());
                    stage.close();
                }
            } else {
                AlertUtil.showErrorAlert(stage, "Login Invalido!"); 
            }
        } catch (Exception ex) {
            AlertUtil.showErrorAlert(stage,"Ocorreu um erro inesperado!");
        }
    }
}
