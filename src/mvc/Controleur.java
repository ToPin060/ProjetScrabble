package mvc;

import annexe.Piece;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Controleur implements EventHandler {

    private Modele modl;

    public Controleur(Modele modl) {
        this.modl = modl;
    }

    @Override
    public void handle(Event event) {
        Object o = event.getSource();
        System.out.println("Hello");
    }

    public void drag(MouseEvent event) {
        Piece p = (Piece) event.getSource();

        if (p.jouable) {
            p.setTranslateX(event.getX() + p.getTranslateX()-25);
            p.setTranslateY(event.getY() + p.getTranslateY()-25);
            event.consume();
        }
	}

    public void ajoutCase(MouseEvent event) {
        Piece p = (Piece) event.getSource();

        if (p.jouable) {
            int x = (int) ((event.getSceneX()+50)/50)-1;
            int y = (int) ((event.getSceneY()+50)/50)-1;

            if (x>14 || x<0 || y>14 || y<0) {
                System.out.println("Erreur");
            }

            else {
                p.setTranslateX(0);
                p.setTranslateY(0);
                this.modl.grilleJeu.add(p,x,y);
                p.jouable = false;
            }
        }
    }
}