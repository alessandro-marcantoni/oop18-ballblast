package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Generic implementation of the {@link Component} interface.
 * Defines base behavior that all components share.
 */
public abstract class AbstractComponent implements Component {
    private final ComponentTypes type;
    @SuppressWarnings("unused")
    private final GameObject parent;
    private boolean isDestroyed;

    /**
     * Constructor for class AbstractComponent.
     * @param type
     *        It's the type of a specific component.
     * @param parent
     *        It's the game object to which the component is attached.
     */
    public AbstractComponent(final ComponentTypes type, final GameObject parent) {
        this.type = type;
        this.parent = parent;
        this.isDestroyed = false;
    }

    @Override
    public void update(final double elapsed) {
        // TODO implemented by sub-classes
    }

    @Override
    public final boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public final ComponentTypes getComponentType() {
        return type;
    }

    @SuppressWarnings("unused")
    private void destroy() {
        this.isDestroyed = true;
    }
}
