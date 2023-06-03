package view.employee;

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

public class PrintData extends Application {
    private String empId;
    private String name;
    private String father;
    private String address;
    private String phone;
    private String email;
    private String education;
    private String post;

    public static void main(String[] args) {
        launch(args);
    }

    public PrintData(String empId) {
        this.empId = empId;

        // Retrieve employee data from database
        try {
            conn co = new conn();
            String query = "select * from employee where emp_id = '" + empId + "'";
            ResultSet rs = co.st.executeQuery(query);

            if (rs.next()) {
                name = rs.getString("name");
                father = rs.getString("fname");
                address = rs.getString("address");
                phone = rs.getString("phone");
                email = rs.getString("email");
                education = rs.getString("education");
                post = rs.getString("post");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Print Data");
        primaryStage.setResizable(false);

        // Create UI controls
        Label idLabel = new Label("Employee ID:");
        Label idValue = new Label(empId);
        Label nameLabel = new Label("Name:");
        Label nameValue = new Label(name);
        Label fatherLabel = new Label("Father's Name:");
        Label fatherValue = new Label(father);
        Label addressLabel = new Label("Address:");
        Label addressValue = new Label(address);
        Label phoneLabel = new Label("Mobile No:");
        Label phoneValue = new Label(phone);
        Label emailLabel = new Label("Email ID:");
        Label emailValue = new Label(email);
        Label educationLabel = new Label("Education:");
        Label educationValue = new Label(education);
        Label postLabel = new Label("Job Post:");
        Label postValue = new Label(post);
        Button printButton = new Button("Print");
        Button cancelButton = new Button("Cancel");

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
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameValue, 1, 1);
        gridPane.add(fatherLabel, 0, 2);
        gridPane.add(fatherValue, 1, 2);
        gridPane.add(addressLabel, 0, 3);
        gridPane.add(addressValue, 1, 3);
        gridPane.add(phoneLabel, 0, 4);
        gridPane.add(phoneValue, 1, 4);
        gridPane.add(emailLabel, 0, 5);
        gridPane.add(emailValue, 1, 5);
        gridPane.add(educationLabel, 0, 6);
        gridPane.add(educationValue, 1, 6);
        gridPane.add(postLabel, 0, 7);
        gridPane.add(postValue, 1, 7);
        gridPane.add(printButton, 0, 8);
        gridPane.add(cancelButton, 1, 8);

        // Set CSS styles
        gridPane.setStyle("-fx-background-color: white;");
        printButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        cancelButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

        // Create scene and set it in the stage
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPrintSuccessDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Print Success");
        alert.setHeaderText(null);
        alert.setContentText("Printed successfully.");
        alert.showAndWait();
    }
}
