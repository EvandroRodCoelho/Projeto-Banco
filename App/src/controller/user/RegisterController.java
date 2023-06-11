package controller.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Random;

import controller.utils.validador.ValidadorAccountUser;
import controller.utils.validador.ValidadorAccountUser.ValidationException;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.database.Conn;
import view.globals.LoginPage;
import view.user.RegisterPage;
import view.utils.AlertUtil;

public class RegisterController {
    RegisterPage view;

    public RegisterController(RegisterPage view) {
        this.view = view;
    }
    public void insertAddUser() {
        setInputsEnabled(false);
        String name =view.getNameTextField().getText();
        String email =view.getEmailTextField().getText();
        String password =view.getPasswordTextField().getText();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            Conn c1 = null;
            Savepoint savepoint = null;

            try {
                ValidadorAccountUser.validateName(name);
                ValidadorAccountUser.validateEmail(email);
                ValidadorAccountUser.validatePassword(password);
                c1 = new Conn();

                c1.getConnection().setAutoCommit(false);

                String queryCreateUser = "INSERT INTO usuario (nome, email, senha, acesso) VALUES ('"
                    + name + "', '" + email + "', '" + password + "', '" + 1 + "')";

                c1.getStatement().executeUpdate(queryCreateUser);

                String querySearchUser = "SELECT * FROM usuario WHERE email = '" + email + "'";

                ResultSet rs = c1.getStatement().executeQuery(querySearchUser);

                if (rs.next()) {
                    
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
                        AlertUtil.showSuccessAlert(view.getStage(), "Adicionado com sucesso.\n Num Da conta: "+ numconta);
                        handleLoginLink(null);
                    }
                }
                else {
                    rs.close();
                }
            } 
            catch (SQLException e) {                
                AlertUtil.showErrorAlert(view.getStage(), "Ocorreu um erro");
                            try {
                    if (savepoint != null) {
                        c1.getConnection().rollback(savepoint);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (ValidationException e) {
                AlertUtil.showErrorAlert(view.getStage(), e.getMessage());
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
                } catch (Exception se) {
                    se.printStackTrace();
                }
                setInputsEnabled(true);
            } 
        }
        else {
            AlertUtil.showErrorAlert(null, "Os campos estão vazios!");
            setInputsEnabled(true);
        }
    }
    
    public void handleLoginLink(ActionEvent event) {
        Stage stage = (Stage) view.getEmailTextField().getScene().getWindow();
        stage.close();
        new LoginPage().start(new Stage());
    }

    private void setInputsEnabled(boolean enabled) {
        view.getNameTextField().setDisable(!enabled);
        view.getEmailTextField().setDisable(!enabled);
        view.getPasswordTextField().setDisable(!enabled);
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
}
