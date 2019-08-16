package ballblast.view.rendering;

/**
 * Provides a partial implementation of the {@link Renderer} interface that stores layer. 
 */
public abstract class AbstractRenderer implements Renderer {

    private int renderLayer;

    @Override
    public final int compareTo(final Renderer other) {
        return Integer.compare(this.getLayer(), other.getLayer());
    }

    @Override
    public final void setLayer(final int layer) {
        this.renderLayer = layer;
    }

    @Override
    public final int getLayer() {
        return this.renderLayer;
    }

}
