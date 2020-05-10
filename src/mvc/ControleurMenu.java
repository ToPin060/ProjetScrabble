package mvc;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControleurMenu {

    private Stage stage;
    private Scene scrabble;
    private Scene statistiques;
    private ControleurStats stats;
    private ControleurScrabble scrab;
    private Modele modl;

    public ControleurMenu() {
    }

    @FXML
    private void solo() {
        this.modl.mode(false);
        this.scrab.initVueMain();
        this.stage.setScene(scrabble);
        this.stage.centerOnScreen();
    }

    @FXML
    private void multi() {
        this.modl.mode(true);
        this.scrab.initVueMain();
        this.stage.setScene(scrabble);
        this.stage.centerOnScreen();
    }

    @FXML
    private void stats() {
        this.stats.update();
        this.stage.setScene(statistiques);
        this.stage.centerOnScreen();
    }

    @FXML
    private void quitter() {
        System.exit(0);
    }

    public void getStage(Stage stage) {
        this.stage = stage;
    }

    public void getScenes(Scene scrabble, Scene statistiques) {
        this.scrabble = scrabble;
        this.statistiques = statistiques;
    }

    public void getModl(Modele modl) {
        this.modl = modl;
    }

    public void getCtrlSt(ControleurStats ctrl) {
        this.stats = ctrl;
    }

    public void getCtrlSc(ControleurScrabble ctrl) {
        this.scrab = ctrl;
    }
}