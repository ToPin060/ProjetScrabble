package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import annexe.Stats;
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

public class ControleurScrabble {

    private Stage stage;
    private Scene menu;
    private Modele modl;
    private VueMain main;
    private VuePlateau plat;
    private double x, y;

    public ControleurScrabble(Modele modl) {
        this.modl = modl;
    }

    @FXML public GridPane grillePanel;
    @FXML public HBox mainPanel;
    @FXML public Text joueurTexte;
    @FXML public Text scoreTexte;
    @FXML public Button echangerBtn;

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
        this.stage.setIconified(true);
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
        this.stage.setX(event.getScreenX() - x);
        this.stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void finTour() {
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
            this.modl.passer = 0;
            this.modl.tourSuivant();

            if (this.modl.pioche.n_pieces < 7) {
                this.echangerBtn.setDisable(true);
            }

            else if (this.modl.pioche.n_pieces == 0) {
                if (this.modl.mainJ1.size() == 0) {
                    for (int i = 0; i < this.modl.mainJ2.size(); i++) {
                        int score = Integer.parseInt((String) this.modl.mainJ2.get(i).score.getText());
                        this.modl.score.j1 += score;
                        this.gameOver();
                    }
                }

                else if (this.modl.mainJ2.size() == 0) {
                    for (int i = 0; i < this.modl.mainJ1.size(); i++) {
                        int score = Integer.parseInt((String) this.modl.mainJ1.get(i).score.getText());
                        this.modl.score.j2 += score;
                        this.gameOver();
                    }
                }
            }
        }

        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(this.modl.erreur);
            alert.showAndWait();
        }
    }

    @FXML
    private void abandonner() {
        this.stage.setScene(this.menu);
        this.stage.centerOnScreen();

        this.modl.reset();
        this.plat.reset();
        this.joueurTexte.setText("Joueur 1");
        this.scoreTexte.setText("Score: 0");
        this.echangerBtn.setDisable(false);
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
        dialog.setTitle("Échanger les x dernières pièces");
        dialog.setHeaderText(null);
        dialog.setContentText("Nombre de pièces à échanger :");
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
        this.modl.passer++;
        this.modl.motCourant.clear();
        this.modl.tourSuivant();

        if (this.modl.passer >= 6) {
            this.gameOver();
        }
    }

    public void initVueMain() {
        this.main.init();
    }

    public void getStage(Stage stage) {
        this.stage = stage;
    }

    public void getMenu(Scene menu) {
        this.menu = menu;
    }

    public void drag(MouseEvent event) {
        VuePiece p = (VuePiece) event.getSource();
        p.toFront();

        if (p.jouable) {
            p.setTranslateX(event.getX() + p.getTranslateX() - 25);
            p.setTranslateY(event.getY() + p.getTranslateY() - 25);
        }
    }

    public void dragFin(MouseEvent event) {
        VuePiece p = (VuePiece) event.getSource();
        p.setTranslateX(0);
        p.setTranslateY(0);

        if (p.jouable) {
            int xOffset = (int) ((event.getSceneX() - 10) / 50);
            int yOffset = (int) ((event.getSceneY() - 36) / 50);
            int col = (int) ((event.getSceneX() - 10 - xOffset) / 50);
            int lig = (int) ((event.getSceneY() - 36 - yOffset) / 50);
            p.col = col;
            p.lig = lig;
            this.modl.deplacerPiece(p);
        }
    }

    public void majTexte() {
        if (!this.modl.multijoueur) {this.scoreTexte.setText("Score: " + this.modl.score.j1);}

        else {
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

    public void gameOver() {
        ArrayList<Stats> scoreListe = new ArrayList<Stats>();
        Stats stats = new Stats(this.modl.score.j1, this.modl.score.j2, this.modl.tour);
        if (!this.modl.multijoueur) {
            stats.j2 = null;
        }

        File fichier = new File("ressources/stats.dat");
        
        // Récupérer la liste des scores
        try {
            FileInputStream fis = new FileInputStream(fichier);
            ObjectInputStream ois = new ObjectInputStream(fis);
            scoreListe = (ArrayList<Stats>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fichier vide, manquant ou corrompu");
        }

        // Compléter la liste des scores
        scoreListe.add(stats);
       
        try {
            FileOutputStream fos = new FileOutputStream(fichier);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(scoreListe);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Fichier vide, manquant ou corrompu");
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Partie terminée");
        alert.setHeaderText(null);

        if (this.modl.score.j1 == this.modl.score.j2) {
            alert.setContentText("Personne n'a gagné !");
        }

        else if (this.modl.score.j1 > this.modl.score.j2) {
            alert.setContentText("Le joueur 1 a gagné avec " + this.modl.score.j1 + " points !");
        }

        else {
            alert.setContentText("Le joueur 2 a gagné avec " + this.modl.score.j2 + " points !");
        }

        alert.showAndWait();
        this.abandonner();
    }
}