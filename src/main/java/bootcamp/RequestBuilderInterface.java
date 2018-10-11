package bootcamp;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface RequestBuilderInterface {

    public ArrayList<Request> parse (File file) throws IOException, SAXException, ParserConfigurationException;

}
