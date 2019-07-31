package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Generic implementation of the {@link Component} interface.
 * Defines base behavior that all components share.
 */
public abstract class AbstractComponent implements Component {
    private final ComponentTypes type;
    private final GameObject parent;
    private boolean isDestroyed;

    /**
     * Create a new instance of AbstractComponent.
     * @param type
     *       the type of a specific component.
     * @param parent
     *       the {@link GameObject} to which the {@link Component} is attached.
     */
    public AbstractComponent(final ComponentTypes type, final GameObject parent) {
        this.type = type;
        this.parent = parent;
        this.isDestroyed = false;
    }

    @Override
    public abstract void update(double elapsed);

    @Override
    public final boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public final ComponentTypes getComponentType() {
        return type;
    }

    @Override
    public final GameObject getParent() {
        return this.parent;
    }
}
