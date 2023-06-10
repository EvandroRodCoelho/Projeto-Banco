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
import model.database.Conn;
import view.admin.MainPage;
import view.globals.LoginPage;
import view.utils.ButtonComponent;

public class DetailsUserPage extends Application {

    private GridPane gridPane;
    private ButtonComponent withdrawButton, depositButton, transferButton, balanceCheckButton, logoutButton, deleteAccountButton;
    private double addButtonWidthRatio = 0.3;

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
        
            int accessLevel = AppSession.getUsuarioLogado().getAcesso();
            
            if (accessLevel == 1) {
                Stage loginPageStage = new Stage();
                LoginPage loginPage = new LoginPage();
                loginPage.start(loginPageStage);
            } else if (accessLevel == 2) {
                Stage adminPageStage = new Stage();
                MainPage adminPage = new MainPage();
                adminPage.start(adminPageStage);
            }
        
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
                try {
                    Usuario usuarioLogado = AppSession.getUsuarioLogado();
                    
                    Conn c1 = new Conn();
                    String query = "DELETE FROM usuario WHERE id = '" + usuarioLogado.getId() + "'";
                    int rowsAffected = c1.getStatement().executeUpdate(query);
        
                    if (rowsAffected > 0) {                
                       handleLogout();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getContaUsuario(){
        try {
            Usuario usuarioLogado = AppSession.getUsuarioLogado();
            
            Conn c1 = new Conn();
            String query = "select * from contas where usuarioid = '" + usuarioLogado.getId() + "'";

            ResultSet rs = c1.getStatement().executeQuery(query);

            if (rs.next()) {                
                int id = rs.getInt("id");  
                int userId = rs.getInt("usuarioId");   
                int typeAccount = rs.getInt("tipoConta");                 
                double withdraw = rs.getDouble("saldo");
                String accountHolder = rs.getString("titular");
                String accountNumber = rs.getString("numConta");
    
                Conta conta1 = new Conta(accountNumber, accountHolder, withdraw, typeAccount, userId, id);
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
