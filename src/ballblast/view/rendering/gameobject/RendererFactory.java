package ballblast.view.rendering.gameobject;

import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.Bullet;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.Player;
import ballblast.view.rendering.Renderer;
import ballblast.view.rendering.Sprite;
/**
 * Represents a factory used to instantiate new {@link Renderer}s.
 */
public final class RendererFactory {
    private RendererFactory() { }
    /**
     * Creates a {@link Player} {@link Renderer}.
     * @param sprite            the {@link Sprite}.
     * @param gameObject        the {@link GameObject} to be rendered.
     * @return                  the {@link Renderer} used to render a {@link Player}.
     */
    public static Renderer createPlayerRenderer(final Sprite sprite, final GameObject gameObject) {
        return new PlayerRenderer(sprite, (Player) gameObject);
    }
    /**
     * Creates a {@link Bullet} {@link Renderer}.
     * @param sprite            the {@link Sprite}.
     * @param gameObject        the {@link GameObject} to be rendered.
     * @return                  the {@link Renderer} used to render a {@link Bullet}.
     */
    public static Renderer createBulletRenderer(final Sprite sprite, final GameObject gameObject) {
        return new BulletRenderer(sprite, (Bullet) gameObject);
    }
    /**
     * Creates a {@link Ball} {@link Renderer}.
     * @param sprite            the {@link Sprite}.
     * @param gameObject        the {@link GameObject} to be rendered.
     * @return                  the {@link Renderer} used to render a {@link Ball}.
     */
    public static Renderer createBallRenderer(final Sprite sprite, final GameObject gameObject) {
        return new BallRenderer(sprite, (Ball) gameObject);
    }

    /*public static Renderer createWallRenderer(final Sprite s, final GameObject g) {
        return new WallRenderer(s, (Wall) g);
    }*/
}