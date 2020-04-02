package mvc;

import javafx.event.Event;
import javafx.event.EventHandler;

public class Controleur implements EventHandler {

    private Modele modl;

    public Controleur(Modele modl) {
        this.modl = modl;
    }

    @Override
    public void handle(Event event) {
        Object o = event.getSource();
        System.out.println("Hello");
    }
}