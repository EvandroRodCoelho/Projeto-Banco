package controller.admin;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import view.admin.AddUser;
import view.admin.ListUsersPage;
import view.admin.MainPage;
import view.admin.RemoveUserPage;
import view.admin.UpdateUserPage;
import view.utils.ButtonComponent;

public class DetailsPageController {

    public void handleAddButtonAction(ActionEvent event) {
        Stage currentStage = (Stage) ((ButtonComponent) event.getSource()).getScene().getWindow();
        currentStage.close();

        Stage addEmployeeStage = new Stage();
        AddUser addUser = new AddUser();
        addUser.start(addEmployeeStage);
    }

    public void handleRemoveButtonAction(ActionEvent event) {
        Stage currentStage = (Stage) ((ButtonComponent) event.getSource()).getScene().getWindow();
        currentStage.close();

        Stage removeUserStage = new Stage();
        RemoveUserPage removeUser = new RemoveUserPage();
        removeUser.start(removeUserStage);
    }

    public void handleViewButtonAction(ActionEvent event) {
        Stage currentStage = (Stage) ((ButtonComponent) event.getSource()).getScene().getWindow();
        currentStage.close();

        Stage viewEmployeeStage = new Stage();
        ListUsersPage listUsersPage = new ListUsersPage();
        listUsersPage.start(viewEmployeeStage);
    }

    public void handleUpdateButtonAction(ActionEvent event) {
        Stage currentStage = (Stage) ((ButtonComponent) event.getSource()).getScene().getWindow();
        currentStage.close();

        Stage updatePageStage = new Stage();
        UpdateUserPage updatePage = new UpdateUserPage();
        updatePage.start(updatePageStage);
    }

    public void handleLogoutButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((ButtonComponent) event.getSource()).getScene().getWindow();
        stage.close();

        Stage mainPageStage = new Stage();
        MainPage mainPage = new MainPage();
        mainPage.start(mainPageStage);
    }
}
