package ballblast.view.rendering;

/**
 * Represent an object that know how to draw itself on the canvas.
 * Layer numbers are used to decide the order in which renderers will be drawn on the screen.
 * Higher layer will be drawn last.
 */
public interface Renderer extends Comparable<Renderer> {
    /**
     * Draws this object onto the canvas.
     */
    void render();
    /**
     * Sets the layer of this renderer.
     * @param layer
     *          the new layer.
     */
    void setLayer(int layer);
    /**
     * Return the layer of this renderer.
     * @return
     *          the layer.
     */
    int getLayer();

}
