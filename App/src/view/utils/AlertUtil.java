package view.utils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AlertUtil {
    public static void showErrorAlert(Stage ownerStage, String message) {
        Text text = new Text(message);
        text.setFill(Color.RED);
        text.setFont(Font.font("Arial", 14));

        VBox vbox = new VBox(text);
        vbox.setAlignment(Pos.CENTER);

        Stage alertStage = new Stage();
        alertStage.initOwner(ownerStage);
        alertStage.setTitle("Alert");
        alertStage.setScene(new Scene(vbox, 600, 100));
        alertStage.showAndWait();
    }
    public static void showSuccessAlert(Stage ownerStage, String message) {
        Text text = new Text(message);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Arial", 14));

        VBox vbox = new VBox(text);
        vbox.setAlignment(Pos.CENTER);

        Stage alertStage = new Stage();
        alertStage.initOwner(ownerStage);
        alertStage.setTitle("Alert");
        alertStage.setScene(new Scene(vbox, 200, 100));
        alertStage.showAndWait();
    }
}
