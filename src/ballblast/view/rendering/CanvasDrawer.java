package ballblast.view.rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import ballblast.controller.Controller;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallTypes;
import ballblast.model.gameobjects.Bullet;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectFactory;
import ballblast.model.gameobjects.GameObjectTypes;
import ballblast.model.gameobjects.Player;
import ballblast.model.physics.CollisionManager;
import ballblast.model.physics.SimpleCollisionManager;
import ballblast.view.rendering.gameobject.BallRenderer;
import ballblast.view.rendering.gameobject.BulletRenderer;
import ballblast.view.rendering.gameobject.PlayerRenderer;
import ballblast.view.scenefactory.RendererFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * A canvas drawer.
 */
public class CanvasDrawer {

    private final List<Renderer> renderers;
    private final Canvas canvas;
    // private final RendererFactory factory;
    private final List<GameObject> gameObjects;
    //private final Controller controller;
    // TESTING
    private final CollisionManager manager = new SimpleCollisionManager();
    private final Ball ball = (Ball) GameObjectFactory.createBall(BallTypes.MEDIUM, 20, new Coordinate(0, 0),
                                                                new Vector2D(1, 1),  manager);
    private final Player player = (Player) GameObjectFactory.createPlayer(null, null, null, null, null, null);
    private final Bullet bullet = (Bullet) GameObjectFactory.createBullet(null, null, null);
    /**
     * @param canvas
     *          the canvas
     */
    public CanvasDrawer(final Canvas canvas) {
        this.renderers = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        this.canvas = canvas;
        //gameObjects.addAll(controller.getGameObjects());

        gameObjects.add(ball);
        gameObjects.add(player);
        gameObjects.add(bullet);
        for (GameObject object:gameObjects) {
            if (object.getType().equals(GameObjectTypes.BALL)) {
                renderers.add(new BallRenderer(generateSprite(), ball));
            } else if (object.getType().equals(GameObjectTypes.PLAYER)) {
                renderers.add(new PlayerRenderer(generateSprite(), player));
            } else if (object.getType().equals(GameObjectTypes.BULLET)) {
                renderers.add(new BulletRenderer(generateSprite(), bullet));
            }
        }
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
        final Sprite sprite = this.generateSprite();
        this.addRenderer(sprite);
        return sprite;
    }
    /**
     * 
     * @return
     *          a Sprite
     */
    protected Sprite generateSprite() {
        return new ImageSprite(this.getCanvas().getGraphicsContext2D());
    }
}
