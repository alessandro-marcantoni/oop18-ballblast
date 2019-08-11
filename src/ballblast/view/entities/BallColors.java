package ballblast.view.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

/**
 * 
 * Enumeration used to select ball colors.
 */
public enum BallColors {

    /**
     * Ball color blue
     */
    BALL_BLUE("ballBlue.png"),
    /**
     * Ball color red
     */
    BALL_RED("ballRed.png"),
    /**
     * Ball color green
     */
    BALL_GREEN("ballGreen.png"),
    /**
     * Ball color yellow
     */
    BALL_YELLOW("ballYellow.png"),
    /**
     * Ball color pink
     */
    BALL_PINK("ballPink.png"),
    /**
     * Ball color lightblue
     */
    BALL_LIGHTBLUE("ballLightblue.png"),
    /**
     * Ball color purple
     */
    BALL_PURPLE("ballPurple.png"),
    /**
     * Ball color brown
     */
    BALL_BROWN("ballBrown.png"),
    /**
     * Ball color orange
     */
    BALL_ORANGE("ballOrange.png");
    
    private static final String PATH = "/view/balls/";
    private Image image;
    
    private BallColors(final String ballColor) {

        this.image = new Image(getClass().getResourceAsStream(PATH + ballColor));
    }
    
}
