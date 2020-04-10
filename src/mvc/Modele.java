package mvc;

import java.util.Observable;
import annexe.Plateau;

public class Modele extends Observable {

    public Plateau plateau = new Plateau();

    public Modele() {
    }

    public void ajoutPiece(VuePiece p) {
        this.setChanged();
        this.notifyObservers(p);
    }
}