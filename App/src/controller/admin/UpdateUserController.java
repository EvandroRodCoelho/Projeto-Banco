package controller.admin;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import controller.utils.validador.ValidadorAccountUser;
import controller.utils.validador.ValidadorAccountUser.ValidationException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.database.Conn;
import view.admin.DetailsPage;
import view.admin.UpdateUserPage;
import view.utils.AlertUtil;

public class UpdateUserController {
    private UpdateUserPage view;

    public UpdateUserController(UpdateUserPage view) {
        this.view = view;
    }

    public void handleSearchButton(ActionEvent event) {
        String userId = view.getIdTextField().getText();
        if (!userId.isEmpty()) {
            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM usuario WHERE id = '" + userId + "'";
                ResultSet result = conn.getStatement().executeQuery(query);
                if (result.next()) {
                    String nome = result.getString("nome");
                    String email = result.getString("email");
                    String senha = result.getString("senha");
                    String acesso = result.getString("acesso");

                    view.getNameTextField().setText(nome);
                    view.getEmailTextField().setText(email);
                    view.getPasswordTextField().setText(senha);
                    view.getAccessLevelComboBox().setValue(acesso);
                } else {
                    AlertUtil.showErrorAlert(null, "Usuário não encontrado");
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Digite um ID de usuário");
        }
    }

    public void handleUpdateButton(ActionEvent event) {
        String id = view.getIdTextField().getText();
        String name = view.getNameTextField().getText();
        String email = view.getEmailTextField().getText();
        String password = view.getPasswordTextField().getText();
        String accessLevel = view.getAccessLevelComboBox().getValue();

        if (!id.isEmpty()) {
            try {
                ValidadorAccountUser.validateName(name);
                ValidadorAccountUser.validateEmail(email);
                ValidadorAccountUser.validatePassword(password);

                Conn conn = new Conn();
                String query = "UPDATE usuario SET nome='" + name + "', email='" + email + "', senha='" + password + "', acesso='" + accessLevel + "' WHERE id='" + id + "'";
                int rowsAffected = conn.getStatement().executeUpdate(query);

                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atualizar dados do Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Atualizado com sucesso!");
                    alert.showAndWait();

                    view.getIdTextField().setText("");
                    view.getNameTextField().setText("");
                    view.getEmailTextField().setText("");
                    view.getPasswordTextField().setText("");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Atualizar dados do Usuário");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuário não encontrado!");
                    alert.showAndWait();
                }
                conn.close();
            } catch (ValidationException e) {
                AlertUtil.showErrorAlert(null, e.getMessage());
            } catch (SQLIntegrityConstraintViolationException e) {
                AlertUtil.showErrorAlert(null, "Email Já existe, tente outro");
            } catch (Exception e) {
                AlertUtil.showErrorAlert(null, "Ocorreu um erro inesperado");
            }
        }
    }

    public void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) view.getCancelButton().getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }
}
