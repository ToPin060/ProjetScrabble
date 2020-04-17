package mvc;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VueMain implements Observer {

    private Modele modl;
    private Controleur ctrl;
    private HBox main;

    public VueMain(Modele modl, Controleur ctrl) {
        this.modl = modl;
        this.ctrl = ctrl;
        this.main = ctrl.main;

        for (int i = 0; i < 5; i++) {
            this.creerPiece();
        }
    }

    public void creerPiece() {
        char c = this.modl.pioche.piocher();
        Text l = new Text("");
        if (c != '[') {
            l.setText("" + c);
        }
        Text s = new Text("" + this.modl.pioche.get(c)[0]);
        VuePiece p = new VuePiece(l, s);
        this.main.getChildren().add(p);
        p.setOnMouseDragged(event -> this.ctrl.drag(event));
        p.setOnMouseReleased(event -> this.ctrl.dragFin(event));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            while (this.main.getChildren().size() < 7 && this.modl.pioche.n_pieces > 0) {
                this.creerPiece();
            }
        }
    }
}