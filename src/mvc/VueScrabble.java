package mvc;

import javafx.scene.layout.BorderPane;

public class VueScrabble extends BorderPane {

    Modele modl;

    public VueScrabble(Modele modl, Controleur ctrl) {
        this.modl = modl;
        VuePlateau plateau = new VuePlateau(modl, ctrl);
        VueMain mainJ1 = new VueMain(ctrl);
        this.modl.addObserver(plateau);
        this.setCenter(plateau);
        this.setBottom(mainJ1);
    }
}