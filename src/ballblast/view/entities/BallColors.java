package ballblast.view.entities;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;

/**
 * 
 * Enumeration used to select ball colors.
 */
public enum BallColors {

    /**
     * Ball color blue
     */
    BALL_BLUE(Color.BLUE),
    /**
     * Ball color red
     */
    BALL_RED(Color.RED),
    /**
     * Ball color green
     */
    BALL_GREEN(Color.GREEN),
    /**
     * Ball color yellow
     */
    BALL_YELLOW(Color.YELLOW),
    /**
     * Ball color pink
     */
    BALL_PINK(Color.PINK),
    /**
     * Ball color lightblue
     */
    BALL_LIGHTBLUE(Color.LIGHTBLUE),
    /**
     * Ball color purple
     */
    BALL_PURPLE(Color.PURPLE),
    /**
     * Ball color brown
     */
    BALL_BROWN(Color.BROWN),
    /**
     * Ball color orange
     */
    BALL_ORANGE(Color.ORANGE);
    
//    private static final String PATH = "/view/balls/";
//    private Image image;
//    
//    private BallColors(final String ballColor) {
//        this.image = new Image(getClass().getResourceAsStream(PATH + ballColor));
//    }
//    
//    /**
//     * 
//     * @return
//     *          the ball image
//     */
//    public Image getImage() {
//        return this.image;
//    }
    
    private BallColors(Color color) {
        
    }
    public Paint getColor() {
return null;
        
    }
    
    public static BallColors randomColor() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
    
}
