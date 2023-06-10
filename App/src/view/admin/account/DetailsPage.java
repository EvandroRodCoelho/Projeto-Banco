package view.admin.account;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.globals.LoginPage;
import view.utils.ButtonComponent;

public class DetailsPage extends Application {

    private GridPane gridPane;
    private ButtonComponent viewButton, updateButton, removeButton, addButton, logoutButton;
    private double addButtonWidthRatio = 0.3;
    private double logoutButtonWidthRatio = 0.6;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard");

        Text title = new Text("Área Administrador");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        addButton = createButton("Adicionar conta", "#1E488F", "white");
        addButton.setOnAction(e -> openAddAccountPage());

        viewButton = createButton("Visualizar contas", "#1E488F", "white");
        viewButton.setOnAction(e -> openViewAccountPage());

        removeButton = createButton("Remover conta", "#1E488F", "white");
        removeButton.setOnAction(e -> openRemoveAccountPage());

        updateButton = createButton("Atualizar conta", "#1E488F", "white");
        updateButton.setOnAction(e -> openUpdateAccountPage());

        logoutButton = createButton("Sair", "#D32F2F", "white");
        logoutButton.setOnAction(e -> logout());
        logoutButton.setMaxWidth(Double.MAX_VALUE);
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, addButton, viewButton);
        gridPane.addRow(1, removeButton, updateButton);
        gridPane.add(logoutButton, 0, 2, 2, 1); 
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double newButtonWidth = newWidth.doubleValue() * addButtonWidthRatio;
            double maxWidth = 300; 
            double maxLogoutButtonWidth = newWidth.doubleValue() * logoutButtonWidthRatio;

            if (newButtonWidth > maxWidth) {
                newButtonWidth = maxWidth;
            }

            if (maxLogoutButtonWidth > maxWidth) {
                maxLogoutButtonWidth = maxWidth;
            }

            addButton.setPrefWidth(newButtonWidth);
            viewButton.setPrefWidth(newButtonWidth);
            removeButton.setPrefWidth(newButtonWidth);
            updateButton.setPrefWidth(newButtonWidth);
            logoutButton.setPrefWidth(maxLogoutButtonWidth);
        });
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    private void openAddAccountPage() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage addStage = new Stage();
        AddAccount addPage = new AddAccount();
        addPage.start(addStage);
    }

    private void openRemoveAccountPage() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage removeStage = new Stage();
        RemoveAccount removePage = new RemoveAccount();
        removePage.start(removeStage);
    }

    private void openViewAccountPage() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage viewStage = new Stage();
        ListAccountsPage viewPage = new ListAccountsPage();
        viewPage.start(viewStage);
    }

    private void openUpdateAccountPage() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();
        
        Stage viewStage = new Stage();
        UpdateAccount updatePage = new UpdateAccount();
        updatePage.start(viewStage);
    }
    
    private void logout() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();

        Stage loginPageStage = new Stage();
        LoginPage loginPage = new LoginPage();
        loginPage.start(loginPageStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
