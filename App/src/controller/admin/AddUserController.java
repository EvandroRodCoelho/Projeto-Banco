package controller.admin;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.database.Conn;
import view.admin.DetailsPage;
import view.admin.AddUser;
import view.utils.AlertUtil;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import controller.utils.validador.ValidadorAccountUser;
import controller.utils.validador.ValidadorAccountUser.ValidationException;

public class AddUserController {
    private AddUser view;

    public AddUserController(AddUser view) {
        this.view = view;
    }

    public void handleInsertUserDetails(ActionEvent e) {
        setInputsAndButtonsEnabled(false);

        String name = view.getNameTextField().getText();
        String email = view.getEmailTextField().getText();
        String password = view.getPasswordTextField().getText();
        String accessLevel = view.getAccessLevelComboBox().getValue();

        try {
            ValidadorAccountUser.validateName(name);
            ValidadorAccountUser.validateEmail(email);
            ValidadorAccountUser.validatePassword(password);

            Conn conn = new Conn();
            String query = "INSERT INTO usuario (nome, email, senha, acesso) VALUES ('"
                    + name + "', '" + email + "', '" + password + "', '" + Integer.parseInt(accessLevel) + "')";
            int rowsAffected = conn.getStatement().executeUpdate(query);

            if (rowsAffected > 0) {
                AlertUtil.showSuccessAlert(view.getStage(), "Adicionado com sucesso");
            }
            conn.close();
            clearInputs();
            setInputsAndButtonsEnabled(true);
        } catch (ValidationException ev) {
            
            AlertUtil.showErrorAlert(null, ev.getMessage());
            setInputsAndButtonsEnabled(true);
        } 
        catch (SQLIntegrityConstraintViolationException  ev) {
            AlertUtil.showErrorAlert(null, "Email Já existe, tente outro");
            setInputsAndButtonsEnabled(true);
        }
        catch (SQLException ev) {
            AlertUtil.showErrorAlert(view.getStage(), "Ocorreu um erro");
            setInputsAndButtonsEnabled(true);
        } 
    

        setInputsAndButtonsEnabled(true);
    }

    public void handleCancelButton(ActionEvent event) {
        view.getStage().close();
        new DetailsPage().start(new Stage());
    }

    private void setInputsAndButtonsEnabled(boolean enabled) {
        view.getNameTextField().setDisable(!enabled);
        view.getEmailTextField().setDisable(!enabled);
        view.getPasswordTextField().setDisable(!enabled);
        view.getAccessLevelComboBox().setDisable(!enabled);
        view.getSubmitButton().setDisable(!enabled);
        view.getCancelButton().setDisable(!enabled);
        view.getAddButton().setDisable(!enabled);
        view.getSubmitButton().setDisable(!enabled);
    }

    private void clearInputs() {
        view.getNameTextField().clear();
        view.getEmailTextField().clear();
        view.getPasswordTextField().clear();
    }
}
