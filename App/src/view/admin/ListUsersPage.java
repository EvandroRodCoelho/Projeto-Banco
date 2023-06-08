package view.admin;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Usuario;
import view.utils.ButtonComponent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListUsersPage extends Application {

    private TableView<Usuario> table;
    private TextField nomeFilterInput;
    private TextField emailFilterInput;
    private TextField idFilterInput;
    private ComboBox<String> acessoFilterComboBox;
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

        nomeFilterInput = createTextField("Nome");
        emailFilterInput = createTextField("Email");
        idFilterInput = createTextField("ID");
        acessoFilterComboBox = createComboBox();
        acessoFilterComboBox.setOnAction(this::handleComboBoxAction);

        HBox filterBox = new HBox(10);
        filterBox.getChildren().addAll(nomeFilterInput, emailFilterInput, idFilterInput, acessoFilterComboBox);
        filterBox.setPadding(new Insets(10));

        cancelButton = createButton("Sair", "#D32F2F", "white");
        cancelButton.setOnAction(this::handleCancelButtonAction);

        HBox buttonBox = new HBox(cancelButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane root = new BorderPane();
        root.setTop(filterBox);
        root.setCenter(table);
        root.setBottom(buttonBox);

        ObservableList<Usuario> users = getUsersFromDatabase();
        table.setItems(users);

        addFocusManagement();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<Usuario> getUsersFromDatabase() {
        ObservableList<Usuario> users = FXCollections.observableArrayList();

        try {
            conn c1 = new conn();
            String query = "SELECT * FROM usuario";
            ResultSet rs = c1.st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int acesso = rs.getInt("acesso");

                Usuario user = new Usuario(id, nome, email, "", acesso);
                users.add(user);
            }

            rs.close();
            c1.st.close();
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
        String nomeFilter = nomeFilterInput.getText().toLowerCase();
        String emailFilter = emailFilterInput.getText().toLowerCase();
        String idFilter = idFilterInput.getText().toLowerCase();
        String acessoFilter = acessoFilterComboBox.getValue().toLowerCase();

        String nome = user.getNome().toLowerCase();
        String email = user.getEmail().toLowerCase();
        String id = String.valueOf(user.getId()).toLowerCase();
        String acesso = String.valueOf(user.getAcesso()).toLowerCase();

        return nome.contains(nomeFilter) &&
                email.contains(emailFilter) &&
                id.contains(idFilter) &&
                acesso.contains(acessoFilter);
    }

    private void handleComboBoxAction(ActionEvent event) {
        filterUsers();
    }

    private void handleCancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        new DetailsPage().start(new Stage());
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    private TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }

    private ComboBox<String> createComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("1", "2");
        comboBox.setPromptText("Nível de Acesso");
        return comboBox;
    }

    private void addFocusManagement() {
        nomeFilterInput.addEventHandler(KeyEvent.KEY_PRESSED, this::handleTextFieldKeyPress);
        emailFilterInput.addEventHandler(KeyEvent.KEY_PRESSED, this::handleTextFieldKeyPress);
        idFilterInput.addEventHandler(KeyEvent.KEY_PRESSED, this::handleTextFieldKeyPress);
        acessoFilterComboBox.addEventHandler(KeyEvent.KEY_PRESSED, this::handleComboBoxKeyPress);
    }

    private void handleTextFieldKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            if (event.getSource() == nomeFilterInput) {
                emailFilterInput.requestFocus();
            } else if (event.getSource() == emailFilterInput) {
                idFilterInput.requestFocus();
            } else if (event.getSource() == idFilterInput) {
                acessoFilterComboBox.requestFocus();
            }
        }
    }

    private void handleComboBoxKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            nomeFilterInput.requestFocus();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
