package ballblast.view.rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import ballblast.view.scenefactory.RendererFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * A canvas drawer.
 */
public class CanvasDrawer {

    private final List<Renderer> renderers;
    private final Canvas canvas;
    private final RendererFactory factory;
    /**
     * @param canvas
     *          the canvas
     */
    public CanvasDrawer(final Canvas canvas) {
        this.renderers = new ArrayList<>();
        this.canvas = canvas;
        this.factory = new RendererFactory(this);
    }
    /**
     * 
     */
    public void draw() {
        //this.getRenderers().forEach(Renderer::render);
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
        return this.factory;
    }
    /**
     * 
     * @return
     *          the canvas.
     */
    public Canvas getCanvas() {
        return this.canvas;
    }
}
