package BootCamp;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FXScene extends Application {

    int buttonWidth = 500;
    List<String> printLoadedFiles;
    GridPane mainGrid = new GridPane();
    Scene startingScene = new Scene(mainGrid, 420, 500);
    Button totalRequestButton = new Button("Generate a Total Request Report");
    Button totalRequestsSingleUserButton = new Button("Generate a Total Request Report for a Single Customer");
    Button sumOfPricesButton = new Button("Sum of All Prices");
    Button sumOfPricesSingleUserButton = new Button("Sum of All Prices for a Single Customer");
    Button listOfRequestButton = new Button("List of All Requests");
    Button listOfRequestSingleUserButton = new Button("List of All Requests for a Single Customer");
    Button averageAllPricesButton = new Button("Average of All Priices");
    Button getAverageAllPricesSingleUserButton = new Button("Average of All Prices for Single User");
    Button totalRequestSaveAs = new Button("\uD83D\uDCBE");
    Button totalRequestsSingleUserSaveAs = new Button("Save As");
    Button sumOfPricesSaveAs = new Button("Save As");
    Button sumOfPricesSingleUserSaveAs = new Button("Save As");
    Button listOfRequestSaveAs = new Button("Save As");
    Button listOfRequestSingleUserSaveAs = new Button("Save As");
    Button averageAllPricesSaveAs = new Button("Save As");
    Button getAverageAllPricesSingleUserSaveAs = new Button("Save As");
    TextField resultsTxtField = new TextField();
    FileChooser saveAsFileChooser = new FileChooser();
    TextInputDialog inputDialog = new TextInputDialog();



    RequestDB requestDB = new RequestDB();

    @Override
    public void init() throws SQLException, ParserConfigurationException, SAXException, IOException {

        Parameters param1 = this.getParameters();
        List<String> params = param1.getRaw();
        for (int i = 0; i < params.size(); i++) {
            File file = new File(params.get(i));
            String extension = FilenameUtils.getExtension(params.get(i)).toLowerCase();
            ArrayList<Request> result = RequestBuilderFactory.getBuilder(extension).parse(file);
            requestDB.addRequests(result);
        }
        printLoadedFiles = params;
        inputDialog.setTitle("ID Selector");
        inputDialog.setHeaderText("Enter the Customer ID: ");
        inputDialog.setContentText("ID:");
    }


    public FXScene() throws SQLException {
    }



    @Override
    public void start(Stage stage) {
        stage.setTitle("Receipt Manager by Szymon Ilnicki");
        stage.setScene(startingScene);
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        mainGrid.setPadding(new Insets(25, 25, 25, 25));
        String startupMessage = new String("Welcome to Receipt Manager. The files " + printLoadedFiles + " have been loaded");
//        mainGrid.add(startupMessage, 0, 0);
        mainGrid.add(totalRequestButton, 0, 1);
        totalRequestButton.setMaxWidth(buttonWidth);
        totalRequestButton.setOnAction(event -> {
            try {
                requestDB.getReportGenerator().listOfRequests().print(new ReportScreenPrinter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        mainGrid.add(totalRequestsSingleUserButton, 0, 2);
        totalRequestsSingleUserButton.setMaxWidth(buttonWidth);
        totalRequestsSingleUserButton.setOnAction(event -> {
                Optional<String> result = inputDialog.showAndWait();
                result.ifPresent(name -> {
                    try {
                        requestDB.getReportGenerator().listOfClientRequests(Integer.parseInt(name)).print(new ReportScreenPrinter());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        });
        mainGrid.add(sumOfPricesButton, 0, 3);
        sumOfPricesButton.setMaxWidth(buttonWidth);
        sumOfPricesButton.setOnAction(event -> {
            try {
                requestDB.getReportGenerator().sumOfPrices().print(new ReportScreenPrinter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        mainGrid.add(sumOfPricesSingleUserButton, 0, 4);
        sumOfPricesSingleUserButton.setMaxWidth(buttonWidth);
        sumOfPricesSingleUserButton.setOnAction(event -> {
            try {
                requestDB.getReportGenerator().clientSumOfPrices(2).print(new ReportScreenPrinter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        mainGrid.add(listOfRequestButton, 0, 5);
        listOfRequestButton.setMaxWidth(buttonWidth);
        listOfRequestButton.setOnAction(event -> {
            try {
                requestDB.getReportGenerator().listOfRequests().print(new ReportScreenPrinter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        mainGrid.add(listOfRequestSingleUserButton, 0, 6);
        listOfRequestSingleUserButton.setMaxWidth(buttonWidth);
        listOfRequestSingleUserButton.setOnAction(event -> {
            try {
                requestDB.getReportGenerator().listOfClientRequests(1).print(new ReportScreenPrinter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        mainGrid.add(averageAllPricesButton, 0, 7);
        averageAllPricesButton.setMaxWidth(buttonWidth);
        averageAllPricesButton.setOnAction(event -> {
            try {
                requestDB.getReportGenerator().averageOfPrices().print(new ReportScreenPrinter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        mainGrid.add(getAverageAllPricesSingleUserButton, 0, 8);
        getAverageAllPricesSingleUserButton.setMaxWidth(buttonWidth);
        getAverageAllPricesSingleUserButton.setOnAction(event -> {
            try {
                requestDB.getReportGenerator().averageOfClientPrices(1).print(new ReportScreenPrinter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        mainGrid.add(totalRequestSaveAs, 1, 1);
        totalRequestSaveAs.setOnAction(event -> {
            saveAsFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = saveAsFileChooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    requestDB.getReportGenerator().listOfRequests().print(new ReportCsvPrinter(file));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        resultsTxtField.setPrefHeight(300);
        resultsTxtField.setText(startupMessage);
        mainGrid.add(resultsTxtField, 0, 9);

        stage.show();
    }

}