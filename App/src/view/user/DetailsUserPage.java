package view.user;

import controller.user.DetailsUserController;
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

public class DetailsUserPage extends Application {
    private Stage stage;
    private GridPane gridPane;
    private ButtonComponent withdrawButton, depositButton, transferButton, balanceCheckButton, logoutButton, deleteAccountButton;
    private double addButtonWidthRatio = 0.3;
    private DetailsUserController controller;

    public DetailsUserPage() {
        this.controller  = new DetailsUserController(this);
        controller.getContaUsuario();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Detalhes do Usuário");

        Text title = new Text("Detalhes do Usuário");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        withdrawButton = createButton("Sacar", "#1E488F", "white");
        withdrawButton.setOnAction(e ->controller.handleWithdraw());

        depositButton = createButton("Depositar", "#1E488F", "white");
        depositButton.setOnAction(e -> controller.handleDeposit());

        transferButton = createButton("Transferir", "#1E488F", "white");
        transferButton.setOnAction(e -> controller.handleTransfer());

        balanceCheckButton = createButton("Saldo", "#1E488F", "white");
        balanceCheckButton.setOnAction(e -> controller.handleBalanceCheck());

        logoutButton = createButton("Sair", "#D32F2F", "white");
        logoutButton.setOnAction(e -> controller.handleLogout());

        deleteAccountButton = createButton("Excluir conta", "#D32F2F", "white");
        deleteAccountButton.setOnAction(e -> controller.confirmDeleteAccount());

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, withdrawButton, depositButton);
        gridPane.addRow(1, transferButton, balanceCheckButton);
        gridPane.addRow(2, logoutButton, deleteAccountButton);

        Scene scene = new Scene(gridPane, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double newButtonWidth = newWidth.doubleValue() * addButtonWidthRatio;
            double maxButtonWidth = 200; 
            
            if (newButtonWidth > maxButtonWidth) {
                newButtonWidth = maxButtonWidth;
            }
    
        
            withdrawButton.setPrefWidth(newButtonWidth);
            depositButton.setPrefWidth(newButtonWidth);
            transferButton.setPrefWidth(newButtonWidth);
            balanceCheckButton.setPrefWidth(newButtonWidth);
            logoutButton.setPrefWidth(newButtonWidth);
            deleteAccountButton.setPrefWidth(newButtonWidth);
        });

        stage = primaryStage;
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    public Stage getStage() {
        return stage;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public ButtonComponent getWithdrawButton() {
        return withdrawButton;
    }

    public ButtonComponent getDepositButton() {
        return depositButton;
    }

    public ButtonComponent getTransferButton() {
        return transferButton;
    }

    public ButtonComponent getBalanceCheckButton() {
        return balanceCheckButton;
    }

    public ButtonComponent getLogoutButton() {
        return logoutButton;
    }

    public ButtonComponent getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public double getAddButtonWidthRatio() {
        return addButtonWidthRatio;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
