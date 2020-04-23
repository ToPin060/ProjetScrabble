package mvc;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.Modele.Joueur;

public class Controleur {

    private Scene menu;
    private Modele modl;

    public Controleur(Scene menu, Modele modl) {
        this.menu = menu;
        this.modl = modl;
    }

    @FXML
    public GridPane grillePanel;
    public HBox mainPanel;
    public Text joueurTexte;

    @FXML
    private void initialize() {
        VuePlateau plat = new VuePlateau(modl, this);
        VueMain main = new VueMain(modl, this);
        this.modl.addObserver(plat);
        this.modl.addObserver(main);
        this.joueurTexte.setText("Joueur 1");
    }

    @FXML
    private void fin() {
        this.modl.tourSuivant();
        if (this.modl.joueur == Joueur.J1) {
            this.joueurTexte.setText("Joueur 1");
        }

        else {
            this.joueurTexte.setText("Joueur 2");
        }
    }

    @FXML
    private void abandonner() {
        Stage stage = (Stage) this.grillePanel.getScene().getWindow();
        stage.setScene(this.menu);
    }

    @FXML
    private void echanger() {
        System.out.println("echanger");
    }

    @FXML
    private void passer() {
        System.out.println("passer");
    }

    public void drag(MouseEvent event) {
        VuePiece p = (VuePiece) event.getSource();

        if (p.jouable) {
            p.setTranslateX(event.getX() + p.getTranslateX() - 25);
            p.setTranslateY(event.getY() + p.getTranslateY() - 25);
            event.consume();
        }
    }

    public void dragFin(MouseEvent event) {
        VuePiece p = (VuePiece) event.getSource();
        p.setTranslateX(0);
        p.setTranslateY(0);

        if (p.jouable) {
            int col = (int) ((event.getSceneX() + 50) / 50) - 2;
            int lig = (int) ((event.getSceneY() + 50) / 50) - 1;
            p.col = col;
            p.lig = lig;
            this.modl.deplacerPiece(p);
        }
    }
}