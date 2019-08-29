package ballblast.view.entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * Enumeration used to select ball colors.
 */
public enum BallColors {

    /**
     * Ball color blue.
     */
    BALL_BLUE("blue.png"),
    /**
     * Ball color red.
     */
    BALL_RED("red.png"),
    /**
     * Ball color green.
     */
    BALL_GREEN("green.png"),
    /**
     * Ball color yellow.
     */
    BALL_YELLOW("yellow.png"),
    /**
     * Ball color bordeaux.
     */
    BALL_BORDEAUX("bordeaux.png"),
    /**
     * Ball color light blue.
     */
    BALL_LIGHTBLUE("lightblue.png"),
    /**
     * Ball color purple.
     */
    BALL_PURPLE("purple.png"),
    /**
     * Ball color orange.
     */
    BALL_ORANGE("orange.png");

    private static final String PATH = "/balls/";
    private static final List<BallColors> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private final String selectedBall;


    BallColors(final String ball) {
        this.selectedBall = ball;
    }
    /**
     * 
     * @return
     *          A random {@link BallColors} color.
     */
    private static String randomColor() {
        return VALUES.get(RANDOM.nextInt(SIZE)).toString();
    }
    /**
     * 
     * @return
     *          the path where the ball is stored.
     */
    public static String getPath() {
        return PATH + randomColor();
    }
}
