package view.user;

import controller.user.TransfererController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AppSession;
import view.utils.ButtonComponent;

public class TransfererPage extends Application {
    private Stage stage;

    private GridPane gridPane;
    private ButtonComponent transferButton;
    private Button cancelButton;
    private TextField amountTextField;
    private TextField accountNumberTextField;
    private Label balanceLabel;

    TransfererController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new TransfererController(this);
        primaryStage.setTitle("Transferência");

        Text title = new Text("Transferência");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        transferButton = createButton("Transferir", "#1E488F", "white");
        transferButton.setOnAction(e -> controller.handleTransfer());

        cancelButton = createButton("Cancelar", "#D32F2F", "white");
        cancelButton.setOnAction(e -> controller.goBackToDetailsPage());

        amountTextField = new TextField();
        amountTextField.setPromptText("Digite o valor a ser transferido");
        amountTextField.setPrefWidth(250);

        accountNumberTextField = new TextField();
        accountNumberTextField.setPromptText("Digite o número da conta de destino");
        accountNumberTextField.setPrefWidth(250);

        balanceLabel = new Label("Saldo atual: " + AppSession.getContaUsuarioLogado().getSaldo());

        HBox buttonBox = new HBox(10); 
        buttonBox.getChildren().addAll(transferButton, cancelButton);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, title);
        gridPane.addRow(1, amountTextField);
        gridPane.addRow(2, accountNumberTextField);
        gridPane.addRow(3, balanceLabel);
        gridPane.addRow(4, buttonBox);

        Scene scene = new Scene(gridPane, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
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

    public ButtonComponent getTransferButton() {
        return transferButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public TextField getAmountTextField() {
        return amountTextField;
    }

    public TextField getAccountNumberTextField() {
        return accountNumberTextField;
    }

    public Label getBalanceLabel() {
        return balanceLabel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
