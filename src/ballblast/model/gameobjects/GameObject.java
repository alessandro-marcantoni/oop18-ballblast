package ballblast.model.gameobjects;

import ballblast.model.components.Component;
import java.util.List;
import ballblast.utils.Point2D;

/**
 * Represent a general entity of the game.
 */
public interface GameObject {
    /**
     * Gets the {@link GameObject} height.
     * @return
     *      the height of game object.
     */
    int getHeight();
    /**
     * Gets the {@link GameObject} width.
     * @return
     *      the width of {@link GameObject}.
     */
    int getWidth();
    /**
     * Gets the GameObject position.
     * @return
     *      {@link Point2d} of {@link GameObject}.
     */
    Point2D getPosition();
    /**
     * Sets the GameObject position.
     * @param position
     *      the new {@link Point2d} of {@link GameObject}.
     */
    void setPosition(Point2D position);
    /**
     * Returns a boolean which notifies if the {@link GameObject} is destroyed.
     * @return
     *      true if {@link GameObject} is destroyed, false otherwise.
     */
    boolean isDestroyed();
    /**
     * Updates {@link GameObject} status.
     * @param elapsed
     *      time elapsed since last update.
     */
    void update(double elapsed);
    /**
     * Adds a {@link Component} to the {@link GameObject}.
     * @param component
     *      {@link Component} to be added.
     */
    void addComponent(Component component);
    /**
     * Removes a specific {@link Component} attached to the {@link GameObject}.
     * @param component
     *      {@link Component} to be removed.
     */
    void removeComponent(Component component);
    /**
     * Gets the {@link List} of all components attached to the {@link GameObject}.
     * @return
     *      the {@link List} of all components attached to the {@link GameObject}. 
     */
    List<Component> getComponents();
}
