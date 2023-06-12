package view.admin.account;

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
import view.utils.ButtonComponent;
import model.database.Conn;
import view.utils.AlertUtil;

import java.sql.SQLException;

import controller.utils.validador.ValidatorData;

public class AddAccount extends Application {
    private Stage stage;

    private TextField numContaTextField, titularTextField, saldoTextField, tipoContaTextField, usuarioIdTextField;
    private Button cancelButton;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Adicionar conta");

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
        Label titleLabel = new Label("Adicionar Conta");
        titleLabel.setFont(new Font("serif", 25));
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label numContaLabel = new Label("Número da Conta:");
        gridPane.add(numContaLabel, 0, 1);

        numContaTextField = new TextField();
        gridPane.add(numContaTextField, 1, 1);

        Label titularLabel = new Label("Titular:");
        gridPane.add(titularLabel, 0, 2);

        titularTextField = new TextField();
        gridPane.add(titularTextField, 1, 2);

        Label saldoLabel = new Label("Saldo:");
        gridPane.add(saldoLabel, 0, 3);

        saldoTextField = new TextField();
        gridPane.add(saldoTextField, 1, 3);

        Label tipoContaLabel = new Label("Tipo de Conta:");
        gridPane.add(tipoContaLabel, 0, 4);

        tipoContaTextField = new TextField();
        gridPane.add(tipoContaTextField, 1, 4);

        Label usuarioIdLabel = new Label("Usuário ID:");
        gridPane.add(usuarioIdLabel, 0, 5);

        usuarioIdTextField = new TextField();
        gridPane.add(usuarioIdTextField, 1, 5);

        Button submitButton = new ButtonComponent("Enviar", "#1E488F", "white");
        HBox submitHBox = new HBox(10);
        submitHBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitHBox.getChildren().add(submitButton);
        gridPane.add(submitHBox, 0, 12, 2, 1);

        submitButton.setOnAction(e -> insertAccountDetails());
        Button addButton = new ButtonComponent("Adicionar", "#1E488F", "white");
        addButton.setOnAction(e -> {
            insertAccountDetails();
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

    private void insertAccountDetails() {
        setInputsAndButtonsEnabled(false);
        String numconta = numContaTextField.getText();
        String titular = titularTextField.getText();
        String saldo = saldoTextField.getText();
        String tipoconta = tipoContaTextField.getText();
        String usuarioid = usuarioIdTextField.getText();

        if(!numconta.isEmpty() || !titular.isEmpty() || !saldo.isEmpty() || !tipoconta.isEmpty() || !usuarioid.isEmpty()){
            try {
                ValidatorData.isValidNumber(saldo);
                ValidatorData.isValidNumber(numconta);
                ValidatorData.isValidNumber(tipoconta);
                ValidatorData.isValidNumber(usuarioid);

                Conn c1 = new Conn();
                String query = "INSERT INTO contas (numconta, titular, saldo, tipoconta, usuarioid) VALUES ('"
                    + numconta + "', '" 
                    + titular + "', '" 
                    + saldo + "', '" 
                    + tipoconta + "', '" 
                    + usuarioid + "')";
                int rowsAffected = c1.getStatement().executeUpdate(query);

                if (rowsAffected > 0) {
                    AlertUtil.showSuccessAlert(stage, "Adicionado com sucesso");
                }
            } catch (NumberFormatException e) {
                AlertUtil.showErrorAlert(stage, "Alguns campos permitem somente números!");
            }
            catch (SQLException e) {
                AlertUtil.showErrorAlert(stage, "Ocorreu um erro");
            }
        } else {
            AlertUtil.showErrorAlert(stage, "Existem campos vazios!");
        }

        setInputsAndButtonsEnabled(true);
    }

    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }
    
    private void setInputsAndButtonsEnabled(boolean enabled) {
        numContaTextField.setDisable(!enabled);
        titularTextField.setDisable(!enabled);
        saldoTextField.setDisable(!enabled);
        tipoContaTextField.setDisable(!enabled);
        usuarioIdTextField.setDisable(!enabled);
    }
    
    private void clearInputs() {
        numContaTextField.clear();
        titularTextField.clear();
        saldoTextField.clear();
        tipoContaTextField.clear();
        usuarioIdTextField.clear();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
