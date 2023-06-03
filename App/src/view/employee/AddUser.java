package view.employee;

import java.sql.SQLException;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddUser extends Application {
    private Stage stage;
    private TextField nameTextField, fatherNameTextField, ageTextField, dobTextField, addressTextField,
            phoneTextField, emailTextField, educationTextField, jobPostTextField, aadharNoTextField, empIdTextField;
    private Button cancelButton;

    @Override
    public void start(Stage primaryStage) {
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
        Label titleLabel = new Label("Adicionar Cliente");
        titleLabel.setFont(new Font("serif", 25));
        gridPane.add(titleLabel, 0, 0, 2, 1);
    
        Label nameLabel = new Label("Nome:");
        gridPane.add(nameLabel, 0, 1);
    
        nameTextField = new TextField();
        gridPane.add(nameTextField, 1, 1);
    
        Label fatherNameLabel = new Label("Nome do Pai:");
        gridPane.add(fatherNameLabel, 0, 2);
    
        fatherNameTextField = new TextField();
        gridPane.add(fatherNameTextField, 1, 2);
    
        // Rest of the UI controls...
    
        Label ageLabel = new Label("Idade:");
        gridPane.add(ageLabel, 0, 3);
    
        ageTextField = new TextField();
        gridPane.add(ageTextField, 1, 3);
    
        Label dobLabel = new Label("Aniversário:");
        gridPane.add(dobLabel, 0, 4);
    
        dobTextField = new TextField();
        gridPane.add(dobTextField, 1, 4);
    
        Label addressLabel = new Label("Endereço:");
        gridPane.add(addressLabel, 0, 5);
    
        addressTextField = new TextField();
        gridPane.add(addressTextField, 1, 5);
    
        Label phoneLabel = new Label("Telefone:");
        gridPane.add(phoneLabel, 0, 6);
    
        phoneTextField = new TextField();
        gridPane.add(phoneTextField, 1, 6);
    
        Label emailLabel = new Label("Email:");
        gridPane.add(emailLabel, 0, 7);
    
        emailTextField = new TextField();
        gridPane.add(emailTextField, 1, 7);
    
        Label educationLabel = new Label("Education:");
        gridPane.add(educationLabel, 0, 8);
    
        educationTextField = new TextField();
        gridPane.add(educationTextField, 1, 8);
    
        Label jobPostLabel = new Label("Trabalho:");
        gridPane.add(jobPostLabel, 0, 9);
    
        jobPostTextField = new TextField();
        gridPane.add(jobPostTextField, 1, 9);
    
        Label aadharNoLabel = new Label("Aadhar No:");
        gridPane.add(aadharNoLabel, 0, 10);
    
        aadharNoTextField = new TextField();
        gridPane.add(aadharNoTextField, 1, 10);
    
        Label empIdLabel = new Label(" Id:");
        gridPane.add(empIdLabel, 0, 11);
    
        empIdTextField = new TextField();
        gridPane.add(empIdTextField, 1, 11);
    
        Button submitButton = new Button("Enviar");
        submitButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        HBox submitHBox = new HBox(10);
        submitHBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitHBox.getChildren().add(submitButton);
        gridPane.add(submitHBox, 0, 12, 2, 1);
    
        submitButton.setOnAction(e -> insertEmployeeDetails());
    
        cancelButton = new Button("Cancelar");
        cancelButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        cancelButton.setOnAction(this::handleCancelButton);
    
        Button addButton = new Button("Adicionar");
        addButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        addButton.setOnAction(e -> insertEmployeeDetails());
        HBox addButtonHBox = new HBox(10);
        addButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        addButtonHBox.getChildren().add(addButton);
    
        HBox cancelHBox = new HBox(10);
        cancelHBox.setAlignment(Pos.BOTTOM_RIGHT);
        cancelHBox.getChildren().addAll(cancelButton, addButtonHBox);
    
        gridPane.add(cancelHBox, 1, 12);
    }
    private void insertEmployeeDetails() {
        String name = nameTextField.getText();
        String fatherName = fatherNameTextField.getText();
        String age = ageTextField.getText();
        String dob = dobTextField.getText();
        String address = addressTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        String education = educationTextField.getText();
        String jobPost = jobPostTextField.getText();
        String aadharNo = aadharNoTextField.getText();
        String empId = empIdTextField.getText();
    
        try {
            conn c1 = new conn();
            String query = "INSERT INTO employee (name, fname, age, dob, address, phone, email, education, post, aadhar, emp_id) VALUES ('"
                    + name + "', '" + fatherName + "', '" + age + "', '" + dob + "', '" + address + "', '" + phone
                    + "', '" + email + "', '" + education + "', '" + jobPost + "', '" + aadharNo + "', '" + empId
                    + "')";
            int rowsAffected = c1.st.executeUpdate(query);
    
            if (rowsAffected > 0) {
                showAlert("Adicionado");
            } else {
                showAlert("Ocorreu um erro");
            }
    
        } catch (SQLException e) {
            System.out.println("Error inserting employee details: " + e.getMessage());
        }
    }
    
    

    private void showAlert(String message) {
        Text text = new Text(message);
        text.setFill(Color.RED);
        text.setFont(Font.font("Arial", 14));

        VBox vbox = new VBox(text);
        vbox.setAlignment(Pos.CENTER);

        Stage alertStage = new Stage();
        alertStage.initOwner(stage);
        alertStage.setTitle("Login Error");
        alertStage.setScene(new Scene(vbox, 200, 100));
        alertStage.showAndWait();
    }

    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}
