package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import annexe.Stats;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControleurStats {

    private Stage stage;
    private Scene menu;
    private double x, y;

    public ControleurStats() {

    }

    @FXML private TableView<Stats> tableauScore;
    @FXML private TableColumn<Stats, Integer> j1;
    @FXML private TableColumn<Stats, Integer> j2;
    @FXML private TableColumn<Stats, Integer> tours;

    @FXML
    public void initialize() {
        this.update();
    }

    @FXML
    private void retour() {
        this.stage.setScene(this.menu);
        this.stage.centerOnScreen();
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

    public void getStage(Stage stage) {
        this.stage = stage;
    }

    public void getMenu(Scene menu) {
        this.menu = menu;
    }

    public void update() {
        File fichier = new File("ressources/stats.dat");
        ArrayList<Stats> scoreListe = new ArrayList<Stats>();

        try {
            FileInputStream fis = new FileInputStream(fichier);
            ObjectInputStream ois = new ObjectInputStream(fis);
            scoreListe = (ArrayList<Stats>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fichier vide, manquant ou corrompu");
        }
        
        j1.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("J1"));
        j2.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("J2"));
        tours.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("Tours"));
        
        if (!scoreListe.isEmpty() && tableauScore.getItems().size()!=scoreListe.size()) {
            tableauScore.getItems().clear();
            tableauScore.getItems().addAll(scoreListe);
        }
    }
}