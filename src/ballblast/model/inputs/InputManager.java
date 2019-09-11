package ballblast.model.inputs;

import java.util.List;

import ballblast.model.components.InputComponent;

/**
 * Manages inputs and redirects them to the right {@link Player} thanks {@link InputComponent}.
 */
public interface InputManager {

    /**
     * Adds a inputHandler for a new {@link Player}.
     * 
     * @param tag            the tag identifies a specific {@link Player}.
     * @param inputComponent the {@link InputComponent} of a specific
     *                       {@link Player}.
     */
    void addInputHandler(PlayerTags tag, InputComponent inputComponent);

    /**
     * Removes a inputHandler associated with a specific {@link PlayerTags}.
     * 
     * @param tag the tag which identifies the inputhandler to be removed.
     */
    void removeInputHandler(PlayerTags tag);

    /**
     * Translates received inputs into {@link Command}s and sends them to the right
     * {@link InputComponent}.
     * 
     * @param tag    the tag which identifes the right {@link Player}.
     * @param inputs the inputs to be translated.
     */
    void processInputs(PlayerTags tag, List<InputTypes> inputs);

    /**
     * All possible {@link Player}s.
     */
    enum PlayerTags {
        /**
         * First.
         */
        FIRST,
        /**
         * Second.
         */
        SECOND;
    }
}
