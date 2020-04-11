package mvc;

import java.util.Observable;
import java.util.Stack;
import annexe.Plateau;

public class Modele extends Observable {

    public Plateau plateau = new Plateau();
    public Stack<VuePiece> motCourant = new Stack();

    public Modele() {
    }

    public void ajouterPiece(VuePiece p) {
        // Observer: VuePlateau
        this.setChanged();
        this.notifyObservers(p);
    }

    public void retourDansMain(VuePiece p) {
        // Observer: VueScrabble
        this.setChanged();
        this.notifyObservers(p);
    }

    public void remplirMain(Controleur ctrl) {
        // Observer: VueMain
        this.setChanged();
        this.notifyObservers(ctrl);

        while (!motCourant.empty()) {
            motCourant.peek().jouable = false;
            motCourant.pop();
        }
    }
}