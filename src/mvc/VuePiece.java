package mvc;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class VuePiece extends StackPane {

    public Text lettre, score;
    public int col, lig;
    public boolean jouable = true;

    public VuePiece(Text l, Text s) {
        Rectangle r = new Rectangle(50, 50);
        this.lettre = l;
        this.score = s;
        this.setId("piece");
        lettre.setId("lettre");
        score.setId("score");
        r.setId("rect");
        this.getChildren().addAll(r, lettre, score);
        this.setAlignment(score, Pos.BOTTOM_RIGHT);
    }
}