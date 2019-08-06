package ballblast.model.gameobjects;

import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;

/**
 * Represent a general entity of the game.
 */
public interface GameObject {
    /**
     * Gets the {@link GameObject} height.
     * @return
     *      the height of game object.
     */
    double getHeight();
    /**
     * Gets the {@link GameObject} width.
     * @return
     *      the width of {@link GameObject}.
     */
    double getWidth();
    /**
     * Gets the GameObject position.
     * @return
     *      {@link Point2d} of {@link GameObject}.
     */
    Coordinate getPosition();
    /**
     * Sets the GameObject position.
     * @param position
     *      the new {@link Point2d} of {@link GameObject}.
     */
    void setPosition(Coordinate position);
    /**
     * Returns a boolean which notifies if the {@link GameObject} is destroyed.
     * @return
     *      true if {@link GameObject} is destroyed, false otherwise.
     */
    boolean isDestroyed();
    /**
     * Destroys the {@link GameObject}.
     */
    void destroy();
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
     * @param type
     *      {@link Component} to be removed.
     */
    void removeComponent(ComponentTypes type);
    /**
     * Gets the {@link List} of all components attached to the {@link GameObject}.
     * @return
     *      the {@link List} of all components attached to the {@link GameObject}. 
     */
    List<Component> getComponents();
    /**
     * Gets the {@link GameObjectTypes}.
     * @return
     *      the tag which specifies the {@link GameObjectTypes}.
     */
    GameObjectTypes getType();
}
