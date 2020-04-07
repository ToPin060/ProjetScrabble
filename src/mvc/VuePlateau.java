package mvc;

import annexe.Plateau;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VuePlateau extends GridPane {

    private Plateau p = new Plateau();

    public VuePlateau(Controleur ctrl) {

        this.setStyle("-fx-background-color: lightgray; -fx-vgap: 1; -fx-hgap: 1; -fx-padding: 1;");
        
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                Rectangle r = new Rectangle(50*i,50*j,50,50);

                if (p.get(i,j)==-4) {
                    r.setFill(Color.rgb(196,18,44));
                }

                else if (p.get(i,j)==-3) {
                    r.setFill(Color.rgb(228,182,167));
                }

                else if (p.get(i,j)==-2) {
                    r.setFill(Color.rgb(38,100,177));
                }

                else if (p.get(i,j)==-1) {
                    r.setFill(Color.rgb(122,161,216));
                }

                else if (p.get(i,j)==0) {
                    r.setFill(Color.rgb(38,144,144));
                }

                this.add(r,i,j);
            }
        }
    }
}