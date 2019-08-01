package ballblast.model.gameobjects;

/**
 * This class implements the Player object. It represents the entity controlled by the user 
 * which can shoot to destroy objects and dies when hit by a ball.
 */
public class Player extends AbstractGameObject {
    private static final int DEFAULT_WIDTH = 12;
    private static final int DEFAULT_HEIGHT = 15;
    /**
     * Creates a Player instance.
     */
    protected Player() {
        super(GameObjectTypes.PLAYER);
        this.setHeight(DEFAULT_HEIGHT);
        this.setWidth(DEFAULT_WIDTH);
    }
    /**
     * Concrete implementation of {@link AbstractGameObject.AbstractBuilder}.
     */
    public static class Builder extends AbstractGameObject.AbstractBuilder<Player, Builder> {
        @Override
        protected final Player initGameObject() {
            return new Player();
        }

        @Override
        protected final Builder getBuilder() {
            return this;
        }
    }
}
