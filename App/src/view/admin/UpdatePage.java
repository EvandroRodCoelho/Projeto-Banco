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

public class UpdatePage extends Application {
    private TextField idTextField;
    private TextField nameTextField;
    private TextField fatherNameTextField;
    private TextField addressTextField;
    private TextField mobileTextField;
    private TextField emailTextField;
    private TextField educationTextField;
    private TextField jobPostTextField;
    private Button searchButton;
    private Button updateButton;
    private ButtonComponent cancelButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Update Cliente");
        primaryStage.setResizable(false);

        // Create UI controls
        Label idLabel = new Label("Employee ID:");
        idTextField = new TextField();
        searchButton = new ButtonComponent("Search", "#007bff", "white");

        Label nameLabel = new Label("Nome:");
        nameTextField = new TextField();
        Label fatherNameLabel = new Label("Nome do pai:");
        fatherNameTextField = new TextField();
        Label addressLabel = new Label("Endereço:");
        addressTextField = new TextField();
        Label mobileLabel = new Label("Numero de telefone:");
        mobileTextField = new TextField();
        Label emailLabel = new Label("Email:");
        emailTextField = new TextField();
        Label educationLabel = new Label("Educação:");
        educationTextField = new TextField();
        Label jobPostLabel = new Label("Trabalho:");
        jobPostTextField = new TextField();

        updateButton = new ButtonComponent("Update", "#1E488F", "white");
        cancelButton = new ButtonComponent("Cancelar", "#dc3545", "white");

        // Configure event handlers
        searchButton.setOnAction(this::handleSearchButton);
        updateButton.setOnAction(this::handleUpdateButton);
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
        gridPane.add(nameTextField, 1, 1);
        gridPane.add(fatherNameLabel, 0, 2);
        gridPane.add(fatherNameTextField, 1, 2);
        gridPane.add(addressLabel, 0, 3);
        gridPane.add(addressTextField, 1, 3);
        gridPane.add(mobileLabel, 0, 4);
        gridPane.add(mobileTextField, 1, 4);
        gridPane.add(emailLabel, 0, 5);
        gridPane.add(emailTextField, 1, 5);
        gridPane.add(educationLabel, 0, 6);
        gridPane.add(educationTextField, 1, 6);
        gridPane.add(jobPostLabel, 0, 7);
        gridPane.add(jobPostTextField, 1, 7);
        gridPane.add(updateButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

        // Create scene and set it in the stage
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSearchButton(ActionEvent event) {
        String employeeId = idTextField.getText();
        if (!employeeId.isEmpty()) {
            try {
                // Execute SQL query to retrieve employee information
                String query = "SELECT * FROM employee WHERE emp_id = '" + employeeId + "'";
                conn c1 = new conn();
                ResultSet result = c1.st.executeQuery(query);
                if (result.next()) {
                    String name = result.getString("name");
                    String fatherName = result.getString("fname");
                    String address = result.getString("address");
                    String mobile = result.getString("phone");
                    String email = result.getString("email");
                    String education = result.getString("education");
                    String jobPost = result.getString("post");

                    // Update UI text fields with retrieved information
                    nameTextField.setText(name);
                    fatherNameTextField.setText(fatherName);
                    addressTextField.setText(address);
                    mobileTextField.setText(mobile);
                    emailTextField.setText(email);
                    educationTextField.setText(education);
                    jobPostTextField.setText(jobPost);
                } else {
                    AlertUtil.showErrorAlert(null, "Cliente não achado");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Digite um id");
        }
    }

    private void handleUpdateButton(ActionEvent event) {
        String employeeId = idTextField.getText();
        String name = nameTextField.getText();
        String fatherName = fatherNameTextField.getText();
        String address = addressTextField.getText();
        String mobile = mobileTextField.getText();
        String email = emailTextField.getText();
        String education = educationTextField.getText();
        String jobPost = jobPostTextField.getText();

        if (!employeeId.isEmpty()) {
            try {
                // Execute SQL query to update the employee details
                String query = "UPDATE employee SET name='" + name + "', fname='" + fatherName + "', address='" + address + "', phone='" + mobile + "', email='" + email + "', education='" + education + "', post='" + jobPost + "' WHERE emp_id='" + employeeId + "'";
                conn c1 = new conn();
                int rowsAffected = c1.st.executeUpdate(query);

                if (rowsAffected > 0) {
                    // Employee details updated successfully
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update Employee Details");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee details updated successfully!");
                    alert.showAndWait();

                    // Clear text fields
                    idTextField.setText("");
                    nameTextField.setText("");
                    fatherNameTextField.setText("");
                    addressTextField.setText("");
                    mobileTextField.setText("");
                    emailTextField.setText("");
                    educationTextField.setText("");
                    jobPostTextField.setText("");
                } else {
                    // Employee not found, show error message
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Update Employee Details");
                    alert.setHeaderText(null);
                    alert.setContentText("Unable to find the employee.");
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
        new DetailsPage().start(new Stage());
    }
}
