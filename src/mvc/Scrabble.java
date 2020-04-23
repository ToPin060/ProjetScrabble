package mvc;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scrabble extends Application {

    private Stage stage;
    private Scene menu;
    private Scene scrabble;
    private Modele modl = new Modele();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        primaryStage.setTitle("Scrabble");
        primaryStage.setX(100);
        primaryStage.setY(100);

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        menuLoader.setController(this);
        Parent menuRoot = (Parent) menuLoader.load();
        menu = new Scene(menuRoot);

        FXMLLoader scrabbleLoader = new FXMLLoader(getClass().getResource("scrabble.fxml"));
        scrabbleLoader.setController(new Controleur(menu, modl));
        Parent scrabbleRoot = (Parent) scrabbleLoader.load();
        scrabble = new Scene(scrabbleRoot);

        primaryStage.setScene(menu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void nouvellePartie() {
        stage.setScene(scrabble);
    }

    @FXML
    private void quitter() {
        System.exit(0);
    }
}