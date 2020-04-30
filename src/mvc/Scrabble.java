package mvc;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        Font ui = Font.loadFont(new FileInputStream(new File("ressources/segmdl2.ttf")), 12);
        Font f = Font.loadFont(new FileInputStream(new File("ressources/FallingSky.otf")),12);

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        menuLoader.setController(this);
        Parent menuRoot = (Parent) menuLoader.load();
        menu = new Scene(menuRoot);
        menu.setFill(Color.TRANSPARENT);

        FXMLLoader scrabbleLoader = new FXMLLoader(getClass().getResource("scrabble.fxml"));
        scrabbleLoader.setController(new Controleur(menu, modl));
        Parent scrabbleRoot = (Parent) scrabbleLoader.load();
        scrabble = new Scene(scrabbleRoot);
        scrabble.setFill(Color.TRANSPARENT);

        primaryStage.setScene(menu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void nouvellePartie() {
        stage.setScene(scrabble);
        stage.centerOnScreen();
    }

    @FXML
    private void quitter() {
        System.exit(0);
    }
}