package ballblast.model.gameobjects;

import com.google.common.collect.ImmutableMap;
/**
 * Represents a class used to create {@link GameObject}s without having to specify 
 * the exact class of the {@link GameObject} that will be created. 
 * It follows the Factory pattern. 
 *
 */
public class GameObjectFactory {
    private ImmutableMap<GameObjectTypes, GameObjectBuilder> builders;
    /**
     * Creates a GameObjectFactory instance.
     */
    public GameObjectFactory() {
        builders = ImmutableMap.<GameObjectTypes, GameObjectBuilder>builder()
                               .put(GameObjectTypes.BALL, () -> new Ball(GameObjectTypes.BALL))
                               .build();
    }
    /**
     * Creates {@link GameObject}.
     * @param type
     *     the type of {@link GameObject} to be created.
     * @return
     *     the chosen {@link GameObject}
     */
    public GameObject create(final GameObjectTypes type) {
        return builders.get(type).generate();
    }
    /**
     * Interfaces used to implement the GameObject generation.
     *
     */
    private interface GameObjectBuilder {
        GameObject generate();
    }
}
