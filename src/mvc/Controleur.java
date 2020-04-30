package mvc;

import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controleur {

    private Scene menu;
    private Modele modl;
    private VueMain main;
    private VuePlateau plat;
    private double x, y;

    public Controleur(Scene menu, Modele modl) {
        this.menu = menu;
        this.modl = modl;
    }

    @FXML
    public GridPane grillePanel;
    public HBox mainPanel;
    public Text joueurTexte;
    public Text scoreTexte;
    public Button echangerBtn;

    @FXML
    private void initialize() {
        this.plat = new VuePlateau(modl, this);
        this.main = new VueMain(modl, this);
        this.modl.addObserver(plat);
        this.modl.addObserver(main);
        this.joueurTexte.setText("Joueur 1");
        this.scoreTexte.setText("Score: 0");
    }

    @FXML
    private void minimiser() {
        Stage stage = (Stage) this.grillePanel.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void fermer() {
        System.exit(0);
    }

    @FXML
    private void getCoord(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    private void dragFenetre(MouseEvent event) {
        Stage stage = (Stage) this.grillePanel.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void fin() {
        System.out.println("---------");
        if (this.modl.verificationTour()) {
            switch (this.modl.joueur) {
                case J1:
                    this.modl.score.scoreInser(this.modl.lettresP, 1);
                    break;
                case J2:
                    this.modl.score.scoreInser(this.modl.lettresP, 2);
                    break;
            }

            this.majTexte();
            this.modl.tourSuivant();

            if (this.modl.pioche.n_pieces < 7) {
                this.echangerBtn.setDisable(true);
            }
        }

        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Fin de tour impossible");
            alert.setContentText("Le mot est incorrect ou mal placé.");
            alert.showAndWait();
        }
    }

    @FXML
    private void abandonner() {
        Stage stage = (Stage) this.grillePanel.getScene().getWindow();
        stage.setScene(this.menu);
        stage.centerOnScreen();

        this.modl.reset();
        this.plat.reset();
        this.main.reset();
        this.joueurTexte.setText("Joueur 1");
        this.scoreTexte.setText("Score: 0");
    }

    @FXML
    private void echanger() {
        ArrayList<Integer> choix = new ArrayList<Integer>();
        switch (this.modl.joueur) {
            case J1:
                for (int i = 0; i < this.modl.mainJ1.size(); i++) {
                    choix.add(i + 1);
                }
                break;
            case J2:
                for (int i = 0; i < this.modl.mainJ2.size(); i++) {
                    choix.add(i + 1);
                }
                break;
        }

        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(null, choix);
        dialog.setTitle("Échanger");
        dialog.setHeaderText("Les x dernières pièces seront échangées");
        dialog.setContentText("Nombre de pièces:");
        Optional<Integer> res = dialog.showAndWait();
        if (res.isPresent()) {
            switch (this.modl.joueur) {
                case J1:
                    for (int i = this.modl.mainJ1.size() - 1; i >= this.modl.mainJ1.size() - res.get(); i--) {
                        VuePiece old = this.modl.mainJ1.get(i);
                        VuePiece p = this.main.creerPiece();
                        char c;
                        if (old.score.getText().equals("0")) {
                            c = '[';
                        } else {
                            c = old.lettre.getText().charAt(0);
                        }

                        this.modl.mainJ1.remove(old);
                        this.modl.pioche.ajouter(c);
                        this.modl.mainJ1.add(p);
                    }

                    break;

                case J2:
                    for (int i = this.modl.mainJ2.size() - 1; i >= this.modl.mainJ2.size() - res.get(); i--) {
                        VuePiece old = this.modl.mainJ2.get(i);
                        VuePiece p = this.main.creerPiece();
                        char c;
                        if (old.score.getText().equals("0")) {
                            c = '[';
                        } else {
                            c = old.lettre.getText().charAt(0);
                        }

                        this.modl.mainJ2.remove(old);
                        this.modl.pioche.ajouter(c);
                        this.modl.mainJ2.add(p);
                    }

                    break;
            }

            choix.clear();
            this.passer();
        }
    }

    @FXML
    private void passer() {
        for (int i = 0; i < this.modl.motCourant.size(); i++) {
            this.grillePanel.getChildren().remove(this.modl.motCourant.get(i));

            switch (this.modl.joueur) {
                case J1:
                    this.modl.mainJ1.add(this.modl.motCourant.get(i));
                    break;
                case J2:
                    this.modl.mainJ2.add(this.modl.motCourant.get(i));
                    break;
            }

            if (this.modl.motCourant.get(i).score.getText().equals("0")) {
                this.modl.motCourant.get(i).lettre.setText("");
            }
        }

        if (this.modl.tour == 0) {
            this.modl.tour--;
        }
        this.majTexte();
        this.modl.motCourant.clear();
        this.modl.tourSuivant();
    }

    public void drag(MouseEvent event) {
        VuePiece p = (VuePiece) event.getSource();
        p.toFront();

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
            int col = (int) ((event.getSceneX() + 50) / 50) - 1;
            int lig = (int) ((event.getSceneY() + 50) / 50) - 2;
            p.col = col;
            p.lig = lig;
            this.modl.deplacerPiece(p);
        }
    }

    public void majTexte() {
        switch (this.modl.joueur) {
            case J1:
                this.joueurTexte.setText("Joueur 2");
                this.scoreTexte.setText("Score: " + this.modl.score.j2);
                break;
            case J2:
                this.joueurTexte.setText("Joueur 1");
                this.scoreTexte.setText("Score: " + this.modl.score.j1);
                break;
        }
    }
}