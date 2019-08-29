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
    BALL_BLUE("/balls/blue.png"),
    /**
     * Ball color red.
     */
    BALL_RED("/balls/red.png"),
    /**
     * Ball color green.
     */
    BALL_GREEN("/balls/green.png"),
    /**
     * Ball color yellow.
     */
    BALL_YELLOW("/balls/yellow.png"),
    /**
     * Ball color bordeaux.
     */
    BALL_BORDEAUX("/balls/bordeaux.png"),
    /**
     * Ball color light blue.
     */
    BALL_LIGHTBLUE("/balls/lightblue.png"),
    /**
     * Ball color purple.
     */
    BALL_PURPLE("/balls/purple.png"),
    /**
     * Ball color orange.
     */
    BALL_ORANGE("/balls/orange.png");

    private static final List<BallColors> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private final String ballPath;


    BallColors(final String ballPath) {
        this.ballPath = ballPath;
    }
    /**
     * 
     * @return
     *          A random {@link BallColors} color.
     */
    public static String getRandomColor() {
        return VALUES.get(RANDOM.nextInt(SIZE)).getBallPath();
    }
    /**
     * 
     * @return
     *          pippo
     */
    public String getBallPath() {
        return this.ballPath;
    }
    
    public String getBallColor() {
        return this.toString();
    }
}
