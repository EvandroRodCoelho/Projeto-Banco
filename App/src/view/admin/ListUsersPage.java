package view.admin;

import controller.admin.ListUsersController;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import view.utils.ButtonComponent;

public class ListUsersPage extends Application {

    private TableView<Usuario> table;
    private TextField nameFilterInput;
    private TextField emailFilterInput;
    private TextField idFilterInput;
    private ComboBox<String> accessFilterComboBox;
    private ButtonComponent cancelButton;
    private ListUsersController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new ListUsersController(this);
        primaryStage.setTitle("Lista de Usuários");

        TableColumn<Usuario, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Usuario, String> nameColumn = new TableColumn<>("Nome");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Usuario, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Usuario, String> accessLevelColumn = new TableColumn<>("Nível de Acesso");
        accessLevelColumn.setCellValueFactory(new PropertyValueFactory<>("acesso"));

        table = new TableView<>();
        table.getColumns().addAll(idColumn, nameColumn, emailColumn, accessLevelColumn);

        nameFilterInput = new TextField();
        nameFilterInput.setPromptText("Nome");
        nameFilterInput.setOnKeyReleased(e -> controller.filterUsers());

        emailFilterInput = new TextField();
        emailFilterInput.setPromptText("Email");
        emailFilterInput.setOnKeyReleased(e -> controller.filterUsers());

        idFilterInput = new TextField();
        idFilterInput.setPromptText("ID");
        idFilterInput.setOnKeyReleased(e -> controller.filterUsers());

        accessFilterComboBox = new ComboBox<>();
        accessFilterComboBox.getItems().addAll("1", "2");
        accessFilterComboBox.setPromptText("Nível de Acesso");
        accessFilterComboBox.setOnAction(e -> controller.filterUsers());

        HBox filterBox = new HBox(10);
        filterBox.getChildren().addAll(nameFilterInput, emailFilterInput, idFilterInput, accessFilterComboBox);
        filterBox.setPadding(new Insets(10));

        cancelButton = createButton("Sair", "#D32F2F", "white");
        cancelButton.setOnAction(e -> controller.handleCancelButton(e));

        HBox buttonBox = new HBox(cancelButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane root = new BorderPane();
        root.setTop(filterBox);
        root.setCenter(table);
        root.setBottom(buttonBox);

        ObservableList<Usuario> users = controller.getUsersFromDatabase();
        table.setItems(users);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public TableView<Usuario> getTable() {
        return table;
    }

    public TextField getNameFilterInput() {
        return nameFilterInput;
    }

    public TextField getEmailFilterInput() {
        return emailFilterInput;
    }

    public TextField getIdFilterInput() {
        return idFilterInput;
    }

    public ComboBox<String> getAccessFilterComboBox() {
        return accessFilterComboBox;
    }

    public ButtonComponent getCancelButton() {
        return cancelButton;
    }
}
