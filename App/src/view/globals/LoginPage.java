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
    private TextField emailField;
    private PasswordField senhaField;
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
                AlertUtil.showErrorAlert(stage, "Login inválido!"); 
                Stage viewWelcomeStage = new Stage();
                WelcomePage viewEmployeePage = new WelcomePage();
                viewEmployeePage.start(viewWelcomeStage);
            }
        } catch (Exception ex) {
            AlertUtil.showErrorAlert(stage,"Ocorreu um erro inesperado!");
        }
    }
}
