package ballblast.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ballblast.model.data.GameDataManager.GameData;
import ballblast.model.data.Leaderboard;
import ballblast.model.data.UserData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager.PlayerTags;
import ballblast.model.inputs.InputTypes;

/**
 * Represents the Controller in the MVC pattern.
 */
public interface Controller {

    /**
     * Starts a new survival mode game.
     */
    void startSurvivalMode();

    /**
     * Pauses the game.
     */
    void pauseGame();

    /**
     * Resumes the paused game.
     */
    void resume();

    /**
     * Ends the game.
     */
    void notifyGameOver();

    /**
     * Sends an input to be resolved the next update by the right {@link Player} to
     * the {@link Controller}.
     * 
     * @param tag   the tag which identifies the {@link Player}.
     * @param input the input to be resolved.
     */
    void receiveInput(PlayerTags tag, InputTypes input);

    /**
     * Returns the list of active {@link GameObjects} to be rendered.
     * 
     * @return A list of {@link GameObject}.
     */
    List<GameObject> getGameObjects();

    /**
     * Gets the game data (score, time, destroyed balls ecc..).
     * 
     * @return The {@link GameData}.
     */
    GameData getGameData();

    /**
     * Checks if login is successful.
     * 
     * @param username The user name.
     * @param password The password.
     * @return True if the login is successful. False otherwise.
     * @throws IOException 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     */
    boolean checkLoginUser(String username, String password) throws ParserConfigurationException, SAXException, IOException;
    /**
     * Checks if register is successful.
     * 
     * @param username The user name.
     * @param password The password.
     * @return True if the register is successful. False otherwise.
     * @throws SAXException 
     * @throws TransformerException 
     * @throws IOException 
     * @throws ParserConfigurationException 
     */
    boolean checkRegisterUser(String username, String password) throws ParserConfigurationException, IOException, TransformerException, SAXException;

    /**
     * 
     * @return The current user.
     */
    UserData getCurrentUser();

    /**
     * Returns the {@link Leaderboard}.
     * 
     * @return the {@link Leaderboard}.
     */
    Leaderboard getLeaderboard();
}
