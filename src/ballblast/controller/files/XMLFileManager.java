package ballblast.controller.files;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import ballblast.controller.DirectoryManager;
import ballblast.model.data.UserData;

/**
 * Class to handle xml files.
 */
public final class XMLFileManager {

    private XMLFileManager() {
        // Cannot create an XMLFileManager object.
    }

    /**
     * The getter for document to modify.
     * @param path
     *          the route path of the file.
     * @return
     *          the file converted in the document format.
     * @throws ParserConfigurationException
     *          parser exception.
     * @throws SAXException
     *          SAX exception.
     * @throws IOException
     *          IO exception.
     */
    public static Document getDocument(final String path) throws ParserConfigurationException, SAXException, IOException {
        final File file = new File(path);
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

    /**
     * Creates an empty xml file.
     * @param filePath
     *          the new file route path in the file system.
     * @throws ParserConfigurationException
     *          exception for the parser.
     * @throws TransformerException
     *          exception for the transformer.
     */
    public static void createEmptyFile(final String filePath) throws ParserConfigurationException, TransformerException {
        final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        final Document doc = docBuilder.newDocument();
        final Element rootElement = doc.createElement("players");
        doc.appendChild(rootElement);

        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        final DOMSource source = new DOMSource(doc);

        final StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source,  result);

    }

    /**
     * Creates a new xml file for the user credentials.
     * @param directoryPath
     *          the directory route path of the user file.
     * @param userName
     *          the name of the user.
     * @param password
     *          the password of the user.
     * @throws ParserConfigurationException
     *          exception for the parser.
     * @throws TransformerException
     *          exception for the transformer.
     */
    public static void createUserFile(final String directoryPath, final String userName, final String password) throws ParserConfigurationException, TransformerException {
        final String ext = ".xml";
        final String path = directoryPath + DirectoryManager.SEPARATOR + userName + ext;

        final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        final Document doc = docBuilder.newDocument();
        final Element rootElement = doc.createElement("credentials");
        doc.appendChild(rootElement);

        final Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(userName));
        rootElement.appendChild(name);

        final Element pwd = doc.createElement("password");
        pwd.setAttribute("hashCode", String.valueOf(password.hashCode()));
        pwd.appendChild(doc.createTextNode(password));
        rootElement.appendChild(pwd);

        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        final DOMSource source = new DOMSource(doc);

        final StreamResult result = new StreamResult(new File(path + ext));
        transformer.transform(source,  result);
    }

    /**
     * Add a player to the file.
     * @param player
     *          the user to add in the file.
     * @throws ParserConfigurationException
     *          parser exception.
     * @throws SAXException
     *          SAX exception.
     * @throws IOException
     *          IO exception.
     * @throws TransformerException
     *          transformer exception.
     */
    public static void addPlayer(final UserData player) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        final Document doc = getDocument(DirectoryManager.SURVIVAL_FILE);
        final Element root = doc.getDocumentElement();

        final Element user = doc.createElement("user");
        user.setAttribute("id", String.valueOf(player.getUserName().hashCode()));
        root.appendChild(user);

        final Element userName = doc.createElement("name");
        userName.appendChild(doc.createTextNode(player.getUserName()));
        user.appendChild(userName);

        final Element globalScore = doc.createElement("score");
        globalScore.appendChild(doc.createTextNode(String.valueOf(player.getGlobalScore())));
        user.appendChild(globalScore);

        final Element destroyedBalls = doc.createElement("balls");
        destroyedBalls.appendChild(doc.createTextNode(String.valueOf(player.getDestroyedBalls())));
        user.appendChild(destroyedBalls);

        final Element spawnedBullets = doc.createElement("bullets");
        spawnedBullets.appendChild(doc.createTextNode(String.valueOf(player.getSpawnedBullets())));
        user.appendChild(spawnedBullets);

        final Element playedMatches = doc.createElement("matches");
        playedMatches.appendChild(doc.createTextNode(String.valueOf(player.getMatchesPlayed())));
        user.appendChild(playedMatches);

        final Element gameTime = doc.createElement("time");
        gameTime.appendChild(doc.createTextNode(String.valueOf(player.getGameTime())));
        user.appendChild(gameTime);

        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        final DOMSource source = new DOMSource(doc);

        final StreamResult result = new StreamResult(new File(DirectoryManager.SURVIVAL_FILE));
        transformer.transform(source,  result);
    }
}
