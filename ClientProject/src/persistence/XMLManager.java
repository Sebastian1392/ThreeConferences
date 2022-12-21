package persistence;

import models.TagConstants;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLManager {

    private static final String NAME_NODE_TAG = "NodeName";
  
    public static ArrayList<String[]> readConferenceTree(String treeInString) {
        ArrayList<String[]> data = new ArrayList<>();
        Document document = convertStringToXMLDocument(treeInString);
        document.getDocumentElement().normalize();
        selectNodes(data,"",document);
        return data;
    }

    public static void selectNodes(ArrayList<String[]> data,String fatherName,Node node) {
        String dataName = "";
        switch (node.getNodeName()) {
            case TagConstants.ROOT_TAG:
            case TagConstants.TOPIC_TAG:
            case TagConstants.SUB_TOPIC_TAG:
            case TagConstants.CONFERENCE_TAG:
            case TagConstants.LECTURER_TAG:
            case TagConstants.ASSISTANT_TAG:
                dataName = getElements(node);
                data.add(new String[]{node.getNodeName(),fatherName,dataName});
            break;
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            selectNodes(data,dataName,list.item(i));
        }
    }

    private static String getElements(Node node){
        Element element = (Element) node;
        String name = element.getElementsByTagName(NAME_NODE_TAG).item(0).getTextContent();
        return name;
    }

    private static Document convertStringToXMLDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try{
            builder = factory.newDocumentBuilder();
             
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
