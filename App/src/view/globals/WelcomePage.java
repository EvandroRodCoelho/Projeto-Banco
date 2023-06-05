package view.globals;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.utils.ButtonComponent;


public class WelcomePage extends Application {
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Pagina inicial");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white");

        Text title = createBlinkingText("Banco RTX");
        title.setFont(Font.font("Serif", 63));

        Button continueButton = createButton("Clique para seguir", "#1E488F", "white");
        continueButton.setOnAction(e -> openLoginPage());

        root.getChildren().addAll(title, continueButton);

        Scene scene = new Scene(root, 1260, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Text createBlinkingText(String text) {
        Text blinkingText = new Text(text);
        blinkingText.setFill(Color.BLACK);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), blinkingText);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(Animation.INDEFINITE);
        fadeIn.setAutoReverse(true);
        fadeIn.play();

        return blinkingText;
    }

    private Button createButton(String text, String backgroundColor, String textColor) {
        Button button = new ButtonComponent(text, backgroundColor, textColor);
        return button;
    }

    private void openLoginPage() {
        stage.close();
        LoginPage loginPage = new LoginPage();
        loginPage.start(new Stage());
    }
}
