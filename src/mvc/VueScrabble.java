package mvc;

import javafx.scene.layout.BorderPane;

public class VueScrabble extends BorderPane {

    Modele modl;

    public VueScrabble(Modele modl, Controleur ctrl) {
        //VueHello v1 = new VueHello(ctrl);
        //VuePlateau v2 = new VuePlateau(ctrl);
        //this.setTop(v1);
        //this.setCenter(v2);
        this.modl = modl;
        this.setCenter(modl.grilleJeu);
        VueMain mainJ1 = new VueMain(ctrl);
        this.setBottom(mainJ1);
    }
}