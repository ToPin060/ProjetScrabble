package mvc;

import annexe.*;
import java.util.ArrayList;
import java.util.Observable;

public class Modele extends Observable {

    public Plateau plateau;
    public Pioche pioche;
    public ArrayList<VuePiece> motCourant, mainJ1, mainJ2;

    public enum Joueur {
        J1, J2
    };

    public Joueur joueur;
    public int tour;

    public Modele() {
        this.plateau = new Plateau();
        this.pioche = new Pioche();
        this.motCourant = new ArrayList<VuePiece>();
        this.mainJ1 = new ArrayList<VuePiece>();
        this.mainJ2 = new ArrayList<VuePiece>();
        this.joueur = Joueur.J1;
        this.tour = 0;
    }

    public void deplacerPiece(VuePiece p) {
        // Observer: VuePlateau
        this.setChanged();
        this.notifyObservers(p);
    }

    public void tourSuivant() {
        // Observer: VueMain
        this.setChanged();
        this.notifyObservers(this.joueur);
        this.tour++;
        this.motCourant.clear();
    }
}