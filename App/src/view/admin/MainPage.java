package view.admin;

import controller.admin.MainPageController;
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

public class MainPage extends Application {

    private GridPane gridPane;
    private ButtonComponent manageAccountsButton;
    private ButtonComponent manageUsersButton;
    private ButtonComponent logoutButton;
    private final double addButtonWidthRatio = 0.3;
    private final double logoutButtonWidthRatio = 0.6;


    private MainPageController controller = new MainPageController(this);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Page");

        Text title = new Text("Main Page");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        manageAccountsButton = createButton("Gerenciar Conta", "#1E488F", "white");
        manageAccountsButton.setOnAction(controller::handleManageAccounts);

        manageUsersButton = createButton("Gerenciar Usuários", "#1E488F", "white");
        manageUsersButton.setOnAction(controller::handleManageUsers);

        logoutButton = createButton("Sair", "#D32F2F", "white");
        logoutButton.setOnAction(controller::handleLogout);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, manageAccountsButton);
        gridPane.addRow(1, manageUsersButton);
        gridPane.add(logoutButton, 0, 2, 2, 1);

        Scene scene = new Scene(gridPane, 400, 250);
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

            manageAccountsButton.setPrefWidth(newButtonWidth);
            manageUsersButton.setPrefWidth(newButtonWidth);
            logoutButton.setPrefWidth(maxLogoutButtonWidth);
        });
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }


    public ButtonComponent getManageAccountsButton() {
        return manageAccountsButton;
    }

    public ButtonComponent getManageUsersButton() {
        return manageUsersButton;
    }

    public ButtonComponent getLogoutButton() {
        return logoutButton;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
