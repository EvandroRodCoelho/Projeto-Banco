package controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Usuario;
import model.database.Conn;
import view.admin.DetailsPage;
import view.admin.ListUsersPage;
import view.utils.ButtonComponent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListUsersController {
    private ListUsersPage view;
    
    public ListUsersController(ListUsersPage view) {
        this.view = view;
    }

    public ObservableList<Usuario> getUsersFromDatabase() {
        ObservableList<Usuario> users = FXCollections.observableArrayList();

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM usuario";
            ResultSet rs = conn.getStatement().executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int acesso = rs.getInt("acesso");

                Usuario user = new Usuario(id, nome, email, "", acesso);
                users.add(user);
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void filterUsers( ) {
        ObservableList<Usuario> allUsers = getUsersFromDatabase();
        ObservableList<Usuario> filteredUsers = FXCollections.observableArrayList();

        for (Usuario user : allUsers) {
            if (filterUser(user)) {
                filteredUsers.add(user);
            }
        }

        view.getTable().setItems(filteredUsers);
    }

    private boolean filterUser(Usuario user) {
        String nomeFilter = view.getNameFilterInput().getText().toLowerCase();
        String emailFilter = view.getEmailFilterInput().getText().toLowerCase();
        String idFilter = view.getIdFilterInput().getText().toLowerCase();
        String acessoFilter = view.getAccessFilterComboBox().getValue();

        String nome = user.getNome().toLowerCase();
        String email = user.getEmail().toLowerCase();
        String id = String.valueOf(user.getId()).toLowerCase();
        String acesso = String.valueOf(user.getAcesso());

        if (acessoFilter != null) {
            acessoFilter = acessoFilter.toLowerCase();
            acesso = acesso.toLowerCase();
        }

        return nome.contains(nomeFilter) &&
                email.contains(emailFilter) &&
                id.contains(idFilter) &&
                (acessoFilter == null || acesso.equals(acessoFilter))
;
    }

    public void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage)  ((ButtonComponent) event.getSource()).getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }
}
