package view.admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Usuario;
import model.database.Conn;
import view.utils.ButtonComponent;

public class ListUsersPage extends Application {

    private TableView<Usuario> table;
    private TextField nameFilterInput;
    private TextField emailFilterInput;
    private TextField idFilterInput;
    private ComboBox<String> accessFilterComboBox;
    private ButtonComponent cancelButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lista de Usuários");

        TableColumn<Usuario, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Usuario, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Usuario, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Usuario, String> acessoColumn = new TableColumn<>("Nível de Acesso");
        acessoColumn.setCellValueFactory(new PropertyValueFactory<>("acesso"));

        table = new TableView<>();
        table.getColumns().addAll(idColumn, nomeColumn, emailColumn, acessoColumn);

        nameFilterInput = new TextField();
        nameFilterInput.setPromptText("Nome");
        nameFilterInput.setOnKeyReleased(e -> filterUsers());

        emailFilterInput = new TextField();
        emailFilterInput.setPromptText("Email");
        emailFilterInput.setOnKeyReleased(e -> filterUsers());

        idFilterInput = new TextField();
        idFilterInput.setPromptText("ID");
        idFilterInput.setOnKeyReleased(e -> filterUsers());

        accessFilterComboBox = new ComboBox<>();
        accessFilterComboBox.getItems().addAll("1", "2");
        accessFilterComboBox.setPromptText("Nível de Acesso");
        accessFilterComboBox.setOnAction(e -> filterUsers());

        HBox filterBox = new HBox(10);
        filterBox.getChildren().addAll(nameFilterInput, emailFilterInput, idFilterInput, accessFilterComboBox);
        filterBox.setPadding(new Insets(10));

        cancelButton = createButton("Sair", "#D32F2F", "white");
        cancelButton.setOnAction(e -> handleCancelButton(e));

        HBox buttonBox = new HBox(cancelButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane root = new BorderPane();
        root.setTop(filterBox);
        root.setCenter(table);
        root.setBottom(buttonBox);

        ObservableList<Usuario> users = getUsersFromDatabase();
        table.setItems(users);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<Usuario> getUsersFromDatabase() {
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

    private void filterUsers() {
        ObservableList<Usuario> allUsers = getUsersFromDatabase();
        ObservableList<Usuario> filteredUsers = FXCollections.observableArrayList();

        for (Usuario user : allUsers) {
            if (filterUser(user)) {
                filteredUsers.add(user);
            }
        }

        table.setItems(filteredUsers);
    }

    private boolean filterUser(Usuario user) {
        String nomeFilter = nameFilterInput.getText().toLowerCase();
        String emailFilter = emailFilterInput.getText().toLowerCase();
        String idFilter = idFilterInput.getText().toLowerCase();
        String acessoFilter = accessFilterComboBox.getValue();

        String nome = user.getNome().toLowerCase();
        String email = user.getEmail().toLowerCase();
        String id = String.valueOf(user.getId()).toLowerCase();
        String acesso = String.valueOf(user.getAcesso()).toLowerCase();

        if (acessoFilter != null) {
            acessoFilter = acessoFilter.toLowerCase();
        }

        return nome.contains(nomeFilter) &&
                email.contains(emailFilter) &&
                id.contains(idFilter) &&
                (acessoFilter == null || acesso.contains(acessoFilter));
    }

    private void handleCancelButton(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
