package ballblast.model.gameobjects;

import java.util.List;
import com.google.common.collect.ImmutableList;

/**
 * Interface that handles all objects on a specific {@link Level}.
 *
 */
public class GameObjectManager {

    private ImmutableList<GameObject> gameObjects;
    private ImmutableList<GameObject> toBeAdded;
    private final GameObject player;

    /**
     * Creates a new instance of GameObjectManager.
     * @param player
     *      the {@link GameObject} handled by the User.
     */
    public GameObjectManager(final GameObject player) {
        this.player = player;
        this.gameObjects = ImmutableList.of(player);
        this.toBeAdded = ImmutableList.of();
    }
    /**
     * Updates the status of every {@link GameObject}.
     * @param elapsed
     *      the time elapsed since last update.
     */
    public void update(final double elapsed) {
        this.joinGameObjects();
        this.removeDestoyedObjects();
        this.gameObjects.forEach(o -> o.update(elapsed));
    }
    /**
     * Adds a {@link GameObject} {@link List}.
     * @param gameObjects
     *      the {@link GameObject} {@link List} to be added.
     */
    public void addGameObjects(final List<GameObject> gameObjects) {
        this.toBeAdded = ImmutableList.<GameObject>builder()
                .addAll(toBeAdded)
                .addAll(gameObjects)
                .build();
    }
    /**
     * Gets the {@link GameObject} player.
     * @return
     *      the player.
     */
    public GameObject getPlayer() {
        return player;
    }
    /**
     * Gets the {@link List} containing all {@link GameObject}s.
     * @return
     *      the {@link List} containing all {@link GameObject}s.. 
     */
    public List<GameObject> getGameObjects() {
        return ImmutableList.copyOf(this.gameObjects);
    }
    /**
     * Concatenates the main {@link GameObject} {@link List} 
     * with the {@link GameObject} {@link List} to be added.
     */
    private void joinGameObjects() {
        this.gameObjects = ImmutableList.<GameObject>builder()
                .addAll(gameObjects)
                .addAll(toBeAdded)
                .build();
        this.toBeAdded = ImmutableList.of();
    }
    /**
     * Removes destroyed game objects from the {@link List}.
     */
    private void removeDestoyedObjects() {
        this.gameObjects = gameObjects.stream()
                .filter(o -> !o.isDestroyed())
                .collect(ImmutableList.toImmutableList());
    }
}
