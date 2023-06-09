package view.admin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.database.Conn;
import view.utils.ButtonComponent;
import view.utils.AlertUtil;

import java.sql.SQLException;

public class AddUser extends Application {
    private Stage stage;

    private TextField nomeTextField, emailTextField, senhaTextField, acessoTextField;
    private Button cancelButton;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Add Employee");

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
        Label titleLabel = new Label("Adicionar Usuário");
        titleLabel.setFont(new Font("serif", 25));
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label nomeLabel = new Label("Nome:");
        gridPane.add(nomeLabel, 0, 1);

        nomeTextField = new TextField();
        gridPane.add(nomeTextField, 1, 1);

        Label EmailLabel = new Label("Email:");
        gridPane.add(EmailLabel, 0, 2);

        emailTextField = new TextField();
        gridPane.add(emailTextField, 1, 2);

        Label senhaLabel = new Label("Senha:");
        gridPane.add(senhaLabel, 0, 3);

        senhaTextField = new TextField();
        gridPane.add(senhaTextField, 1, 3);

        Label acessoLabel = new Label("Acesso:");
        gridPane.add(acessoLabel, 0, 4);

        acessoTextField = new TextField();
        gridPane.add(acessoTextField, 1, 4);

        Button submitButton = new ButtonComponent("Enviar", "#1E488F", "white");
        HBox submitHBox = new HBox(10);
        submitHBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitHBox.getChildren().add(submitButton);
        gridPane.add(submitHBox, 0, 12, 2, 1);

        submitButton.setOnAction(e -> insertEmployeeDetails());
        Button addButton = new ButtonComponent("Adicionar", "#1E488F", "white");
        addButton.setOnAction(e -> {
            insertEmployeeDetails();
            clearInputs();
        });
    
        HBox addButtonHBox = new HBox(10);
        addButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        addButtonHBox.getChildren().add(addButton);
    
        cancelButton = new ButtonComponent("Cancelar", "red", "white");
        cancelButton.setOnAction(this::handleCancelButton);
    
        HBox cancelHBox = new HBox(10);
        cancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        cancelHBox.getChildren().addAll(addButtonHBox, cancelButton);
    
        gridPane.add(cancelHBox, 1, 12);
    }

    private void insertEmployeeDetails() {
        setInputsAndButtonsEnabled(false);
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String senha = senhaTextField.getText();
        String acesso = acessoTextField.getText();

        try {
            Conn c1 = new Conn();
            String query = "INSERT INTO usuario (nome, email, senha, acesso) VALUES ('"
                + nome + "', '" + email + "', '" + senha + "', '" + Integer.parseInt(acesso) + "')";
            int rowsAffected = c1.st.executeUpdate(query);

            if (rowsAffected > 0) {
                AlertUtil.showSuccessAlert(stage, "Adicionado com sucesso");
            }
        } catch (SQLException e) {
            AlertUtil.showErrorAlert(stage, "Ocorreu um erro");
        }
        setInputsAndButtonsEnabled(true);
    }

    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }
    
    private void setInputsAndButtonsEnabled(boolean enabled) {
        nomeTextField.setDisable(!enabled);
        emailTextField.setDisable(!enabled);
        senhaTextField.setDisable(!enabled);
        acessoTextField.setDisable(!enabled);
    }
    
    private void clearInputs() {
        nomeTextField.clear();
        emailTextField.clear();
        senhaTextField.clear();
        acessoTextField.clear();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
