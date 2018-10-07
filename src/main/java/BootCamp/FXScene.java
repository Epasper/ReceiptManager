package BootCamp;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;


public class FXScene extends Application {

    int buttonWidth = 500;
    Stage startingStage;
    String loadedFiles;
    GridPane mainGrid = new GridPane();
    Scene startingScene = new Scene(mainGrid, 380, 500);
    Button totalRequestButton = new Button("Generate a Total Request Report");
    Button totalRequestsSingleUserButton = new Button("Generate a Total Request Report for a Single Customer");
    Button sumOfPricesButton = new Button("Sum of All Prices");
    Button sumOfPricesSingleUserButton = new Button("Sum of All Prices for a Single Customer");
    Button listOfRequestButton = new Button("List of All Requests");
    Button listOfRequestSingleUserButton = new Button("List of All Requests for a Single Customer");
    Button averageAllPricesButton = new Button("Average of All Priices");
    Button getAverageAllPricesSingleUserButton = new Button("Average of All Prices for Single User");
    TextField resultsTxtField = new TextField();


    public FXScene() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        RequestDB requestDB = new RequestDB();
        stage.setTitle("Receit Manager by Szymon Ilnicki");
        stage.setScene(startingScene);
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        mainGrid.setPadding(new Insets(25, 25, 25, 25));
        Text startupMessage = new Text("Welcome to Receit Manager. The files " + loadedFiles + " have been loaded");
        mainGrid.add(startupMessage, 0, 0);
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
            try {
                requestDB.getReportGenerator().listOfClientRequests(1).print(new ReportScreenPrinter());
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        resultsTxtField.setPrefHeight(300);
        mainGrid.add(resultsTxtField,0,9);

        stage.show();
    }

}
