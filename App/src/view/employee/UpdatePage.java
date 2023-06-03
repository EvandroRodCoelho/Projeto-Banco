package view.employee;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class UpdatePage extends Application {

    private TextField idTextField;
    private Button searchButton;
    private TextField nameTextField;
    private TextField fatherNameTextField;
    private TextField addressTextField;
    private TextField mobileTextField;
    private TextField emailTextField;
    private TextField educationTextField;
    private TextField jobPostTextField;
    private TextField aadharTextField;
    private TextField empIdTextField;
    private Button updateButton;
    private Button cancelButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Update Employee Details");

        GridPane gridPane = createInputGridPane();
        addUIControls(gridPane);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createInputGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        Label idLabel = new Label("ID:");
        idTextField = new TextField();
        searchButton = new Button("Search");

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(searchButton, 2, 0);

        searchButton.setOnAction(e -> {
            String id = idTextField.getText();
            if (!id.isEmpty()) {
                showUpdatePage(id);
            } else {
                showAlert("Please enter an ID");
            }
        });
    }

    private void showUpdatePage(String id) {
        Stage updateStage = new Stage();
        updateStage.setTitle("Update Employee Details");

        GridPane gridPane = createInputGridPane();
        addUpdateUIControls(gridPane, id);

        Scene scene = new Scene(gridPane, 600, 400);
        updateStage.setScene(scene);

        updateStage.show();
    }

    private void addUpdateUIControls(GridPane gridPane, String id) {
        conn con = new conn();
        String str = "select * from employee where emp_id = '" + id + "'";
        ResultSet rs;
        try {
            rs = con.st.executeQuery(str);
            if (rs.next()) {
                String name = rs.getString("name");
                String fatherName = rs.getString("fname");
                String address = rs.getString("address");
                String mobile = rs.getString("phone");
                String email = rs.getString("email");
                String education = rs.getString("education");
                String jobPost = rs.getString("post");
                String aadhar = rs.getString("aadhar");
                String empId = rs.getString("emp_id");

                Label nameLabel = new Label("Name:");
                nameTextField = new TextField(name);
                Label fatherNameLabel = new Label("Father's Name:");
                fatherNameTextField = new TextField(fatherName);
                Label addressLabel = new Label("Address:");
                addressTextField = new TextField(address);
                Label mobileLabel = new Label("Mobile No:");
                mobileTextField = new TextField(mobile);
                Label emailLabel = new Label("Email Id:");
                emailTextField = new TextField(email);
                Label educationLabel = new Label("Education:");
                educationTextField = new TextField(education);
                Label jobPostLabel = new Label("Job Post:");
                jobPostTextField = new TextField(jobPost);
                Label aadharLabel = new Label("Aadhar No:");
                aadharTextField = new TextField(aadhar);
                Label empIdLabel = new Label("Employee Id:");
                empIdTextField = new TextField(empId);

                updateButton = new Button("Update");
                updateButton.setOnAction(e -> {
                    updateEmployeeDetails(id, nameTextField.getText(), fatherNameTextField.getText(),
                            addressTextField.getText(), mobileTextField.getText(), emailTextField.getText(),
                            educationTextField.getText(), jobPostTextField.getText(), aadharTextField.getText(),
                            empIdTextField.getText());
                });

                cancelButton = new Button("Cancel");
                cancelButton.setOnAction(e -> handleCancelButton());

                gridPane.add(nameLabel, 0, 0);
                gridPane.add(nameTextField, 1, 0);
                gridPane.add(fatherNameLabel, 0, 1);
                gridPane.add(fatherNameTextField, 1, 1);
                gridPane.add(addressLabel, 0, 2);
                gridPane.add(addressTextField, 1, 2);
                gridPane.add(mobileLabel, 0, 3);
                gridPane.add(mobileTextField, 1, 3);
                gridPane.add(emailLabel, 0, 4);
                gridPane.add(emailTextField, 1, 4);
                gridPane.add(educationLabel, 0, 5);
                gridPane.add(educationTextField, 1, 5);
                gridPane.add(jobPostLabel, 0, 6);
                gridPane.add(jobPostTextField, 1, 6);
                gridPane.add(aadharLabel, 0, 7);
                gridPane.add(aadharTextField, 1, 7);
                gridPane.add(empIdLabel, 0, 8);
                gridPane.add(empIdTextField, 1, 8);
                gridPane.add(updateButton, 1, 9);
                gridPane.add(cancelButton, 0, 9);
            } else {
                showAlert("No employee found with the given ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateEmployeeDetails(String id, String name, String fatherName, String address, String mobile,
                                       String email, String education, String jobPost, String aadhar, String empId) {
        conn con = new conn();
        String query = "UPDATE employee SET name='" + name + "', fname='" + fatherName + "', address='" + address +
                "', phone='" + mobile + "', email='" + email + "', education='" + education + "', post='" + jobPost +
                "', aadhar='" + aadhar + "', emp_id='" + empId + "' WHERE emp_id='" + id + "'";
        try {
            int rowsAffected = con.st.executeUpdate(query);
            if (rowsAffected > 0) {
                showAlert("Employee details updated successfully");
            } else {
                showAlert("Failed to update employee details");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
