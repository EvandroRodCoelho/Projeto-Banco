package controller.user;

import controller.utils.validador.ValidatorData;
import javafx.stage.Stage;
import model.AppSession;
import model.database.Conn;
import view.user.DetailsUserPage;
import view.user.WithdrawPage;
import view.utils.AlertUtil;

public class WithdrawController {
    WithdrawPage view;

    public WithdrawController(WithdrawPage view) {
        this.view = view;
    }

    public void handleWithdraw() {
        String amountField = view.getAmountTextField().getText();

        if (!amountField.isEmpty()) {
            try {
                ValidatorData.isValidNumber(amountField);

                double currentAmount = AppSession.getContaUsuarioLogado().getSaldo();
                double totalAmount = currentAmount - Double.parseDouble(amountField);

                String query = "UPDATE contas SET numconta='" + AppSession.getContaUsuarioLogado().getNumConta() +
                        "', titular='" + AppSession.getContaUsuarioLogado().getTitular() +
                        "', saldo='" + totalAmount +
                        "', tipoconta='" + AppSession.getContaUsuarioLogado().getTipoConta() +
                        "', usuarioid='" + AppSession.getContaUsuarioLogado().getUsuarioId() +
                        "' WHERE id='" + AppSession.getContaUsuarioLogado().getId() + "'";

                Conn c1 = new Conn();
                int rowsAffected = c1.getStatement().executeUpdate(query);

                if (rowsAffected > 0) {
                    AlertUtil.showSuccessAlert(view.getStage(), "Sacado com sucesso");
                    goBackToDetailsPage();
                } else {
                    AlertUtil.showErrorAlert(view.getStage(), "Erro ao sacar!");
                }
            } catch (NumberFormatException e) {
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
        Stage currentStage = (Stage) view.getGridPane().getScene().getWindow();
        currentStage.close();

        Stage detailsUserStage = new Stage();
        DetailsUserPage detailsUserPage = new DetailsUserPage();
        detailsUserPage.start(detailsUserStage);
    }
}
