package mvc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Controleur implements EventHandler<ActionEvent> {

    Modele modl;

    public Controleur(Modele modl) {
        this.modl = modl;
    }

    public void drag(MouseEvent event) {
        VuePiece p = (VuePiece) event.getSource();

        if (p.jouable) {
            p.setTranslateX(event.getX() + p.getTranslateX() - 25);
            p.setTranslateY(event.getY() + p.getTranslateY() - 25);
            event.consume();
        }
    }

    public void dragFin(MouseEvent event) {
        VuePiece p = (VuePiece) event.getSource();
        p.setTranslateX(0);
        p.setTranslateY(0);

        if (p.jouable) {
            int col = (int) ((event.getSceneX() + 50) / 50) - 1;
            int lig = (int) ((event.getSceneY() + 50) / 50) - 1;
            p.col = col;
            p.lig = lig;

            if (col > 14 || col < 0 || lig > 14 || lig < 0) {
                this.modl.retourDansMain(p);
            }

            else {
                this.modl.ajouterPiece(p);
            }
        }
    }

    @Override
    public void handle(ActionEvent event) {
        this.modl.remplirMain(this);
    }
}