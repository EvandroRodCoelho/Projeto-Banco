package view.employee;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage extends Application {
    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;

    public static void main(String[] args) throws Exception{
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Login");

        GridPane gridPane = createLoginForm();
        Button loginButton = createButton("Login", Color.BLACK, Color.WHITE);
        Button cancelButton = createButton("Cancel", Color.BLACK, Color.WHITE);

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

    private Button createButton(String text, Color backgroundColor, Color textColor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + toHexCode(backgroundColor) + "; -fx-text-fill: " + toHexCode(textColor) + ";");
        button.setPrefWidth(120);
        return button;
    }

    private String toHexCode(Color color) {
        return "#" + color.toString().substring(2, 8);
    }

    private void login() {
        String username = usernameField.getText();
		String password = passwordField.getText();

		if(username == "" || password == "") showAlert("Login Invalido");
        try {
            conn c1 = new conn();
			String query = "select * from login where username='"+username+"' and password='"+password+"' ";
            ResultSet result = c1.st.executeQuery(query);
            if (result.next()) {
                new DetailsPage().start(new Stage());;
                stage.close();
            } else {
                showAlert("Login Invalido");
            }
        } catch (Exception ex) {
            showAlert("Falha No servidor");
        }
    }

    private void showAlert(String message) {
        Text text = new Text(message);
        text.setFill(Color.RED);
        text.setFont(Font.font("Arial", 14));

        VBox vbox = new VBox(text);
        vbox.setAlignment(Pos.CENTER);

        Stage alertStage = new Stage();
        alertStage.initOwner(stage);
        alertStage.setTitle("Login Error");
        alertStage.setScene(new Scene(vbox, 200, 100));
        alertStage.showAndWait();
    }
    
}
