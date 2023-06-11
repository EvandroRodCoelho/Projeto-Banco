package controller.user;

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
        String amountField =view.getAmountTextField().getText();

        double amountConverted = Integer.parseInt(view.getAmountTextField().getText());
        double currentAmount = AppSession.getContaUsuarioLogado().getSaldo();
        double totalAmount = currentAmount - amountConverted;

        if (!amountField.isEmpty()) {
            try {
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
            } catch (Exception e) {
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
