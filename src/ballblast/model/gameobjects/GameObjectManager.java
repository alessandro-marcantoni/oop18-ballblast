package ballblast.model.gameobjects;

import java.util.List;
import com.google.common.collect.ImmutableList;

/**
 * Handles all {@link GameObject}s of a specific {@link Level}.
 *
 */
public class GameObjectManager {

    private List<GameObject> gameObjects;
    private List<GameObject> toBeAdded;
    /**
     * Creates a new instance of GameObjectManager.
     */
    public GameObjectManager() {
        this.gameObjects = ImmutableList.of();
        this.toBeAdded = ImmutableList.of();
    }
    /**
     * Updates the status of every {@link GameObject}.
     * @param elapsed
     *      the time elapsed since last update.
     */
    public void update(final double elapsed) {
        this.gameObjects.forEach(o -> o.update(elapsed));
        this.joinGameObjects();
        this.removeDestoyedObjects();
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
     * Gets the {@link List} containing all {@link GameObject}s.
     * @return
     *      the {@link List} containing all {@link GameObject}s.. 
     */
    public List<GameObject> getGameObjects() {
        return ImmutableList.copyOf(this.gameObjects);
    }
    /**
     * Gets the {@link List} of destroyed {@link Ball}s.
     * @return
     *     the {@link Ball}s which have been destroyed in the last update.
     */
    public List<GameObject> getDestroyedBalls() {
        return gameObjects.stream()
                .filter(g -> g.getType() == GameObjectTypes.BALL)
                .filter(GameObject::isDestroyed)
                .collect(ImmutableList.toImmutableList());
    }
    /*
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

    private void removeDestoyedObjects() {
        this.gameObjects = gameObjects.stream()
                .filter(g -> !g.isDestroyed())
                .collect(ImmutableList.toImmutableList());
    }
}
