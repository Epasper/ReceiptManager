package BootCamp;

import javafx.application.Application;
import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class App {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {


        RequestDB requestDB = new RequestDB();


        for (int i = 0; i < args.length; i++) {
            File file = new File(args[i]);
            String extension = FilenameUtils.getExtension(args[i]).toLowerCase();

            ArrayList<Request> result = RequestBuilderFactory.getBuilder(extension).parse(file);
            requestDB.addRequests(result);

        }

        requestDB.getReportGenerator().clientSumOfPrices(1).print(new ReportScreenPrinter());

        Application.launch(FXScene.class, args);
    }
}
