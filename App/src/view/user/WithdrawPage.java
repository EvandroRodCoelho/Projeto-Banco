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

public class WithdrawPage extends Application {

    private GridPane gridPane;
    private ButtonComponent withdrawButton;
    private Button cancelButton;
    private TextField amountTextField;
    private Label balanceLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Saque");

        Text title = new Text("Saque");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        withdrawButton = createButton("Sacar", "#1E488F", "white");
        withdrawButton.setOnAction(e -> handleWithdraw());

        cancelButton = createButton("Cancelar", "#D32F2F", "white");
        cancelButton.setOnAction(e -> goBackToDetailsPage());

        amountTextField = new TextField();
        amountTextField.setPromptText("Digite o valor a ser sacado");
        amountTextField.setPrefWidth(250); 

        balanceLabel = new Label("Saldo atual: R$ 0.00");

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(withdrawButton, cancelButton);

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

    private void handleWithdraw() {
        String amountText = amountTextField.getText();
      

       
        //TODO: Adicionar a lógica aqui para realizar o saque

     
        double currentBalance = getCurrentBalance(); // Obtenha o saldo atual do usuário
        double newBalance = currentBalance;
        setBalanceLabel(newBalance);

       
        amountTextField.clear();
    }

    private double getCurrentBalance() {
      
        // TODO: Substitua esse método com a sua lógica real para obter o saldo atual do usuário
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
