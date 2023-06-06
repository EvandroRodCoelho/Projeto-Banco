package  view.user;
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

public class DepositPage extends Application {

    private GridPane gridPane;
    private ButtonComponent depositButton;
    private Button cancelButton;
    private TextField amountTextField;
    private Label balanceLabel;
    private String  amountText;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Depósito");

        Text title = new Text("Depósito");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        depositButton = createButton("Depositar", "#1E488F", "white");
        depositButton.setOnAction(e -> handleDeposit());

        cancelButton = createButton("Cancelar", "#D32F2F", "white");
        cancelButton.setOnAction(e -> goBackToDetailsPage());

        amountTextField = new TextField();
        amountTextField.setPromptText("Digite o valor a ser depositado");
        amountTextField.setPrefWidth(250); 

        balanceLabel = new Label("Saldo atual: R$ 0.00");

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

    private void handleDeposit(){
   
        double currentBalance = getCurrentBalance(); 
        double newBalance = currentBalance + 100;
        setBalanceLabel(newBalance);


        amountTextField.clear();
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
