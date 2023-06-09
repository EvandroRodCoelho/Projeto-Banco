package view.user;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.database.Conn;
import view.utils.ButtonComponent;
import view.globals.LoginPage;
import view.utils.AlertUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Random;

public class RegisterPage extends Application {
    private Stage stage;

    private TextField nomeTextField, emailTextField, senhaTextField;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Registro");

        GridPane gridPane = createGridPane();
        addUIControls(gridPane);

        Scene scene = new Scene(gridPane, 900, 700);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        Label titleLabel = new Label("Registro");
        titleLabel.setFont(new Font("serif", 25));
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label nomeLabel = new Label("Nome:");
        gridPane.add(nomeLabel, 0, 1);

        nomeTextField = new TextField();
        gridPane.add(nomeTextField, 1, 1);

        Label emailLabel = new Label("Email:");
        gridPane.add(emailLabel, 0, 2);

        emailTextField = new TextField();
        gridPane.add(emailTextField, 1, 2);

        Label senhaLabel = new Label("Senha:");
        gridPane.add(senhaLabel, 0, 3);

        senhaTextField = new TextField();
        gridPane.add(senhaTextField, 1, 3);

        Button submitButton = new ButtonComponent("Enviar", "#1E488F", "white");
        HBox submitHBox = new HBox(10);
        submitHBox.setAlignment(Pos.BOTTOM_RIGHT);
        submitHBox.getChildren().add(submitButton);
        gridPane.add(submitHBox, 0, 6, 2, 1);

        submitButton.setOnAction(e -> insertAddUser());

        Hyperlink loginLink = new Hyperlink("Voltar para a página de login");
        loginLink.setOnAction(this::handleLoginLink);
        loginLink.setStyle( "-fx-text-fill:#1E488F ;");
        gridPane.add(loginLink, 1, 7);
    }

    private void insertAddUser() {
        setInputsEnabled(false);
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String senha = senhaTextField.getText();

        if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
            Conn c1 = null;
            Savepoint savepoint = null;

            try {
                c1 = new Conn();

                c1.getConnection().setAutoCommit(false);

                String queryCreateUser = "INSERT INTO usuario (nome, email, senha, acesso) VALUES ('"
                    + nome + "', '" + email + "', '" + senha + "', '" + 1 + "')";

                c1.getStatement().executeUpdate(queryCreateUser);

                String querySearchUser = "SELECT * FROM usuario WHERE email = '" + email + "'";

                ResultSet rs = c1.getStatement().executeQuery(querySearchUser);

                if(rs.next()){
                    int numconta = Integer.parseInt(generateNumContaSequence(6));
                    
                    String queryCreateBankAcc = "INSERT INTO contas (numconta, titular, tipoconta, saldo, usuarioid) VALUES ('" +
                        numconta + "', '" + 
                        rs.getString("nome") + "', '" + 
                        1 + "', '" + 
                        100 + "', '" + 
                        rs.getString("id") + "')";

                     savepoint = c1.getConnection().setSavepoint();
                    
                    int rowsAffectedBankAcc = c1.getStatement().executeUpdate(queryCreateBankAcc);

                    c1.getConnection().commit();
                    rs.close();

                    if (rowsAffectedBankAcc > 0) {
                        AlertUtil.showSuccessAlert(stage, "Adicionado com sucesso");
                        handleLoginLink(null);
                    }
                }
                else {
                    rs.close();
                }
            } 
            catch (SQLException e) {
                // e.printStackTrace();
                
                AlertUtil.showErrorAlert(stage, "Ocorreu um erro");

                try {
                    System.out.println("rollback...");
                    c1.getConnection().rollback(savepoint);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            finally{
                try{
                    if(c1.getStatement() != null)
                    c1.close();
                } catch(Exception se2){
                    se2.printStackTrace();
                } 
                try{
                    if(c1.getConnection() != null)
                        c1.close();
                } catch(Exception se){
                    se.printStackTrace();
                }
            } 
        }
        else {
            AlertUtil.showErrorAlert(null, "Os campos estão vazios!");
            setInputsEnabled(true);
        }
    }
    
    private void handleLoginLink(ActionEvent event) {
        Stage stage = (Stage) emailTextField.getScene().getWindow();
        stage.close();
        new LoginPage().start(new Stage());
    }

    private void setInputsEnabled(boolean enabled) {
        nomeTextField.setDisable(!enabled);
        emailTextField.setDisable(!enabled);
        senhaTextField.setDisable(!enabled);
    }

    public String generateNumContaSequence(int len) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int digito = random.nextInt(10);
            builder.append(digito);
        }

        return builder.toString();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
