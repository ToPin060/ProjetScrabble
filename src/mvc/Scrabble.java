package mvc;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
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
    private Scene statistiques;
    private Modele modl = new Modele();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        Font ui = Font.loadFont(new FileInputStream(new File("ressources/segmdl2.ttf")), 12);
        Font f = Font.loadFont(new FileInputStream(new File("ressources/FallingSky.otf")), 12);

        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        ControleurMenu ctrlMenu = new ControleurMenu();
        menuLoader.setController(ctrlMenu);
        Parent menuRoot = (Parent) menuLoader.load();
        menu = new Scene(menuRoot);
        menu.setFill(Color.TRANSPARENT);
        ctrlMenu.getStage(stage);

        FXMLLoader scrabbleLoader = new FXMLLoader(getClass().getResource("scrabble.fxml"));
        ControleurScrabble ctrlScrabble = new ControleurScrabble(modl);
        scrabbleLoader.setController(ctrlScrabble);
        Parent scrabbleRoot = (Parent) scrabbleLoader.load();
        scrabble = new Scene(scrabbleRoot);
        scrabble.setFill(Color.TRANSPARENT);
        ctrlScrabble.getStage(stage);
        ctrlScrabble.getMenu(menu);

        FXMLLoader statistiquesLoader = new FXMLLoader(getClass().getResource("statistiques.fxml"));
        ControleurStats ctrlStats = new ControleurStats();
        statistiquesLoader.setController(ctrlStats);
        Parent statistiquesRoot = (Parent) statistiquesLoader.load();
        statistiques = new Scene(statistiquesRoot);
        statistiques.setFill(Color.TRANSPARENT);
        ctrlStats.getStage(stage);
        ctrlStats.getMenu(menu);

        ctrlMenu.getScenes(scrabble, statistiques);
        ctrlMenu.getModl(this.modl);
        ctrlMenu.getCtrlSc(ctrlScrabble);
        ctrlMenu.getCtrlSt(ctrlStats);

        primaryStage.setScene(menu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}