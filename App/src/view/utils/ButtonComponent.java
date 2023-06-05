package view.utils;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ButtonComponent extends Button {
    private static final double OPACITY_NORMAL = 1.0;
    private static final double OPACITY_FOCUS = 0.8;

    public ButtonComponent(String text, String backgroundColor, String textColor) {
        setText(text);
        setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: " + textColor + ";");
        setPrefWidth(120);

        setOnMouseEntered(e -> {
            if (!isFocused()) {
                setOpacity(OPACITY_FOCUS);
            }
        });
        setOnMouseExited(e -> {
            if (!isFocused()) {
                setOpacity(OPACITY_NORMAL);
            }
        });

        focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                setOpacity(OPACITY_FOCUS);
            } else {
                setOpacity(OPACITY_NORMAL);
            }
        });

        addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB && isFocused()) {
                setOpacity(OPACITY_NORMAL);
            }
        });

        addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.TAB && isFocused() && isHover()) {
                setOpacity(OPACITY_FOCUS);
            }
        });
    }
}
