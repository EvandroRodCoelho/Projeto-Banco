package view.admin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.utils.ButtonComponent;

public class DetailsPage extends Application {

    private GridPane gridPane;
    private ButtonComponent viewButton, updateButton, removeButton, addButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard");

        Text title = new Text("Dashboard Administrador");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        addButton = createButton("Adicionar usuário", "#1E488F", "white");
        addButton.setOnAction(e -> openAddClientPage());

        viewButton = createButton("Visualizar usuário", "#1E488F", "white");
        viewButton.setOnAction(e -> openViewEmployeePage());

        removeButton = createButton("Remover usuário", "#1E488F", "white");
        removeButton.setOnAction(e -> openRemoveClientPage());

        updateButton = createButton("Atualizar usuário", "#1E488F", "white");
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

        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double newButtonWidth = newWidth.doubleValue() * 0.3;
            addButton.setPrefWidth(newButtonWidth);
            viewButton.setPrefWidth(newButtonWidth);
            removeButton.setPrefWidth(newButtonWidth);
            updateButton.setPrefWidth(newButtonWidth);
        });
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
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

    private void openUpdatePage() {
        Stage viewEmployeeStage = new Stage();
        UpdatePage updatePage = new UpdatePage();
        updatePage.start(viewEmployeeStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
