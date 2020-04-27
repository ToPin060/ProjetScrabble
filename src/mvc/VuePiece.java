package mvc;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class VuePiece extends StackPane {

    public Text lettre, score;
    public int col, lig;
    public boolean jouable;

    public VuePiece(Text l, Text s) {
        this.jouable = true;
        this.lettre = l;
        this.score = s;
        this.setId("piece");
        lettre.setId("lettre");
        score.setId("score");

        if (this.score.getText().equals("0")) {
            this.getChildren().add(lettre);
        }

        else {
            this.getChildren().addAll(lettre, score);
            this.setAlignment(score, Pos.BOTTOM_RIGHT);
        }
    }

    @Override
    public String toString(){

        return "["+this.lig+", "+this.col+"]";
    }
}