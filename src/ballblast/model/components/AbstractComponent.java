package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Generic implementation of the {@link Component} interface.
 * Defines base behavior that all components share.
 */
public abstract class AbstractComponent implements Component {
    private final ComponentTypes type;
    private GameObject parent;
    private boolean isAvailable;
    /**
     * Create a new instance of AbstractComponent.
     * @param type 
     *     the type of a specific component.
     */
    public AbstractComponent(final ComponentTypes type) {
        this.type = type;
    }

    @Override
    public abstract void update(double elapsed);
    /** {@inheritDoc} */
    @Override
    public void enable() {
        this.isAvailable = true;
    }
    /** {@inheritDoc} */
    @Override
    public void disable() {
        this.isAvailable = false;
    }

    @Override
    public final ComponentTypes getType() {
        return type;
    }

    @Override 
    public final void setParent(final GameObject parent) {
        this.parent = parent;
    }

    @Override
    public final GameObject getParent() {
        return this.parent;
    }
    /**
     * Returns a boolean which notifies if the Component is enabled.
     * @return
     *     true if Component is enabled, false otherwise.
     */
    protected final boolean isEnabled() {
        return this.isAvailable;
    }
}
