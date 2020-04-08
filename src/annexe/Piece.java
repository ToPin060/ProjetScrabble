package annexe;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Piece extends StackPane {

    public Text lettre = new Text("A");
    public boolean jouable = true;

    public Piece() {
        Rectangle r = new Rectangle(50,50,Color.DARKSALMON);
        this.getChildren().addAll(r,lettre);
    }
}