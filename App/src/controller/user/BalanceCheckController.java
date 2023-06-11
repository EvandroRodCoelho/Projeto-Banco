package controller.user;

import javafx.stage.Stage;
import view.user.BalanceCheckPage;
import view.user.DetailsUserPage;

public class BalanceCheckController {
    BalanceCheckPage view;

    public BalanceCheckController(BalanceCheckPage view) {
        this.view = view;
    }
    
    public void handleCancelButton() {
        Stage currentStage = (Stage) view.getCancelButton().getScene().getWindow();
        currentStage.close();

        Stage detailsUserStage = new Stage();
        DetailsUserPage detailsUserPage = new DetailsUserPage();
        detailsUserPage.start(detailsUserStage);
    }
}
