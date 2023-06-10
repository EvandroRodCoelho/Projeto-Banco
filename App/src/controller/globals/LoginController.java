package controller.globals;
import javafx.event.ActionEvent;

import javafx.stage.Stage;
import model.AppSession;
import model.Usuario;
import model.database.Conn;
import view.admin.MainPage;
import view.globals.LoginPage;
import view.user.DetailsUserPage;
import view.user.RegisterPage;
import view.utils.AlertUtil;
import view.utils.ButtonComponent;

import java.sql.ResultSet;

public class LoginController {
    private LoginPage view;

    public LoginController(LoginPage view) {
        this.view = view;
    }


    public void handleLoginButtonAction(ActionEvent event) {
        String email = view.getEmaTextField().getText();
        String password = view.getPasswordTextField().getText();
        Stage currentStage = (Stage)  ((ButtonComponent) event.getSource()).getScene().getWindow();
        if (email.isEmpty() || password.isEmpty()) {
            AlertUtil.showErrorAlert(view.getStage(), "Login inválido!");
            return;
        }

        try {
            Conn conn = new Conn();
            view.getEmaTextField().setDisable(true);
            view.getCancelButton().setDisable(true);
            String query = "select * from usuario where email='" + email + "' and senha='" + password + "' ";
            ResultSet result = conn.getStatement().executeQuery(query);
            if (result.next()) {
                int acesso = result.getInt("acesso");
                int id = result.getInt("id");
                String userName = result.getString("nome");
                String userEmail = result.getString("email");
                String userAccountType = result.getString("senha");
                int accountNum = result.getInt("acesso");

                Usuario user = new Usuario(id, userName, userEmail, userAccountType, accountNum);
                AppSession.setUsuarioLogado(user);

                if (acesso == 2) {
                    new MainPage().start(new Stage());
                    currentStage.close();
                } else {
                    new DetailsUserPage().start(new Stage());
                    currentStage.close();
                }
            } else {
                AlertUtil.showErrorAlert(view.getStage(), "Login inválido!");
                view.getEmaTextField().setDisable(false);
                view.getPasswordTextField().setDisable(false);
                view.getCancelButton().setDisable(false);
            }
        } catch (Exception ex) {
            AlertUtil.showErrorAlert(view.getStage(), "Ocorreu um erro inesperado!");
                view.getEmaTextField().setDisable(false);
                view.getPasswordTextField().setDisable(false);
                view.getCancelButton().setDisable(false);
        }
    }

    public void handleCancelAction(ActionEvent event) {
        view.getStage().close();
    }

    public void handleRegisterAction(ActionEvent event) {
         view.getStage().close();
        new RegisterPage().start(new Stage());
    }

}
