package controller.user;

import controller.utils.validador.ValidatorData;
import javafx.stage.Stage;
import model.AppSession;
import model.database.Conn;
import view.user.DepositPage;
import view.user.DetailsUserPage;
import view.utils.AlertUtil;

public class DepositController {
    DepositPage view;

    public DepositController(DepositPage view) {
        this.view = view;
    }
    public void handleDeposit() {
        String amountField = view.getAmountTextField().getText();

        if (!amountField.isEmpty()) {
            try {
                ValidatorData.isValidNumber(amountField);

                double amountConverted = Integer.parseInt(view.getAmountTextField().getText());
                double currentAmount = AppSession.getContaUsuarioLogado().getSaldo();
                double totalAmount = amountConverted + currentAmount;

                String query = "UPDATE contas SET numconta='" + AppSession.getContaUsuarioLogado().getNumConta() + 
                    "', titular='" + AppSession.getContaUsuarioLogado().getTitular() + 
                    "', saldo='" + totalAmount + 
                    "', tipoconta='" + AppSession.getContaUsuarioLogado().getTipoConta() + 
                    "', usuarioid='" + AppSession.getContaUsuarioLogado().getUsuarioId() + 
                    "' WHERE id='" + AppSession.getContaUsuarioLogado().getId() + "'";
                
                Conn c1 = new Conn();
                int rowsAffected = c1.getStatement().executeUpdate(query);

                if (rowsAffected > 0) {
                    AlertUtil.showSuccessAlert(view.getStage(), "Depositado com sucesso");
                    goBackToDetailsPage();
                } else {
                    AlertUtil.showErrorAlert(view.getStage(), "Erro ao depositar!");
                }
            } 
            catch (NumberFormatException e) {
                AlertUtil.showErrorAlert(view.getStage(), "Somente números são aceitos!");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(view.getStage(), "O campo está vazio!");
        }

        view.getAmountTextField().clear();
    }

    public void goBackToDetailsPage() {
        Stage currentStage = (Stage) view.getCancelButton().getScene().getWindow();
        currentStage.close();

        Stage detailsUserStage = new Stage();
        DetailsUserPage detailsUserPage = new DetailsUserPage();
        detailsUserPage.start(detailsUserStage);
    }

}
