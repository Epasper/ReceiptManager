package bootcamp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RequestBuilderCsv implements RequestBuilderInterface {
    @Override
    public ArrayList<Request> parse(File file) throws IOException, SAXException, ParserConfigurationException {
        FileReader fileReader = new FileReader(file);
        ArrayList <Request> results = new ArrayList<Request>();

        try (CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : parser) {
                Request req = new Request(csvRecord.get("client_Id"),
                        csvRecord.get("name"),
                        Long.parseLong(csvRecord.get("request_Id")),
                        Integer.parseInt(csvRecord.get("quantity")),
                        Double.parseDouble(csvRecord.get("price")));

                results.add(req);
            }
        }
        return results;
    }



}
