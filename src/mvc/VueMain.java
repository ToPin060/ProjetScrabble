package mvc;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VueMain extends HBox {

    public VueMain(Controleur ctrl) {
        for (int i = 0; i < 10; i++) {
            Text l = new Text("A");
            Text s = new Text("1");
            VuePiece p = new VuePiece(l, s);
            this.getChildren().add(p);
            p.setOnMouseDragged(event -> ctrl.drag(event));
            p.setOnMouseReleased(event -> ctrl.dragFin(event));
        }

        this.setAlignment(Pos.BASELINE_CENTER);
        this.setSpacing(10);
    }
}