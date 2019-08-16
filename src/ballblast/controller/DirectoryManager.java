package ballblast.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class that contains constants used to find files saved on the user home directory.
 * It is responsible of checking if the install directory exist and when necessary creates it.
 */
public final class DirectoryManager {

    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * Static field that contains the route path of the user.
     */
    public static final String APP_PATH = System.getProperty("user.home")
                                          + SEPARATOR
                                          + ".ballblast";

    /**
     * The path to the scoreboard directory.
     */
    public static final String SCOREBOARD_DIR = APP_PATH
                                                 + SEPARATOR
                                                 + "scoreboard";

    /**
     * The path to the users directory.
     */
    public static final String USERS_DIR = APP_PATH
                                           + SEPARATOR
                                           + "users";

    /**
     * Static field that contains the file of the users list.
     */
    public static final String USERS_LIST_FILE = USERS_DIR
                                                 + SEPARATOR
                                                 + ".users_list";

    /**
     * Static field that contains the file with the score of the users in the survival mode.
     */
    public static final String SURVIVAL_FILE = SCOREBOARD_DIR
                                               + SEPARATOR
                                               + "survival.bin";

    private DirectoryManager() {
        // Cannot create a DirectroyManager object.
    }

    /**
     * Static method that get user file.
     * @param userName
     *       name of the user.
     * @return
     *       the couple userName and hashcode_password of the user.
     */
    public static String getUserFile(final String userName) {
        return USERS_DIR
               + SEPARATOR
               + userName
               + ".bin";
    }

    /**
     * Sets up the application by creating app directories and utility files.
     */
    public static void setupApplication() {
        try {
            Files.createDirectories(Paths.get(SCOREBOARD_DIR));
            Files.createDirectories(Paths.get(USERS_DIR));
            if (!Files.exists(Paths.get(USERS_LIST_FILE))) {
                Files.createFile(Paths.get(USERS_LIST_FILE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
