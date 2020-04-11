package mvc;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class VueInfo extends Pane {

    public VueInfo(Controleur ctrl) {
        Button btn = new Button("Fin du tour");
        btn.setOnAction(ctrl);
        this.getChildren().add(btn);
    }
}