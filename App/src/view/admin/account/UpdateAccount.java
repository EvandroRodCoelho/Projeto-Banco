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

public class UpdateAccount extends Application {
    private TextField idTextField, numContaTextField, titularTextField, saldoTextField, tipoContaTextField, usuarioIdTextField;
    private Button searchButton;
    private Button updateButton;
    private ButtonComponent cancelButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Atualizar Conta");
        primaryStage.setResizable(false);

        Label idLabel = new Label("Conta ID:");
        idTextField = new TextField();
        searchButton = new ButtonComponent("Procurar", "#007bff", "white");

        Label numContaLabel = new Label("Número da Conta:");
        numContaTextField = new TextField();

        Label titularLabel = new Label("Titular:");
        titularTextField = new TextField();

        Label saldoLabel = new Label("Saldo:");
        saldoTextField = new TextField();

        Label tipoContaLabel = new Label("Tipo de Conta:");
        tipoContaTextField = new TextField();

        Label usuarioIdLabel = new Label("Usuário ID:");
        usuarioIdTextField = new TextField();

        updateButton = new ButtonComponent("Atualizar", "#1E488F", "white");
        cancelButton = new ButtonComponent("Cancelar", "#dc3545", "white");

        searchButton.setOnAction(this::handleSearchButton);
        updateButton.setOnAction(this::handleUpdateButton);
        cancelButton.setOnAction(this::handleCancelButton);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(searchButton, 2, 0);

        gridPane.add(numContaLabel, 0, 4);
        gridPane.add(numContaTextField, 1, 4);
        gridPane.add(titularLabel, 0, 5);
        gridPane.add(titularTextField, 1, 5);
        gridPane.add(saldoLabel, 0, 6);
        gridPane.add(saldoTextField, 1, 6);
        gridPane.add(tipoContaLabel, 0, 7);
        gridPane.add(tipoContaTextField, 1, 7);
        gridPane.add(usuarioIdLabel, 0, 8);
        gridPane.add(usuarioIdTextField, 1, 8);
        gridPane.add(updateButton, 0, 9);
        gridPane.add(cancelButton, 1, 9);

        // Create scene and set it in the stage
        Scene scene = new Scene(gridPane, 600, 400);
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
                    String numConta = result.getString("numConta");
                    String titular = result.getString("titular");
                    String saldo = result.getString("saldo");
                    String tipoConta = result.getString("tipoConta");
                    String usuarioId = result.getString("usuarioId");

                    numContaTextField.setText(numConta);
                    titularTextField.setText(titular);
                    saldoTextField.setText(saldo);
                    tipoContaTextField.setText(tipoConta);
                    usuarioIdTextField.setText(usuarioId);
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

    private void handleUpdateButton(ActionEvent event) {
        String id = idTextField.getText();
        String numConta = numContaTextField.getText();
        String titular = titularTextField.getText();
        Double saldo = Double.parseDouble(saldoTextField.getText());
        Integer tipoConta = Integer.parseInt(tipoContaTextField.getText());
        Integer usuarioId = Integer.parseInt(usuarioIdTextField.getText());

        if (!id.isEmpty()) {
            try {
                String query = "UPDATE contas SET numconta='"+ numConta + 
                "', titular='" + titular + 
                "', saldo='" + saldo + 
                "', tipoconta='" + tipoConta + 
                "', usuarioid='" + usuarioId + 
                "' WHERE id='" + id + "'";

                conn c1 = new conn();
                int rowsAffected = c1.st.executeUpdate(query);

                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atualizar dados da conta");
                    alert.setHeaderText(null);
                    alert.setContentText("Atualizado com sucesso!");
                    alert.showAndWait();

                    handleRedirectDetailsPage();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Atualizar dados da conta");
                    alert.setHeaderText(null);
                    alert.setContentText("Conta não encontrada!");
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
