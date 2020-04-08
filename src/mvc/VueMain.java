package mvc;

import annexe.Piece;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class VueMain extends HBox {

    public VueMain(Controleur ctrl) {
        for (int i=0; i<10; i++) {
            Piece p = new Piece();
            this.getChildren().add(p);
            p.setOnMouseDragged(event -> ctrl.drag(event));
            p.setOnMouseReleased(event -> ctrl.ajoutCase(event));
        }

        this.setAlignment(Pos.BASELINE_CENTER);
        this.setSpacing(10);
    }
}