package view.user;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AppSession;
import model.Conta;
import model.Usuario;
import view.admin.conn;
import view.utils.ButtonComponent;

public class DetailsUserPage extends Application {

    private GridPane gridPane;
    private ButtonComponent withdrawButton, depositButton, transferButton, balanceCheckButton, logoutButton, deleteAccountButton;

    public DetailsUserPage() {
        this.getContaUsuario();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Detalhes do Usuário");

        Text title = new Text("Detalhes do Usuário");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        withdrawButton = createButton("Sacar", "#1E488F", "white");
        withdrawButton.setOnAction(e -> handleWithdraw());

        depositButton = createButton("Depositar", "#1E488F", "white");
        depositButton.setOnAction(e -> handleDeposit());

        transferButton = createButton("Transferir", "#1E488F", "white");
        transferButton.setOnAction(e -> handleTransfer());

        balanceCheckButton = createButton("Saldo", "#1E488F", "white");
        balanceCheckButton.setOnAction(e -> handleBalanceCheck());

        logoutButton = createButton("Sair", "#D32F2F", "white");
        logoutButton.setOnAction(e -> handleLogout());

        deleteAccountButton = createButton("Excluir conta", "#D32F2F", "white");
        deleteAccountButton.setOnAction(e -> confirmDeleteAccount());

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
            double newButtonWidth = newWidth.doubleValue() * 0.3;
            withdrawButton.setPrefWidth(newButtonWidth);
            depositButton.setPrefWidth(newButtonWidth);
            transferButton.setPrefWidth(newButtonWidth);
            balanceCheckButton.setPrefWidth(newButtonWidth);
            logoutButton.setPrefWidth(newButtonWidth);
            deleteAccountButton.setPrefWidth(newButtonWidth);
        });
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    private void handleWithdraw() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage withdrawPageStage = new Stage();
        WithdrawPage withdrawPage = new WithdrawPage();
        withdrawPage.start(withdrawPageStage);
    }

    private void handleDeposit() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage depositPageStage = new Stage();
        DepositPage depositPage = new DepositPage();
        depositPage.start(depositPageStage);
    }

    private void handleTransfer() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage transfererPageStage = new Stage();
        TransfererPage transfererPage = new TransfererPage();
        transfererPage.start(transfererPageStage);
    }

    private void handleBalanceCheck() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        Stage BalanceCheckPageStage = new Stage();
        BalanceCheckPage checkBalancePage = new BalanceCheckPage();
        checkBalancePage.start(BalanceCheckPageStage);
    }
    
    private void handleLogout() {
        Stage currentStage = (Stage) gridPane.getScene().getWindow();
        currentStage.close();

        // Adicione aqui a lógica para fazer logout do usuário
    }
    
    private void confirmDeleteAccount() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Excluir conta");
        alert.setContentText("Tem certeza de que deseja excluir sua conta? Esta ação é irreversível.");

        ButtonType confirmButton = new ButtonType("Confirmar");
        ButtonType cancelButton = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(confirmButton, cancelButton);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == confirmButton) {
               
            }
        });
    }

    private void getContaUsuario(){
        try {
            Usuario usuarioLogado = AppSession.getUsuarioLogado();
            
            conn c1 = new conn();
            String query = "select * from contas where usuarioid = '" + usuarioLogado.getId() + "'";

            ResultSet rs = c1.st.executeQuery(query);

            if (rs.next()) {                
                int id = rs.getInt("id");  
                int usuarioid = rs.getInt("usuarioId");   
                int tipoConta = rs.getInt("tipoConta");                 
                double saldo = rs.getDouble("saldo");
                String titular = rs.getString("titular");
                String numConta = rs.getString("numConta");
    
                Conta conta1 = new Conta(numConta, titular, saldo, tipoConta, usuarioid, id);
                AppSession.setContaUsuarioLogado(conta1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
