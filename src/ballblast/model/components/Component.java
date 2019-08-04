package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Interface used to implement Composite pattern.
 */
public interface Component {
    /**
     * Updates Component status.
     * @param elapsed
     *     the time elapsed since last update.
     */
    void update(double elapsed);
    /**
     * Returns a boolean which notifies if the Component is destroyed.
     * @return
     *     true if Component is destroyed, false otherwise.
     */
    boolean isDestroyed();
    /**
     * @return
     *     the tag which defines the {@link ComponentType}.
     */
    ComponentTypes getComponentType();
    /**
     * Sets the {@link Component}'s parent.
     * @param parent
     *     the {@link GameObject} attached to the {@link Component}. 
     */
    void setParent(GameObject parent);
    /**
     * @return
     *     the {@link GameObject} attached to the {@link Component}.
     */
    GameObject getParent();
}
