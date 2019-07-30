package ballblast.model.gameobjects;

import java.util.List;

import com.google.common.collect.ImmutableList;

import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;
import ballblast.utils.Point2D;

/**
 * Generic implementation of the {@link GameObject} interface.
 * Defines base behavior that all game objects share.
 *
 */
public class AbstractGameObject implements GameObject {
    private final GameObjectTypes type;
    private int height;
    private int width;
    private Point2D position;
    private boolean isDestroyed;
    private ImmutableList<Component> components;

    /**
     * Creates a AbstractGameObject instance.
     * @param type
     *     the type of {@link GameObject}.
     */
    public AbstractGameObject(final GameObjectTypes type) {
        this.type = type;
        this.isDestroyed = false;
    }

    @Override
    public final int getHeight() {
        return this.height;
    }

    @Override
    public final int getWidth() {
        return this.width;
    }

    @Override
    public final void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public final void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public final Point2D getPosition() {
        return this.position;
    }

    @Override
    public final void setPosition(final Point2D position) {
        this.position = position;
    }

    @Override
    public final boolean isDestroyed() {
        return this.isDestroyed;
    }

    @Override
    public final void update(final double elapsed) {
        components.forEach(c -> c.update(elapsed));
    }

    @Override
    public final void addComponent(final Component component) {
        this.components = ImmutableList.<Component>builder()
                .addAll(this.components)
                .add(component)
                .build();
    }

    @Override
    public final void removeComponent(final ComponentTypes type) {
        this.components = this.components.stream()
                .filter(c -> c.getComponentType() != type)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public final List<Component> getComponents() {
        return ImmutableList.copyOf(this.components);
    }

    @Override
    public final GameObjectTypes getType() {
        return this.type;
    }

}
