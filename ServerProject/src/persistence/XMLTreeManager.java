package persistence;

import models.IOConference;
import models.IPermission;
import servers.NodeTree;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLTreeManager {

    private static final String DOCUMENT_TAG = "DocumentRoot";
    public static final String OPTIONS_TAG = "Options";
    public static final String OPTION_TAG = "Option";
    private static final String NAME_NODE_TAG = "NodeName";

    public static String writeConferencesTree(NodeTree<IOConference> rootTree) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootDocument = document.createElement(DOCUMENT_TAG);
            document.appendChild(rootDocument);
            createTagsConference(document, rootDocument, rootTree);
            return writeXmlDocumentToXmlFile(document);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String writePermissionsTree(NodeTree<IPermission> rootTree) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootDocument = document.createElement(DOCUMENT_TAG);
            document.appendChild(rootDocument);
            createTagsPermissions(document, rootDocument, rootTree);
            return writeXmlDocumentToXmlFile(document);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createTagsConference(Document document, Element baseDocument, NodeTree<IOConference> base) {
        Element conferenceBase = document.createElement(base.getData().getTag());
        Element nameConferenceBase = document.createElement(NAME_NODE_TAG);
        nameConferenceBase.setTextContent(base.getData().getName());
        conferenceBase.appendChild(nameConferenceBase);
        // Element optionsConferenceBase = document.createElement(OPTIONS_TAG);
        // for (int i = 0; i < base.getData().getOptions().length; i++) {
        //     Element option = document.createElement(OPTION_TAG);
        //     option.setTextContent(base.getData().getOptions()[i].toString());
        //     optionsConferenceBase.appendChild(option);
        // }
        // conferenceBase.appendChild(optionsConferenceBase);
        baseDocument.appendChild(conferenceBase);
        for (NodeTree<IOConference> child : base.getChildren()) {
            createTagsConference(document, conferenceBase, child);
        }
    }

    private static void createTagsPermissions(Document document, Element baseDocument, NodeTree<IPermission> base) {
        Element conferenceBase = document.createElement(base.getData().getTag());
        Element nameConferenceBase = document.createElement(NAME_NODE_TAG);
        nameConferenceBase.setTextContent(base.getData().getName());
        conferenceBase.appendChild(nameConferenceBase);
        // Element optionsConferenceBase = document.createElement(OPTIONS_TAG);
        // for (int i = 0; i < base.getData().getOptions().length; i++) {
        //     Element option = document.createElement(OPTION_TAG);
        //     option.setTextContent(base.getData().getOptions()[i].toString());
        //     optionsConferenceBase.appendChild(option);
        // }
        // conferenceBase.appendChild(optionsConferenceBase);
        baseDocument.appendChild(conferenceBase);
        for (NodeTree<IPermission> child : base.getChildren()) {
            createTagsPermissions(document, conferenceBase, child);
        }
    }

    public static String writeXmlDocumentToXmlFile(Document xmlDocument){
        String xmlString = "";
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
            xmlString = writer.getBuffer().toString();   
        }catch (TransformerException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return xmlString;
    }
}
