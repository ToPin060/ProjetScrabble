package mvc;

import java.util.Observable;
import java.util.Observer;
import annexe.Pioche;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VueMain extends HBox implements Observer {

    Pioche pioche;

    public VueMain(Controleur ctrl) {
        this.pioche = new Pioche();

        for (int i = 0; i < 7; i++) {
            this.creerPiece(ctrl);
        }

        this.setAlignment(Pos.BASELINE_CENTER);
        this.setSpacing(10);
    }

    public void creerPiece(Controleur ctrl) {
        char c = pioche.piocher();
        Text l = new Text("" + c);
        Text s = new Text("" + pioche.get(c)[0]);
        VuePiece p = new VuePiece(l, s);
        this.getChildren().add(p);
        p.setOnMouseDragged(event -> ctrl.drag(event));
        p.setOnMouseReleased(event -> ctrl.dragFin(event));
    }

    @Override
    public void update(Observable o, Object arg) {
        // Rempli la main à la fin du tour
        if (arg instanceof Controleur) {
            Controleur ctrl = (Controleur) arg;

            while (this.getChildren().size() < 7 && pioche.n_pieces > 0) {
                this.creerPiece(ctrl);
            }
        }
    }
}