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
    private ControleurScrabble ctrl;
    private GridPane grillePanel;
    private HBox mainPanel;

    public VuePlateau(Modele modl, ControleurScrabble ctrl) {
        this.modl = modl;
        this.ctrl = ctrl;
        this.grillePanel = ctrl.grillePanel;
        this.mainPanel = ctrl.mainPanel;
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

    public void reset() {
        for (Node node : this.grillePanel.lookupAll("#piece")) {
            this.grillePanel.getChildren().remove(node);
        }
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
                        dialog.setHeaderText(null);
                        dialog.setContentText("Entrez une lettre :");
                        Optional<String> ltr = dialog.showAndWait();

                        if (!ltr.isPresent() || ltr.get().equals("") || ltr.get().length() != 1
                                || (int) ltr.get().toLowerCase().charAt(0) < 97
                                || (int) ltr.get().toLowerCase().charAt(0) > 122) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setHeaderText(null);
                            alert.setContentText("Le Joker est invalide.");
                            alert.showAndWait();
                        }

                        else {
                            p.lettre.setText(ltr.get().toUpperCase());
                            this.grillePanel.add(p, p.col, p.lig);
                            this.modl.motCourant.add(p);
                        }
                    }

                    // Si la pièce n'est pas un joker
                    else {
                        this.grillePanel.add(p, p.col, p.lig);
                        this.modl.motCourant.add(p);
                    }

                    // Enlève la pièce de la main du joueur
                    switch (this.modl.joueur) {
                        case J1:
                            this.modl.mainJ1.remove(p);
                            break;
                        case J2:
                            this.modl.mainJ2.remove(p);
                            break;
                    }
                }
            }

            // Retourne la pièce dans la main
            else if (depHorsGrille && this.grillePanel.getChildren().contains(p)) {
                this.grillePanel.getChildren().remove(p);

                if (p.score.getText().equals("0")) {
                    p.lettre.setText("");
                }

                switch (this.modl.joueur) {
                    case J1:
                        this.modl.mainJ1.add(p);
                        break;
                    case J2:
                        this.modl.mainJ2.add(p);
                        break;
                }

                this.mainPanel.getChildren().add(p);
                this.modl.motCourant.remove(p);
            }

            // Déplace la pièce à l'intérieur de la main
            else {
                switch (this.modl.joueur) {
                    case J1:
                        this.modl.mainJ1.remove(p);
                        this.modl.mainJ1.add(p);
                        break;
                    case J2:
                        this.modl.mainJ2.remove(p);
                        this.modl.mainJ2.add(p);
                        break;
                }
            }
        }
    }
}