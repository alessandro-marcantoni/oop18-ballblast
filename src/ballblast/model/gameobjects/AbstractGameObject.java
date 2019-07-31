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
public abstract class AbstractGameObject implements GameObject {
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
    protected AbstractGameObject(final GameObjectTypes type) {
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

    /**
     * Generic builder class for {@link GameObject} creation.
     * @param <A>
     *     the {@link GameObject} to be built.
     * @param <B>
     *     the concrete {@link AbstractBuilder} to be used.
     */
    protected abstract static class AbstractBuilder<A extends AbstractGameObject, B extends AbstractBuilder<?, ?>> {
        /**
         * The initial {@link GameObject} to be set.
         */
        protected final A gameObject;
        private final B builder;
        /**
         * Creates an AbstractBuilder instance.
         */
        protected AbstractBuilder() {
            this.gameObject = getGameObject();
            this.builder = getBuilder();
        }
        /**
         * Gets the {@link GameObject} to be set.
         * @return
         *     the {@link GameObject} to be set.
         */
        protected abstract A getGameObject();
        /**
         * Gets the concrete {@link AbstractBuilder}.
         * @return
         *     the concrete {@link AbstractBuilder}.
         */
        protected abstract B getBuilder();
        /**
         * Gets the set {@link GameObject}.
         * @return
         *     the set {@link GameObject}.
         */
        public A build() {
            return this.gameObject;
        }
        /**
         * Sets the {@link GameObject} height.
         * @param height
         *     the {@link GameObject} height.
         * @return
         *     the concrete {@link AbstractBuilder}.
         */
        public B setHeight(final int height) {
            this.gameObject.setHeight(height);
            return this.builder;
        }
        /**
         * Sets the {@link GameObject} width.
         * @param width
         *     the {@link GameObject} width.
         * @return
         *     the concrete {@link AbstractBuilder}.
         */
        public B setWidth(final int width) {
            this.gameObject.setWidth(width);
            return this.builder;
        }
        /**
         * Sets the {@link GameObject} position.
         * @param position
         *     the {@link GameObject} {@link Point2D} position.
         * @return
         *     the concrete {@link AbstractBuilder}.
         */
        public B setPostion(final Point2D position) {
            this.gameObject.setPosition(position);
            return this.builder;
        }
        /**
         * Adds a {@link Component} inside the {@link GameObject}.
         * @param component
         *     the {@link Component} to be added.
         * @return
         *     the concrete {@link AbstractBuilder}.
         */
        public B addComponent(final Component component) {
            this.gameObject.addComponent(component);
            return this.builder;
        }
    }
}
