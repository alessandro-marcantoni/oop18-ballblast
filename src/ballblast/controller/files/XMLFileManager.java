package ballblast.controller.files;

import java.io.File;

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

import ballblast.controller.DirectoryManager;

/**
 * Class to handle xml files.
 */
public final class XMLFileManager {

    private XMLFileManager() {
        // Cannot create an XMLFileManager object.
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
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("players");
        doc.appendChild(rootElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File(filePath));
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
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("credentials");
        doc.appendChild(rootElement);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(userName));
        rootElement.appendChild(name);

        Element pwd = doc.createElement("password");
        pwd.setAttribute("hashCode", String.valueOf(password.hashCode()));
        pwd.appendChild(doc.createTextNode(password));
        rootElement.appendChild(pwd);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File(directoryPath + DirectoryManager.SEPARATOR + userName + ".xml"));
        transformer.transform(source,  result);
    }
}
