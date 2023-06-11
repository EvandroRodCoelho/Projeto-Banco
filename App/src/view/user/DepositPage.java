package  view.user;

import controller.user.DepositController;
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

public class DepositPage extends Application {
    private Stage stage;

    private GridPane gridPane;
    private ButtonComponent depositButton;
    private Button cancelButton;
    private TextField amountTextField;
    private Label balanceLabel;

    private DepositController controller =  new DepositController(this);

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Depósito");

        Text title = new Text("Depósito");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        depositButton = createButton("Depositar", "#1E488F", "white");
        depositButton.setOnAction(e -> controller.handleDeposit());

        cancelButton = createButton("Cancelar", "#D32F2F", "white");
        cancelButton.setOnAction(e -> controller.goBackToDetailsPage());

        amountTextField = new TextField();
        amountTextField.setPromptText("Digite o valor a ser depositado");
        amountTextField.setPrefWidth(250); 

        balanceLabel = new Label("Saldo atual: " + AppSession.getContaUsuarioLogado().getSaldo());

        HBox buttonBox = new HBox(10); 
        buttonBox.getChildren().addAll(depositButton, cancelButton);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, title);
        gridPane.addRow(1, amountTextField);
        gridPane.addRow(2, balanceLabel);
        gridPane.addRow(3, buttonBox);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
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

    public ButtonComponent getDepositButton() {
        return depositButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public TextField getAmountTextField() {
        return amountTextField;
    }

    public Label getBalanceLabel() {
        return balanceLabel;
    }

    public DepositController getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
