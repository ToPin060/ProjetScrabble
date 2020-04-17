package mvc;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class VuePlateau implements Observer {

    private Modele modl;
    private Controleur ctrl;
    private GridPane grillePanel;
    private HBox main;

    public VuePlateau(Modele modl, Controleur ctrl) {
        this.modl = modl;
        this.ctrl = ctrl;
        this.grillePanel = ctrl.grille;
        this.main = ctrl.main;
    }

    public Node recupPiece(int col, int lig) {
        Node node = null;
        ObservableList<Node> l = this.grillePanel.getChildren();
        int cpt = l.size() - 1;

        while (cpt >= 0) {
            node = l.get(cpt);

            if ((node instanceof VuePiece) && this.grillePanel.getRowIndex(node) == lig
                    && this.grillePanel.getColumnIndex(node) == col) {
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
            boolean depHorsGrille = p.col > 14 || p.col < 0 || p.lig > 14 || p.lig < 0;

            if (!depHorsGrille) {
                // Si la pièce est déjà sur le plateau
                if (this.grillePanel.getChildren().contains(p) && this.recupPiece(p.col, p.lig) == null) {
                    this.grillePanel.setColumnIndex(p, p.col);
                    this.grillePanel.setRowIndex(p, p.lig);
                }

                // Si la pièce est dans la main
                else if (this.recupPiece(p.col, p.lig) == null) {
                    // Si la pièce est un joker
                    if (p.score.getText().equals("0")) {
                        TextInputDialog dialog = new TextInputDialog("");
                        dialog.setTitle("Joker");
                        dialog.setHeaderText("Choix du Joker");
                        dialog.setContentText("Entrez une lettre :");
                        Optional<String> ltr = dialog.showAndWait();

                        if (!ltr.isPresent() || ltr.get().equals("") || ltr.get().length() != 1
                                || (int) ltr.get().toLowerCase().charAt(0) < 97
                                || (int) ltr.get().toLowerCase().charAt(0) > 122) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setHeaderText("Joker non valide");
                            alert.setContentText("Le Joker doit être une lettre!");
                            alert.showAndWait();
                        }

                        else {
                            p.lettre.setText(ltr.get().toUpperCase());
                            this.grillePanel.add(p, p.col, p.lig);
                            this.modl.motCourant.push(p);
                        }
                    }

                    // Si la pièce n'est pas un joker
                    else {
                        this.grillePanel.add(p, p.col, p.lig);
                        this.modl.motCourant.push(p);
                    }
                }
            }

            // Retourne la pièce dans la main
            else if (depHorsGrille && this.grillePanel.getChildren().contains(p)) {
                this.grillePanel.getChildren().remove(p);

                if (p.score.getText().equals("0")) {
                    p.lettre.setText("");
                }

                this.main.getChildren().add(p);
                this.modl.motCourant.remove(p);
            }
        }
    }
}