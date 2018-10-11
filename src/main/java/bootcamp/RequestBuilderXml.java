package bootcamp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RequestBuilderXml implements RequestBuilderInterface {

    private static String getSingleTagValue ( Element element,  String tagname) {
        NodeList nodelist = element.getElementsByTagName(tagname);
        if(nodelist.getLength() == 0){
            throw new RuntimeException("Tag Not Found");
        }
        else if(nodelist.getLength() > 1){
            throw new RuntimeException("Tag not Unique");
        }
        return nodelist.item(0).getTextContent();
    }

    @Override
    public ArrayList<Request> parse(File file) throws IOException, SAXException, ParserConfigurationException {
        ArrayList <Request> results = new ArrayList<Request>();
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dcbuilder = dbfactory.newDocumentBuilder();
        Document doc = dcbuilder.parse(file);
        Element root = doc.getDocumentElement();
        NodeList requests = root.getElementsByTagName("request");

        for (int j = 0; j < requests.getLength(); j++){
            Node n = requests.item(j);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) n.getChildNodes();
                Request req = new Request(getSingleTagValue(eElement, "clientId"),
                        getSingleTagValue(eElement, "name"),
                        Long.parseLong(getSingleTagValue(eElement, "requestId")),
                        Integer.parseInt(getSingleTagValue(eElement, "quantity")),
                        Double.parseDouble(getSingleTagValue(eElement, "price")));

                results.add(req);
            }
        }
        return results;
    }
}
