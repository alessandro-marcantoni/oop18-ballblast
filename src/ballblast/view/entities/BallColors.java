package ballblast.view.entities;

import javafx.scene.image.Image;

/**
 * 
 * Enumeration used to select ball colors.
 */
public enum BallColors {

    /**
     * Ball color blue
     */
    BALL_BLUE("blue.png"),
    /**
     * Ball color red
     */
    BALL_RED("red.png"),
    /**
     * Ball color green
     */
    BALL_GREEN("green.png"),
    /**
     * Ball color yellow
     */
    BALL_YELLOW("yellow.png"),
    /**
     * Ball color pink
     */
    BALL_PINK("pink.png"),
    /**
     * Ball color lightblue
     */
    BALL_LIGHTBLUE("lightblue.png"),
    /**
     * Ball color purple
     */
    BALL_PURPLE("purple.png"),
    /**
     * Ball color brown
     */
    BALL_BROWN("brown.png"),
    /**
     * Ball color orange
     */
    BALL_ORANGE("orange.png");
    
    private static final String PATH = "/view/balls/";
    private Image image;
    
    private BallColors(final String ballColor) {
        this.image = new Image(getClass().getResourceAsStream(PATH + ballColor));
    }
    
    /**
     * 
     * @return
     *          the ball image
     */
    public Image getImage() {
        return this.image;
    }
    
}
