package ballblast.settings;

/**
 * 
 * 
 */
public enum FpsSet {
    /**
     * 25 FPS.
     */
    FPS_25("25", 40),
    /**
     * 30 FPS.
     */
    FPS_30("30", 33),
    /**
     * 60 FPS.
     */
    FPS_60("60", 17),
    /**
     * 120 FPS.
     */
    FPS_120("120", 8);

    private final String fps;
    private final long period;

    /**
     * 
     * @param fps    the selected fps.
     * @param period the period used to generate selected fps.
     */
    FpsSet(final String fps, final long period) {
        this.fps = fps;
        this.period = period;
    }

    /**
     * 
     * @return selected FPS.
     */
    public String getFPS() {
        return this.fps;
    }

    /**
     * 
     * @return period to generate selected FPS.
     */
    public long getPeriod() {
        return this.period;
    }
}
