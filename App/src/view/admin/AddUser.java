package view.admin;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.utils.ButtonComponent;
import controller.admin.AddUserController;
public class AddUser extends Application {
    private Stage stage;

    private TextField nameTextField, emailTextField;
    private Button submitButton, addButton, cancelButton;
    AddUserController controller;
    private PasswordField passwordTextField;
    private ComboBox<String> accessLevelComboBox;
    @Override
    public void start(Stage primaryStage) {
        controller = new AddUserController(this);

        stage = primaryStage;
        primaryStage.setTitle("Adicionar Usuário");

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
        Label titleLabel = new Label("Adicionar usuário");
        titleLabel.setFont(new Font("serif", 25));
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label nameLabel = new Label("Name:");
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

        Label accessLevelLabel = new Label("Nível de acesso:");
        gridPane.add(accessLevelLabel, 0, 4);

        accessLevelComboBox = new ComboBox<>();
        accessLevelComboBox.getItems().addAll("1", "2");
        accessLevelComboBox.setValue("1");
        gridPane.add(accessLevelComboBox, 1, 4);

        submitButton = new ButtonComponent("Enviar", "#1E488F", "white");
        submitButton.setOnAction(controller::handleInsertUserDetails);

        addButton = new ButtonComponent("Adicionar", "#1E488F", "white");
        addButton.setOnAction(controller::handleInsertUserDetails);
        HBox buttonHBox = new HBox(10);
        buttonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonHBox.getChildren().addAll(submitButton);
        gridPane.add(buttonHBox, 0, 12, 2, 1);

        cancelButton = new ButtonComponent("Cancelar", "red", "white");
        cancelButton.setOnAction(controller::handleCancelButton);

        HBox cancelHBox = new HBox(10);
        cancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        cancelHBox.getChildren().addAll(addButton,cancelButton);
        gridPane.add(cancelHBox, 1, 12);
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

    public ComboBox<String> getAccessLevelComboBox() {
        return accessLevelComboBox;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
