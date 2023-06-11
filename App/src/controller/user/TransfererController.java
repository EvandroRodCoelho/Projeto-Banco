package controller.user;

import java.sql.ResultSet;

import javafx.stage.Stage;
import model.AppSession;
import model.database.Conn;
import view.user.DetailsUserPage;
import view.user.TransfererPage;
import view.utils.AlertUtil;

public class TransfererController {
    private TransfererPage view;

    public TransfererController(TransfererPage view) {
        this.view = view;
    }

    public void handleTransfer() {
        String amountField = view.getAmountTextField().getText();
        double amount = Double.parseDouble(amountField);

        String accountNumberField = view.getAccountNumberTextField().getText();

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
                        AlertUtil.showSuccessAlert(view.getStage(), "Transferido com sucesso");
                        goBackToDetailsPage();
                    } else {
                        AlertUtil.showErrorAlert(view.getStage(), "Erro ao transferir!");
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

    public void goBackToDetailsPage() {
        Stage currentStage = (Stage) view.getGridPane().getScene().getWindow();
        currentStage.close();

        Stage detailsUserStage = new Stage();
        DetailsUserPage detailsUserPage = new DetailsUserPage();
        detailsUserPage.start(detailsUserStage);
    }
}
