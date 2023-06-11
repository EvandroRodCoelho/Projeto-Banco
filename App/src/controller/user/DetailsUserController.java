package controller.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.AppSession;
import model.Conta;
import model.Usuario;
import model.database.Conn;
import view.admin.MainPage;
import view.globals.LoginPage;
import view.user.BalanceCheckPage;
import view.user.DepositPage;
import view.user.DetailsUserPage;
import view.user.TransfererPage;
import view.user.WithdrawPage;

public class DetailsUserController {
    DetailsUserPage view;

    public DetailsUserController(DetailsUserPage view) {
        this.view = view;
    }
    public void handleWithdraw() {
        Stage currentStage = (Stage) view.getWithdrawButton().getScene().getWindow();
        currentStage.close();

        Stage withdrawPageStage = new Stage();
        WithdrawPage withdrawPage = new WithdrawPage();
        withdrawPage.start(withdrawPageStage);
    }

    public void handleDeposit() {
        Stage currentStage = (Stage) view.getDepositButton().getScene().getWindow();
        currentStage.close();

        Stage depositPageStage = new Stage();
        DepositPage depositPage = new DepositPage();
        depositPage.start(depositPageStage);
    }

    public void handleTransfer() {
        Stage currentStage = (Stage) view.getTransferButton().getScene().getWindow();
        currentStage.close();

        Stage transfererPageStage = new Stage();
        TransfererPage transfererPage = new TransfererPage();
        transfererPage.start(transfererPageStage);
    }

    public void handleBalanceCheck() {
        Stage currentStage = (Stage) view.getBalanceCheckButton().getScene().getWindow();
        currentStage.close();

        Stage BalanceCheckPageStage = new Stage();
        BalanceCheckPage checkBalancePage = new BalanceCheckPage();
        checkBalancePage.start(BalanceCheckPageStage);
    }
    
    public void handleLogout() {
       
            Stage currentStage = (Stage) view.getLogoutButton().getScene().getWindow();
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
    
    public void confirmDeleteAccount() {
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

    public void getContaUsuario(){
        try {
            Usuario usuarioLogado = AppSession.getUsuarioLogado();
            System.out.println(usuarioLogado.getNome());
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
}
