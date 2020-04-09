package annexe;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Piece extends StackPane {

    public Text lettre;
    public Text score;
    public boolean jouable = true;

    public Piece(Text l, Text s) {
        this.lettre = l;
        this.score = s;
        Rectangle r = new Rectangle(50,50);
        this.setId("piece");
        lettre.setId("lettre");
        score.setId("score");
        r.setId("rect");
        this.getChildren().addAll(r,lettre,score);
        this.setAlignment(score,Pos.BOTTOM_RIGHT);
    }
}