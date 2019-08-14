package ballblast.view.entities;

import ballblast.model.gameobjects.BallTypes;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class ViewBall {

    private ViewBall() {
    }
    
    public static Circle getBall(final BallTypes dimension) {
        int radius = dimension.getDiameter();
        Paint cc = BallColors.randomColor().getColor();
        return new Circle(radius, cc);
       
    }
}
