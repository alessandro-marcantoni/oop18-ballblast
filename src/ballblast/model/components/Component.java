package ballblast.model.components;
/**
 * Interface used to implement Composite pattern.
 */
public interface Component {
    /**
     * Updates component status.
     * @param elapsed
     *        Time elapsed since last update.
     */
    void update(double elapsed);
    /**
     * Returns a boolean which notifies if the component is destroyed.
     * @return
     *      True if component is destroyed, false otherwise.
     */
    boolean isDestroyed();
    /**
     * @return
     *      Return an enum which defines the component type.
     */
    ComponentTypes getComponentType();
}
