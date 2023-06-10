package view.admin.account;

import java.sql.ResultSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.admin.conn;
import view.utils.AlertUtil;
import view.utils.ButtonComponent;

public class RemoveAccount extends Application {
    private TextField idTextField;
    private String numConta, titular, saldo, tipoConta, usuarioId;
    private Label idLabel, numContaLabel, titularLabel, saldoLabel, tipoContaLabel, usuarioIdLabel;
    private Button searchButton, removeButton, cancelButton;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Remover conta");
        primaryStage.setResizable(false);


        idLabel = new Label("Conta ID:");
        idTextField = new TextField();
        searchButton = new ButtonComponent("Procurar", "#007bff", "white");

        numContaLabel = new Label("Número da Conta:");
        titularLabel = new Label("Titular:");
        saldoLabel = new Label("Saldo:");
        tipoContaLabel = new Label("Tipo de Conta:");
        usuarioIdLabel = new Label("Usuário ID:");

        removeButton = new ButtonComponent("Remover", "#dc3545", "white");
        cancelButton = new ButtonComponent("Cancelar", "#6c757d", "white");

        searchButton.setOnAction(this::handleSearchButton);
        removeButton.setOnAction(this::handleRemoveButton);
        cancelButton.setOnAction(this::handleCancelButton);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(searchButton, 2, 0);
        gridPane.add(numContaLabel, 0, 1);
        gridPane.add(titularLabel, 0, 2);
        gridPane.add(saldoLabel, 0, 3);
        gridPane.add(tipoContaLabel, 0, 4);
        gridPane.add(usuarioIdLabel, 0, 5);
        gridPane.add(removeButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

    
        Scene scene = new Scene(gridPane, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleSearchButton(ActionEvent event) {
        String id = idTextField.getText();

        if (!id.isEmpty()) {
            try {

                String query = "SELECT * FROM contas WHERE id = '" + id + "'";
                conn c1 = new conn();
                ResultSet result = c1.st.executeQuery(query);

                if (result.next()) {
                    numConta = result.getString("numConta");
                    titular = result.getString("titular");
                    saldo = result.getString("saldo");
                    tipoConta = result.getString("tipoConta");
                    usuarioId = result.getString("usuarioId");

                    // Update UI labels with retrieved information
                    numContaLabel.setText("Número da Conta: " + numConta);
                    titularLabel.setText("Titular: " + titular);
                    saldoLabel.setText("Saldo: " + saldo);
                    tipoContaLabel.setText("Tipo de Conta: " + tipoConta);
                    usuarioIdLabel.setText("Usuário ID: " + usuarioId);

                } else {
                    AlertUtil.showErrorAlert(null, "Conta não encontrada!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Campo vazio");
        }
    }

    private void handleRemoveButton(ActionEvent event) {
        String id = idTextField.getText();
        if (!id.isEmpty()) {
            try {
                String query = "DELETE FROM contas WHERE id = '" + id + "'";
                conn c1 = new conn();
                int rowsAffected = c1.st.executeUpdate(query);
                
                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Remover Conta");
                    alert.setHeaderText(null);
                    alert.setContentText("Conta removida com sucesso!");
                    alert.showAndWait();

                    handleRedirectDetailsPage();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Remover Conta");
                    alert.setHeaderText(null);
                    alert.setContentText("Não foi possível encontrar a conta.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    private void handleCancelButton(ActionEvent event) {
        handleRedirectDetailsPage();
    }

    private void handleRedirectDetailsPage() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());;
    }
}
