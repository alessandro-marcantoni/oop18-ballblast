package ballblast.view.rendering;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
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
    private static final Map<GameObjectTypes, Function<Pair<Sprite, GameObject>, Renderer>> RENDERER_MAP;
    private final Canvas canvas;

    static {
        RENDERER_MAP = ImmutableMap.of(
                GameObjectTypes.BALL,   p -> {
                    try {
                        return RendererFactory.createBallRenderer(p.getKey(), p.getValue());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                },
                GameObjectTypes.BULLET, p -> {
                    try {
                        return RendererFactory.createBulletRenderer(p.getKey(), p.getValue());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                },
                GameObjectTypes.PLAYER, p -> {
                    try {
                        return RendererFactory.createPlayerRenderer(p.getKey(), p.getValue());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                },
                GameObjectTypes.WALL, p -> {
                    try {
                        return RendererFactory.createWallRenderer(p.getKey(), p.getValue());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
    }
    /**
     * @param canvas
     *          the canvas
     */
    public CanvasDrawer(final Canvas canvas) {
        this.canvas = canvas;
    }
    /**
     * @param gameObjects
     *          gameObjects
     */
    public void draw(final List<GameObject> gameObjects) {
        final GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.convertToRenderers(gameObjects).forEach(r -> {
            gc.save();
            r.render();
            gc.restore();
        });
    }
    /**
     * 
     * @param gameObjects
     * @return
     */
    private List<Renderer> convertToRenderers(final List<GameObject> gameObjects) {
        return gameObjects.stream().map(this::getRenderer).collect(ImmutableList.toImmutableList());
    }
    /**
     * @return
     *          the canvas.
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    private Sprite generateSprite(final GameObject gameObject) {
        return new ImageSprite(this.canvas.getGraphicsContext2D(), gameObject);
    }

    private Renderer getRenderer(final GameObject gameObject) {
        return RENDERER_MAP.get(gameObject.getType()).apply(new Pair<>(this.generateSprite(gameObject), gameObject));
    }
}
