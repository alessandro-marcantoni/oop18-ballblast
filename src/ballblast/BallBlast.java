package ballblast;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;

/**
 * The class containing the main method to start the application.
 */
public final class BallBlast {
    private BallBlast() { };
    /**
     * The main entry point of the application.
     * @param args
     *      CLI arguments.
     */
    public static void main(final String [] args) {
        System.out.println("Hello World");
        System.out.println(new Ball.Builder().setLife(4).setBallType(BallTypes.LARGE).build().toString());
    }
}
