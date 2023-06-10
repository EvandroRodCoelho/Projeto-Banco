package view.admin.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Conta;
import view.admin.conn;
import view.utils.ButtonComponent;

public class ListAccountsPage extends Application {

    private TableView<Conta> table;

    private TextField idFilterInput;
    private TextField numContaFilterInput;
    private TextField titularFilterInput;
    private TextField saldoFilterInput;
    private TextField tipoContaFilterInput;
    private TextField usuarioIdFilterInput;

    private ButtonComponent cancelButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lista de Contas");

        TableColumn<Conta, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Conta, String> numcontaColumn = new TableColumn<>("Número da Conta");
        numcontaColumn.setCellValueFactory(new PropertyValueFactory<>("numConta"));

        TableColumn<Conta, String> titularColumn = new TableColumn<>("Titular");
        titularColumn.setCellValueFactory(new PropertyValueFactory<>("titular"));

        TableColumn<Conta, Double> saldoColumn = new TableColumn<>("Saldo");
        saldoColumn.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        TableColumn<Conta, Integer> tipoContaColumn = new TableColumn<>("Tipo de Conta");
        tipoContaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoConta"));

        TableColumn<Conta, Integer> usuarioIdColumn = new TableColumn<>("Usuário ID");
        usuarioIdColumn.setCellValueFactory(new PropertyValueFactory<>("usuarioId"));

        table = new TableView<>();
        table.getColumns().addAll(idColumn, numcontaColumn, titularColumn, saldoColumn, tipoContaColumn, usuarioIdColumn);

        idFilterInput = new TextField();
        idFilterInput.setPromptText("ID");
        idFilterInput.setOnKeyReleased(e -> filterAccounts());

        numContaFilterInput = new TextField();
        numContaFilterInput.setPromptText("Número de Conta");
        numContaFilterInput.setOnKeyReleased(e -> filterAccounts());

        titularFilterInput = new TextField();
        titularFilterInput.setPromptText("Titular");
        titularFilterInput.setOnKeyReleased(e -> filterAccounts());

        saldoFilterInput = new TextField();
        saldoFilterInput.setPromptText("Saldo");
        saldoFilterInput.setOnKeyReleased(e -> filterAccounts());

        tipoContaFilterInput = new TextField();
        tipoContaFilterInput.setPromptText("Tipo de Conta");
        tipoContaFilterInput.setOnKeyReleased(e -> filterAccounts());

        usuarioIdFilterInput = new TextField();
        usuarioIdFilterInput.setPromptText("Usuário ID");
        usuarioIdFilterInput.setOnKeyReleased(e -> filterAccounts());

        HBox filterBox = new HBox(10);
        filterBox.getChildren().addAll(
            idFilterInput, 
            numContaFilterInput, 
            titularFilterInput, 
            saldoFilterInput, 
            tipoContaFilterInput, 
            usuarioIdFilterInput
        );
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

        ObservableList<Conta> contas = getAccountsFromDatabase();
        table.setItems(contas);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<Conta> getAccountsFromDatabase() {
        ObservableList<Conta> contas = FXCollections.observableArrayList();

        try {
            conn c1 = new conn();
            String query = "SELECT * FROM contas";
            ResultSet rs = c1.st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");  
                int userId = rs.getInt("usuarioId");   
                int typeAccount = rs.getInt("tipoConta");                 
                double withdraw = rs.getDouble("saldo");
                String accountHolder = rs.getString("titular");
                String accountNumber = rs.getString("numConta");

                Conta conta = new Conta(accountNumber, accountHolder, withdraw, typeAccount, userId, id);
                contas.add(conta);
            }

            rs.close();
            c1.st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contas;
    }

    private void filterAccounts() {
        ObservableList<Conta> allAccounts = getAccountsFromDatabase();
        ObservableList<Conta> filteredAccounts = FXCollections.observableArrayList();

        for (Conta conta : allAccounts) {
            if (filterAccount(conta)) {
                filteredAccounts.add(conta);
            }
        }

        table.setItems(filteredAccounts);
    }

    private boolean filterAccount(Conta conta) {
        String idFilter = idFilterInput.getText();
        String numContaField = numContaFilterInput.getText();
        String titularField = titularFilterInput.getText().toLowerCase();
        String saldoField = saldoFilterInput.getText();
        String tipoContaField = tipoContaFilterInput.getText();
        String usuarioIdField = usuarioIdFilterInput.getText();
    
        String id = String.valueOf(conta.getId());
        String numConta = String.valueOf(conta.getNumConta());
        String titular = String.valueOf(conta.getTitular()).toLowerCase();
        String saldo = String.valueOf(conta.getSaldo());
        String tipoConta = String.valueOf(conta.getTipoConta());  
        String usuarioId = String.valueOf(conta.getUsuarioId());
    
        return id.contains(idFilter) &&
            numConta.contains(numContaField) &&
            titular.contains(titularField) &&
            saldo.contains(saldoField) &&
            tipoConta.contains(tipoContaField) &&
            usuarioId.contains(usuarioIdField);
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