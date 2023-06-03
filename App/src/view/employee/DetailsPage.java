package view.employee;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DetailsPage extends Application {

    private Button addButton;
    private Button removeButton;
    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard");

        Text title = new Text("Dashboard Administrador");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        addButton = new Button("Adicionar usuário");
        addButton.setOnAction(e -> openAddClientPage());
        Button viewButton = new Button("Visualizar usuário");
        viewButton.setOnAction(e -> openViewEmployeePage());
        removeButton = new Button("Remover usuário");
        removeButton.setOnAction(e -> openRemoveClientPage());
        Button updateButton = new Button("Atualizar usuário");
        updateButton.setOnAction(e -> openUpdatePage());

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, addButton, viewButton);
        gridPane.addRow(1, removeButton, updateButton);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ajustar a largura dos botões com base na largura da janela
        double buttonWidth = primaryStage.getWidth() * 0.3; // 30% da largura da janela
        addButton.setPrefWidth(buttonWidth);
        viewButton.setPrefWidth(buttonWidth);
        removeButton.setPrefWidth(buttonWidth);
        updateButton.setPrefWidth(buttonWidth);

        // Atualizar a largura dos botões quando a janela for redimensionada
        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double newButtonWidth = newWidth.doubleValue() * 0.3;
            addButton.setPrefWidth(newButtonWidth);
            viewButton.setPrefWidth(newButtonWidth);
            removeButton.setPrefWidth(newButtonWidth);
            updateButton.setPrefWidth(newButtonWidth);
        });
    }

    private void openAddClientPage() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage addEmployeeStage = new Stage();
        AddUser addEmployeePage = new AddUser();
        addEmployeePage.start(addEmployeeStage);
    }

    private void openRemoveClientPage() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage removeUserStage = new Stage();
        RemoveUser removeEmployeePage = new RemoveUser();
        removeEmployeePage.start(removeUserStage);
    }

    private void openViewEmployeePage() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

         Stage viewEmployeeStage = new Stage();
         ViewUser viewEmployeePage = new ViewUser();
         viewEmployeePage.start(viewEmployeeStage);
     }
    
    private void openUpdatePage()  {
        Stage viewEmployeeStage = new Stage();
        UpdatePage updatePage = new UpdatePage();
        updatePage.start(viewEmployeeStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
