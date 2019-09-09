package ballblast.commons.events;

import java.util.List;

/**
 * Interface representing an handler of {@link EventTypes}.
 */
@FunctionalInterface
public interface EventHandler {

    /**
     * Handles an {@link EventTypes}.
     * 
     * @param event The {@link EventTypes} to be handled.
     */
    void handleEvent(EventTypes event);

    /**
     * Handles all the available {@link EventTypes}.
     * 
     * @param events The list of {@link EventTypes}.
     */
    default void handleAll(List<EventTypes> events) {
        events.forEach(this::handleEvent);
    }

}
