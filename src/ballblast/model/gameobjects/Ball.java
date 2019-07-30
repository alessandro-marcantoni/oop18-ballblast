package ballblast.model.gameobjects;
/**
 * Implements the GameObject Ball. It bounces always at same height 
 * based on its size and not depending on gravity values.
 */
public class Ball extends AbstractGameObject {
    /**
     * Creates a Ball instance.
     * @param type
     *     the type of {@link GameObject}.
     */
    public Ball(final GameObjectTypes type) {
        super(type);
    }

}
