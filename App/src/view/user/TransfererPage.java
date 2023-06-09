package view.user;

import java.sql.ResultSet;

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
import model.database.Conn;
import view.utils.AlertUtil;
import view.utils.ButtonComponent;

public class TransfererPage extends Application {
    private Stage stage;

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
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    private void handleTransfer() {
        String amountField = amountTextField.getText();
        double amount = Double.parseDouble(amountField);

        String accountNumberField = accountNumberTextField.getText();

        if (!amountField.isEmpty() && !accountNumberField.isEmpty()) {
            try {
                Conn c1 = new Conn();
        
                String query = "select * from contas where numconta = '" + accountNumberField + "'";
                ResultSet rs = c1.getStatement().executeQuery(query);
        
                if (rs.next()) {
                    double totalAmountOriginAccount = AppSession.getContaUsuarioLogado().getSaldo() - amount;
                    double totalAmountDestinationAccount = rs.getDouble("saldo") + amount;
                
                    Conn c2 = new Conn();
                    String originAccountQuery = "UPDATE contas SET numconta='" + AppSession.getContaUsuarioLogado().getNumConta() +
                        "', titular='" + AppSession.getContaUsuarioLogado().getTitular() +
                        "', saldo='" + totalAmountOriginAccount +
                        "', tipoconta='" + AppSession.getContaUsuarioLogado().getTipoConta() +
                        "', usuarioid='" + AppSession.getContaUsuarioLogado().getUsuarioId() +
                        "' WHERE id='" + AppSession.getContaUsuarioLogado().getId() + "'";
        
                    int rowsAffectedOriginAccount = c2.getStatement().executeUpdate(originAccountQuery);
        
                    Conn c3 = new Conn(); 
                    String destinationAccountQuery = "UPDATE contas SET numconta='" + rs.getString("numconta") +
                        "', titular='" + rs.getString("titular") +
                        "', saldo='" + totalAmountDestinationAccount +
                        "', tipoconta='" + rs.getInt("tipoconta") +
                        "', usuarioid='" + rs.getInt("usuarioid") +
                        "' WHERE id='" + rs.getInt("id") + "'";
        
                    int rowsAffectedDestinationAccount = c3.getStatement().executeUpdate(destinationAccountQuery);

                    rs.close();
        
                    if (rowsAffectedOriginAccount > 0 && rowsAffectedDestinationAccount > 0) {
                        AlertUtil.showSuccessAlert(stage, "Transferido com sucesso");
                        goBackToDetailsPage();
                    } else {
                        AlertUtil.showErrorAlert(null, "Erro ao transferir!");
                    }
                } else {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Os campos estão vazios!");
        }        
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
