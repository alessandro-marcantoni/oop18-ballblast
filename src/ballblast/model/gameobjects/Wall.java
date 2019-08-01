package ballblast.model.gameobjects;
/**
 * Represents a wall object used to create the level's boundaries. 
 */
public class Wall extends AbstractGameObject {
    private static final int DEFAULT_WIDTH = 5;
    private static final int DEFAULT_HEIGHT = 5;
    /**
     * Creates a {@link Wall} instance.
     */
    protected Wall() {
        super(GameObjectTypes.WALL);
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
    }

}
