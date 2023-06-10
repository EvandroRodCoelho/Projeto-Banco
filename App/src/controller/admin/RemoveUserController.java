package controller.admin;

import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.database.Conn;
import view.admin.DetailsPage;
import view.admin.RemoveUserPage;
import view.utils.AlertUtil;


public class RemoveUserController {
    private RemoveUserPage view;

    public RemoveUserController(RemoveUserPage view) {
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

                    // Update UI labels with retrieved information
                    view.getNameLabel().setText("Nome: " + nome);
                    view.getEmailLabel().setText("Email: " + email);
                    view.getPasswordLabel().setText("Senha: " + senha);
                    view.getAcessoLabel().setText("Acesso: " + acesso);
                } else {
                    AlertUtil.showErrorAlert(null, "Usuário não encontrado");
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertUtil.showErrorAlert(null, "Campo vazio");
        }
    }

    public void handleRemoveButton(ActionEvent event) {
        String userId = view.getIdTextField().getText();
        if (!userId.isEmpty()) {
            try {
                Conn conn = new Conn();
                String query = "DELETE FROM usuario WHERE id = '" + userId + "'";
                int rowsAffected = conn.getStatement().executeUpdate(query);
                if (rowsAffected > 0) {
                    AlertUtil.showSuccessAlert(null, "Usuário removido com sucesso!");

                    view.getNameLabel().setText("Nome:");
                    view.getEmailLabel().setText("Email:");
                    view.getPasswordLabel().setText("Senha:");
                    view.getAcessoLabel().setText("Acesso:");
                    view.getIdTextField().setText("");
                } else {
                    AlertUtil.showErrorAlert(null, "Não foi possível encontrar o usuário.");
                }
                conn.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) view.getCancelButton().getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }
}
