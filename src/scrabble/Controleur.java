package scrabble;

import javafx.event.Event;
import javafx.event.EventHandler;

public class Controleur implements EventHandler {

    private Modèle modl;

    public Controleur(Modèle modl) {
        this.modl = modl;
    }

    @Override
    public void handle(Event event) {
        Object o = event.getSource();
        System.out.println("Hello");
    }
}