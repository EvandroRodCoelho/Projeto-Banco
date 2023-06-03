package view.employee;

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

public class RemoveUser extends Application {
    private TextField idTextField;
    private Label nameLabel, mobileLabel, emailLabel;
    private Button searchButton, removeButton, cancelButton;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Remover usuário");
        primaryStage.setResizable(false);
        
        // Create UI controls
        Label idLabel = new Label("Usuário ID:");
        idTextField = new TextField();
        searchButton = new Button("Procurar:");
        
        nameLabel = new Label("Nome:");
        mobileLabel = new Label("Num Celular:");
        emailLabel = new Label("Email:");
        
        removeButton = new Button("Remove");
        cancelButton = new Button("Cancel");
        
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
        gridPane.add(mobileLabel, 0, 2);
        gridPane.add(emailLabel, 0, 3);
        gridPane.add(removeButton, 0, 4);
        gridPane.add(cancelButton, 1, 4);
        

        searchButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        removeButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
        cancelButton.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");
        
        // Create scene and set it in the stage
        Scene scene = new Scene(gridPane, 600, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void handleSearchButton(ActionEvent event) {
        String userId = idTextField.getText();
        if (!userId.isEmpty()) {
            try {
                // Execute SQL query to retrieve user information
                String query = "SELECT * FROM employee WHERE emp_id = '"+ userId +"'";
                conn c1 = new conn();
                ResultSet result = c1.st.executeQuery(query);
                System.out.println(result);
                if (result.next()) {
                    // User found, display information
                    String name = result.getString("name");
                    System.out.println(name);
                    String mobile = result.getString("phone");
                    String email = result.getString("email");
                    nameLabel.setText("Nome: " + name);
                    mobileLabel.setText("Número de Celular: " + mobile);
                    emailLabel.setText("Email: " + email);
                } else {
                    // User not found, clear labels
                    nameLabel.setText("Nome:");
                    mobileLabel.setText("Número de Celular:");
                    emailLabel.setText("Email:");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
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
