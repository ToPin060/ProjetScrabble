package mvc;

import javafx.scene.input.MouseEvent;

public class Controleur {

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

            if (col > 14 || col < 0 || lig > 14 || lig < 0) {
                System.out.println("Déplacement impossible");
            }

            else {
                p.col = col;
                p.lig = lig;
                this.modl.ajoutPiece(p);
                // p.jouable = false;
            }
        }
    }
}