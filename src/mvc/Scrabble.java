package mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scrabble extends Application {

    private Modele modl = new Modele();
    private Controleur ctrl = new Controleur(modl);
    private VueHello v1 = new VueHello(ctrl);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Scrabble");
        Scene sc1 = new Scene(v1);
        primaryStage.setScene(sc1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}