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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ballblast.controller.DirectoryManager;

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
     * Check if the input password of the user is correct.
     * @param userName
     *          the user name.
     * @param pwd
     *          the password to verify.
     * @return
     *          true if the password is correct, false otherwise.
     * @throws ParserConfigurationException
     *          parser exception.
     * @throws SAXException
     *          SAX exception.
     * @throws IOException
     *          IO exception.
     */
    public static boolean checkUserPassword(final String userName, final String pwd) throws ParserConfigurationException, SAXException, IOException {
        final Document doc = getDocument(DirectoryManager.getUserFile(userName));
        final Node root = doc.getFirstChild();
        final Node obj = root.getChildNodes().item(1);

        final NodeList property = obj.getChildNodes();
        for (int i = 0; i < property.getLength() - 1; i++) {
                i++;
                final Node field = property.item(i);
                if (field.getAttributes().item(0).getNodeValue().equals("password")) {
                        final Node password = field.getChildNodes().item(1);
                        if (password.getTextContent().equals(pwd)) {
                                return true;
                        }
                }
        }
        return false;
    }

    /**
     * Creates an empty xml file.
     * @param filePath
     *          the new file route path in the file system.
     * @param rootName
     *          the name of the xml file root. 
     * @throws ParserConfigurationException
     *          exception for the parser.
     * @throws TransformerException
     *          exception for the transformer.
     */
    public static void createEmptyFile(final String filePath, final String rootName) throws ParserConfigurationException, TransformerException {
        final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        final Document doc = docBuilder.newDocument();
        final Element rootElement = doc.createElement(rootName);
        doc.appendChild(rootElement);

        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        final DOMSource source = new DOMSource(doc);

        final StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source,  result);

    }

    /**
     * Add a player to the file.
     * @param userName
     *          the user name to add in the file.
     * @param password
     *          the password associated with the user.
     * @throws ParserConfigurationException
     *          parser exception.
     * @throws IOException
     *          IO exception.
     * @throws TransformerException
     *          transformer exception.
     * @throws SAXException 
     *          SAX exception.
     */
    public static void submitUser(final String userName, final String password) throws ParserConfigurationException, IOException, TransformerException, SAXException  {
        int countUsers;
        final Document doc = getDocument(DirectoryManager.USERS_LIST_FILE);
        final Element root = doc.getDocumentElement();

        if (!root.hasChildNodes()) {
            countUsers = 1;
        } else {
            final Element lastUser = (Element) root.getLastChild();
            countUsers = Integer.valueOf(lastUser.getAttribute("id")) + 1;
        }

        final Element user = doc.createElement("user");
        user.setAttribute("id", String.valueOf(countUsers));
        root.appendChild(user);

        final Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(userName));
        user.appendChild(name);

        final Element pwd = doc.createElement("password");
        pwd.appendChild(doc.createTextNode(password));
        user.appendChild(pwd);

        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        final DOMSource source = new DOMSource(doc);

        final StreamResult result = new StreamResult(new File(DirectoryManager.USERS_LIST_FILE));
        transformer.transform(source,  result);
    }
}
