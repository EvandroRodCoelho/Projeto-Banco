package view.user;

import controller.user.BalanceCheckController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AppSession;
import view.utils.ButtonComponent;

public class BalanceCheckPage extends Application {
    private Stage stage;
    private GridPane gridPane;
    private Button cancelButton;
    private Label balanceLabel;
    private BalanceCheckController controller;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Verificação de Saldo");
        stage = primaryStage;
        controller = new BalanceCheckController(this);


        Text title = new Text("Verificação de Saldo");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        cancelButton = createButton("Cancelar", "#D32F2F", "white");
        cancelButton.setOnAction(e ->controller.handleCancelButton());

        balanceLabel = new Label("Seu saldo atual é: R$ " + AppSession.getContaUsuarioLogado().getSaldo());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(cancelButton);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, title);
        gridPane.addRow(1, balanceLabel);
        gridPane.addRow(2, buttonBox);

        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ButtonComponent createButton(String text, String backgroundColor, String textColor) {
        return new ButtonComponent(text, backgroundColor, textColor);
    }

    public Stage getStage() {
        return stage;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public Label getBalanceLabel() {
        return balanceLabel;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
