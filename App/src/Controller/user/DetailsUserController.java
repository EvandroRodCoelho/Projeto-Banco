package Controller.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DetailsUserController {
    @FXML
    private Button exitButton;
    @FXML
    private void handleExit() {
        // Perform actions when the "Exit" button is clicked
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void handleBalanceCheck(ActionEvent event) {
        System.out.println("Saiu");
    }

    @FXML
    void handleDeposit(ActionEvent event) {
        System.out.println("Saiu");
    }

    @FXML
    void handleTransfer(ActionEvent event) {
        System.out.println("Saiu");
    }

    @FXML
    void handleWithdraw(ActionEvent event) {
        System.out.println("Saiu");
    }

}
