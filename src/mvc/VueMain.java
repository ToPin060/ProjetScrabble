package mvc;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import mvc.Modele.Joueur;

public class VueMain implements Observer {

    private Modele modl;
    private ControleurScrabble ctrl;
    private HBox mainPanel;

    public VueMain(Modele modl, ControleurScrabble ctrl) {
        this.modl = modl;
        this.ctrl = ctrl;
        this.mainPanel = ctrl.mainPanel;
    }

    public VuePiece creerPiece() {
        char c = this.modl.pioche.piocher();
        Text l = new Text("");
        if (c != '[') {
            l.setText("" + c);
        }
        Text s = new Text("" + this.modl.pioche.get(c)[0]);
        VuePiece p = new VuePiece(l, s);
        p.setOnMouseDragged(event -> this.ctrl.drag(event));
        p.setOnMouseReleased(event -> this.ctrl.dragFin(event));

        return p;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Joueur) {
            // Multijoueur
            Joueur joueur = (Joueur) arg;
            this.mainPanel.getChildren().clear();

            switch (joueur) {
                case J1:
                    // Met les pièces du j2 dans le panel main
                    for (int i = 0; i < this.modl.mainJ2.size(); i++) {
                        this.mainPanel.getChildren().add(this.modl.mainJ2.get(i));
                    }

                    // Enlève les pièces posées dans la liste main du j1
                    for (int i = 0; i < this.modl.motCourant.size(); i++) {
                        VuePiece p = this.modl.motCourant.get(i);
                        p.jouable = false;
                    }

                    // Rempli la main du j1
                    while (this.modl.mainJ1.size() < 7 && this.modl.pioche.n_pieces > 0) {
                        VuePiece p = this.creerPiece();
                        this.modl.mainJ1.add(p);
                    }

                    break;

                case J2:
                    // Met les pièces du j1 dans le panel main
                    for (int i = 0; i < this.modl.mainJ1.size(); i++) {
                        this.mainPanel.getChildren().add(this.modl.mainJ1.get(i));
                    }

                    // Enlève les pièces posées dans la liste main du j2
                    for (int i = 0; i < this.modl.motCourant.size(); i++) {
                        VuePiece p = this.modl.motCourant.get(i);
                        p.jouable = false;
                    }

                    // Rempli la main du j2
                    while (this.modl.mainJ2.size() < 7 && this.modl.pioche.n_pieces > 0) {
                        VuePiece p = this.creerPiece();
                        this.modl.mainJ2.add(p);
                    }

                    break;
            }
        }

        else if (arg==null) {
            // Solo
            this.mainPanel.getChildren().clear();

            for (int i = 0; i < this.modl.motCourant.size(); i++) {
                VuePiece p = this.modl.motCourant.get(i);
                p.jouable = false;
            }

            while (this.modl.mainJ1.size() < 7 && this.modl.pioche.n_pieces > 0) {
                VuePiece p = this.creerPiece();
                this.modl.mainJ1.add(p);
            }

            for (int i=0; i<this.modl.mainJ1.size(); i++) {
                this.mainPanel.getChildren().add(this.modl.mainJ1.get(i));
            }
        }
    }

    public void init() {
        this.mainPanel.getChildren().clear();

        for (int i = 0; i < 7; i++) {
            VuePiece p1 = this.creerPiece();
            this.modl.mainJ1.add(p1);
            this.mainPanel.getChildren().add(p1);

            if (this.modl.multijoueur) {
                VuePiece p2 = this.creerPiece();
                this.modl.mainJ2.add(p2);
            }
        }
    }
}