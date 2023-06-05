package view.admin;

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
import view.utils.AlertUtil;
import view.utils.ButtonComponent;

public class RemoveUser extends Application {
    private TextField idTextField;
    private String name;
    private String father;
    private String address;
    private String phone;
    private String email;
    private String education;
    private String post;
    private Label idLabel,nameLabel, mobileLabel, emailLabel,postLabel,addressLabel,fatherLabel,educationLabel,phoneLabel;
    private Button searchButton, removeButton, cancelButton;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Remover usuário");
        primaryStage.setResizable(false);

        // Create UI controls
        idLabel = new Label("Usuário ID:");
        idTextField = new TextField();
        searchButton = new ButtonComponent("Procurar", "#007bff", "white");

         nameLabel = new Label("Nome:");
         fatherLabel = new Label("Nome do pai:");
         addressLabel = new Label("Endereço:");
         phoneLabel = new Label("Número de Celular:");
         emailLabel = new Label("Email:");
         educationLabel = new Label("Educação:");
         postLabel = new Label("Trabalho:");

        removeButton = new ButtonComponent("Remove", "#dc3545", "white");
        cancelButton = new ButtonComponent("Cancel", "#6c757d", "white");

        // Configure event handlers
        searchButton.setOnAction(this::handleSearchButton);
        removeButton.setOnAction(this::handleRemoveButton);
        cancelButton.setOnAction(this::handleCancelButton);

        // Create grid pane and set its properties
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Add UI controls to grid pane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(searchButton, 2, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(fatherLabel, 0, 2);
        gridPane.add(addressLabel, 0, 3);
        gridPane.add(phoneLabel, 0, 4);
        gridPane.add(emailLabel, 0, 5);
        gridPane.add(educationLabel, 0, 6);
        gridPane.add(postLabel, 0, 7);
        gridPane.add(removeButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

        // Create scene and set it in the stage
        Scene scene = new Scene(gridPane, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void handleSearchButton(ActionEvent event) {
        String userId = idTextField.getText();
        if (!userId.isEmpty()) {
            try {
                // Execute SQL query to retrieve user information
                String query = "SELECT * FROM employee WHERE emp_id = '" + userId + "'";
                conn c1 = new conn();
                ResultSet result = c1.st.executeQuery(query);
                if (result.next()) {
                    name = result.getString("name");
                    father = result.getString("fname");
                    address = result.getString("address");
                    phone = result.getString("phone");
                    email = result.getString("email");
                    education = result.getString("education");
                    post = result.getString("post");

                    // Update UI labels with retrieved information
                    nameLabel.setText("Nome: " + name);
                    fatherLabel.setText("Nome do pai: " + father);
                    addressLabel.setText("Endereço: " + address);
                    phoneLabel.setText("Número de Celular: " + phone);
                    emailLabel.setText("Email: " + email);
                    educationLabel.setText("Educação: " + education);
                    postLabel.setText("Trabalho: " + post);
                } else {
                    AlertUtil.showErrorAlert(null, "Usuário não encontrado");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Campo vazio");
        }
    }

    private void handleRemoveButton(ActionEvent event) {
        String userId = idTextField.getText();
        if (!userId.isEmpty()) {
            try {
                // Execute SQL query to remove the user
                String query = "DELETE FROM employee WHERE emp_id = '" + userId + "'";
                conn c1 = new conn();
                int rowsAffected = c1.st.executeUpdate(query);
                
                if (rowsAffected > 0) {
                    // User removed successfully
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Remover Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuário removido com sucesso!");
                    alert.showAndWait();
                    
                    // Clear labels and text field
                    nameLabel.setText("Nome:");
                    mobileLabel.setText("Número de Celular:");
                    emailLabel.setText("Email:");
                    idTextField.setText("");
                } else {
                    // User not found, show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Remover Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Não foi possível encontrar o usuário.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    
    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());;
    }
}
