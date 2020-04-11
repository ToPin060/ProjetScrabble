package mvc;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.layout.BorderPane;

public class VueScrabble extends BorderPane implements Observer {

    Modele modl;
    VuePlateau plateau;
    VueMain mainJ1;
    VueInfo info;

    public VueScrabble(Modele modl, Controleur ctrl) {
        this.modl = modl;
        this.plateau = new VuePlateau(modl, ctrl);
        this.mainJ1 = new VueMain(ctrl);
        this.info = new VueInfo(ctrl);
        this.modl.addObserver(this);
        this.modl.addObserver(plateau);
        this.modl.addObserver(mainJ1);
        this.setCenter(plateau);
        this.setBottom(mainJ1);
        this.setRight(info);
    }

    @Override
    public void update(Observable o, Object arg) {
        // Retourne la pièce dans la main
        if (arg instanceof VuePiece) {
            VuePiece p = (VuePiece) arg;

            if ((p.col > 14 || p.col < 0 || p.lig > 14 || p.lig < 0) && plateau.getChildren().contains(p)) {
                plateau.getChildren().remove(p);
                mainJ1.getChildren().add(p);
                this.modl.motCourant.remove(p);
            }
        }
    }
}