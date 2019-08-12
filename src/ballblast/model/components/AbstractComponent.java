package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Generic implementation of the {@link Component} interface.
 * Defines base behavior that all components share.
 */
public abstract class AbstractComponent implements Component {
    private final ComponentTypes type;
    private GameObject parent;
    private boolean isEnable;

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

    @Override
    public final void enable() {
        this.isEnable = true;
    }

    @Override
    public final void disable() {
        this.isEnable = false;
    }

    @Override
    public final boolean isEnabled() {
        return this.isEnable;
    }

    @Override
    public final ComponentTypes getComponentType() {
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
}
