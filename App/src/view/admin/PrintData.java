package view.admin;

import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.utils.ButtonComponent;
import view.utils.AlertUtil;

public class PrintData extends Application {
    private String usuarioId;
    private String nome;
    private String email;
    private String senha;
    private String acesso;

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public PrintData(String id, Stage previuosStage) {
        this.usuarioId = id;

        try {
            conn co = new conn();
            String query = "select * from usuario where id = '" + id + "'";
            ResultSet rs = co.st.executeQuery(query);

            if (rs.next()) {
                nome = rs.getString("nome");
                email = rs.getString("email");
                senha = rs.getString("senha");
                acesso = rs.getString("acesso");
                this.start(new Stage());
                previuosStage.close();
                return;
            } else {
                AlertUtil.showErrorAlert(previuosStage, "Usuario não existe!");
            }
        } catch (Exception e) {
            AlertUtil.showErrorAlert(primaryStage, "Ocorreu um erro inesperado!");
            Stage viewEmployeeStage = new Stage();
            ViewUser viewEmployeePage = new ViewUser();
            viewEmployeePage.start(viewEmployeeStage);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Print Data");
        primaryStage.setResizable(false);
        this.primaryStage = primaryStage;
        // Create UI controls
        Label idLabel = new Label("Usuário ID:");
        Label idValue = new Label(usuarioId);
        Label nomeLabel = new Label("Nome:");
        Label nomeValue = new Label(nome);
        Label emailLabel = new Label("Email:");
        Label emailValue = new Label(email);
        Label senhaLabel = new Label("Senha:");
        Label senhaValue = new Label(senha);
        Label acessoLabel = new Label("Acesso:");
        Label acessoValue = new Label(acesso);
        Button printButton = new ButtonComponent("Print", "#1E488F", "white");
        Button cancelButton = new ButtonComponent("Cancelar", "#dc3545", "white");

        // Configure event handlers
        printButton.setOnAction(e -> {
            // Perform printing logic here
            // ...
            showPrintSuccessDialog();
            primaryStage.close();
            new DetailsPage();
        });

        cancelButton.setOnAction(e -> {
            primaryStage.close();
            new ViewUser();
        });

        // Create grid pane and set its properties
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Add UI controls to grid pane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idValue, 1, 0);
        gridPane.add(nomeLabel, 0, 1);
        gridPane.add(nomeValue, 1, 1);
        gridPane.add(emailLabel, 0, 5);
        gridPane.add(emailValue, 1, 5);
        gridPane.add(senhaLabel, 0, 6);
        gridPane.add(senhaValue, 1, 6);
        gridPane.add(acessoLabel, 0, 7);
        gridPane.add(acessoValue, 1, 7);
        gridPane.add(printButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

        // Set CSS styles
        gridPane.setStyle("-fx-background-color: white;");

        // Create scene and set it in the stage
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPrintSuccessDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Print com Successo");
        alert.setHeaderText(null);
        alert.setContentText("Printed com sucesso!");
        alert.showAndWait();
    }
}