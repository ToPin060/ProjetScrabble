package mvc;

import java.util.Observable;
import java.util.Observer;
import annexe.Plateau;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VuePlateau extends GridPane implements Observer {

    Modele modl;

    public VuePlateau(Modele modl, Controleur ctrl) {
        this.modl = modl;
        Plateau plateau = this.modl.plateau;
        this.setId("plateau");

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Rectangle r = new Rectangle(50, 50);

                if (plateau.get(i, j) == -4) {
                    r.setFill(Color.rgb(196, 18, 44));
                }

                else if (plateau.get(i, j) == -3) {
                    r.setFill(Color.rgb(228, 182, 167));
                }

                else if (plateau.get(i, j) == -2) {
                    r.setFill(Color.rgb(38, 100, 177));
                }

                else if (plateau.get(i, j) == -1) {
                    r.setFill(Color.rgb(122, 161, 216));
                }

                else if (plateau.get(i, j) == 0) {
                    r.setFill(Color.rgb(38, 144, 144));
                }

                this.add(r, i, j);
            }
        }
    }

    public Node recupPiece(int col, int lig) {
        Node node = null;
        ObservableList<Node> l = this.getChildren();
        int cpt = l.size() - 1;

        while (cpt >= 0) {
            node = l.get(cpt);

            if ((node instanceof VuePiece) && this.getRowIndex(node) == lig && this.getColumnIndex(node) == col) {
                return node;
            }
            cpt--;
        }

        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        // Gère le placement de la pièce en cours
        if (arg instanceof VuePiece) {
            VuePiece p = (VuePiece) arg;

            if (!(p.col > 14 || p.col < 0 || p.lig > 14 || p.lig < 0)) {
                // Si la pièce est déjà sur le plateau
                if (this.getChildren().contains(p) && this.recupPiece(p.col, p.lig) == null) {
                    this.setColumnIndex(p, p.col);
                    this.setRowIndex(p, p.lig);
                }

                // Si la pièce est dans la main
                else if (this.recupPiece(p.col, p.lig) == null) {
                    this.add(p, p.col, p.lig);
                    this.modl.motCourant.push(p);
                }

                // Si le déplacement est invalide
                else {
                    System.out.println("Déplacement impossible");
                }
            }
        }
    }
}