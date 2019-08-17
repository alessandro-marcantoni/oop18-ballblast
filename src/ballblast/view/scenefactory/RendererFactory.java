package ballblast.view.scenefactory;

import ballblast.view.rendering.CanvasDrawer;
import ballblast.view.rendering.ImageSprite;
import ballblast.view.rendering.Sprite;

/**
 * 
 */
public class RendererFactory {
    private final CanvasDrawer canvasDrawer;
    // private final GameObjectVisitor<Renderer> rendererGenerator;
    /**
     * 
     * @param canvasDrawer
     *          pippo
     */
    public RendererFactory(final CanvasDrawer canvasDrawer) {
        this.canvasDrawer = canvasDrawer;
        // this.rendererGenerator
    }
    /**
     * 
     * @return
     *          a Sprite
     */
    public final Sprite createSprite() {
        final Sprite sprite = this.generateSprite();
        this.canvasDrawer.addRenderer(sprite);
        return sprite;
    }
    /**
     * 
     * @return
     *          a Sprite
     */
    protected Sprite generateSprite() {
        return new ImageSprite(this.canvasDrawer.getCanvas().getGraphicsContext2D());
    }
}
