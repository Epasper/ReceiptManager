package bootcamp;

import javafx.application.Application;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.*;


public class App {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {

        Application.launch(FXScene.class, args);
    }
}
