package scrabble;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class VueHello extends StackPane {

    private Button btn = new Button("Hello");

    public VueHello(Controleur ctrl) {
        btn.setOnAction(ctrl);
        this.getChildren().add(btn);
    }
}