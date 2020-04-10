package mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scrabble extends Application {

    private Modele modl = new Modele();
    private Controleur ctrl = new Controleur(modl);
    private VueScrabble v = new VueScrabble(modl, ctrl);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Scrabble");
        Scene sc = new Scene(v);
        sc.getStylesheets().add("style.css");
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}