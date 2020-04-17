package mvc;

import annexe.*;
import java.util.Observable;
import java.util.Stack;

public class Modele extends Observable {

    public Plateau plateau;
    public Pioche pioche;
    public Stack<VuePiece> motCourant;

    public Modele() {
        this.plateau = new Plateau();
        this.pioche = new Pioche();
        this.motCourant = new Stack();
    }

    public void deplacerPiece(VuePiece p) {
        // Observer: VuePlateau
        this.setChanged();
        this.notifyObservers(p);
    }

    public void remplirMain() {
        // Observer: VueMain
        this.setChanged();
        this.notifyObservers();

        while (!motCourant.empty()) {
            motCourant.peek().jouable = false;
            motCourant.pop();
        }
    }
}