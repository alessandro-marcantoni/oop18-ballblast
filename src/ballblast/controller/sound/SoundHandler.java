package ballblast.controller.sound;

import java.util.List;

/**
 * Interface representing an handler of {@link EventTypes}.
 */
@FunctionalInterface
public interface SoundHandler {

    /**
     * Handles an {@link SoundTypes}.
     * 
     * @param sound The {@link SoundtTypes} to be handled.
     */
    void handleSound(SoundTypes sound);

    /**
     * Handles all the available {@link SoundTypes}.
     * 
     * @param sounds The list of {@link SoundTypes}.
     */
    default void handleAll(List<SoundTypes> sounds) {
        sounds.forEach(this::handleSound);
    }

}
