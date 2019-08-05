package ballblast.model.gameobjects;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import com.google.common.collect.ImmutableList;
import ballblast.model.components.Component;
import ballblast.model.components.ComponentTypes;
/**
 * Generic implementation of the {@link GameObject} interface.
 * Defines base behavior that all game objects share.
 *
 */
public abstract class AbstractGameObject implements GameObject {
    private static final Coordinate ZERO = new Coordinate(0, 0);
    private final GameObjectTypes type;
    private Coordinate position;
    private boolean isDestroyed;
    private ImmutableList<Component> components;
    private double width;
    private double height;

    /**
     * Creates a AbstractGameObject instance.
     * @param type
     *     the type of {@link GameObject}.
     */
    protected AbstractGameObject(final GameObjectTypes type) {
        this.type = type;
        this.isDestroyed = false;
        this.components = ImmutableList.of();
        this.position = ZERO;
    }
    /**
     * Sets the {@link GameObject}'s height.
     * @param height
     *     the height of the {@link GameObject}.
     */
    protected void setHeight(final double height) {
        this.height = height;
    }
    /**
     * Sets the {@link GameObject}'s width.
     * @param width
     *     the width of the {@link GameObject}.
     */
    protected void setWidth(final double width) {
        this.width = width;
    }

    @Override
    public final double getWidth() { 
        return this.width; 
    }
    @Override
    public final double getHeight() { 
        return this.height;
    }

    @Override
    public final Coordinate getPosition() {
        return this.position;
    }

    @Override
    public final void setPosition(final Coordinate position) {
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
        component.setParent(this);
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
        private final A gameObject;
        private final B builder;
        /**
         * Creates an AbstractBuilder instance.
         */
        protected AbstractBuilder() {
            this.gameObject = initGameObject();
            this.builder = getBuilder();
        }
        /**
         * Gets the {@link GameObject} to be set.
         * @return
         *     the {@link GameObject} to be set.
         */
        protected A getGameObject() {
            return this.gameObject;
        }
        /**
         * Gets the initial {@link GameObject} to be set.
         * @return
         *     the initial {@link GameObject} to be set.
         */
        protected abstract A initGameObject();
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
         * Sets the {@link GameObject} position.
         * @param position
         *     the {@link GameObject} {@link Coordinate} position.
         * @return
         *     the concrete {@link AbstractBuilder}.
         */
        public B setPosition(final Coordinate position) {
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
