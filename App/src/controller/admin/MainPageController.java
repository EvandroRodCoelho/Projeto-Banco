package controller.admin;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import view.admin.DetailsPage;
import view.admin.MainPage;
import view.globals.LoginPage;

public class MainPageController {
    MainPage view;

    public MainPageController(MainPage view) {
        this.view = view;
    }
    public void handleManageAccounts(ActionEvent event) {
        Stage currentStage = (Stage) view.getManageAccountsButton().getScene().getWindow();
        currentStage.close();

        Stage managerState = new Stage();
        
        new view.admin.account.DetailsPage().start(managerState);
    }

    public void handleManageUsers(ActionEvent event) {
        Stage currentStage = (Stage) view.getManageAccountsButton().getScene().getWindow();
        currentStage.close();

        Stage managerUserState = new Stage();
        
        new DetailsPage().start(managerUserState);
  
    }

    public void handleLogout(ActionEvent event) { 
        Stage currentStage = (Stage) view.getLogoutButton().getScene().getWindow();
        currentStage.close();
        Stage loginPageStage = new Stage();
        LoginPage loginPage = new LoginPage();
        loginPage.start(loginPageStage);
    }
}
