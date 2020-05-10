package mvc;

import annexe.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Modele extends Observable {

    public Plateau plateau;
    public Pioche pioche;
    public Score score;
    public ArrayList<VuePiece> motCourant, mainJ1, mainJ2;
    int[][] lettresP;


    public enum Joueur {
        J1, J2
    };

    public Joueur joueur;
    public boolean multijoueur;
    public int tour;
    public int passer;
    public String erreur;

    public Modele() {
        this.plateau = new Plateau();
        this.pioche = new Pioche();
        this.score = new Score(this.plateau, this.pioche);
        this.motCourant = new ArrayList<VuePiece>();
        this.mainJ1 = new ArrayList<VuePiece>();
        this.mainJ2 = new ArrayList<VuePiece>();
        this.joueur = Joueur.J1;
        this.tour = 0;
        this.passer = 0;
    }

    public void deplacerPiece(VuePiece p) {
        // Observer: VuePlateau
        this.setChanged();
        this.notifyObservers(p);
    }

    public void tourSuivant() {
        // Observer: VueMain
        this.setChanged();

        if (multijoueur) {
            this.notifyObservers(this.joueur);

            switch (this.joueur) {
                case J1:
                    this.joueur = Joueur.J2;
                    break;

                case J2:
                    this.joueur = Joueur.J1;
                    break;
            }
        }

        else {
            this.notifyObservers(null);
        }
        
        this.tour++;
        this.motCourant.clear();
    }

    public boolean verificationTour() {
        // Vérifier la longueur du mot en fonction du tour
        if (this.tour == 0 && this.motCourant.size() < 2 || this.tour != 0 && this.motCourant.size() < 1) {
            this.erreur = "Le mot est trop petit.";
            return false;
        }

        // Parcours du mot posé et initialisation des coordonnées
        boolean lettreCentre = false;
        boolean motCol = true;
        boolean motLig = true;
        Integer[] coordCol = new Integer[this.motCourant.size()];
        Integer[] coordLig = new Integer[this.motCourant.size()];
        int[][] save = new int[this.motCourant.size()][3];

        for (int i = 0; i < this.motCourant.size(); i++) {
            int lettre = this.motCourant.get(i).lettre.getText().toLowerCase().charAt(0);
            coordCol[i] = this.motCourant.get(i).col;
            coordLig[i] = this.motCourant.get(i).lig;
            save[i][0] = this.motCourant.get(i).lig;
            save[i][1] = this.motCourant.get(i).col;
            save[i][2] = this.plateau.placer(this.motCourant.get(i).lig, this.motCourant.get(i).col, lettre);

            if (this.motCourant.get(0).col != this.motCourant.get(i).col) {
                motCol = false;
            }

            if (this.motCourant.get(0).lig != this.motCourant.get(i).lig) {
                motLig = false;
            }

            if (this.motCourant.get(i).col == 7 && this.motCourant.get(i).lig == 7) {
                lettreCentre = true;
            }
        }

        // Vérifier la lettre au centre au premier tour
        if (this.tour == 0 && !(lettreCentre)) {
            this.erreur = "Il n'y a pas de lettre au centre.";
            this.restaurerPlateau(save);
            return false;
        }

        // Vérifier si le mot est sur une seule colonne/ligne
        if (!(motCol || motLig)) {
            this.erreur = "Le mot n'est pas formé sur une seule colonne/ligne.";
            this.restaurerPlateau(save);
            return false;
        }

        // Vérifier si le mot n'a pas de trous et au moins un voisin
        boolean voisin = false;

        if (motCol) {
            Arrays.sort(coordLig);
            int lig = coordLig[0];
            int col = coordCol[0];

            while (lig <= coordLig[this.motCourant.size() - 1]) {
                if (this.plateau.get(lig, col) <= 0) {
                    this.erreur = "Le mot formé a un trou.";
                    this.restaurerPlateau(save);
                    return false;
                }

                if ((col > 0 && this.plateau.get(lig, col - 1) > 0)
                        || (col < 14 && this.plateau.get(lig, col + 1) > 0)) {
                    voisin = true;
                }

                if (lig == coordLig[0] && lig > 0 && this.plateau.get(lig - 1, col) > 0) {
                    voisin = true;
                }

                if (lig == coordLig[this.motCourant.size() - 1] && lig < 14 && this.plateau.get(lig + 1, col) > 0) {
                    voisin = true;
                }

                lig++;
            }
        }

        else {
            Arrays.sort(coordCol);
            int lig = coordLig[0];
            int col = coordCol[0];

            while (col <= coordCol[this.motCourant.size() - 1]) {
                if (this.plateau.get(lig, col) <= 0) {
                    this.erreur = "Le mot formé a un trou.";
                    this.restaurerPlateau(save);
                    return false;
                }

                if ((lig > 0 && this.plateau.get(lig - 1, col) > 0)
                        || (lig < 14 && this.plateau.get(lig + 1, col) > 0)) {
                    voisin = true;
                }

                if (col == coordCol[0] && col > 0 && this.plateau.get(lig, col - 1) > 0) {
                    voisin = true;
                }

                if (col == coordCol[this.motCourant.size() - 1] && col < 14 && this.plateau.get(lig, col + 1) > 0) {
                    voisin = true;
                }

                col++;
            }
        }

        if (this.tour != 0 && !voisin) {
            this.erreur = "Le mot n'est pas rattaché à une lettre du plateau.";
            this.restaurerPlateau(save);
            return false;
        }

        if (!this.plateau.verifierPlateau()) {
            this.erreur = "L'orthographe du mot est incorrecte.";
            this.restaurerPlateau(save);
            return false;
        }

        this.lettresP = save;
        return true;
    }

    public void restaurerPlateau(int[][] save) {
        for (int i = 0; i < save.length; i++) {
            this.plateau.placer(save[i][0], save[i][1], save[i][2]);
        }
    }

    public void mode(boolean multijoueur) {
        this.multijoueur = multijoueur;
    }

    public void reset() {
        this.plateau = new Plateau();
        this.pioche = new Pioche();
        this.score = new Score(this.plateau, this.pioche);
        this.motCourant = new ArrayList<VuePiece>();
        this.mainJ1 = new ArrayList<VuePiece>();
        this.mainJ2 = new ArrayList<VuePiece>();
        this.joueur = Joueur.J1;
        this.tour = 0;
        this.passer = 0;
    }
}