package ballblast.controller.files;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import ballblast.controller.DirectoryManager;
import ballblast.model.data.UserData;
import ballblast.settings.Framerates;
import ballblast.settings.KeyCodeSet;

/**
 * The manager for handling {@link UserData}s.
 */
public class UserManager {

    /**
     * Tries to login a user.
     * 
     * @param userName the user name.
     * @param password the user password.
     * @return an {@link Optional} {@link UserData} object, empty if the login
     *         failed.
     * @throws ParserConfigurationException Parser exception.
     * @throws SAXException                 SAX exception.
     * @throws IOException                  IO exception.
     * @throws XPathExpressionException     XPathExpression exception
     */
    public Optional<UserData> login(final String userName, final String password)
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        if (Files.exists(Paths.get(DirectoryManager.getUserFile(userName)))
                && XMLFileManager.checkUserPassword(userName, password)) {
            try {
                return Optional.of(this.load(userName));
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Tries to register a new user.
     * 
     * @param userName the user name.
     * @param password the user password.
     * @return an {@link Optional} {@link UserData} object, empty if the
     *         registration failed.
     * @throws ParserConfigurationException Parser exception.
     * @throws IOException                  IO exception.
     * @throws TransformerException         Transformer exception.
     * @throws SAXException                 SAX exception.
     */
    public Optional<UserData> register(final String userName, final String password)
            throws ParserConfigurationException, IOException, TransformerException, SAXException {
        if (Files.exists(Paths.get(DirectoryManager.getUserFile(userName)))) {
            // UserName already exists -> choose an other one.
            return Optional.empty();
        } else {
            XMLFileManager.submitUser(userName, password);
            final UserData user = new UserData();
            user.setName(userName);
            user.setFramesPerSecond(Framerates.FPS_30.getFPS());
            user.setKeySetting(KeyCodeSet.SET_ONE.toString());
            try {
                this.save(user);
                return Optional.of(user);
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
    }

    /**
     * Updates the user game datas at the end of a game session.
     * 
     * @param user the {@link UserData} to update
     * @return true if save operation done successfully, false otherwise.
     */
    public boolean updateUserData(final UserData user) {
        try {
            save(user);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void save(final UserData user) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(DirectoryManager.getUserFile(user.getName()));
                XMLEncoder encoder = new XMLEncoder(fos)) {
            encoder.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UserData load(final String userName) throws IOException {
        try (FileInputStream fis = new FileInputStream(DirectoryManager.getUserFile(userName));
                XMLDecoder decoder = new XMLDecoder(fis)) {
            return (UserData) decoder.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
