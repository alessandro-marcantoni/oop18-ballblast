package ballblast.controller.sound;

import java.util.List;

/**
 * Interface representing an handler of {@link EventTypes}.
 */
@FunctionalInterface
public interface SoundHandler {

    /**
     * Handles an {@link EventTypes}.
     * 
     * @param event The {@link EventTypes} to be handled.
     */
    void handleSound(SoundTypes event);

    /**
     * Handles all the available {@link EventTypes}.
     * 
     * @param events The list of {@link EventTypes}.
     */
    default void handleAll(List<SoundTypes> events) {
        events.forEach(this::handleSound);
    }

}
