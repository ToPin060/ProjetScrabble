package mvc;

import javafx.scene.layout.BorderPane;

public class VueScrabble extends BorderPane {

    public VueScrabble(Controleur ctrl) {
        VueHello v1 = new VueHello(ctrl);
        VuePlateau v2 = new VuePlateau(ctrl);
        this.setTop(v1);
        this.setCenter(v2);
    }
}