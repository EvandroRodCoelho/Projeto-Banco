package view.user;

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
import view.utils.ButtonComponent;

public class TransfererPage extends Application {

    private GridPane gridPane;
    private ButtonComponent transferButton;
    private Button cancelButton;
    private TextField amountTextField;
    private TextField accountNumberTextField;
    private Label balanceLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Transferência");

        Text title = new Text("Transferência");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        transferButton = createButton("Transferir", "#1E488F", "white");
        transferButton.setOnAction(e -> handleTransfer());

        cancelButton = createButton("Cancelar", "#D32F2F", "white");
        cancelButton.setOnAction(e -> goBackToDetailsPage());

        amountTextField = new TextField();
        amountTextField.setPromptText("Digite o valor a ser transferido");
        amountTextField.setPrefWidth(250); // Aumenta o tamanho do campo de entrada de valor

        accountNumberTextField = new TextField();
        accountNumberTextField.setPromptText("Digite o número da conta de destino");
        accountNumberTextField.setPrefWidth(250); // Aumenta o tamanho do campo de entrada de número da conta

        balanceLabel = new Label("Saldo atual: R$ 0.00");

        HBox buttonBox = new HBox(10); // Define o espaçamento entre os botões
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
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    private void handleTransfer() {
        String amountText = amountTextField.getText();
        double amount = Double.parseDouble(amountText);

        String accountNumber = accountNumberTextField.getText();

        // Lógica para realizar a transferência
        // Você pode adicionar a lógica aqui para realizar a transferência

        // Atualizar o saldo atual
        double currentBalance = getCurrentBalance(); // Obtenha o saldo atual do usuário
        double newBalance = currentBalance - amount;
        setBalanceLabel(newBalance);

        // Limpar os campos de entrada de valor e número da conta
        amountTextField.clear();
        accountNumberTextField.clear();
    }

    private double getCurrentBalance() {
        // Lógica para obter o saldo atual do usuário
        // Substitua esse método com a sua lógica real para obter o saldo atual do usuário
        return 0.00;
    }

    private void setBalanceLabel(double balance) {
        balanceLabel.setText("Saldo atual: R$ " + balance);
    }

    private void goBackToDetailsPage() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage detailsUserStage = new Stage();
        DetailsUserPage detailsUserPage = new DetailsUserPage();
        detailsUserPage.start(detailsUserStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
