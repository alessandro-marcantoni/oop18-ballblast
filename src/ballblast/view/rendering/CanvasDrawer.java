package ballblast.view.rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableBiMap;

import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.view.rendering.gameobject.RendererFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

/**
 * A canvas drawer.
 */
public class CanvasDrawer {
    private static final ImmutableBiMap<GameObjectTypes, Function<Pair<Sprite, GameObject>, Renderer>> RENDERER_MAP;
    private final List<Renderer> renderers;
    private final Canvas canvas;

    static {
        RENDERER_MAP = ImmutableBiMap.of(
                GameObjectTypes.BALL,   p -> RendererFactory.createBallRenderer(p.getKey(), p.getValue()),
                GameObjectTypes.BULLET, p -> RendererFactory.createBulletRenderer(p.getKey(), p.getValue()),
                GameObjectTypes.PLAYER, p -> RendererFactory.createPlayerRenderer(p.getKey(), p.getValue()),
                GameObjectTypes.WALL, p -> RendererFactory.createWallRenderer(p.getKey(), p.getValue())
        );
    }
    /**
     * @param canvas
     *          the canvas
     */
    public CanvasDrawer(final Canvas canvas) {
        this.renderers = new ArrayList<>();
        this.canvas = canvas;
        //TODO.
        final List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.forEach(g -> this.renderers.add(this.getRenderer(g)));
    }
    /**
     * 
     */
    public void draw() {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.getRenderers().forEach(r -> {
            gc.save();
            r.render();
            gc.restore();
        });
    }
    /**
     * 
     * @param render
     *          The render
     */
    public final void addRenderer(final Renderer render) {
        this.renderers.add(render);
        Collections.sort(this.renderers);
    }
    /**
     * 
     * @param render
     *          The render
     */
    public final void removeRenderer(final Renderer render) {
        this.renderers.remove(render);
    }
    /**
     * 
     * @return
     *          The renderers.
     */
    protected Stream<Renderer> getRenderers() {
        return new ArrayList<>(this.renderers).stream();
    }
    /**
     * 
     * @return
     *          the renderer factory.
     */
    public RendererFactory getRendererFactory() {
        //return this.factory;
        return null;
    }
    /**
     * 
     * @return
     *          the canvas.
     */
    public Canvas getCanvas() {
        return this.canvas;
    }
    /**
     * 
     * @return
     *          a Sprite
     */
    public final Sprite createSprite() {
        final Sprite sprite = generateSprite();
        this.addRenderer(sprite);
        return sprite;
    }

    private Sprite generateSprite() {
        return new ImageSprite(this.canvas.getGraphicsContext2D());
    }

    private Renderer getRenderer(final GameObject gameObject) {
        final Pair<Sprite, GameObject> p = new Pair<>(this.generateSprite(), gameObject);
        return RENDERER_MAP.get(gameObject.getType()).apply(p);
    }
}
